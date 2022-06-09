package com.team16.airbnb.ui.search

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.team16.airbnb.R
import com.team16.airbnb.common.ApiState
import com.team16.airbnb.databinding.FragmentSearchBinding
import com.team16.airbnb.ui.MainActivity
import com.team16.airbnb.ui.home.HomeViewModel
import com.team16.airbnb.ui.search.detail.DetailSearchActivity
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: HomeViewModel by activityViewModels()

    private lateinit var popularAdapter: PopularAdapter
    private lateinit var searchAreaAdapter: SearchAreaAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBackButton()
        setEraseButton()
        setPopularList()

        ///
        searchAreaAdapter = SearchAreaAdapter {
            val intent = Intent(requireActivity(), DetailSearchActivity::class.java)
            intent.putExtra("address", viewModel.search)
            (requireActivity() as MainActivity).resultLauncher.launch(intent)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchAreaListStateFlow.collect {
                    when (it) {
                        is ApiState.Loading -> {
                            Log.d("AppTest", "searchAreaList/ load data started")
                        }
                        is ApiState.Error -> {
                            Log.d("AppTest", "searchAreaList/ load data Error, ${it.message}")
                        }
                        is ApiState.Success -> {
                            Log.d("AppTest", "searchAreaList/ load data Success")
                            searchAreaAdapter.submitList(it.data)
                        }
                        is ApiState.Empty -> {

                        }
                    }
                }
            }
        }

        binding.etSearch.textChangesToFlow().debounce(1000)
            .onEach {
                if (it.toString().isEmpty()) {
                    binding.rvSearchList.adapter = popularAdapter
                    viewModel.getNearList()
                } else {
                    viewModel.search = it.toString()
                    binding.rvSearchList.adapter = searchAreaAdapter
                    viewModel.getSearchAreaList(it.toString())
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setBackButton() {
        binding.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun setEraseButton() {
        binding.ivEraseText.setOnClickListener {
            Log.d("AppTest", "erase clicked")
            binding.etSearch.setText("")
        }
    }


    fun EditText.textChangesToFlow(): Flow<CharSequence?> {
        // flow 콜백 받기
        return callbackFlow<CharSequence?> {
            val listener = object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    Unit
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // 값 내보내기
                    trySend(s).isSuccess
                }

                override fun afterTextChanged(s: Editable?) {
                    Unit
                }
            }

            // 리스너 달아주기
            addTextChangedListener(listener)

            // 콜백이 사라질 때 실행됨
            awaitClose {
                removeTextChangedListener(listener)
            }

        }.onStart {
            emit(text)  // EidtText의 getText
        }
    }

    private fun setPopularList() {

        popularAdapter = PopularAdapter {
            val intent = Intent(requireActivity(), DetailSearchActivity::class.java)
            // startActivity(intent)
            (requireActivity() as MainActivity).resultLauncher.launch(intent)
        }

        binding.rvSearchList.adapter = popularAdapter

        /* viewLifecycleOwner.lifecycleScope.launch {
             viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                 viewModel.nearTripList.collect {
                     adapter.submitList(it)
                 }
             }
         }*/

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.nearListStateFlow.collect{
                    when(it){
                        is ApiState.Loading -> {
                            Log.d("AppTest", "popularlist/ load data started")
                        }
                        is ApiState.Error -> {
                            Log.d("AppTest", "popularlist/ load data Error, ${it.message}")
                        }
                        is ApiState.Success -> {
                            Log.d("AppTest", "popularlist/ load data Success")
                            popularAdapter.submitList(it.data)
                        }
                        is ApiState.Empty -> {

                        }
                    }
                }
            }
        }

        viewModel.getNearList()

    }

}
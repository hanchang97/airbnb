package com.team16.airbnb.ui.search.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.team16.airbnb.R
import com.team16.airbnb.databinding.ActivityDetailSearchBinding
import com.team16.airbnb.ui.MainActivity
import com.team16.airbnb.ui.calendar.CalendarFragment
import com.team16.airbnb.ui.money.MoneyRangeFragment
import com.team16.airbnb.ui.person.PersonFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailSearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailSearchBinding

    private val viewModel: DetailSearchViewModel by viewModels()

    private val fragmentList = listOf(CalendarFragment(), MoneyRangeFragment(), PersonFragment())

    private var currentView = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        setFragment()
        setCalendarPickDateState()
        setSkip()
        setRemove()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean { // 지정한 메뉴 레이아웃을 현재 툴바의 메뉴로 설정
        menuInflater.inflate(R.menu.detail_search_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                when (currentView == 0) {
                    true -> onBackPressed()
                    false -> {

                        currentView--

                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fcv_fragment, fragmentList[currentView]).commit()
                    }
                }

                true
            }
            R.id.menu_check -> {
                if(currentView < 3) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fcv_fragment, fragmentList[currentView]).commit()
                    currentView++
                }else {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("address", viewModel.address)
                    intent.putExtra("checkIn", viewModel.checkIn)
                    intent.putExtra("checkOut", viewModel.checkOut)
                    intent.putExtra("min", viewModel.minMoney)
                    intent.putExtra("max", viewModel.maxMoney)
                    intent.putExtra("adult", viewModel.adult)
                    intent.putExtra("child", viewModel.child)
                    intent.putExtra("infant", viewModel.infant)
                    setResult(RESULT_OK, intent)
                    finish()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun setCalendarPickDateState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pickState.collect {
                    binding.pickState = it
                }
            }
        }
    }

    private fun setSkip() {
        binding.btnSkip.setOnClickListener {
            when(currentView > 2) {
                true -> {
                    val intent = Intent(this, MainActivity::class.java)
                    //startActivity(intent)
                    setResult(RESULT_OK, intent)
                    finish()
                }

                false -> {
                    setFragment()
                }
            }

        }
    }

    private fun setFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_fragment, fragmentList[currentView]).commit()
        currentView++
    }


    private fun setRemove() {
        binding.btnRemove.setOnClickListener {
            viewModel.setCancelDate()
        }
    }


}
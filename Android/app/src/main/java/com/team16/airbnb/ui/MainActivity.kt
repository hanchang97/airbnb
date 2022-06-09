package com.team16.airbnb.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.team16.airbnb.R
import com.team16.airbnb.databinding.ActivityMainBinding
import com.team16.airbnb.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private lateinit var navController: NavController

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        supportFragmentManager.findFragmentById(R.id.nav_host_fragment)?.findNavController()?.let{
            navController = it
            binding.bottomNavigation.setupWithNavController(it)
        }

        setActivityResult()
    }

    private fun setActivityResult(){
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if (result.resultCode == Activity.RESULT_OK){
               Log.d("AppTest", "result ok")
                val checkIn = result.data?.getStringExtra("checkIn") ?: ""
                val checkOut = result.data?.getStringExtra("checkOut") ?: ""
                val min = result.data?.getIntExtra("min", 0) ?: 0
                val max = result.data?.getIntExtra("max", 0) ?: 0
                val adult = result.data?.getIntExtra("adult", 0) ?: 0
                val child = result.data?.getIntExtra("child", 0) ?: 0
                val infant = result.data?.getIntExtra("infant",0) ?: 0
                viewModel.getSearchResult(
                    checkIn,checkOut,min,max,adult,child,infant
                )

               // 검색 결과 리스트를 보여주도록 다른 fragment로 바꿔주기
                navController.navigate(R.id.searchResultFragment)
            }
            else{
                Log.d("AppTest", "result not ok")
                // search fragment 상태 그대로 있으며 됨

            }
        }
    }
}
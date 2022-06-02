package com.team16.airbnb.ui.search.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.team16.airbnb.MoneyRangeFragment
import com.team16.airbnb.PersonFragment
import com.team16.airbnb.R
import com.team16.airbnb.databinding.ActivityDetailSearchBinding
import com.team16.airbnb.ui.MainActivity
import com.team16.airbnb.ui.calendar.CalendarFragment
import com.team16.airbnb.ui.calendar.CalendarViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailSearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailSearchBinding

    private val calendarViewModel: CalendarViewModel by viewModels()

//    private lateinit var navController: NavController

    private val fragmentList = listOf(CalendarFragment(), MoneyRangeFragment(), PersonFragment())

    private var currentView = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.detail_nav_host) as NavHostFragment
//        navController = navHostFragment.navController

        setFragment()

        setCalendarPickDateState()
        setSkip()
        setRemove()
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

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setCalendarPickDateState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                calendarViewModel.pickState.collect {
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
                    //sealed class로 묶어서 homefragment 변경
                    //intent에 지역, 날짜, 인원을 그냥 보여주기 용으로 갖을것인가 값을 가질 것인가
                    //
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
            calendarViewModel.setCancelDate()
        }
    }


}
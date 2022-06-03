package com.team16.airbnb.ui.search.roomdetail

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.team16.airbnb.R
import com.team16.airbnb.databinding.ActivityRoomDetailBinding

class RoomDetailActivity: AppCompatActivity() {

    private lateinit var binding: ActivityRoomDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_room_detail)

        // 상태바 영역까지 차지하도록 하기
        // 1. 밑의 방법은 deprecated
        /*this.window?.apply {
            this.statusBarColor = Color.TRANSPARENT
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }*/

        // 2. FullScreenTheme 스타일 정의 후 매니페스트에서 해당 액티비티의 theme 에 설정하기!!

    }
}
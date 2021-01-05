package org.mydaily.ui.view.keyword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_keyword_list.*
import org.mydaily.R

class KeywordListActivity : AppCompatActivity() {
    val count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        btn_add_word.setOnClickListener{
//            val chipgroup3 = cg_add
//            val chip = Chip(this)
//            chip.text = "hi"
//            chip.setTextColor(getColor(R.color.white))
//            chip.setChipBackgroundColorResource(R.color.chip)

//            //xml처럼 style을 지정해줘야 하는데 어떻게 하는지 모르겠음.
//            //style="@style/Widget.MaterialComponents.Chip.Choice"
//            chipgroup3.addView(chip)as
//        }
    }
}
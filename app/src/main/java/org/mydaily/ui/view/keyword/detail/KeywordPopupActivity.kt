package org.mydaily.ui.view.keyword.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_keyword_popup.*
import org.mydaily.R
import org.mydaily.ui.adapter.KeywordPopupVPAdapter
import org.mydaily.ui.view.keyword.FirstFragment
import org.mydaily.ui.view.keyword.SecondFragment
import org.mydaily.ui.view.keyword.ThirdFragment

class KeywordPopupActivity : AppCompatActivity() {

    private lateinit var viewpagerAdapter : KeywordPopupVPAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keyword_popup)

        viewpagerAdapter = KeywordPopupVPAdapter(supportFragmentManager)

        viewpagerAdapter.fragments = listOf(
            FirstFragment(),
            SecondFragment(),
            ThirdFragment()
        ) as List<Fragment>
        vp_keyword_popup.adapter = viewpagerAdapter
    }
}
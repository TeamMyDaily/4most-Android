package org.mydaily.ui.view.keyword

import android.app.AlertDialog
import android.graphics.Color
import android.view.*
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import kotlinx.android.synthetic.main.activity_keyword_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityKeywordListBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.viewmodel.KeywordViewModel
import org.mydaily.util.extension.shortToast


class KeywordListActivity : BaseActivity<ActivityKeywordListBinding, KeywordViewModel>() {
    private var clickedChipCount : Int = 0
    override val layoutResourceId: Int
        get() = R.layout.activity_keyword_list
    override val viewModel: KeywordViewModel by viewModel()

    override fun initView() {
        initToolbar()
        initAddButton()
        initModifyCompleteButton()
        setModifyState()
        setCompleteState()
        floatingDialog()
    }

    private fun onClickCompleteButton() { //이런식
//        val chipDrawable = ChipDrawable.createFromAttributes( 
//            this,
//            null,
//            0,
//            R.style.Widget_MaterialComponents_Chip_Choice
//        )
//        return Chip(this).apply {
//            text = str
//            tag = chipId
//            setChipDrawable(chipDrawable)
//            setChipBackgroundColorResource(R.color.selector_chip)
//            setTextAppearance(R.style.MyDailyChipTextStyleAppearance)
//            setRippleColorResource(android.R.color.transparent)
//            setOnClickListener {
//                if(isChecked) {
//                    shortToast("$tag CHIP 클릭됨")
//                } else{
//                    shortToast("$tag Chip 클릭해제")
//                }
//            }
//        }
    }

    private fun onClickModifyButton() { //이런식
//        val chipDrawable = ChipDrawable.createFromAttributes(
//            this,
//            null,
//            0,
//            R.style.Widget_MaterialComponents_Chip_Choice
//        )
//        return Chip(this).apply {
//            text = str
//            tag = chipId
//            setChipDrawable(chipDrawable)
//            setChipBackgroundColorResource(R.color.selector_chip)
//            setTextAppearance(R.style.MyDailyChipTextStyleAppearance)
//            setRippleColorResource(android.R.color.transparent)
//            setOnClickListener {
//                if(isChecked) {
//                    shortToast("$tag CHIP 클릭됨")
//                } else{
//                    shortToast("$tag Chip 클릭해제")
//                }
//            }
//        }
    }

    private fun setCompleteState() {
        tv_complete.visibility = View.VISIBLE
        tv_modify.visibility = View.GONE
    }

    private fun floatingDialog() {
        val builder = AlertDialog.Builder(ContextThemeWrapper(this@KeywordListActivity, R.style.Theme_AppCompat_Light_Dialog))
        builder.setTitle(R.string.up_to_eight)
        builder.setMessage(R.string.too_many_keyword_selected)
        builder.setPositiveButton("확인", null).create()

        builder.show()
        //TODO -> 확인 버튼 색깔 변경이 안됨..

    }

    private fun setModifyState() {
        tv_modify.visibility = View.VISIBLE
        tv_complete.visibility = View.GONE
    }

    private fun initModifyCompleteButton() {
        tv_modify.visibility = View.GONE
        tv_complete.visibility = View.GONE
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = this
        viewModel.getLifeWord()
        viewModel.getWorkWord()
        viewModel.getMyWord()
    }

    override fun initAfterBinding() {
        observeLifeWordList()
        observeWorkWordList()
        //observeMyWordList() -> 이게 필요한가..?
    }

    private fun initToolbar() {
        setSupportActionBar(binding.tbKeywordAddActivity)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun initAddButton() {
        val chipId : String = "my_word"
        var index : Int = 0
        binding.chipAdd.setOnClickListener {
            shortToast("추가 버튼 클릭됨")
            index++
            clickedChipCount++
            //여기서 observer pattern 사용해서 추가하면 되나?

            //예시 : 키워드 추가 시 맨 앞에 chip 생성
            binding.cgMyWord.addView(createChip("추가된 샘플", chipId + index.toString()),binding.cgMyWord.childCount-1)
        }
        if(binding.cgMyWord.childCount > 0) {
            setModifyState()
        }
    }


    private fun observeLifeWordList() {
        val chipId : String = "life_word"
        var index = 0
        viewModel.lifeWordList.observe(this, { list ->
            for(str in list){
                index++
                clickedChipCount++
                binding.cgLife.addView(createChip(str, chipId + index.toString()))
            }
        })
    }

    private fun observeWorkWordList() {
        val chipId : String = "work_word"
        var index = 0
        viewModel.workWordList.observe(this, { list ->
            for(str in list){
                index++
                clickedChipCount++
                binding.cgWork.addView(createChip(str, chipId + index.toString()))
            }
        })
    }

//    private fun observeMyWordList() {
//        val chipId : String = "my_word"
//        var index : Int = 0
//        viewModel.myWordList.observe(this, { list ->
//            for(str in list){
//                index++
//                binding.cgMyWord.addView(createChip(str, chipId + index.toString()))
//            }
//        })
//    }

    private fun createChip(str: String, chipId : String): Chip {
        val chipDrawable = ChipDrawable.createFromAttributes( //TODO -> 뷰 그룹의 자식은 원래 저렇게 속성 적용 가능?
            this,
            null,
            0,
            R.style.Widget_MaterialComponents_Chip_Choice
        )
        return Chip(this).apply {
            text = str
            tag = chipId // TODO -> tag로 해도 상관 없나??
            setChipDrawable(chipDrawable)
            setChipBackgroundColorResource(R.color.selector_chip)
            setTextAppearance(R.style.MyDailyChipTextStyleAppearance)
            setRippleColorResource(android.R.color.transparent)
            setOnClickListener {
                if(isChecked) {
                    shortToast("$tag CHIP 클릭됨")
                } else{
                    shortToast("$tag Chip 클릭해제")
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_help -> {
                shortToast("도움말 버튼 클릭됨")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_keyword_list, menu)
        return true
    }

    private fun activeModifyButton() {

    }
}
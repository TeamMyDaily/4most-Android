package org.mydaily.ui.view.remind

import android.content.Intent
import android.text.Editable
import android.text.method.ScrollingMovementMethod
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentRemindWriteBinding
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.RemindViewModel

class RemindWriteFragment : BaseFragment<FragmentRemindWriteBinding, RemindViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_remind_write
    override val viewModel: RemindViewModel by sharedViewModel()
    private var start: Long = 0
    private var end: Long = 0

    override fun initView() {
        initSetting()
        startRmdindDetailWrite()
        scrollEvent()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = activity
    }

    override fun initAfterBinding() {
        observeReview()
        observeStartEnd()
    }

    private fun startRemindDetailWriteActivityWithAction(
        tv_content: TextView,
        tV_title: TextView,
        subType: Int,
        start: Long,
        end: Long
    ) {
        val intent: Intent =
            Intent(requireActivity(), RemindDetailWriteActivity::class.java).apply {
                putExtra("remind", tv_content.text.toString())
                putExtra("title", tV_title.text.toString())
                putExtra("subType", subType)
                putExtra("start", start)
                putExtra("end", end)
            }
        if (tv_content.text.isNotEmpty()) {
            intent.putExtra("isWriten", true)
        } else {
            intent.putExtra("isWriten", false)
        }
        startActivity(intent)
    }

    private fun startRmdindDetailWrite() {
        binding.tvWeekGoodContent.setOnClickListener {
            startRemindDetailWriteActivityWithAction(
                binding.tvWeekGoodContent,
                binding.tvWeekGoodTitle,
                1,
                start,
                end
            )
        }
        binding.tvWeekBadContent.setOnClickListener {
            startRemindDetailWriteActivityWithAction(
                binding.tvWeekBadContent,
                binding.tvWeekBadTitle,
                2,
                start,
                end
            )
        }
        binding.tvNextWeekContent.setOnClickListener {
            startRemindDetailWriteActivityWithAction(
                binding.tvNextWeekContent,
                binding.tvNextWeekTitle,
                3,
                start,
                end
            )
        }
    }

    private fun observeStartEnd() {
        viewModel.startEnd.observe(requireActivity(), {
            start = it[0]
            end = it[1]
        })
    }

    private fun observeReview() {
        viewModel.reviewList.observe(requireActivity(), {
            if (it.isWritten) {
                if (it.review.good != null) {
                    binding.tvWeekGoodContent.text =
                        Editable.Factory.getInstance().newEditable(it.review.good)
                    binding.tvWeekGoodContent.setBackgroundResource(R.drawable.rectangle_cherry_radius_15)
                    binding.tvGoodCount.text = it.review.good.length.toString()
                } else {
                    binding.tvWeekGoodContent.setBackgroundResource(R.drawable.remind_write_textbox)
                    binding.tvGoodCount.text = "0"
                }

                if (it.review.bad != null) {
                    binding.tvWeekBadContent.text =
                        Editable.Factory.getInstance().newEditable(it.review.bad)
                    binding.tvWeekBadContent.setBackgroundResource(R.drawable.rectangle_cherry_radius_15)
                    binding.tvBadCount.text = it.review.bad.length.toString()
                } else {
                    binding.tvWeekBadContent.setBackgroundResource(R.drawable.remind_write_textbox)
                    binding.tvBadCount.text = "0"
                }

                if (it.review.next != null) {
                    binding.tvNextWeekContent.text =
                        Editable.Factory.getInstance().newEditable(it.review.next)
                    binding.tvNextWeekContent.setBackgroundResource(R.drawable.rectangle_cherry_radius_15)
                    binding.tvNextWeekCount.text = it.review.next.length.toString()
                } else {
                    binding.tvNextWeekContent.setBackgroundResource(R.drawable.remind_write_textbox)
                    binding.tvNextWeekCount.text = "0"
                }
            } else {
                binding.tvNextWeekContent.setBackgroundResource(R.drawable.remind_write_textbox)
                binding.tvWeekGoodContent.setBackgroundResource(R.drawable.remind_write_textbox)
                binding.tvWeekBadContent.setBackgroundResource(R.drawable.remind_write_textbox)
                binding.tvNextWeekContent.text = null
                binding.tvWeekGoodContent.text = null
                binding.tvWeekBadContent.text = null
                binding.tvGoodCount.text = "0"
                binding.tvBadCount.text = "0"
                binding.tvNextWeekCount.text = "0"
            }
        })
    }

    private fun initSetting() {
        binding.tvWeekGoodContent.movementMethod = ScrollingMovementMethod()
        binding.tvWeekBadContent.movementMethod = ScrollingMovementMethod()
        binding.tvNextWeekContent.movementMethod = ScrollingMovementMethod()
    }

    private fun scrollEvent() {
        binding.tvWeekGoodContent.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                binding.svEntire.requestDisallowInterceptTouchEvent(true)
                return false
            }
        })

        binding.tvWeekBadContent.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                binding.svEntire.requestDisallowInterceptTouchEvent(true)
                return false
            }
        })

        binding.tvNextWeekContent.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                binding.svEntire.requestDisallowInterceptTouchEvent(true)
                return false
            }
        })
    }
}
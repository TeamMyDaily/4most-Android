package org.mydaily.ui.view.remind

import android.content.Intent
import android.widget.TextView
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentRemindWriteBinding
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.RemindViewModel

class RemindWriteFragment : BaseFragment<FragmentRemindWriteBinding, RemindViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_remind_write
    override val viewModel: RemindViewModel by viewModel()

    override fun initView() {
        startRmdindDetailWrite()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun gotoRemindDetailWrite() {
        binding.tvWeekGoodContent.setOnClickListener {

        }

        binding.tvWeekBadContent.setOnClickListener {

        }

        binding.tvNextWeekContent.setOnClickListener {

        }
    }

    private fun startRemindDetailWriteActivityWithAction(tv_content: TextView, tV_title: TextView) {
        val intent: Intent =
            Intent(requireActivity(), RemindDetailWriteActivity::class.java).apply {
                putExtra("remind", tv_content.text.toString())
                putExtra("title", tV_title.text.toString())
            }
        startActivity(intent)
    }

    private fun startRmdindDetailWrite() {
        binding.tvWeekGoodContent.setOnClickListener {
            startRemindDetailWriteActivityWithAction(
                binding.tvWeekGoodContent,
                binding.tvWeekGoodTitle
            )
        }
        binding.tvWeekBadContent.setOnClickListener {
            startRemindDetailWriteActivityWithAction(
                binding.tvWeekBadContent,
                binding.tvWeekBadTitle
            )
        }
        binding.tvNextWeekContent.setOnClickListener {
            startRemindDetailWriteActivityWithAction(
                binding.tvNextWeekContent,
                binding.tvNextWeekTitle
            )
        }
    }

}
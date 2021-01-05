package org.mydaily.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.mydaily.data.model.Keyword
import org.mydaily.data.model.ReportListData
import org.mydaily.ui.base.BaseViewModel

class ReportViewModel: BaseViewModel() {
    private val reportListdata = MutableLiveData<List<ReportListData>>()
    val reportList : LiveData<List<ReportListData>>
        get() = reportListdata

    fun getReportData() {
        /* 임시 데이터 */
        val tempList = listOf(
            ReportListData("아웃풋", "블로그에 1개이상 퍼블리싱 하기", 3.2, 5),
            ReportListData("열정", "열정 만수르 유노윤호의\n영상보고 감상문 5장 이상 쓰기", 2.7, 5),
            ReportListData("경청", "PM님 말씀하실 때 가위춤추지 않기", 3.4, 5),
            ReportListData("선한영향력", "거짓말 하지 말고 선하게 살기", 3.2, 5)
        )
        reportListdata.value = tempList
    }
}
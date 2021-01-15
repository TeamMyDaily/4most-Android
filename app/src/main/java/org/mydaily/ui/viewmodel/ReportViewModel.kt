package org.mydaily.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.mydaily.data.model.ReportListData
import org.mydaily.ui.base.BaseViewModel

class ReportViewModel: BaseViewModel() {
    private val reportListdata = MutableLiveData<List<ReportListData>>()
    val reportList : LiveData<List<ReportListData>>
        get() = reportListdata

    fun getReportData() {
        /* 임시 데이터 */
        val tempDailyList = listOf(
            ReportListData.Daily("2020. 12. 20", "IT 기술에 관한 아티클 정리하기", 3),
            ReportListData.Daily("2020. 12. 19", "글감 수집하기", 3),
            ReportListData.Daily("2020. 12. 18", "아티클 리뷰하기", 3),
            ReportListData.Daily("2020. 12. 18", "아티클 5개 이상 읽기", 3),
            ReportListData.Daily("2020. 12. 17", "글감 수집하기", 3),
            ReportListData.Daily("2020. 12. 16", "IT 기술에 관한 아티클 정리하기", 3)
        )
        val tempList = listOf(
            ReportListData(tempDailyList,"아웃풋", "블로그에 1개이상 퍼블리싱 하기", 3.2, 5),
            ReportListData(tempDailyList,"열정", "열정 만수르 유노윤호의\n영상보고 감상문 5장 이상 쓰기", 2.7, 5),
            ReportListData(tempDailyList,"경청", "PM님 말씀하실 때 가위춤추지 않기", 3.4, 5),
            ReportListData(tempDailyList,"선한영향력", "거짓말 하지 말고 선하게 살기", 3.2, 5)
        )
        reportListdata.value = tempList
    }
}
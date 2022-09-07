package com.huatian.eduService.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.huatian.eduService.entity.excel.SubjectData;
//监听器
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

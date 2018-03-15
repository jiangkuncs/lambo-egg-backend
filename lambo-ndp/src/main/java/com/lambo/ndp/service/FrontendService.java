package com.lambo.ndp.service;

import java.util.List;
import java.util.Map;

/**
* DemoUserService接口
* Created by lambo on 2017/11/14.
*/
public interface FrontendService {

    List getCategoryList(Map param);

    List getSubjectList(Map param);

    List getSubjectInfo(Map param);

    Map getDimensionInfo(Map param);

    Map getTableData(Map param);

    Map getDimensionData(Map param);
}
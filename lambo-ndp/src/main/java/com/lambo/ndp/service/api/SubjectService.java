package com.lambo.ndp.service.api;

import com.lambo.common.base.BaseService;

import com.lambo.ndp.model.Dict;
import com.lambo.ndp.model.Subject;
import com.lambo.ndp.model.SubjectExample;

import java.util.List;
import java.util.Map;


/**
* UpmsLogService接口
* Created by lambo on 2017/3/20.
*/
public interface SubjectService extends BaseService<Subject, SubjectExample> {
    public List<Map<String,Object>> querySubject(Map<String, Object> param);
    public Map<String,Object> getSubject(int subjectId);
    public List<Map<String,Object>> querySubjectColumn(int subjectId);
    Object insertSubject(String categoryId,String tableCode,String tableId,String subjectDesc,String subjectName,String subjectColumns);
}
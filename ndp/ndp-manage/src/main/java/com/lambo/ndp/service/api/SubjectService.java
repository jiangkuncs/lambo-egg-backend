package com.lambo.ndp.service.api;

import com.lambo.common.base.BaseService;

import com.lambo.ndp.model.Subject;

import com.lambo.ndp.model.SubjectExample;

import java.util.List;
import java.util.Map;


/**
 * SubjectService接口
 * Created by zxc on 2018/3/15.
*/
public interface SubjectService extends BaseService<Subject, SubjectExample> {
    public List<Map<String,Object>> querySubject(Map<String, Object> param);
    public Map<String,Object> getSubject(int subjectId);
    public List<Map<String,Object>> querySubjectColumn(int subjectId);
    public Object insertSubject(int categoryId,String tableCode,int tableId,String subjectDesc,String subjectName,String subjectColumns);
    public Object updateSubject(int subjectId,int categoryId,String tableCode,int tableId,String subjectDesc,String subjectName,String subjectColumns);
    public int deleteSubjectBySubjectId(Integer subjectId);
    public Object get(int subjectId);
}
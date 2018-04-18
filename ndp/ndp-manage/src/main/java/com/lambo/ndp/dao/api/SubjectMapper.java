package com.lambo.ndp.dao.api;

import com.lambo.ndp.model.Subject;
import java.util.List;
import java.util.Map;

public interface SubjectMapper {
    int deleteByPrimaryKey(Integer subjectId);

    int insert(Subject record);

    int insertSelective(Subject record);

    Subject selectByPrimaryKey(Integer subjectId);

    int updateByPrimaryKeySelective(Subject record);
    int updateByPrimaryKey(Subject record);
    int updateSubject(Subject subject);
    List<Map<String,Object>> querySubject(Map<String, Object> param);
    public Map<String,Object> getSubject(int subjectId);
    public List<Map<String,Object>> querySubjectColumn(int subjectId);
    int insertSubject(Subject subject);
    int insertSubjectColumn(Map<String, Object> param);
    int deleteSubjectColumnBySubjectId(Integer subjectId);
    int deleteSubjectBySubjectId(Integer subjectId);
}
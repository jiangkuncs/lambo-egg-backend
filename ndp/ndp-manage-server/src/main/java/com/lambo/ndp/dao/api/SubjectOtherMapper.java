package com.lambo.ndp.dao.api;

import com.lambo.ndp.model.Subject;
import com.lambo.ndp.model.SubjectExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SubjectOtherMapper {
    int insertSubjectColumn(Map<String, Object> param);
    int insertSubjectDoc(Map<String, Object> param);
    int deleteSubjectColumnBySubjectId(Integer subjectId);
    int deleteSubjectDocBySubjectId(Integer subjectId);
    int deleteTagBySubjectId(Integer subjectId);
    public List<Map<String,Object>> getDictData(String data);
    public List<Map<String,Object>> querySubjectColumn(int subjectId);
    public List<Map<String,Object>> querySubjectDoc(int subjectId);
    public Map<String,Object> getSubject(int subjectId);
    public int insertTag(Map<String, Object> parm);
    public List<Map<String,Object>> getTagData(int subjectId);
    List<Map<String,Object>> querySubject(Map<String, Object> param);
}
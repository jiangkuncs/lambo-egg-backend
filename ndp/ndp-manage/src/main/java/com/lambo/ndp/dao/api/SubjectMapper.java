package com.lambo.ndp.dao.api;

import com.lambo.ndp.model.Subject;
import com.lambo.ndp.model.SubjectExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SubjectMapper {
    int deleteByExample(SubjectExample example);

    int deleteByPrimaryKey(Integer subjectId);

    int insert(Subject record);

    int insertSelective(Subject record);

    List<Subject> selectByExample(SubjectExample example);

    Subject selectByPrimaryKey(Integer subjectId);

    int updateByExampleSelective(@Param("record") Subject record, @Param("example") SubjectExample example);

    int updateByExample(@Param("record") Subject record, @Param("example") SubjectExample example);

    int updateByPrimaryKeySelective(Subject record);

    int updateByPrimaryKey(Subject record);

    int insertSubjectColumn(Map<String, Object> param);
    int insertSubjectDoc(Map<String, Object> param);
    int deleteSubjectColumnBySubjectId(Integer subjectId);
    int deleteSubjectDocBySubjectId(Integer subjectId);
    public List<Map<String,Object>> getDictData(String data);
    public List<Map<String,Object>> querySubjectColumn(int subjectId);
    public List<Map<String,Object>> querySubjectDoc(int subjectId);
    public Map<String,Object> getSubject(int subjectId);
    public int insertTag(Map<String,Object> parm);
    public List<Map<String,Object>> getTagData(int subjectId);
}
package com.lambo.ndp.dao.api;

import com.lambo.ndp.model.SubjectRecord;
import com.lambo.ndp.model.SubjectRecordExample;
import java.util.List;

public interface SubjectRecordMapper {
    int deleteByPrimaryKey(Integer recordId);

    int insert(SubjectRecord record);

    int insertSelective(SubjectRecord record);

    List<SubjectRecord> selectByExample(SubjectRecordExample example);

    SubjectRecord selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(SubjectRecord record);

    int updateByPrimaryKey(SubjectRecord record);
}
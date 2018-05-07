package com.lambo.ndp.dao.api;

import com.lambo.ndp.model.Dict;
import com.lambo.ndp.model.DictExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DictOtherMapper {

    public List<Map<String,Object>> getDict(Map parm);

}
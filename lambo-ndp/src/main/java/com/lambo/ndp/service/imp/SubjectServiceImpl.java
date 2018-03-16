package com.lambo.ndp.service.imp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.ndp.dao.api.DictMapper;
import com.lambo.ndp.dao.api.SubjectMapper;
import com.lambo.ndp.model.Dict;
import com.lambo.ndp.model.DictExample;
import com.lambo.ndp.model.Subject;
import com.lambo.ndp.model.SubjectExample;
import com.lambo.ndp.service.api.DictService;
import com.lambo.ndp.service.api.SubjectService;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* UpmsLogService实现
* Created by lambo on 2017/3/20.
*/
@Service
@Transactional
@BaseService
public class SubjectServiceImpl extends BaseServiceImpl<SubjectMapper, Subject, SubjectExample> implements SubjectService {

    private static Logger logger = LoggerFactory.getLogger(SubjectServiceImpl.class);

    @Autowired
    SubjectMapper subjectMapper;

    public List<Map<String, Object>> querySubject(Map<String, Object> param) {
        return subjectMapper.querySubject(param);
    }

    public Map<String, Object> getSubject(int subjectId) {
        return subjectMapper.getSubject(subjectId);
    }

    public List<Map<String, Object>> querySubjectColumn(int subjectId) {
        return subjectMapper.querySubjectColumn(subjectId);
    }

    public Object insertSubject(String categoryId, String tableCode, String tableId, String subjectDesc, String subjectName, String subjectColumns) {

        System.out.println("categoryId=" + categoryId + "tableCode=" + tableCode + ",tableId=" + tableId + "subjectDesc=" + subjectDesc + ",subjectName=" + subjectName);
        System.out.println("subjectColumns=" + subjectColumns);
        Subject subject = new Subject();
        subject.setCategoryId(Integer.valueOf(categoryId));
        subject.setSubjectDesc(subjectDesc);
        subject.setTableId(Integer.valueOf(tableId));
        subject.setTableCode(tableCode);
        subject.setSubjectName(subjectName);
        int con = subjectMapper.insertSubject(subject);
        int subjectId = subject.getSubjectId();
        //列插入
        JSONArray json = JSONArray.parseArray(subjectColumns);
        if (json.size() > 0) {
            for (int i = 0; i < json.size(); i++) {
                JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                System.out.println("job:" + job);  // 得到 每个对象中的属性值
                Map<String,Object> parm = new HashMap();
//                if (job.get("cellName") == null) {
//                    parm.put("columnName",null);
//                }else{
//                    parm.put("columnName",job.get("cellName"));
//                }
                parm.put("columnName",job.get("cellName"));
                parm.put("cellId",job.get("cellId"));
                parm.put("searchCondition",job.get("searchCondition"));
                parm.put("searchSetting",job.get("searchSetting"));
                parm.put("columnOrder",job.get("columnOrder"));
                parm.put("isShow","1");
                parm.put("subjectId",subjectId);
                System.out.println("parm:"+parm);
                int conColumn=subjectMapper.insertSubjectColumn(parm);
                System.out.println("conColumn:"+conColumn);


            }
        }
            return subjectId;

    }
}
package com.lambo.ndp.service.imp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.ndp.constant.NdpResult;
import com.lambo.ndp.constant.NdpResultConstant;
import com.lambo.ndp.dao.api.SubjectMapper;
import com.lambo.ndp.model.Subject;
import com.lambo.ndp.model.SubjectExample;
import com.lambo.ndp.service.api.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SubjectService实现
 * Created by zxc on 2018/2/28.
*/
@Service
@Transactional
public class SubjectServiceImpl extends BaseServiceImpl<SubjectMapper,Subject, SubjectExample> implements SubjectService {

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

    public Object insertSubject(int categoryId, String tableCode, int tableId, String subjectDesc, String subjectName, String subjectColumns) {

        //System.out.println("categoryId=" + categoryId + "tableCode=" + tableCode + ",tableId=" + tableId + "subjectDesc=" + subjectDesc + ",subjectName=" + subjectName);
        //System.out.println("subjectColumns=" + subjectColumns);
        Subject subject = new Subject();
        subject.setCategoryId(categoryId);
        subject.setSubjectDesc(subjectDesc);
        subject.setTableId(tableId);
        subject.setTableCode(tableCode);
        subject.setSubjectName(subjectName);
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        subject.setCreateTime(df.format(day).toString());
        int con = subjectMapper.insertSubject(subject);
        int subjectId = subject.getSubjectId();
        //列插入
        JSONArray json = JSONArray.parseArray(subjectColumns);
        if (json.size() > 0) {
            for (int i = 0; i < json.size(); i++) {
                JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                System.out.println("job:" + job);  // 得到 每个对象中的属性值
                Map<String,Object> parm = new HashMap();
                parm.put("columnName",job.get("cellName"));
                parm.put("cellId",job.get("cellId"));
                parm.put("searchCondition",job.get("searchCondition"));
                parm.put("searchSetting",job.get("searchSetting"));
                parm.put("columnOrder",job.get("columnOrder"));
                if (job.get("isShow") == null) {
                    parm.put("isShow","0");
                }else{
                    parm.put("isShow",job.get("isShow"));
                }
                //parm.put("isShow","1");
                parm.put("subjectId",subjectId);
                //System.out.println("parm:"+parm);
                int conColumn=subjectMapper.insertSubjectColumn(parm);
                //System.out.println("conColumn:"+conColumn);

            }
        }
            return subjectId;

    }
    public Object updateSubject(int subjectId,int categoryId, String tableCode, int tableId, String subjectDesc, String subjectName, String subjectColumns) {

        System.out.println("categoryId=" + categoryId + "tableCode=" + tableCode + ",tableId=" + tableId + "subjectDesc=" + subjectDesc + ",subjectName=" + subjectName);
        System.out.println("subjectColumns=" + subjectColumns);
        Subject subject = new Subject();
        subject.setCategoryId(categoryId);
        subject.setSubjectDesc(subjectDesc);
        subject.setTableId(tableId);
        subject.setTableCode(tableCode);
        subject.setSubjectName(subjectName);
        subject.setSubjectId(subjectId);
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        subject.setCreateTime(df.format(day).toString());
         subjectMapper.updateSubject(subject);
         subjectMapper.deleteSubjectColumnBySubjectId(subjectId);
        //列插入
        int con=0;
        JSONArray json = JSONArray.parseArray(subjectColumns);
        if (json.size() > 0) {
            for (int i = 0; i < json.size(); i++) {
                JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                //System.out.println("job:" + job);  // 得到 每个对象中的属性值
                Map<String,Object> parm = new HashMap();
                parm.put("columnName",job.get("cellName"));
                parm.put("cellId",job.get("cellId"));
                parm.put("searchCondition",job.get("searchCondition"));
                parm.put("searchSetting",job.get("searchSetting"));
                parm.put("columnOrder",job.get("columnOrder"));
                if (job.get("isShow") == null) {
                    parm.put("isShow","0");
                }else{
                    parm.put("isShow",job.get("isShow"));
                }
                //parm.put("isShow","1");
                parm.put("subjectId",subjectId);
                //System.out.println("parm:"+parm);
                con=subjectMapper.insertSubjectColumn(parm);
                //System.out.println("conColumn:"+conColumn);

            }
        }
        return con;

    }
    public int deleteSubjectBySubjectId(Integer subjectId){
        int con=1;
        //删除专题列
        con=subjectMapper.deleteSubjectColumnBySubjectId(subjectId);
        //删除专题
        con=subjectMapper.deleteSubjectBySubjectId(subjectId);
        return con;
    }
    public Object get(int subjectId){
        Map<String,Object> param = subjectMapper.getSubject(subjectId);
        List<Map<String,Object>> data = subjectMapper.querySubjectColumn(subjectId);
        data.add(0,param);
        return new NdpResult(NdpResultConstant.SUCCESS, data);
    }
}
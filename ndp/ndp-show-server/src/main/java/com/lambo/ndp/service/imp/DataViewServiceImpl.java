package com.lambo.ndp.service.imp;


import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.ndp.constant.DictConstants;
import com.lambo.ndp.dao.api.DataViewMapper;
import com.lambo.ndp.model.Dict;
import com.lambo.ndp.model.DictExample;
import com.lambo.ndp.service.api.DataViewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
* DictService实现
* Created by zxc on 2018/3/10.
*/
@Service
@Transactional
@BaseService
public class DataViewServiceImpl extends BaseServiceImpl<DataViewMapper,Dict, DictExample> implements DataViewService {

    private static Logger logger = LoggerFactory.getLogger( DataViewServiceImpl.class);

    @Autowired
    private DataViewMapper dataViewMapper;

    @Override
    public List<Map<String, String>> getDictListByDictId(String dictId) {
        if(logger.isDebugEnabled()){
            logger.debug("根据dict_id查询枚举值");
        }
        List<Map<String,String>> resultlist = dataViewMapper.getDictListByDictId(dictId);
        return resultlist;
    }

    @Override
    public Map<String, List<Map<String, String>>> getConditionMap() {
        //获取分类
        List<Map<String,String>> categoryTypeList = dataViewMapper.getCatogryNameByCategoryId();

        //获取时间粒度
        List<Map<String,String>> timeTypeList = dataViewMapper.getDictListByDictId(DictConstants.TIME_DICT_ID);

        //获取组织粒度
        List<Map<String,String>> organTypeList = dataViewMapper.getDictListByDictId(DictConstants.ORGAN_DICT_ID);

        //获取指标
        List<Map<String,String>> indexTypeList = dataViewMapper.getDictListByDictId(DictConstants.INDEX_DICT_ID);

        Map<String, List<Map<String, String>>> resultMap = new HashMap<>();
        resultMap.put("categoryTypeList",categoryTypeList);
        resultMap.put("timeTypeList",timeTypeList);
        resultMap.put("organTypeList",organTypeList);
        resultMap.put("indexTypeList",indexTypeList);
        return resultMap;
    }

    @Override
    public List<Map<String, String>> getSearchResult(Map condition) {
        List<String> tagList = new ArrayList();
        StringBuffer str = new StringBuffer();
        str.append( "SELECT" +
                "    SUB.SUBJECT_ID," +
                "    SUB.CATEGORY_ID," +
                "    SUB.SUBJECT_NAME," +
                "    SUB.SUBJECT_DESC," +
                "    SUB.TABLE_ID," +
                "    SUB.TABLE_CODE," +
                "    SUB.CREATE_TIME," +
                "    SUB.PERIOD_TYPE," +
                "    SUB.ORGAN_TYPE," +
                "    SUB.SUBJECT_TYPE," +
                "    SUB.RATE_COUNT," +
                "    SUB.VISIT_COUNT," +
                "    ORG.DICT_VALUE AS organTypeName," +
                "    PER.DICT_VALUE AS periodTypeName," +
                "    CAT.CATEGORY_NAME AS catograyName " +
                "FROM" +
                "    NDP_SUBJECT SUB," +
                "    NDP_DICT ORG," +
                "    NDP_DICT PER," +
                "    ndp_category CAT " +
                "WHERE" +
                "    SUB.ORGAN_TYPE=ORG.DICT_KEY" +
                " AND ORG.DICT_ID='ORGAN_TYPE_ID'" +
                " AND SUB.PERIOD_TYPE=PER.DICT_KEY" +
                " AND PER.DICT_ID='PERIOD_TYPE_ID'" +
                " AND SUB.CATEGORY_ID=CAT.CATEGORY_ID ");
        if(!condition.isEmpty()){
            if(condition.get("searchText")!=null&&!condition.get("searchText").equals("")){
                str.append(" AND (SUB.SUBJECT_NAME like '%"+condition.get("searchText")+"%' or SUB.SUBJECT_DESC like '%"+condition.get("searchText")+"%')  ");
            }
            if(condition.get("catograyId")!=null&&!condition.get("catograyId").equals("")){
                str.append(" AND SUB.CATEGORY_ID ='"+condition.get("catograyId")+"' ");
            }
            //生成标签查询SQL
            if(condition.get("activeTags")!=null){

                tagList = (List) condition.get("activeTags");
                if(!tagList.isEmpty()){
                    str.append( " AND SUB.SUBJECT_ID IN (SELECT SUBJECT_ID FROM (SELECT SUBJECT_ID,GROUP_CONCAT(TAG_KEY)  AS TAGS " +
                            " FROM NDP_SUBJECT_TAG  " +
                            " GROUP BY SUBJECT_ID ) WHERE");
                    for(String dictMap : tagList){
                        str.append(" TAGS LIKE'%"+dictMap+"%' ");
                    }
                    str.append(")");

                }else{
                    str.append(" LEFT JOIN (SELECT SUBJECT_ID FROM (SELECT SUBJECT_ID,GROUP_CONCAT(TAG_KEY)  AS TAGS FROM NDP_SUBJECT_TAG  GROUP BY SUBJECT_ID ) K ON NDP_SUBJECT.SUBJECT_ID=K.SUBJECT_ID");

                }
            }
            if(condition.get("organTypeId")!=null&&!condition.get("organTypeId").equals("")){

                str.append(" AND ORGAN_TYPE='" +condition.get("organTypeId")+"' ");
            }
            if(condition.get("activeStars")!=null&&!condition.get("activeStars").equals("")){

                str.append(" AND RATE_COUNT >=" +condition.get("activeStars"));
            }
            if(condition.get("periodTypeId")!=null&&!condition.get("periodTypeId").equals("")){

                str.append(" AND PERIOD_TYPE ='" +condition.get("periodTypeId")+"' ");
            }
            if(condition.get("activeOrder")!=null&&!condition.get("activeOrder").equals("")){

                String oderStr;
                int i = Integer.valueOf(condition.get("activeOrder").toString());
                switch(i){
                    case 1://创建时间
                        str.append(" ORDER BY SUB.CREATE_TIME" );
                    break;
                    case 2://数据量
                        //str.append(" ORDER BY SUB.CREATE_TIME" );
                        break;
                    case 3://访问量
                        str.append(" ORDER BY SUB.VISIT_COUNT" );
                        break;
                    case 4://评分
                        str.append(" ORDER BY SUB.RATE_COUNT" );
                        break;
                }



            }
            //计算分页
            int curPage = Integer.valueOf(condition.get("pageNum").toString()) ;
            int pageSize = Integer.valueOf(condition.get("pageSize").toString());
            int startRow = (curPage - 1) * pageSize;

           // str.append(" LIMIT "+startRow+","+ pageSize);

        }
        if(logger.isDebugEnabled()){
            logger.debug(str.toString());
        }
        List<Map<String, String>> resultList = dataViewMapper.getSearchResult(str.toString());

        return resultList;
    }

    @Override
    public int updateRateCountBySubjectId(Map condition) {
        return dataViewMapper.updateRateCountBySubjectId(condition);
    }

    @Override
    public int updateVisitCountBySubjectId(int condition) {
        return dataViewMapper.updateVisitCountBySubjectId(condition);
    }

}
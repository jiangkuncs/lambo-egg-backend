package com.lambo.ndp.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lambo.common.annotation.EnableExportTable;
import com.lambo.common.annotation.LogAround;
import com.lambo.ndp.service.DemoUserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户controller
 * Created by wangjie on 2017/11/14.
 */
@Controller()
@RequestMapping("/manage")
public class userOldController {
    private static Logger logger = LoggerFactory.getLogger(userOldController.class);

    @Autowired
    private DemoUserService demoUserService;
    /**
     * 用户列表页面
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String query(){
        return "/userold/query.jsp";
    }
    /**
     * 用户列表
     * @return
     */
    @ApiOperation(value = "用户列表")
    @RequestMapping(value = "/queryList")
    @ResponseBody
    @LogAround(value="测试测试")
    @EnableExportTable
    public Object queryList(
            @RequestParam(required = false, defaultValue = "", value = "id") String id,
            @RequestParam(required = false, defaultValue = "", value = "name") String name,
            @RequestParam(required = false, defaultValue = "", value = "age") String age,
            @RequestParam(required = false, defaultValue = "", value = "sex") String sex)  {

        Map<String,Object> param = new HashMap<String, Object>();
        if(!id.isEmpty()){
            param.put("id",id);
        }
        if(!name.isEmpty()){
            param.put("name",name);
        }
        if(!age.isEmpty()){
            String[] ages = age.split("-");
            param.put("startAge",ages[0]);
            param.put("endAge",ages[1]);
        }
        if(!sex.isEmpty()){
            param.put("sex",sex);
        }

        PageHelper.startPage(1, 10);
        List<Map<String,Object>> list = demoUserService.queryUser(param);
        PageInfo page = new PageInfo(list);

        return page;
    }
    /**
     * 用户明细页面
     * @return
     */
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(
            @PathVariable("id") int id,
            @RequestParam(required = true, defaultValue = "0", value = "type")
                    String type,
            ModelMap modelMap){
        Map<String,Object> demoUser = null;
        if(!"0".equals(type)){
            if(id > 0){
                demoUser = demoUserService.selectUserById(id);
            }
        }
        modelMap.put("demoUser",demoUser);
        modelMap.put("type",type);
        return "/userold/detail.jsp";
    }
    /**
     * 用户明细
     * @return
     */
    @ApiOperation(value = "用户明细")
    @RequestMapping(value = "/detailInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> detailInfo(
            @RequestParam(required = true, defaultValue = "0", value = "id") int id
    ){
        Map<String,Object> result = null;
        if(id > 0){
            demoUserService.selectUserById(id);
        }
        return result;
    }

    /**
     * 添加用户
     * @return
     */
    @ApiOperation(value = "添加用户")
    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    @ResponseBody
    public int insertUser(
            @RequestParam(required = true, defaultValue = "", value = "name") String name,
            @RequestParam(required = false, defaultValue = "0", value = "age") int age,
            @RequestParam(required = false, defaultValue = "0", value = "sex") String sex
    ){
        int count = 0;
        if(!name.isEmpty()){
            Map<String,Object> param = new HashMap<String, Object>();
            param.put("name",name);
            param.put("age",age);
            param.put("sex",sex);
            count = demoUserService.insertUser(param);
        }
        return count;
    }
    /**
     * 更新用户
     * @return
     */
    @ApiOperation(value = "更新用户")
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ResponseBody
    public int updateUser(
            @RequestParam(required = true, defaultValue = "0", value = "id") int id,
            @RequestParam(required = false, defaultValue = "", value = "name") String name,
            @RequestParam(required = false, defaultValue = "0", value = "age") int age,
            @RequestParam(required = false, defaultValue = "0", value = "sex") String sex
    ){
        int count = 0;
        if(id > 0){
            Map<String,Object> param = new HashMap<String, Object>();
            param.put("id",id);
            if(!name.isEmpty()){
                param.put("name",name);
            }
            if(age > 0){
                param.put("age",age);
            }
            if(!sex.isEmpty()){
                param.put("sex",sex);
            }
            count = demoUserService.updateUser(param);
        }
        return count;
    }
    /**
     * 删除用户
     * @return
     */
    @ApiOperation(value = "删除用户")
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public int deleteUser(
        @RequestParam(required = true, defaultValue = "", value = "id") String id
    ){
        int count = 0;
        if(!id.isEmpty()){
            count = demoUserService.deleteUser(id);
        }
        return count;
    }
}

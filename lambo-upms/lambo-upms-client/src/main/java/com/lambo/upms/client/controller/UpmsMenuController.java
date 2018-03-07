package com.lambo.upms.client.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lambo.common.base.BaseController;
import com.lambo.common.util.PropertiesFileUtil;
import com.lambo.upms.client.dao.model.UpmsPermission;
import com.lambo.upms.client.dao.model.UpmsSystem;
import com.lambo.upms.client.dao.model.UpmsSystemExample;
import com.lambo.upms.client.dao.model.UpmsUser;
import com.lambo.upms.client.service.api.UpmsClientApiService;
import com.lambo.upms.common.constant.UpmsResult;
import com.lambo.upms.common.constant.UpmsResultConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.List;

/**
 * 其他接口
 */
@Controller
@RequestMapping("/manage/menu")
@Api(value = "其他接口", description = "其他接口")
public class UpmsMenuController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(UpmsAuthController.class);

    private static String SYSTEM_ID = PropertiesFileUtil.getInstance("config").get("upms.system.id");

    @Autowired
    private UpmsClientApiService upmsClientApiService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取当前登录用户有权限的菜单")
    public Object getMenu(){

        // 已注册系统
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        upmsSystemExample.createCriteria().andSystemIdEqualTo(Integer.parseInt(SYSTEM_ID));
        List<UpmsSystem> upmsSystems = upmsClientApiService.selectUpmsSystemByExample(upmsSystemExample);

        // 当前登录用户权限
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        UpmsUser upmsUser = upmsClientApiService.selectUpmsUserByUsername(username);
        List<UpmsPermission> upmsPermissions = upmsClientApiService.selectUpmsPermissionByUpmsUserId(upmsUser.getUserId());

        JSONArray jsonArr = new JSONArray();
        for(UpmsSystem upmsSystem : upmsSystems) {
            JSONObject rootNode = new JSONObject();
            rootNode.put("id", upmsSystem.getSystemId());
            rootNode.put("name", upmsSystem.getName());
            rootNode.put("path", "");
            jsonArr.add(menuTreeFactory(rootNode,upmsPermissions, true));
        }

        return new UpmsResult(UpmsResultConstant.SUCCESS, jsonArr);
    }


    /**
     * 生成菜单树
     * @return
     */
    private Object menuTreeFactory(JSONObject treeNode, List<UpmsPermission> permissionList, boolean root){
        int id = treeNode.getIntValue("id");
        JSONArray childrenNodes = new JSONArray();
        Iterator<UpmsPermission> it = permissionList.iterator();
        while(it.hasNext()){
            UpmsPermission upmsPermission = it.next();
            if((root &&  upmsPermission.getPid() == 0) || (!root && upmsPermission.getPid() == id && upmsPermission.getType() != 3)){
                JSONObject node = new JSONObject();
                node.put("id",upmsPermission.getPermissionId());
                node.put("name",upmsPermission.getName());
                node.put("permissionValue",upmsPermission.getPermissionValue());
                node.put("title",upmsPermission.getName());
                node.put("icon",upmsPermission.getIcon());
                node.put("order",upmsPermission.getOrders());
                node.put("path",treeNode.getString("path") + "#" +upmsPermission.getName());
                node.put("pid",upmsPermission.getPid());
                childrenNodes.add(node);
                menuTreeFactory(node,permissionList, false);
            }
        }
        if(childrenNodes.size() != 0){
            treeNode.put("children",childrenNodes);
        }

        return treeNode;
    }

}

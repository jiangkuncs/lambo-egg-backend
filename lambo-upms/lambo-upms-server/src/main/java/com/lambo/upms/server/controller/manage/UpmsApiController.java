package com.lambo.upms.server.controller.manage;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lambo.common.base.BaseController;
import com.lambo.upms.common.constant.UpmsResult;
import com.lambo.upms.common.constant.UpmsResultConstant;
import com.lambo.upms.server.dao.model.*;
import com.lambo.upms.server.service.api.UpmsApiService;
import com.lambo.upms.server.service.api.UpmsSystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Iterator;
import java.util.List;

/**
 * 其他接口
 */
@Controller
@RequestMapping("/api")
@Api(value = "其他接口", description = "其他接口")
public class UpmsApiController extends BaseController {

    @Autowired
    private UpmsSystemService upmsSystemService;

    @Autowired
    private UpmsApiService upmsApiService;

    @RequestMapping(value = "/menu/getList",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取当前登录用户有权限的菜单")
    public Object getMenu(){
        // 已注册系统
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        upmsSystemExample.createCriteria().andStatusEqualTo((byte) 1);
        List<UpmsSystem> upmsSystems = upmsSystemService.selectByExample(upmsSystemExample);

        // 当前登录用户权限
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        UpmsUser upmsUser = upmsApiService.selectUpmsUserByUsername(username);
        List<UpmsPermission> upmsPermissions = upmsApiService.selectUpmsPermissionByUpmsUserId(upmsUser.getUserId());

        JSONArray jsonArr = new JSONArray();
        for(UpmsSystem upmsSystem : upmsSystems) {
            JSONObject rootNode = new JSONObject();
            rootNode.put("id", upmsSystem.getSystemId());
            rootNode.put("name", upmsSystem.getName());
            rootNode.put("title", upmsSystem.getTitle());
            rootNode.put("description", upmsSystem.getDescription());
            rootNode.put("icon", upmsSystem.getIcon());
            rootNode.put("order", upmsSystem.getOrders());
            rootNode.put("basePath", upmsSystem.getBasepath());
            rootNode.put("banner", upmsSystem.getBanner());
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
                node.put("path",upmsPermission.getUri());
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

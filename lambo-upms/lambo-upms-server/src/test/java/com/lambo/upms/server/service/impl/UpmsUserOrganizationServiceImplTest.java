package com.lambo.upms.server.service.impl;

import com.lambo.common.base.BaseJunit4Test;
import com.lambo.upms.server.dao.model.UpmsUserOrganization;
import com.lambo.upms.server.dao.model.UpmsUserOrganizationExample;
import com.lambo.upms.server.service.api.UpmsUserOrganizationService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class UpmsUserOrganizationServiceImplTest extends BaseJunit4Test {

    @Autowired
    UpmsUserOrganizationService upmsUserOrganizationService;

    @Test
    public void organization() throws Exception {
        String[] organizationIds = {"1","2"};
        upmsUserOrganizationService.organization(organizationIds,1);

        //验证
        UpmsUserOrganizationExample upmsUserOrganizationExample = new UpmsUserOrganizationExample();
        upmsUserOrganizationExample.createCriteria().andUserIdEqualTo(1);
        List<UpmsUserOrganization> list = upmsUserOrganizationService.selectByExample(upmsUserOrganizationExample);
        Assert.assertTrue(list.size() == 2);

    }

}
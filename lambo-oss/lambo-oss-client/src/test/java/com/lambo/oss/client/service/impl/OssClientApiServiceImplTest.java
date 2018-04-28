package com.lambo.oss.client.service.impl;

import com.lambo.common.base.BaseJunit4Test;
import com.lambo.common.utils.io.FileUtils;
import com.lambo.common.utils.io.ResourceUtil;
import com.lambo.oss.client.constant.OssConstant;
import com.lambo.oss.client.service.api.OssClientApiService;
import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

public class OssClientApiServiceImplTest extends BaseJunit4Test {

    @Autowired
    OssClientApiService ossClientApiService;

    @Test
    public void saveFile() throws Exception {

        String filePath = ResourceUtil.getResource("test.jpg").getURI().toString().substring(6);

        File file = new File(filePath);
        MultipartFile mulFile = new MockMultipartFile(
                "test.jpg", //文件名
                "test.jpg", //originalName 相当于上传文件在客户机上的文件名
                ContentType.APPLICATION_OCTET_STREAM.toString(), //文件类型
                new FileInputStream(file) //文件流
        );

        Map map = ossClientApiService.saveFile(mulFile);
        Assert.assertEquals(map.get("originalName"),"test.jpg");
        Assert.assertEquals(map.get("size"),new Long(2809));
        Assert.assertEquals(map.get("md5"),"1a424a5cd7cba080351b68fd727567be");

        // 清理
        String testFile = FileUtils.path(FileUtils.getWebappPath() + OssConstant.UPLOAD_TEMP_PATH + File.separator + map.get("name"));
        FileUtils.deleteFile(testFile);

    }

    @Test
    public void getFileSuffix() throws Exception {
    }

}
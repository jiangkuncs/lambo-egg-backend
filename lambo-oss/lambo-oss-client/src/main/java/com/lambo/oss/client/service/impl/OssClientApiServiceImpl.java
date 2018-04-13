package com.lambo.oss.client.service.impl;

import com.lambo.common.util.FileUtil;
import com.lambo.oss.client.constant.OssConstant;
import com.lambo.oss.client.controller.OssClientApiController;
import com.lambo.oss.client.service.api.OssClientApiService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author sunzhen
 */
@Service
public class OssClientApiServiceImpl implements OssClientApiService {

    private final static Logger logger = LoggerFactory.getLogger(OssClientApiController.class);

    /**
     * 保存MultipartFile到目标路径
     * @param file
     * @return
     */
    @Override
    public Map saveFile(MultipartFile file){
        if (!file.isEmpty()) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            String originalFilename = file.getOriginalFilename();
            String fileExtension = FileUtil.getFileExtension(originalFilename);
            String newName = UUID.randomUUID().toString().replace("-", "");
            String fileMd5 = "";
            try{
                fileMd5 = DigestUtils.md5Hex(file.getInputStream());
            }catch(Exception e){
                //
            }

            // 文件保存路径
            String filePath = FileUtil.path(FileUtil.getWebappPath() + OssConstant.UPLOAD_TEMP_PATH);
            FileUtil.createDirectory(filePath);

            try {
                // 转存文件
                file.transferTo(new File(filePath + File.separator + newName + "." + fileExtension));
            } catch (Exception e) {
                e.printStackTrace();
            }
            Map<String,Object> resultMap = new HashMap(5);
            resultMap.put("originalName",originalFilename);
            resultMap.put("size",file.getSize());
            resultMap.put("name",newName + "." + fileExtension);
            resultMap.put("md5",fileMd5);
            return resultMap;

        }

        return null;
    }


    /**
     * 获取文件后缀
     * @param originalFilename
     * @return
     */
    public  String getFileSuffix(String originalFilename){
        if(originalFilename == null){
            return "";
        }
        String[] arr = originalFilename.split("\\.");
        if(arr.length == 1){
            return "";
        }
        return arr[arr.length -1];
    }

}

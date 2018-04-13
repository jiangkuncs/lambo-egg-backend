package com.lambo.oss.client.service.api;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface OssClientApiService {

    /**
     * 保存MultipartFile到目标路径
     * @param file
     * @return
     */
    public Map<String,Object> saveFile(MultipartFile file);
}

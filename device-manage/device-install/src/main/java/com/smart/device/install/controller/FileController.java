package com.smart.device.install.controller;

import com.github.tobato.fastdfs.domain.conn.FdfsWebServer;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.utils.upload.FastDfsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author f
 */
@Api(tags = "")
@RestController
@RequestMapping("/api/v2/install")
public class FileController {

    /**
     * 导入工具类
     */
    @Autowired
    private FastDfsUtil fastDfsUtil;
    @Autowired
    private FdfsWebServer fdfsWebServer;

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public Result Result(MultipartFile file) throws IOException {
        try {
            String url = fastDfsUtil.uploadFile(file);
            return Result.SUCCESS(url);
        }catch (Exception e){
            e.printStackTrace();
            return Result.ERROR(ResultCode.ERROR_MANAGER_PIC);
        }
    }

    @ApiOperation("删除文件")
    @GetMapping("/filedelete")
    public void deleteFile(String filePath) {
        Boolean result = fastDfsUtil.deleteFile(filePath);
    }

    @ApiOperation("删除文件")
    @GetMapping("/getUrl")
    public String getResAccessUrl(String path) {
        String url = fdfsWebServer.getWebServerUrl() + path;
        System.out.println("上传文件地址为：\n" + url);
        return url;
    }

}

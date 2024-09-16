package com.smart.test.demo.controller;

import com.smart.common.utils.upload.FastDfsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 文件上传
 *
 * @author 三多
 * @Time 2020/6/18
 */
@Api("文件上传")
@RestController
@RequestMapping("/fastDfs")
public class FastDfsController {
    /**
     * 导入工具类
     */
    @Autowired
    private FastDfsUtil fastDfsUtil;

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public void uploadFile(MultipartFile file) throws IOException {
        String s = fastDfsUtil.uploadFile(file);
        String resAccessUrl = fastDfsUtil.getResAccessUrl(s);
    }

    /**
     * @param content 源
     * @throws IOException
     */
    @ApiOperation("生成二维码并上传")
    @PostMapping("/qr/upload")
    public String uploadQrCodeFile(String content) throws IOException {
        return fastDfsUtil.packageUrlForLink(content);
    }

    @ApiOperation("文件下载")
    @GetMapping("/download")
    public void downloadFile(String filePath, HttpServletResponse response) throws IOException {
        byte[] bytes = fastDfsUtil.downloadFile(filePath);
        String fileName = "哈哈.jpg";
        // 设置强制下载不打开
        response.setContentType("application/force-download");
        //方式一
        // fileName=new String(fileName.getBytes(), "ISO8859-1")
        //方式二
        fileName = URLEncoder.encode(fileName, "utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        IOUtils.write(bytes, response.getOutputStream());
    }

    /**
     * 流媒体的方式播放视频，只能从头看到尾，不能手动点击重新看已经看过的内容
     *
     * @param filePath
     * @param response
     * @throws IOException
     */
    @ApiOperation("播放视频")
    @GetMapping("/play")
    public void streamMedia(String filePath, HttpServletResponse response) throws IOException {
        byte[] bytes = fastDfsUtil.downloadFile(filePath);
        IOUtils.copy(new ByteArrayInputStream(bytes), response.getOutputStream());
        response.flushBuffer();
    }

    @ApiOperation("删除文件")
    @GetMapping("/delete")
    public void deleteFile(String filePath) {
        Boolean result = fastDfsUtil.deleteFile(filePath);
    }
}

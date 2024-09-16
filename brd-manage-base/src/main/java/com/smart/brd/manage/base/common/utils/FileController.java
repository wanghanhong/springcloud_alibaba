package com.smart.brd.manage.base.common.utils;

import com.github.tobato.fastdfs.domain.conn.FdfsWebServer;
import com.smart.brd.manage.base.common.exception.CustomException;
import com.smart.common.core.domain.Result;
import com.smart.common.utils.upload.FastDfsUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author
 */
@Slf4j
@RestController
@RequestMapping("/api/v2/brd")
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
            if(file == null ){
                throw new CustomException("文件为空");
            }
            if(!fastDfsUtil.checkFileFormat(file)){
                throw new CustomException("上传文件类型不正确,请选择正确文件上传");
            }
            String url = fastDfsUtil.uploadFile(file);
            return Result.SUCCESS(url);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(211,e.getMessage(),true);
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
        log.info("上传文件地址为：\n" + url);
        return url;
    }

    @ApiOperation("文件下载")
    @GetMapping("/download")
    public void downloadFile(String filePath, HttpServletResponse response) throws IOException {
        byte[] bytes = fastDfsUtil.downloadFile(filePath);
        String fileName = filePath.substring(filePath.lastIndexOf("/")+1);
        // 设置强制下载不打开
        response.setContentType("application/force-download");
        //方式一
        fileName = URLEncoder.encode(fileName, "utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        IOUtils.write(bytes, response.getOutputStream());
    }

}

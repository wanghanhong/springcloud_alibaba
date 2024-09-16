//package com.smart.test.demo.utils;
//
//import cn.hutool.core.img.ImgUtil;
//import cn.hutool.extra.qrcode.QrCodeUtil;
//import com.github.tobato.fastdfs.domain.conn.FdfsWebServer;
//import com.github.tobato.fastdfs.domain.fdfs.StorePath;
//import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
//import com.github.tobato.fastdfs.service.FastFileStorageClient;
//import org.apache.commons.io.FilenameUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
///**
// * @author 三多
// * @Time 2020/6/18
// */
//@Component
//public class FastDfsUtil {
//    @Autowired
//    private FastFileStorageClient fastFileStorageClient;
//    @Autowired
//    private FdfsWebServer fdfsWebServer;
//
//    /**
//     * 文件上传
//     *
//     * @param filessss
//     * @return
//     * @throws IOException
//     */
//    public String uploadFile(MultipartFile filessss) throws IOException {
//        StorePath storePath = fastFileStorageClient.uploadFile(filessss.getInputStream(), filessss.getSize(), FilenameUtils.getExtension(filessss.getOriginalFilename()), null);
//        String fullPath = storePath.getFullPath();
//        getResAccessUrl(fullPath);
//        return fullPath;
//
//    }
//
//    /**
//     *
//     * @param outputStream
//     * @return
//     * @throws IOException
//     */
//    public String uploadFile(ByteArrayOutputStream outputStream) throws IOException {
//
//        // 调用FastDFS中的接口将数据流保存到服务器返回图片地址
//        InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
//        StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(inputStream,inputStream.available(),ImgUtil.IMAGE_TYPE_PNG,null);
//        return  this.getResAccessUrl(storePath.getFullPath());
//    }
//
//    /**
//     * 获取上传文件完整路径
//     *
//     * @param filessss
//     * @return
//     */
//    public String uploadFile(File filessss) {
//        try {
//            FileInputStream inputStream = new FileInputStream(filessss);
//            StorePath storePath = fastFileStorageClient.uploadFile(inputStream, filessss.length(), FilenameUtils.getExtension(filessss.getName()), null);
//            return storePath.getFullPath();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * 文件下载
//     * filessss: /group1/M00/00/00/rBYBFV7rQ0CAVzsaAAAMvVtlKU8159.sql
//     *
//     * @param filePath
//     * @return
//     */
//    public byte[] downloadFile(String filePath) {
//        StorePath storePath = StorePath.parseFromUrl(filePath);
//        byte[] bytes = fastFileStorageClient.downloadFile(storePath.getGroup(), storePath.getPath(), new DownloadByteArray());
//        return bytes;
//    }
//
//    /**
//     * 删除文件
//     *
//     * @param filePath
//     * @return
//     */
//    public Boolean deleteFile(String filePath) {
//        if (StringUtils.isEmpty(filePath)) {
//            return false;
//        }
//        try {
//            StorePath storePath = StorePath.parseFromUrl(filePath);
//            fastFileStorageClient.deleteFile(storePath.getGroup(), storePath.getPath());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * 封装文件完整URL地址
//     *
//     * @param path
//     * @return
//     */
//    public String getResAccessUrl(String path) {
//        String url = fdfsWebServer.getWebServerUrl() + path;
//        System.out.println("上传文件地址为：\n" + url);
//        return url;
//    }
//
//    /**
//     * 根据内容生成二维码
//     * @param content
//     * @return
//     */
//    public String packageUrlForLink(String content) {
//        BufferedImage image = QrCodeUtil.generate(content, 400, 400);
//        String link = "";
//        try {
//            //以流的方式讲图片上传到fastdfs上：
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            ImageIO.write(image, ImgUtil.IMAGE_TYPE_PNG, outputStream);
//            //commonFileUtil.upfileImage()的方式其实是调用的
//            // fastFileStorageClient.uploadImageAndCrtThumbImage(is, size, fileExtName, metaData)的方法
//            link = this.uploadFile(outputStream);
//            System.out.println(link);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        return link;
//    }
//
//}

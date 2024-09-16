package com.smart.test.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Pdf 生成
 * @author 三多
 * @Time 2020/6/23
 */
@Api("PDF下载")
@RestController
@RequestMapping("/pdf")
public class PdfController {
    /**
     * 步骤
     *      1.引入jasper文件
     *      2.创建jasperPrint,填充数据
     *      3.将jasperPrint以PDF的格式输出
     * @param request
     * @param response
     */
    @ApiOperation("pdf生成")
    @GetMapping("/create.pdf")
    public void createPdf(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.引入jasper文件
        Resource resource = new  ClassPathResource("pdf/bank.jasper");
        FileInputStream in = new FileInputStream(resource.getFile());
        ServletOutputStream os = response.getOutputStream();
        //2.创建jasperPrint，如果没有JREmptyDataSource输出PDF会为为空
        /**
         * in：文件输入流
         * new HashMap<>()：输入的参数
         * JasperDataSource：数据源，填充模板的数据源，数据源可以不同（connection，javaBean，Map），没有填充一个空的数据源JREmptyDataSource
         */
        Map<String,Object> data =  new HashMap<>();
        data.put("num","1222222");
        data.put("name","中文测试");
        JasperPrint print = JasperFillManager.fillReport(in, data, new JREmptyDataSource());
        JasperExportManager.exportReportToPdfStream(print,os);
        os.flush();
    }
}

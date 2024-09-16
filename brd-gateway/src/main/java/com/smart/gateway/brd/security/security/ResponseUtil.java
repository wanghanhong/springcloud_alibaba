package com.smart.gateway.brd.security.security;


import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;

public class ResponseUtil {

    public static void out(HttpServletResponse response,Object o) {
        try {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println(o.toString());
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void export(HttpServletResponse response,Workbook wb,String fileName){
        try {
            response.setHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("utf-8"),"iso8859-1"));
            response.setContentType("application/ynd.ms-excel;charset=UTF-8");
            OutputStream out=response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
package com.smart.brd.manage.base.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.smart.brd.manage.base.common.utils.Md5Utils;
import com.smart.brd.manage.base.service.Video;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;


/**
 * @author dukzzz
 * @date 2021/4/12 13:45:下午
 * @desc
 */
@Service
public class VideoImpl implements Video {
    //LnxohaEZ1l5Q6Ja1MBTC
    private static String memberkey="cWgZllpCw5mrSAKvnI2q";
    @Override
    public String getMd5(String channel) {
        Md5Utils md=new Md5Utils();
        //文档里面的参数，原则上是不变的
        String string="5n01793f&&{\"accesstype\":1,\"deviceid\":\""+channel+"\",\"memberkey\":\""+memberkey+"\",\"networktype\":0}";
        return md.encryption(string);
    }
//
//    public static void main(String[] args) {
//        VideoImpl video = new VideoImpl();
//        JSONObject jsonObject = video.sendPostUrl("61010102031320000049");
//        JsonObject obj = new Gson().fromJson(jsonObject.getString("result"), JsonObject.class);
//        System.out.println("==================="+obj.get("flvuri")+"===================");
//    }
    @Override
    public String getUrlVlc(String channel) {
        VideoImpl video = new VideoImpl();
        JSONObject jsonObject = video.sendPostUrl(channel);
        //获取result里面的json对象
        JsonObject obj= new JsonObject();
        String url= "";
        try {
            if(jsonObject != null){
                obj = new Gson().fromJson(jsonObject.getString("result"), JsonObject.class);
            }
            if(obj != null){
                //获取前端需要的VLC播放地址
                String string = String.valueOf(obj.get("flvuri"));
                //去掉两边的引号
                url=string.replace("\"","");
            }
        }catch (Exception e){
            return null;
        }
        return url;
    }
    //请求post接口
    private JSONObject sendPostUrl(String channel){
        //国标对接平台的文档地址，原则上不变
        String url="http://113.133.172.238:19103/api/dict/media/play?appkey=5910240001";
        //文档里面需要传的参数
        String bodyData="{" +
                "\"parmdata\":{" +
                "\"accesstype\":1," +
                "\"deviceid\":\""+channel+"\"," +
                "\"memberkey\":\""+memberkey+"\"," +
                "\"networktype\":0" +
                "}," +
                "\"sign\":\""+getMd5(channel)+"\"" +
                "}";
        PrintWriter out = null;
        BufferedReader in = null;
        JSONObject jsonObject = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流（设置请求编码为UTF-8）
            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8));
            // 发送请求参数
            out.print(bodyData);
            // flush输出流的缓冲
            out.flush();
            // 获取请求返回数据（设置返回数据编码为UTF-8）
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            jsonObject = JSONObject.fromObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }

        return jsonObject;
    }


}

package com.smart.brd.manage.base.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.smart.brd.manage.base.common.exception.CustomException;
import com.smart.brd.manage.base.common.utils.Md5Utils;
import com.smart.brd.manage.base.entity.vo.PlaybackVideoVo;
import com.smart.brd.manage.base.entity.vo.PlaybackVo;
import com.smart.brd.manage.base.service.PlaybackVideo;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.List;

/**
 * @author dukzzz
 * @date 2021/4/13 8:07:上午
 * @desc
 */
@Service
public class PlaybackVideoImpl implements PlaybackVideo {
    //LnxohaEZ1l5Q6Ja1MBTC
    private static String memberkey="cWgZllpCw5mrSAKvnI2q";
    /**
     * 获取md5的32位加密
     */
    @Override
    public String getMd5(PlaybackVideoVo vo) {
        Md5Utils md=new Md5Utils();
        //文档里面的参数，原则上是不变的     参数不能有空格和换行符
        String string="5n01793f&&{\"memberkey\":\""+memberkey+"\",\"deviceid\":\""+
                        vo.getDeviceCode()+"\",\"starttime\":\""+
                        vo.getPlayStartTime().replace(" ","")+
                        "\",\"endtime\":\""+vo.getPlayEndTime().replace(" ","")+
                        "\"," +
                        "\"pagesize\":"+vo.getPageSize()+"," +
                        "\"pagenum\":"+vo.getPageNum()+"}";
        String encryption = md.encryption(string);
        return encryption;
    }

    @Override
    public PlaybackVo getUrlList(PlaybackVideoVo vo) {
        PlaybackVideoImpl impl=new PlaybackVideoImpl();
        JSONObject jsonObject = impl.sendPostUrl(vo);
        try {
            Gson gson = new Gson();
            JsonObject obj = new JsonObject();
            if(jsonObject != null){
                obj = new Gson().fromJson(jsonObject.getString("result"), JsonObject.class);
            }
            PlaybackVo playbackVo = gson.fromJson(obj, PlaybackVo.class);
            JsonArray array = obj.getAsJsonArray("list");
            List<PlaybackVideoVo> persons = gson.fromJson(array, new TypeToken<List<PlaybackVideoVo>>() {
            }.getType());
//            Collections.reverse(persons);
            playbackVo.setList(persons);
            return playbackVo;
        }catch (Exception e){
            throw new CustomException("暂无数据，请稍后重试！");
        }
    }
    //请求post接口
    private JSONObject sendPostUrl(PlaybackVideoVo vo) {
        //国标对接平台的文档地址，原则上不变
        String url="http://113.133.172.238:19103/api/dict/storage/find/videorecord?appkey=5910240001";
        PlaybackVideoImpl impl=new PlaybackVideoImpl();
        String md5 = impl.getMd5(vo);
        //文档里面需要传的参数
        String bodyData="{" +
                "\"parmdata\":{" +
                "\"memberkey\":\""+memberkey+"\"," +
                "\"deviceid\":\""+vo.getDeviceCode()+"\"," +
                "\"starttime\":\""+vo.getPlayStartTime()+"\"," +
                "\"endtime\":\""+vo.getPlayEndTime()+
                "\"," +
                "\"pagesize\":"+vo.getPageSize()+"," +
                "\"pagenum\":"+vo.getPageNum()+
                "}," +
                "\"sign\":\""+md5+"\"" +
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
            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            // 发送请求参数
            out.print(bodyData);
            // flush输出流的缓冲
            out.flush();
            // 获取请求返回数据（设置返回数据编码为UTF-8）
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
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
//    public static void main(String[] args){
//        PlaybackVideoVo vo=new PlaybackVideoVo();
//        vo.setPlayStartTime("2021-04-13 12:00:00");
//        vo.setPlayEndTime("2021-04-13 14:00:00");
//        vo.setDeviceCode("61010102031320000049");
//        PlaybackVideoImpl impl=new PlaybackVideoImpl();
//        String md5 = impl.getMd5(vo);
//        System.out.println(md5);
//        JSONObject jsonObject = impl.sendPostUrl(vo);
//        System.out.println(jsonObject+"==========="+md5);
//
//        JsonObject obj = new Gson().fromJson(jsonObject.getString("result"), JsonObject.class);
//        JsonArray list = obj.getAsJsonArray("list");
//        Gson gson = new Gson();
//        List<PlaybackVideoVo> persons = gson.fromJson(list, new TypeToken<List<PlaybackVideoVo>>() {
//        }.getType());
//
//        System.out.println(persons);
//    }

}

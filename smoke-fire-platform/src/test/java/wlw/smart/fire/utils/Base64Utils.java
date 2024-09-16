package wlw.smart.fire.utils;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Base64加密工具类
 */
@SuppressWarnings("restriction")
@Slf4j
public class Base64Utils {
    /**
     * Base64编码(依赖sun.misc.BASE64Decoder.jar)
     * @param data 要加密的字符数组
     * @return String 加密后的16进制字符串
     */
	public static String encodeString(byte[] data){
        return new BASE64Encoder().encode(data);
    }


    /**
     * Base64解码(依赖sun.misc.BASE64Decoder.jar)
     * @param data 要解密的字符串
     * @return String 解密后的字符串
     * @throws IOException
     */
	public static byte[] decodeByte(String data) throws IOException {
        return new BASE64Decoder().decodeBuffer(data);
    }

    /**
     * 解码为16位集合
     * @param base64Str
     * @return
     */
    public static List<String> decodeBase64To16(String base64Str){
        ArrayList<String> aep_data = new ArrayList<String> ();
        try {
            byte[] b = decodeByte(base64Str);
            int a=1;
            for (int i = 0; i < b.length; i++)
            {
                String hex = Integer.toHexString(b[i] & 0xFF);
                if (hex.length() == 1){
                    hex = '0' + hex;
                }
                a++;
                aep_data.add(hex.toUpperCase());
            }
            log.info("数据包总条数为"+":"+a);
        }catch (IOException e){
            System.out.println("发生异常"+":"+e);
            log.error("e",e);
        }
        return aep_data;
    }

    /**
     * 解码为10位集合
     * @param base64Str
     * @return
     */
    public static List<Long> decodeBase64To10(String base64Str){
        ArrayList<Long> aep_data = new ArrayList<Long> ();
        try {
            byte[] b = decodeByte(base64Str);
            int a=1;
            for (int i = 0; i < b.length; i++)
            {
                String hex = Integer.toHexString(b[i] & 0xFF);
                if (hex.length() == 1){
                    hex = '0' + hex;
                }
                a++;
                aep_data.add(Long.parseLong(hex.toUpperCase(), 16));
            }
            log.info("数据包总条数为"+":"+a);
        }catch (IOException e){
            System.out.println("发生异常"+":"+e);
        }
        return aep_data;
    }


    /** 测试方法
     * @param args
     * @throws IOException
     */
//    public static void main(String args[]) throws IOException{
//        //测试数据
//        String base64Str = "eyJkZXZpY2VJZCI6Ijg5NDJlNjFlZjNkZDQ3MzZhMjQ1ODVjY2EzNTA1YzA3IiwiSU1FSSI6Ijg2MzA4MTAzMzIwNTI1OSIsIklNU0kiOiJ1bmRlZmluZWQiLCJkZXZpY2VUeXBlIjoiIiwidGVuYW50SWQiOiIxMDE0Mzc3OSIsInByb2R1Y3RJZCI6IjEwMDAzOTA0IiwibWVzc2FnZVR5cGUiOiJkYXRhUmVwb3J0IiwidG9waWMiOiJ2MS91cC9hZDE5IiwiYXNzb2NBc3NldElkIjoiIiwidGltZXN0YW1wIjoxNTUwMTMxNzk3OTAwLCJwYXlsb2FkIjp7ImxhdCI6MCwibG9uIjowLCJzcGVlZCI6MCwiYWx0IjowLCJiZWFyIjowLCJ0aW1lIjoxMzYyMjk5NiwidGVtcCI6MzAsImFsZXJ0IjoxMjgsImxhdDEiOjAsImxvbjEiOjAsInNwZWVkMSI6MCwiYWx0MSI6MCwiYmVhcjEiOjAsInRpbWUxIjowLCJ0ZW1wMSI6MCwiYWxlcnQxIjowLCJsYXQyIjowLCJsb24yIjowLCJzcGVlZDIiOjAsImFsdDIiOjAsImJlYXIyIjowLCJ0aW1lMiI6MCwidGVtcDIiOjAsImFsZXJ0MiI6MCwibGF0MyI6MCwibG9uMyI6MCwic3BlZWQzIjowLCJhbHQzIjowLCJiZWFyMyI6MCwidGltZTMiOjAsInRlbXAzIjowLCJhbGVydDMiOjAsImxhdDQiOjAsImxvbjQiOjAsInNwZWVkNCI6MCwiYWx0NCI6MCwiYmVhcjQiOjAsInRpbWU0IjowLCJ0ZW1wNCI6MCwiYWxlcnQ0IjowLCJsYXQ1IjowLCJsb241IjowLCJzcGVlZDUiOjAsImFsdDUiOjAsImJlYXI1IjowLCJ0aW1lNSI6MCwidGVtcDUiOjAsImFsZXJ0NSI6MCwibGF0NiI6MCwibG9uNiI6MCwic3BlZWQ2IjowLCJhbHQ2IjowLCJiZWFyNiI6MCwidGltZTYiOjAsInRlbXA2IjowLCJhbGVydDYiOjAsImxhdDciOjAsImxvbjciOjAsInNwZWVkNyI6MCwiYWx0NyI6MCwiYmVhcjciOjAsInRpbWU3IjowLCJ0ZW1wNyI6MCwiYWxlcnQ3IjowLCJsYXQ4IjowLCJsb244IjowLCJzcGVlZDgiOjAsImFsdDgiOjAsImJlYXI4IjowLCJ0aW1lOCI6MCwidGVtcDgiOjAsImFsZXJ0OCI6MCwibGF0OSI6MCwibG9uOSI6MCwic3BlZWQ5IjowLCJhbHQ5IjowLCJiZWFyOSI6MCwidGltZTkiOjAsInRlbXA5IjowLCJhbGVydDkiOjAsImxhdDEwIjowLCJsb24xMCI6MCwic3BlZWQxMCI6MCwiYWx0MTAiOjAsImJlYXIxMCI6MCwidGltZTEwIjowLCJ0ZW1wMTAiOjAsImFsZXJ0MTAiOjAsImxhdDExIjowLCJsb24xMSI6MCwic3BlZWQxMSI6MCwiYWx0MTEiOjAsImJlYXIxMSI6MCwidGltZTExIjowLCJ0ZW1wMTEiOjAsImFsZXJ0MTEiOjAsImxhdDEyIjowLCJsb24xMiI6MCwic3BlZWQxMiI6MCwiYWx0MTIiOjAsImJlYXIxMiI6MCwidGltZTEyIjowLCJ0ZW1wMTIiOjAsImFsZXJ0MTIiOjAsImxhdDEzIjowLCJsb24xMyI6MCwic3BlZWQxMyI6MCwiYWx0MTMiOjAsImJlYXIxMyI6MCwidGltZTEzIjowLCJ0ZW1wMTMiOjAsImFsZXJ0MTMiOjAsImxhdDE0IjowLCJsb24xNCI6MCwic3BlZWQxNCI6MCwiYWx0MTQiOjAsImJlYXIxNCI6MCwidGltZTE0IjowLCJ0ZW1wMTQiOjAsImFsZXJ0MTQiOjAsImxhdDE1IjowLCJsb24xNSI6MCwic3BlZWQxNSI6MCwiYWx0MTUiOjAsImJlYXIxNSI6MCwidGltZTE1IjowLCJ0ZW1wMTUiOjAsImFsZXJ0MTUiOjAsImxhdDE2IjowLCJsb24xNiI6MCwic3BlZWQxNiI6MCwiYWx0MTYiOjAsImJlYXIxNiI6MCwidGltZTE2IjowLCJ0ZW1wMTYiOjAsImFsZXJ0MTYiOjAsImxhdDE3IjowLCJsb24xNyI6MCwic3BlZWQxNyI6MCwiYWx0MTciOjAsImJlYXIxNyI6MCwidGltZTE3IjowLCJ0ZW1wMTciOjAsImFsZXJ0MTciOjAsImxhdDE4IjowLCJsb24xOCI6MCwic3BlZWQxOCI6MCwiYWx0MTgiOjAsImJlYXIxOCI6MCwidGltZTE4IjowLCJ0ZW1wMTgiOjAsImFsZXJ0MTgiOjAsImxhdDE5IjowLCJsb24xOSI6MCwic3BlZWQxOSI6MCwiYWx0MTkiOjAsImJlYXIxOSI6MCwidGltZTE5IjowLCJ0ZW1wMTkiOjAsImFsZXJ0MTkiOjAsImxhdDIwIjowLCJsb24yMCI6MCwic3BlZWQyMCI6MCwiYWx0MjAiOjAsImJlYXIyMCI6MCwidGltZTIwIjowLCJ0ZW1wMjAiOjAsImFsZXJ0MjAiOjAsImxhdDIxIjowLCJsb24yMSI6MCwic3BlZWQyMSI6MCwiYWx0MjEiOjAsImJlYXIyMSI6MCwidGltZTIxIjowLCJ0ZW1wMjEiOjAsImFsZXJ0MjEiOjAsImxhdDIyIjowLCJsb24yMiI6MCwic3BlZWQyMiI6MCwiYWx0MjIiOjAsImJlYXIyMiI6MCwidGltZTIyIjowLCJ0ZW1wMjIiOjAsImFsZXJ0MjIiOjAsImxhdDIzIjowLCJsb24yMyI6MCwic3BlZWQyMyI6MCwiYWx0MjMiOjAsImJlYXIyMyI6MCwidGltZTIzIjowLCJ0ZW1wMjMiOjAsImFsZXJ0MjMiOjAsImxhdDI0IjowLCJsb24yNCI6MCwic3BlZWQyNCI6MCwiYWx0MjQiOjAsImJlYXIyNCI6MCwidGltZTI0IjowLCJ0ZW1wMjQiOjAsImFsZXJ0MjQiOjAsImxhdDI1IjowLCJsb24yNSI6MCwic3BlZWQyNSI6MCwiYWx0MjUiOjAsImJlYXIyNSI6MCwidGltZTI1IjowLCJ0ZW1wMjUiOjAsImFsZXJ0MjUiOjB9LCJ1cFBhY2tldFNOIjoiIiwidXBEYXRhU04iOiIiLCJzZXJ2aWNlSWQiOjEsInByb3RvY29sIjoibHdtMm0ifQo=";
//        //将测试数据解码为16位编码
//        List<String> strings =  decodeBase64To16(base64Str);
//        log.info("转为16进制的数据为："+strings.toString());
//        //将16进制转为10进制数据
//        List<Long> longs = decodeBase64To10(base64Str);
//        log.info("转为10进制的数据为："+ longs.toString());
//    }
}

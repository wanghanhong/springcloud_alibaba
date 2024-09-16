package wlw.smart.fire.device.access;

import org.apache.commons.lang3.StringUtils;
import wlw.smart.fire.device.access.utils.Base64Utils;
import wlw.smart.fire.device.access.utils.HexUtil;

import java.util.HashMap;
import java.util.List;

/**
 * 解析协议
 * @author 三多
 * @Time 2020/4/8
 */
public class ResolutionProtocol {
    public static void main(String[] args) {
        String str16="5055003010042f08010003001b02f6e9806845482029152002080000000001264c7307a8f852530000000002f6e9800000000000";
        HashMap<String,Object> map = new HashMap<>();

        map.put("Scode",str16.substring(0,1));
        String substring = str16.substring(2, 5);
        map.put("Len", substring);
        map.put("Scode",str16.substring(0));
        String s = hexStringToString(str16);
        str16 ="3010042f08010003001b02f6e9806845482029152002080000000001264c7307a8f852530000000002f6e98000000000";
        String ss = hexStringToString(str16);

        System.out.println(s);
        System.out.println(ss);
        getResultData("");
    }

    /**
     * 16进制转换成为string类型字符串
     * @param s
     * @return
     */
    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "GBK");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }


    public static void getResultData(String rawData){
        String  str="UFUAMBACLwgBAAMAGwL24choRUggKRUgAggAAAAAASNBfAeoyVRcAAAAAAL24cgAAAAAAA==";
        List<String> strings = Base64Utils.decodeBase64To16(str);
        System.out.println("原生："+strings);
        int start = strings.indexOf("55")+3;
        List<String> hexList = strings.subList(start, strings.size());
        HashMap<String,Object> hashMap = new HashMap<String,Object> ();
        System.out.println("加工："+hexList);
        //1.解析header
        //包类型
        String type = hexList.get(0);
        hashMap.put("type",type);
        //包序号
        String index = hexList.get(1);
        hashMap.put("index",index);
        //版本号
        String version = hexList.get(2);
        hashMap.put("version",version);
        //设备类型
        String dev = hexList.get(3);
        hashMap.put("dev",dev);
        //生产厂商
        String manu = hexList.get(4);
        hashMap.put("manu",manu);
        //原始包序号
        String initiator = hexList.get(5);
        hashMap.put("initiator",initiator);
        //运营商
        String opt = hexList.get(6);
        hashMap.put("opt",opt);
        //属性
        String attri = hexList.get(7) + hexList.get(8);
        hashMap.put("attri",attri);
        //时间
        String timeHex = hexList.get(9) + hexList.get(10) + hexList.get(11) + hexList.get(12);
        hashMap.put("timeHex",timeHex);
        //属性
        String attriBinary = HexUtil.hexToBinaryString(attri);
        hashMap.put("attriBinary",attriBinary);

        if (StringUtils.isEmpty(attriBinary) || attriBinary.length() != 16) {
            return;
        }
        String dataLengthBinary = attriBinary.substring(7);
        //data的数据长度
        Integer dataLength = HexUtil.binaryToDecimal(dataLengthBinary);
        hashMap.put("dataLength",dataLength);
        if (dataLength != 27) {
            return;
        }
        //转换后的时间
        String time = HexUtil.getCaculateData(timeHex);
        hashMap.put("time",time);
        String imei = getImei(hexList);
        hashMap.put("imei",imei);
        //2.解析通用数据包
        //通用数据上报
        long cmd = HexUtil.hexToLong(hexList.get(21) + hexList.get(22));
        hashMap.put("cmd",cmd);
        //报警类型
        int alert = HexUtil.hexToLong(hexList.get(23)).intValue();
        hashMap.put("alert",alert);
        //设备故障码
        long deviceCode = HexUtil.hexToLong(hexList.get(24));
        hashMap.put("deviceCode",deviceCode);
        //设备模式
        int deviceMode = HexUtil.hexToLong(hexList.get(25)).intValue();
        hashMap.put("deviceMode",deviceMode);
        //设备电池电压
        float deviceVolt = HexUtil.hexToLong(hexList.get(26) + hexList.get(27)) / 100f;
        hashMap.put("deviceVolt",deviceVolt);
        //电池电量剩余--百分比
        int DevVoltper = HexUtil.hexToLong(hexList.get(28)).intValue();
        hashMap.put("DevVoltper",DevVoltper);
        //设备摄氏温度
        int temp = HexUtil.hexToLong(hexList.get(29)).intValue() - 100;
        hashMap.put("temp",temp);
        //小区信息
        int cellid = HexUtil.hexToLong(hexList.get(30) + hexList.get(31) + hexList.get(32) + hexList.get(33)).intValue();
        hashMap.put("cellid",cellid);
        //信号强度信息
        int rsrp = (HexUtil.hexToLong(hexList.get(34)).intValue() * -1);
        hashMap.put("rsrp",rsrp);

        String Smoketime = HexUtil.getCaculateData(hexList.get(35) + hexList.get(36) + hexList.get(37) + hexList.get(38));
        hashMap.put("Smoketime",Smoketime);
        //最新自检时间
        String Selfdetecttime = HexUtil.getCaculateData(hexList.get(39) + hexList.get(40) + hexList.get(41) + hexList.get(42));
        hashMap.put("Selfdetecttime",Selfdetecttime);
        //最新自检结果
        int selfdetectret = HexUtil.hexToLong(hexList.get(43)).intValue();
        hashMap.put("selfdetectret",selfdetectret);
        //保留
        long Reserve = HexUtil.hexToLong(hexList.get(44) + hexList.get(45) + hexList.get(46) + hexList.get(47));
        hashMap.put("Reserve",Reserve);
        System.out.println(hashMap);
    }

    private static  String getImei(List<String> hexList) {
        StringBuilder imei = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            char[] arr = hexList.get(13 + i).toCharArray();
            imei.append(arr[1]);
            if (i != 7) {
                imei.append(arr[0]);
            }
        }
        return imei.toString();
    }

}

package com.smart.card.cardapi.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.card.cardapi.entity.vo.EntityVo;
import com.smart.card.cardapi.entity.vo.FlowParamVo;
import com.smart.card.cardapi.service.CommonService;
import com.smart.card.cardapi.util.CtAccountService;
import com.smart.card.cardapi.util.DesUtils;
import com.smart.common.utils.StringUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;
import java.io.*;
import java.util.*;

@Service
@Slf4j
public class CommonServiceImpl implements CommonService {

    @Resource
    private CtAccountService ctAccountService;
    @Resource
    private RestTemplate restTemplate;

    private final List<String> ROOT_STRING = Arrays.asList("poolList", "SvcCont", "root", "VAS_QRsp",
            "ContractRoot", "businessServiceResponse");

    private final List<String> WEB_STRING = Arrays.asList("pool", "OutProdInfos", "Response", "PoolInfo",
            "web:BALANCE_QRRsp", "web:CurrAcuRsp", "BillQueryInformation", "SM_TICKET_QRsp", "result",
            "Service_Information", "web:NEW_DATA_TICKET_QRsp", "RESULT");

    @Override
    public Object getXmlCommon(FlowParamVo vo){
        //用户名
        String user_id = ctAccountService.queryAccount().getUserId();
        String aSecret = ctAccountService.queryAccount().getASecret();
        String password = ctAccountService.queryAccount().getPassword();
        //物联网卡号(149或10649号段)
        String access_number = vo.getAccessNumber();
        String iccid = vo.getIccid();
        String param = "";
        String paramAdd = "";
        if(StringUtils.isNotBlank(vo.getParamAdd())){
            paramAdd = vo.getParamAdd();
        }
        if(StringUtils.isNotBlank(access_number)){
            param = access_number;
            paramAdd += "&access_number="+access_number;
        }
        if(StringUtils.isNotBlank(iccid)){
            param = iccid;
            paramAdd += "&iccid="+iccid;
        }
        if(StringUtils.isNotBlank(vo.getImei())){
            param = vo.getImei();
            paramAdd += "&imei="+vo.getImei();
        }
        String method = vo.getMethod();
        String url = vo.getUrl();
        //加密数组，数组所需参数根据对应的接口文档，根据iccid查询时加密数组里面传入iccid
        String[] arr;
        arr = getArr(param,user_id,password,method,vo);
        //key1,key2,key2为电信提供的9位长接口密钥平均分为三段所形成
        //key1为密钥前三位，key2为密钥中间三位，key3位密钥最后三位  yak5485Yc
        String key1 = aSecret.substring(0,3);
        String key2 = aSecret.substring(3,6);
        String key3 = aSecret.substring(6,9);
        //密码加密
        String password_1 = DesUtils.strEnc(password,key1,key2,key3);
        //生成sign加密值
        String sign = DesUtils.strEnc(DesUtils.naturalOrdering(arr),key1,key2,key3);

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json;charset=utf-8");
        headers.setContentType(type);
        String main_url = "http://api.ct10649.com:9001/";
        String path_url = main_url+url+"?method="+method+"&user_id="+user_id+"&passWord="+password_1
                +"&sign="+sign+paramAdd;
        log.info("url数据--"+path_url);
        String body = null;
        try {
            ResponseEntity<String> responseEntiry = restTemplate.getForEntity(path_url, String.class);
            body = responseEntiry.getBody();
        } catch (Exception e) {
           e.printStackTrace();
            main_url = "http://180.101.148.79:9001/";
            path_url = main_url+url+"?method="+method+"&user_id="+user_id+"&passWord="+password_1
                    +"&sign="+sign+paramAdd;
            ResponseEntity<String> responseEntiry = restTemplate.getForEntity(path_url, String.class);
            body = responseEntiry.getBody();
        }
        // 根据接入号码:
        //http://api.ct10649.com:9001/m2m_ec/query.do?method=queryBalance&access_number=149****0000&user_id=te****st&passWord=32****00&sign=03****53
        //根据iccid:
        //http://api.ct10649.com:9001/m2m_ec/query.do?method=queryBalance&iccid=8986****4660&user_id=te****st&passWord=32****00&sign=C5****FD
        //解析数据
        log.info("解析数据--"+4+body);
        Map<String,Object> maplist = EntityVoList(body);
//        generatorEntity(maplist,method);
        return maplist.get("result");
    }

    private String[] getArr(String param,String user_id,String password,String method,FlowParamVo vo) {
        String[] arr = {param,user_id,password,method};
        if ("querySmsDetail".equals(method) || "valueAddedDetailQuery".equals(method)) {
            arr = new String[]{param, user_id, password, method, vo.getStartTime(), vo.getEndTime()};
        }
        if ("getOrdersByAccessNumber".equals(method)) {
            arr = new String[]{user_id, password, method, param};
        }
        if ("getOrdersByRequestId".equals(method)) {
            arr = new String[]{user_id, password, method, vo.getRequestId()};
        }
        if ("getSIMList".equals(method) || "getPoolList".equals(method)) {
            arr = new String[]{user_id, password, method};
        }
        if ("disabledNumber".equals(method)) {
            arr = new String[]{param, user_id, password, method, "", vo.getOrderTypeId()};
        }
        if ("getPoolMemberList".equals(method)) {
            arr = new String[]{user_id, password, method, vo.getPoolNbr(), vo.getCurrentPage()};
        }
        if ("getPoolMember".equals(method) || "poolMemDelete".equals(method)) {
            arr = new String[]{user_id, password, method, vo.getPoolNbr(), vo.getMember_accNbr()};
        }
        if ("poolMemAdd".equals(method) || "modifyPoolMember".equals(method)) {
            arr = new String[]{user_id, password, method, vo.getPoolNbr(), vo.getMember_accNbr(), vo.getFlow_quota()};
        }
        if ("flowPoolPay".equals(method)) {
            arr = new String[]{user_id, password, method, vo.getPoolNbr(), vo.getCharge()};
        }
        if ("poolQry".equals(method)) {
            arr = new String[]{user_id, password, method, vo.getPoolNbr()};
        }
        if ("poolMemQry".equals(method)) {
            arr = new String[]{user_id, password, method, vo.getPoolNbr(), vo.getMember_accNbr(), vo.getMonthSelect()};
        }
        if ("orderFlow".equals(method)) {
            arr = new String[]{param, user_id, password, method, vo.getFlowValue()};
        }
        if ("unsubScribe".equals(method)) {
            arr = new String[]{param, user_id, password, vo.getFlowValue(), method};
        }
        if ("offNetAction".equals(method)) {
            arr = new String[]{param, user_id, password, vo.getAction(), vo.getQuota(), vo.getType(), method};
        }
        if ("imeiReRecord".equals(method)) {
            arr = new String[]{param, user_id, password, method, vo.getAction()};
        }
        if ("pay2".equals(method)) {
            arr = new String[]{param, vo.getOrderNumber(), user_id, password, "pay",
                    vo.getBankSub(), vo.getPayMoney(), vo.getCallBackUrl(), vo.getFundType()};
        }
        return arr;
    }

    /**
     * 将字符串的首字母转大写
     * @param str 需要转换的字符串
     * @return
     */
    private String captureName(String str){
        // 进行字母的ascii编码前移，效率要高于截取字符串进行转换的操作
        char[] cs=str.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }

    private void generatorEntity(Map<String,Object> maplist,String method){
        method = captureName(method);
        // entity 文件模板
        String ENTITY_TEMPLATE_PATH = "/card-manage/src/main/resources/templates/";
        String ENTITY_TEMPLATE_NAME = "entity.java.ftl";
        // 包的基础路径
        String BASE_PACKAGE_URL = "com.smart.card";
        String ENTITY_PATH = "/card-manage/src/main/java/com/smart/card/entity/";
        try {
            //创建一个Configuration对象
            Configuration configuration = new Configuration(Configuration.getVersion());
            //设置模板文件所在的路径
            String projectPath = System.getProperty("user.dir");
            String path = projectPath + ENTITY_TEMPLATE_PATH;
            configuration.setDirectoryForTemplateLoading(new File(path));
            //设置模板文件所使用的字符集，一般是utf-8
            configuration.setDefaultEncoding("utf-8");
            //加载一个模板，创建一个模板对象
            Template template = configuration.getTemplate(ENTITY_TEMPLATE_NAME);

            Map<String,Object> map = new HashMap<>();
            map.put("list",maplist.get("list"));
            map.put("entity",method);
            map.put("package",BASE_PACKAGE_URL);
            //创建一个writer对象，一般创建一个FileWriter对象，指定生成的文件名。
            String path2 = projectPath + ENTITY_PATH;
            Writer writer = new FileWriter(new File(path2+method+".java"));
            //调用模板对象的process方法输出文件
            template.process(map,writer);
            //关闭流
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Map<String,Object> EntityVoList(String str){
        Map<String,Object> map = new HashMap<>();
        List<EntityVo> list = new ArrayList<>();
        if (!str.contains("<")) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                Object result = mapper.readValue(str, Object.class);

                map.put("result", result);
                return map;
            }catch (Exception e) {
                map.put("result", str);
            }
        }
        JSONObject root = XML.toJSONObject(str);
        JSONObject web;
        if(root.has(ROOT_STRING.get(0))){
            web = root.getJSONObject(ROOT_STRING.get(0));
            getList(web, map, list, WEB_STRING.get(0));
        }
        if(root.has(ROOT_STRING.get(1))){
            web = root.getJSONObject(ROOT_STRING.get(1));
            if (web.has(WEB_STRING.get(6))) {
                getList(web, map, list, WEB_STRING.get(6));
            }else if (web.has(WEB_STRING.get(11))) {
                getObject(web, map, WEB_STRING.get(11));
            }else if (web.has(WEB_STRING.get(1))) {
                getList(web, map, list, WEB_STRING.get(1));
            }else if (web.has(WEB_STRING.get(2))) {
                getObject(web, map, WEB_STRING.get(2));
            }else if (web.has(WEB_STRING.get(3))) {
                getObject(root, map, ROOT_STRING.get(1));
            }else if (web.has(WEB_STRING.get(8))) {
                JSONObject res = web.getJSONObject(WEB_STRING.get(8));
                getObject(res, map, "prodInfos");
            }else {
                getObject(root, map, ROOT_STRING.get(1));
            }
        }
        if(root.has(ROOT_STRING.get(2))){
            web = root.getJSONObject(ROOT_STRING.get(2));
            if (web.has(WEB_STRING.get(4))) {
                getObject(web, map, WEB_STRING.get(4));
            }
            if (web.has(WEB_STRING.get(5))) {
                JSONObject res = web.getJSONObject(WEB_STRING.get(5));
                getList(res, map, list, "CumulRspList");
            }
            if (web.has(WEB_STRING.get(7))) {
                getObject(web, map, WEB_STRING.get(7));
            }
            if (web.has(WEB_STRING.get(10))) {
                getObject(web, map, WEB_STRING.get(10));
            }
        }
        if(root.has(ROOT_STRING.get(3))){
            web = root.getJSONObject(ROOT_STRING.get(3));
            getObject(web, map, WEB_STRING.get(9));
        }
        if(root.has(ROOT_STRING.get(4))){
            web = root.getJSONObject(ROOT_STRING.get(4));
            getList(web, map, list, WEB_STRING.get(5));
        }
        if(root.has(ROOT_STRING.get(5))){
            map.put("result", root);
        }
        return map;
    }

    public static void getList(JSONObject root, Map<String,Object> map, List<EntityVo> list, String webKey) {
        JSONObject res;
        String listStr = "";
        if(root.has(webKey)) {
            listStr = root.get(webKey).toString();
            if(listStr.contains("[")) {
                JSONArray jsonArray = root.getJSONArray(webKey);
                map.put("result",jsonArray);
            }else {
                res = root.getJSONObject(webKey);
                map.put("result",res);
            }
        }
    }

    public static void getObject(JSONObject root, Map<String,Object> map, String webKey) {
        JSONObject res = new JSONObject();
        if(root.has(webKey)) {
            res = root.getJSONObject(webKey);
        }
        map.put("result", res);
    }

    public static String execCurl(String[] cmds) {
        ProcessBuilder process = new ProcessBuilder(cmds);
        Process p;
        try {
            p = process.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            return builder.toString();

        } catch (IOException e) {
            System.out.print("error");
            e.printStackTrace();
        }
        return null;
    }

}

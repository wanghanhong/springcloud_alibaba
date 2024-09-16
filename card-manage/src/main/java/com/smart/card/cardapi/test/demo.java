package com.smart.card.cardapi.test;

import com.smart.card.cardapi.util.DesUtils;

public class demo {
		/**
		 * 接口调用示例代码(Java)
		 * @Title: demo.java
		 * @date 2017年1月23日 下午13:19:22
		 * @version V1.0
		 * @Copyright 中国电信股份有限公司物联网分公司
		 * @description API开发手册登录物联网自管理门户->在线文档查看
		 */
		public static void main(String[] args) {
			//具体接口参数需参照自管理门户在线文档
			String access_number="1410132003202";  //物联网卡号(149或10649号段)
			String user_id = "O5VVY4EX6Odz0215n8p239FvDJvx7euk";     //用户名
			String password = "5JT1hJw53MC77F42";    //密码
			String method = "queryBalance";  //接口名-套餐使用量查询

			String[] arr = {access_number,user_id,password,method}; //加密数组，数组所需参数根据对应的接口文档

			// http://api.ct10649.com:9001/m2m_ec/query.do?access_number=1410132003202&method=queryBalance&user_id=O5VVY4EX6Odz0215n8p239FvDJvx7euk&password=5B9D2ED43D8F940FB1ECCC2A2FA012E9D1E78B5F5C18BDA0E9951F663760C3F1&sign=6B74E360FF75F890121FF2DE028AB0FBFB96B201659C1194BEE8E9E45683B6D03F7972EADCF0D322C70A6AA13D5358DB7CE9AB2C4739955A46993FAD069C90071F93DE619E4D08F37B8C2711E92874C79DAAC74F920F3A43A88922F15133B715538CEDBF712CE2696C7353DAA45DB93928FA66363E22E815CA820EB51F859951BF81E3AB256F549265FDF7740B105F947BBE126A3D82E0C0
			// aSecret: yak5485Yc
			//key值指密钥，由电信提供，每个客户都有对应的key值，key值平均分为三段如下：
			String key1 = "yak";
			String key2 = "548";
			String key3 = "5Yc";

			DesUtils des = new DesUtils(); //加密工具类实例化
			String passwordEnc = des.strEnc(password,key1,key2,key3);  //密码加密
			System.out.println("密码加密结果:"+passwordEnc);
			//密码加密结果：441894168BD86A2CC

			String sign = des.strEnc(DesUtils.naturalOrdering(arr),key1,key2,key3); //生成sign加密值
			System.out.println("sign加密结果:"+sign);
			//sign加密结果：45E8B9924DE397A8F7E5764767810CF774CC7E1685BA702C9C4C367EFDAE5D932B37C0C8F0F8EB0CAD6372289F407CA941894168BD86A2CC32E5804EA05BAA5099649468B9418E52

			String passwordDec = des.strDec(passwordEnc,key1,key2,key3);//密码解密
			System.out.println("密码解密结果:"+passwordDec);
			//密码解密结果 :test

			String signDec = des.strDec(sign,key1,key2,key3); //sign解密
			System.out.println("sign解密结果:"+signDec);
			//sign解密结果：14914000000,queryPakage,test,test

			sign="6B74E360FF75F890121FF2DE028AB0FBFB96B201659C1194BEE8E9E45683B6D03F7972EADCF0D322C70A6AA13D5358DB7CE9AB2C4739955A46993FAD069C90071F93DE619E4D08F37B8C2711E92874C79DAAC74F920F3A43A88922F15133B715538CEDBF712CE2696C7353DAA45DB93928FA66363E22E815CA820EB51F859951BF81E3AB256F549265FDF7740B105F947BBE126A3D82E0C0";
			signDec = des.strDec(sign,key1,key2,key3); //sign解密
			System.out.println("1sign解密结果:"+signDec);

		}

	}
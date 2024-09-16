package com.smart.message.manage.sms.smsVerification;

import com.smart.message.manage.sms.SmsConstants;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import java.util.ArrayList;
import java.util.List;

public class TokenUtil {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		System.getProperties().put("proxySet", "true");
//		System.getProperties().put("socksProxyHost", "192.168.13.19");
//		System.getProperties().put("socksProxyPort", 1080);
		// AC,IG,CC//{"res_code":"0","res_message":"Success","p_user_id":"99084135814","access_token":"eb7d0ddea79cc0fad22f33a6f52bbbaa1362638923925","expires_in":2592000,"refresh_token":"910eb1637fd741f165100c0079a9bddf1362638923925"}
		String oauthModel = "CC";
		String accessToken = getAccessToken(oauthModel);
		System.err.println(accessToken);
	}
	
	public static String getAccessToken(String oauth_type) {
		String req_url = SmsConstants.ACCESS_TOKEN_URL;
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("app_id", SmsConstants.APP_ID));
		formparams.add(new BasicNameValuePair("app_secret", SmsConstants.APP_SECRET));
		formparams.add(new BasicNameValuePair("redirect_uri", SmsConstants.REDIRECT_URI));
		if("AC_1".equals(oauth_type)) {
			return SmsConstants.GET_AC_URL;
		} else if("AC_2".equals(oauth_type)) {
			formparams.add(new BasicNameValuePair("grant_type", "authorization_code"));
			formparams.add(new BasicNameValuePair("code", SmsConstants.AUTHORIZATION_CODE));
		} else if("IG".equals(oauth_type)) {
			req_url = SmsConstants.AUTHORIZE_URL;
			formparams.add(new BasicNameValuePair("response_type", "token"));
		} else if("CC".equals(oauth_type)) {
			formparams.add(new BasicNameValuePair("grant_type", "client_credentials"));
		} else if("refresh".equals(oauth_type)) {
			formparams.add(new BasicNameValuePair("grant_type", "refresh_token"));
			formparams.add(new BasicNameValuePair("refresh_token", SmsConstants.REFRESH_TOKEN));
			
		}
		return (String) HttpsUtil.doPost(req_url, formparams, true);
	}
	

}

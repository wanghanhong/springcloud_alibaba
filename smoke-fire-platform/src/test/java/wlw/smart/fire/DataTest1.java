package wlw.smart.fire;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;
import wlw.smart.fire.common.utils.FebsUtil;

public class DataTest1 {

    @Test
    public void dateTest(){

        System.out.println(12);

        String token = "b25e39b47e774b4a05b3cb1555fc377f209457c3fd339d373d3fca7b1ea8be56fdc6ed05b7ffb0700e7300d242fb83b5f1b06b2a73dfea199418de2de8f957f6c2f2c5bb1aee0651056a6adbce31e440144d42dda8569f82af4c69adad0efe37d3c48481011d8b35e884e2b95d290e697dd85846c426026f1b890a5150de5467e455a358175f7035";

        /**
         * 获取token中的信息无需secret解密也能获取
         *
         * @param token 密钥
         * @return  token中包含的用户名
         */

        String tt = FebsUtil.decryptToken(token);
        DecodedJWT jwt = JWT.decode(tt);
        String username = jwt.getClaim("username").asString();

        System.out.println(123);

    }
}

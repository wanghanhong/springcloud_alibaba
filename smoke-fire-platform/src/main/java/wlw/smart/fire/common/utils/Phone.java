package wlw.smart.fire.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Phone {

    /**
     * 中国电信号段 133、149、153、173、177、180、181、189、199
     * 中国联通号段 130、131、132、145、155、156、166、175、176、185、186
     * 中国移动号段 134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188、198
     * 其他号段
     * 14号段以前为上网卡专属号段，如中国联通的是145，中国移动的是147等等。
     *  虚拟运营商
     *  电信：1700、1701、1702
     * 移动：1703、1705、1706
     * 联通：1704、1707、1708、1709、171
     * 卫星通信：1349
     */

    /**
     * 手机号验证
     * @param str
     * @return 验证通过返回true
     */
    public boolean isMobile(final String str) {
        if(StringUtils.isBlank(str)){
            return false;
        }
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
//        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9])|(16[6]))\\d{8}$";
        p = Pattern.compile("^[1][3,4,5,7,8,9][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
    /**
     * 电话号码验证
     * @param str
     * @return 验证通过返回true
     */
    public boolean isPhone(String str) {
        if(StringUtils.isBlank(str)){
            return false;
        }
        Pattern p1 = null, p2 = null;
        Matcher m = null;
        boolean b = false;
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");     // 验证没有区号的
        if (str.length() > 9) {
            m = p1.matcher(str);
            b = m.matches();
        } else {
            m = p2.matcher(str);
            b = m.matches();
        }
        return b;
    }

    public boolean checkPhone(String phone) {
        if(isPhone(phone) || isMobile(phone)){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String phone1 = null;
        String phone2 = "021-88889999";
        String phone3 = "88889999";
        String phone4 = "1111111111";

        Phone phone = new Phone();
        //测试1
        if(phone.isPhone(phone1) || phone.isMobile(phone1)){
            System.out.println("1这是符合的");
        }else {
            System.out.println("1这是不符合的");
        }
//        //测试2
//        if(isPhone(phone2) || isMobile(phone2)){
//            System.out.println("2这是符合的");
//        }
//        //测试3
//        if(isPhone(phone3) || isMobile(phone3)){
//            System.out.println("3这是符合的");
//        }
//        //测试4
//        if(isPhone(phone4) || isMobile(phone4)){
//            System.out.println("4这是符合的");
//        }else{
//            System.out.println("不符合");
//        }
    }


}

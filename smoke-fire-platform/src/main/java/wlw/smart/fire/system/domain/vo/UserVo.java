package wlw.smart.fire.system.domain.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created with IntelliJ IDEA.
 * Date: 2019-11-01
 * Time: 10:52
 *
 * @author Pano
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserVo {

    private Long userId;

    private String username;

    private String password;

    private String status;

    private String avatar;

    private String userNames;

    private String openId;

    private String oldPassword;

    private Long timeExpire;
}

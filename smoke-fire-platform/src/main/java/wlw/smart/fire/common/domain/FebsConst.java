package wlw.smart.fire.common.domain;

/**
 * FEBS常量
 *
 * @author Pano
 */
public interface FebsConst {

    /**
     * user缓存前缀
     */
    String USER_CACHE_PREFIX = "febs.cache.user.";
    /**
     * user角色缓存前缀
     */
    String USER_ROLE_CACHE_PREFIX = "febs.cache.user.role.";
    /**
     * user权限缓存前缀
     */
    String USER_PERMISSION_CACHE_PREFIX = "febs.cache.user.permission.";
    /**
     * user个性化配置前缀
     */
    String USER_CONFIG_CACHE_PREFIX = "febs.cache.user.config.";
    /**
     * token缓存前缀
     */
    String TOKEN_CACHE_PREFIX = "febs.cache.token.";
    /**
     * 存储在线用户的 zset前缀
     */
    String ACTIVE_USERS_ZSET_PREFIX = "febs.user.active";

    /**
     * 排序规则： descend 降序
     */
    String ORDER_DESC = "descend";
    /**
     * 排序规则： ascend 升序
     */
    String ORDER_ASC = "ascend";

    /**
     * 按钮
     */
    String TYPE_BUTTON = "1";
    /**
     * 菜单
     */
    String TYPE_MENU = "0";

    /**
     * 默认状态编码
     */
    int ACTIVE = 1;
    int INACTIVE = 0;

    /**
     * 默认状态编码
     */
    int YES = 1;
    int NO = 0;

    /**
     * 默认管理员账号
     */
    String ADMIN_ACCOUNT = "mrbird";

}

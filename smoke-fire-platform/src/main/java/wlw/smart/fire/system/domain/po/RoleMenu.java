package wlw.smart.fire.system.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Pano
 */
@Data
@Accessors(chain = true)
@TableName("t_role_menu")
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = -7573904024872252113L;

    private Long roleId;

    private Long menuId;
}
package com.smart.card.sys.system.domain.vo;

import com.smart.card.sys.common.domain.router.VueRouter;
import com.smart.card.sys.system.domain.po.Menu;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class MenuVo implements Serializable {

    public List<VueRouter<Menu>> list = new ArrayList();
    public List<String> buttons = new ArrayList();


}
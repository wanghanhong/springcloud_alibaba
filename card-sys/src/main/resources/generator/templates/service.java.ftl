package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;

/**
 * @author ${author}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**------基本方法开始-----------------------------------------*/
    IPage<${entity}> ${entity?uncap_first}List(PageDomain page,${entity} entity);

    ${entity} ${entity?uncap_first}Add(${entity} entity);

    ${entity} ${entity?uncap_first}Update(${entity} entity);

    int ${entity?uncap_first}Del(Long id);

    ${entity} ${entity?uncap_first}Detail(${entity} entity);
    /**------基本方法结束-----------------------------------------*/

}
</#if>

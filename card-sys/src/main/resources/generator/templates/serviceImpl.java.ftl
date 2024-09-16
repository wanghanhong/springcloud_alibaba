package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.page.PageDomain;
import javax.annotation.Resource;
import com.smart.common.utils.IdWorker;
import java.time.LocalDateTime;

/**
 * @author ${author}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private ${table.mapperName} ${table.mapperName?uncap_first};

    @Override
    public IPage<${entity}> ${entity?uncap_first}List(PageDomain page, ${entity} vo) {
        Page<${entity}> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<${entity}> iPage = ${table.mapperName?uncap_first}.${entity?uncap_first}List(pg,vo);
        return iPage;
    }

    @Override
    public ${entity} ${entity?uncap_first}Add(${entity} entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        <#list table.fields as field>
            <#if field.keyFlag><#--生成主键排在第一位-->
         if(entity.get${field.propertyName?cap_first}() == null ){
            entity.set${field.propertyName?cap_first}(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            ${entity?uncap_first}Update(entity);
        }
            </#if>
        </#list>
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            ${entity?uncap_first}Update(entity);
        }
        return entity;
    }

    @Override
    public int ${entity?uncap_first}Del(Long id) {
        int res = ${table.mapperName?uncap_first}.deleteById(id);
        return res;
    }

    @Override
    public ${entity} ${entity?uncap_first}Update(${entity} entity) {
        ${table.mapperName?uncap_first}.updateById(entity);
        return entity;
    }

    @Override
    public ${entity} ${entity?uncap_first}Detail(${entity} entity) {
    <#list table.fields as field>
        <#if field.keyFlag><#--主键-->
        ${entity} detail = ${table.mapperName?uncap_first}.selectById(entity.get${field.propertyName?cap_first}());
        </#if>
    </#list>
        ${entity} detail = ${table.mapperName?uncap_first}.selectById(entity.getId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
</#if>

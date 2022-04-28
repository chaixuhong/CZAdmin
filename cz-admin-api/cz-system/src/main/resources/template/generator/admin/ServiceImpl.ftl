package ${package}.service.impl;

import ${package}.entity.${className};
import ${package}.service.I${className}Service;
import ${package}.model.bo.${className}BO;
import ${package}.model.query.${className}Query;
import org.springframework.stereotype.Service;
import com.cz.mapper.${className}Mapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import com.cz.utils.FilesUtil;
import com.cz.utils.PageUtil;
import com.cz.utils.QueryUtil;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cz.exception.BusinessException;
import javax.annotation.Resource;
/**
* @description 服务实现
* @author ${author}
* @date ${date}
**/
@Service
public class ${className}ServiceImpl  extends MPJBaseServiceImpl<${className}Mapper, ${className}>  implements I${className}Service {

    @Override
    public IPage<${className}> queryAll(${className}Query query, Pageable pageable){
        PageUtil<${className}> pageUtil = new PageUtil<>(pageable);
        MPJQueryWrapper<${className}> wrapper = QueryUtil.buildWrapper(query,${className}.class);
        return this.page(pageUtil,wrapper);
    }

    @Override
    public List<${className}> queryAll(${className}Query query){
        MPJQueryWrapper<${className}> wrapper = QueryUtil.buildWrapper(query,${className}.class);
        return this.list(wrapper);   
    }

    @Override
    @Transactional
    public ${className} findById(${pkColumnType} ${pkChangeColName}) {
        return this.getById(${pkChangeColName});
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ${className} create(${className}BO resources) {
        ${className} bean = new ${className}(resources);
        this.save(bean);
        return bean;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(${className}BO resources) {
        if(Objects.isNull(resources.get${pkCapitalColName}())){
            throw new BusinessException("缺少id，非法操作数据");
        }
        ${className} bean = new ${className}(resources);
        this.updateById(bean);
    }

    @Override
    public List<${className}> deleteAll(List<Integer> ids) {
        List<${className}> ${changeClassName}s = this.listByIds(ids);
        Set<Integer> collect = ${changeClassName}s.stream().map(List<${className}::get${pkCapitalColName}).collect(Collectors.toSet());
        this.removeByIds(collect);
        return ${changeClassName}s;
    }

    @Override
    public void download(List<${className}> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (${className} ${changeClassName} : all) {
            Map<String,Object> map = new LinkedHashMap<>();
        <#list columns as column>
            <#if column.columnKey != 'PRI'>
              <#if column.columnType == 'LocalDateTime' || column.columnType == 'LocalDate'>
                map.put("${column.remark}", Objects.isNull(${changeClassName}.get${column.capitalColumnName}()) ? "-" : ${changeClassName}.get${column.capitalColumnName}().toString());
              <#else>
                <#if column.remark != ''>
                map.put("${column.remark}", ${changeClassName}.get${column.capitalColumnName}());
                <#else>
                map.put(" ${column.changeColumnName}",  ${changeClassName}.get${column.capitalColumnName}());
                </#if>
              </#if>
            </#if>
        </#list>
            list.add(map);
        }
        FilesUtil.downloadExcel(list, response);
    }
}
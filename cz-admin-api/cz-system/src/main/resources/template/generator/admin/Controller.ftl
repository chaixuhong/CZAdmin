package ${package}.controller;

import ${package}.entity.${className};
import ${package}.service.I${className}Service;
import ${package}.model.query.${className}Query;
import ${package}.model.bo.${className}BO;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import com.cz.annotation.Logger;
import com.cz.utils.ResponseUtil;
import com.cz.model.vo.ResultVO;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
* @website ${apiAlias}管理控制器
* @author ${author}
* @date ${date}
**/
@RestController
@Api(tags = "${apiAlias}管理")
@RequestMapping("/${changeClassName}")
@Validated
public class ${className}Controller {

    @Autowired
    private I${className}Service ${changeClassName}Service;


    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("hasAuthority('${changeClassName}:list')")
    public void download(HttpServletResponse response, ${className}Query query) throws IOException {
        ${changeClassName}Service.download(${changeClassName}Service.queryAll(query), response);
    }

    @GetMapping
    @ApiOperation("查询${apiAlias}")
    @PreAuthorize("hasAuthority('${changeClassName}:list')")
    public ResultVO<IPage<${className}>> query(${className}Query query, Pageable pageable){
        return ResponseUtil.success(${changeClassName}Service.queryAll(query,pageable));
    }

    @PostMapping
    @Logger("新增${apiAlias}")
    @ApiOperation("新增${apiAlias}")
    @PreAuthorize("hasAuthority('${changeClassName}:add')")
    public ResultVO create(@Validated @RequestBody ${className}BO resources){
      ${changeClassName}Service.create(resources);
        return ResponseUtil.success();
    }

    @PutMapping
    @Logger("修改${apiAlias}")
    @ApiOperation("修改${apiAlias}")
    @PreAuthorize("hasAuthority('${changeClassName}:edit')")
    public ResultVO update(@Validated @RequestBody ${className}BO resources){
        ${changeClassName}Service.update(resources);
        return ResponseUtil.success();
    }

    @Logger("删除${apiAlias}")
    @ApiOperation("删除${apiAlias}")
    @PreAuthorize("hasAuthority('${changeClassName}:del')")
    @DeleteMapping
    public ResultVO<List<${className}>> delete(@Validated @RequestBody @NotEmpty(message = "id不能为空") List<Integer> ids) {
        return ResponseUtil.success(${changeClassName}Service.deleteAll(ids));
    }
}
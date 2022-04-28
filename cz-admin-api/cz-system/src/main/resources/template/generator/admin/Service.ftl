package ${package}.service;

import ${package}.entity.${className};
import ${package}.model.bo.${className}BO;
import ${package}.model.query.${className}Query;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseService;
/**
* @description 服务接口
* @author ${author}
* @date ${date}
**/
public interface I${className}Service extends MPJBaseService<${className}>  {

    /**
    * 查询数据分页
    * @param query 条件
    * @param pageable 分页参数
    * @return List<${className}
    */
    IPage<${className}> queryAll(${className}Query query, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param query 条件参数
    * @return List<${className}>
    */
    List<${className}> queryAll(${className}Query query);

    /**
     * 根据ID查询
     * @param ${pkChangeColName} ID
     * @return ${className}
     */
    ${className} findById(${pkColumnType} ${pkChangeColName});

    /**
    * 创建
    * @param resources /
    * @return ${className}
    */
    ${className} create(${className}BO resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(${className}BO resources);

    /**
    * 多选删除
    * @param ids /
    */
    List<${className}> deleteAll(List<Integer> ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<${className}> all, HttpServletResponse response) throws IOException;
}
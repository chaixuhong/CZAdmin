package com.cz.service;

import com.cz.entity.SysFiles;
import com.cz.model.query.SysFilesQuery;
import com.cz.model.vo.SysFilesVO;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
* @description 服务接口
* @author chaizi
* @date 2022-04-22
**/
public interface ISysFilesService extends MPJBaseService<SysFiles>  {

    /**
    * 查询数据分页
    * @param query 条件
    * @param pageable 分页参数
    * @return List<SysFiles
    */
    IPage<SysFiles> queryAll(SysFilesQuery query, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param query 条件参数
    * @return List<SysFiles>
    */
    List<SysFiles> queryAll(SysFilesQuery query);

    /**
     * 根据ID查询
     * @param fileId ID
     * @return SysFiles
     */
    SysFiles findById(Integer fileId);

    /**
    * 创建
    * @return SysFiles
    */
    SysFilesVO create(MultipartFile multipartFile, String pathName, String appCode, String fileName);


    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(List<Integer> ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<SysFiles> all, HttpServletResponse response) throws IOException;
}
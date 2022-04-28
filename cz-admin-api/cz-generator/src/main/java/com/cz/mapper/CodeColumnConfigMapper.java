package com.cz.mapper;

import com.cz.entity.CodeColumnConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 代码生成字段信息存储 Mapper 接口
 * </p>
 *
 * @author chaizi
 * @since 2022-04-11
 */
@Mapper
public interface CodeColumnConfigMapper extends BaseMapper<CodeColumnConfig> {


    List<CodeColumnConfig> findColumns(String tableName);
}

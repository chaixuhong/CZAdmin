package com.cz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表的数据信息
 *
 * @author Zheng Jie
 * @date 2019-01-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tables_info")
@ApiModel(value = "TablesInfo实体类", description = "数据库表信息")
public class TablesInfo {

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 创建日期
     */
    private String createTime;

    /**
     * 数据库引擎
     */
    private String engine;

    /**
     * 编码集
     */
    private String coding;

    /**
     * 备注
     */
    private String remark;


}

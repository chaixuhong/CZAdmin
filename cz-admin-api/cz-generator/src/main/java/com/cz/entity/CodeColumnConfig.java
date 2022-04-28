package com.cz.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * @author chaizi
 * @since 2022-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("code_column_config")
@ApiModel(value = "CodeColumnConfig实体类", description = "代码生成字段信息存储")
public class CodeColumnConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "column_id", type = IdType.AUTO)
    private Integer columnId;

    private String tableName;

    private String columnName;

    private String columnType;

    private String dictName;

    private String extra;

    private Boolean formShow;

    private String formType;

    private String keyType;

    private Boolean listShow;

    private Boolean notNull;

    private String queryType;

    private String remark;

}
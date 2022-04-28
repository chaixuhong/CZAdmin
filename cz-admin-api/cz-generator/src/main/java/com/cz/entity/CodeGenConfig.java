package com.cz.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 *
 * @author chaizi
 * @since 2022-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("code_gen_config")
@ApiModel(value = "CodeGenConfig实体类", description = "代码生成器配置")
@NoArgsConstructor
public class CodeGenConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "config_id", type = IdType.AUTO)
    private Integer configId;

    @ApiModelProperty(value = "表名")
    private String tableName;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "是否覆盖")
    private Boolean cover;

    @ApiModelProperty(value = "模块名称")
    private String moduleName;

    @ApiModelProperty(value = "至于哪个包下")
    private String pack;

    @ApiModelProperty(value = "前端代码生成的路径")
    private String path;

    @ApiModelProperty(value = "前端Api文件路径")
    private String apiPath;

    @ApiModelProperty(value = "表前缀")
    private String prefix;

    @ApiModelProperty(value = "接口名称")
    private String apiAlias;

    public CodeGenConfig(String tableName) {
        this.tableName = tableName;
    }
}
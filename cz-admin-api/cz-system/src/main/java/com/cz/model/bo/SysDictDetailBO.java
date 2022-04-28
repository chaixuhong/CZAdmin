package com.cz.model.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("字典接收类")
@Data
public class SysDictDetailBO implements Serializable {

    private static final long serialVersionUID = -6585681961704003668L;

    @ApiModelProperty(value = "ID")
    private Integer detailId;

    @ApiModelProperty(value = "dictId")
    private Integer dictId;

    @ApiModelProperty(value = "排序")
    private Integer dictSort;

    @ApiModelProperty(value = "字典标签")
    private String label;

    @ApiModelProperty(value = "字典值")
    private String value;
}

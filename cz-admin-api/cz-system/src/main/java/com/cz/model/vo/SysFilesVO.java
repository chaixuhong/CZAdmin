package com.cz.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cz.entity.SysFiles;
import java.util.List;
/**
* @description sys_files输出类-文件存储
* @author chaizi
* @date 2022-04-22
**/
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "sys_files实体类", description = "文件存储")
public class SysFilesVO implements Serializable {

    private static final long serialVersionUID = 1650594824091158L;

        @ApiModelProperty(value = "主键")
        private Integer fileId;
        @ApiModelProperty(value = "文件原名称")
        private String fileOriginalName;
        @ApiModelProperty(value = "文件路径")
        private String fileUrl;
        @ApiModelProperty(value = "压缩图地址")
        private String fileUrlThumb;
        @ApiModelProperty(value = "文件大小")
        private String fileSize;
        @ApiModelProperty(value = "文件类型")
        private String fileType;
        @ApiModelProperty(value = "创建时间")
        private LocalDateTime createTime;
        @ApiModelProperty(value = "应用名称")
        private String appCode;
        @ApiModelProperty(value = "操作人")
        private String createBy;

    /**
     * 实体类转换为输出类
     *
     * @param resource
     */
    public SysFilesVO(SysFiles resource){
        BeanUtils.copyProperties(resource,this);
    }

    /**
     * 实体类集合转换为输出类集合
     *
     * @param resource
     */
    public List<SysFilesVO> transform(List<SysFiles> resource){
        return JSONArray.parseArray(JSONObject.toJSONString(resource),SysFilesVO.class);
    }
}
package com.cz.service.impl;

import cn.hutool.core.img.ImgUtil;
import com.cz.config.FilesProperties;
import com.cz.entity.SysFiles;
import com.cz.enums.ResultEnum;
import com.cz.exception.GlobalException;
import com.cz.model.vo.SysFilesVO;
import com.cz.service.ISysFilesService;
import com.cz.model.query.SysFilesQuery;
import org.springframework.stereotype.Service;
import com.cz.mapper.SysFilesMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author chaizi
 * @description 服务实现
 * @date 2022-04-22
 **/
@Service
public class SysFilesServiceImpl extends MPJBaseServiceImpl<SysFilesMapper, SysFiles> implements ISysFilesService {

    @Resource
    private FilesProperties properties;

    @Override
    public IPage<SysFiles> queryAll(SysFilesQuery query, Pageable pageable) {
        PageUtil<SysFiles> pageUtil = new PageUtil<>(pageable);
        MPJQueryWrapper<SysFiles> wrapper = QueryUtil.buildWrapper(query, SysFiles.class);
        return this.page(pageUtil, wrapper);
    }

    @Override
    public List<SysFiles> queryAll(SysFilesQuery query) {
        MPJQueryWrapper<SysFiles> wrapper = QueryUtil.buildWrapper(query, SysFiles.class);
        return this.list(wrapper);
    }

    @Override
    @Transactional
    public SysFiles findById(Integer fileId) {
        return this.getById(fileId);
    }

    @Transactional
    @Override
    public SysFilesVO create(MultipartFile multipartFile, String pathName, String appCode, String fileName) {
        SysFiles sysFiles = new SysFiles();
        SysFilesVO sysFilesVO = null;
        //文件路径处理
        String filePath = FilesUtil.checkPathName(pathName, appCode); //用户自定义路径
        String deskPath = properties.getPath().getPath() + filePath; //磁盘路径
        // 获取上传的格式
        String fileType = FilesUtil.getFileType(FilesUtil.getExtensionName(multipartFile.getOriginalFilename()));
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(multipartFile.getBytes())) {
            File file = FilesUtil.upload(multipartFile, deskPath);
            if (Objects.isNull(file)) throw new BusinessException("文件上传失败");
            if (fileType.equals(FilesUtil.IMAGE)) {
                //图片保存缩略图
                File image = FilesUtil.uploadImageThumb(inputStream, multipartFile.getOriginalFilename(), deskPath);
                if (Objects.isNull(image)) throw new BusinessException("图片压缩失败");
                sysFiles.setFileUrlThumb(filePath + image.getName());
            }
            //保存数据库
            sysFiles.setFileName(fileName).setFileOriginalName(multipartFile.getOriginalFilename()).setFileUrl(filePath + file.getName())
                    .setFileSize(FilesUtil.getSize(multipartFile.getSize())).setFileType(fileType).setAppCode(appCode).setFileInputPath(pathName);
            this.save(sysFiles);
            sysFilesVO = new SysFilesVO(sysFiles);
        } catch (IOException e) {
            e.printStackTrace();
            throw new GlobalException(ResultEnum.FILE_UPLOAD_ERROR);
        }
        return sysFilesVO;
    }

    @Override
    public void deleteAll(List<Integer> ids) {
        for (Integer id : ids) {
            SysFiles sysFiles = this.findById(id);
            if(Objects.isNull(sysFiles)) continue;
            FilesUtil.del(properties.getPath().getPath() + sysFiles.getFileUrl());
            if(sysFiles.getFileType().equals(FilesUtil.IMAGE)){
                FilesUtil.del(properties.getPath().getPath() + sysFiles.getFileUrlThumb());
            }
            this.removeById(id);
        }
    }

    @Override
    public void download(List<SysFiles> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (SysFiles sysFiles : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("文件名称", sysFiles.getFileName());
            map.put("文件原名称", sysFiles.getFileOriginalName());
            map.put("文件路径", sysFiles.getFileUrl());
            map.put("压缩图地址", sysFiles.getFileUrlThumb());
            map.put("文件大小kb", sysFiles.getFileSize());
            map.put("文件类型", sysFiles.getFileType());
            map.put("创建日期", Objects.isNull(sysFiles.getCreateDate()) ? "-" : sysFiles.getCreateDate().toString());
            map.put("创建时间", Objects.isNull(sysFiles.getCreateTime()) ? "-" : sysFiles.getCreateTime().toString());
            map.put("应用名称", sysFiles.getAppCode());
            map.put("输入路径", sysFiles.getFileInputPath());
            map.put("操作人", sysFiles.getCreateBy());
            list.add(map);
        }
        FilesUtil.downloadExcel(list, response);
    }
}
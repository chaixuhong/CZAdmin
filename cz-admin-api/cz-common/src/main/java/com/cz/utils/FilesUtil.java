package com.cz.utils;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import com.cz.enums.ResultEnum;
import com.cz.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * File工具类，扩展 hutool 工具包
 *
 * @author chaixuhong
 * @date 2021年12月24日
 */
@Slf4j
public class FilesUtil extends FileUtil {

    /**
     * 系统临时目录
     * <br>
     * windows 包含路径分割符，但Linux 不包含,
     * 在windows \\==\ 前提下，
     * 为安全起见 同意拼装 路径分割符，
     * <pre>
     *       java.io.tmpdir
     *       windows : C:\Users/xxx\AppData\Local\Temp\
     *       linux: /temp
     * </pre>
     */
    public static final String SYS_TEM_DIR = System.getProperty("java.io.tmpdir") + File.separator;

    /**
     * 定义GB的计算常量
     */
    private static final int GB = 1024 * 1024 * 1024;
    /**
     * 定义MB的计算常量
     */
    private static final int MB = 1024 * 1024;
    /**
     * 定义KB的计算常量
     */
    private static final int KB = 1024;

    /**
     * 格式化小数
     */
    private static final DecimalFormat DF = new DecimalFormat("0.00");

    public static final String IMAGE = "图片";
    public static final String TXT = "文档";
    public static final String WORD = "word";
    public static final String EXCEL = "excel";
    public static final String PPT = "ppt";
    public static final String PDF = "pdf";
    public static final String MUSIC = "音乐";
    public static final String VIDEO = "视频";
    public static final String OTHER = "其他";

    /**
     * 获取文件扩展名，不带 .
     *
     * @param filename 文件名
     * @return 扩展名
     */
    public static String getExtensionName(String filename) {
        if (Strings.isNotBlank(filename)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * 获取文件名，不带扩展名
     *
     * @param filename 文件名
     * @return 文件名
     */
    public static String getFileNameNoEx(String filename) {
        if (Strings.isNotBlank(filename)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    /**
     * 生产新的文件名
     *
     * @param filename 旧文件名
     * @return 新文件名
     */
    public static String genNewFileName(String filename) {
        String timestamp = DateUtil.getCurrent(DateUtil.YMDHMS);
        if (Strings.isNotBlank(filename)) {
            if (filename.contains(".")) {
                String[] split = filename.split("\\.");
                return new StringBuilder(split[0]).append("-").append(timestamp).append(".").append(split[1]).toString();
            }
        }
        return filename;
    }

    /**
     * 生产新的文件名
     *
     * @param filename 旧文件名
     * @return 新文件名
     */
    public static String genImageThumbName(String filename) {
        String timestamp = DateUtil.getCurrent(DateUtil.YMDHMS);
        if (Strings.isNotBlank(filename)) {
            if (filename.contains(".")) {
                String[] split = filename.split("\\.");
                return new StringBuilder(split[0]).append("-").append(timestamp).append("-thumb").append(".").append(split[1]).toString();
            }
        }
        return filename;
    }

    /**
     * 文件大小转换
     *
     * @param size 文件大小
     * @return 返回带单位
     */
    public static String getSize(long size) {
        String resultSize;
        if (size / GB >= 1) {
            //如果当前Byte的值大于等于1GB
            resultSize = DF.format(size / (float) GB) + "GB   ";
        } else if (size / MB >= 1) {
            //如果当前Byte的值大于等于1MB
            resultSize = DF.format(size / (float) MB) + "MB   ";
        } else if (size / KB >= 1) {
            //如果当前Byte的值大于等于1KB
            resultSize = DF.format(size / (float) KB) + "KB   ";
        } else {
            resultSize = size + "B   ";
        }
        return resultSize;
    }

    /**
     * 保存文件
     *
     * @param file     文件名
     * @param filePath 保存路径
     * @return 文件对象
     */
    public static File upload(MultipartFile file, String filePath) {
        try {
            String fileName = genNewFileName(file.getOriginalFilename());
            String path = filePath + fileName;
            // getCanonicalFile 可解析正确各种路径
            File dest = new File(path).getCanonicalFile();
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                if (!dest.getParentFile().mkdirs()) {
                    System.out.println("was not successful.");
                }
            }
            // 文件写入
            file.transferTo(dest);
            return dest;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }


    /**
     * 保存图片缩略图
     *
     * @param inputStream      输入流
     * @param originalFilename 文件原名
     * @param filePath         保存路径
     * @return 文件对象
     */
    public static File uploadImageThumb(InputStream inputStream, String originalFilename, String filePath) {
        try {
            String fileName = genImageThumbName(originalFilename);
            String path = filePath + fileName;
            // getCanonicalFile 可解析正确各种路径
            File dest = new File(path).getCanonicalFile();
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                if (!dest.getParentFile().mkdirs()) {
                    System.out.println("was not successful.");
                }
            }
            // 文件写入
            ImgUtil.scale(inputStream, FilesUtil.getOutputStream(dest), 0.5f);
            return dest;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 导出excel
     *
     * @param list
     * @param response
     * @throws IOException
     */
    public static void downloadExcel(List<Map<String, Object>> list, HttpServletResponse response) throws IOException {
        String tempPath = SYS_TEM_DIR + IdUtil.fastSimpleUUID() + ".xlsx";
        File file = new File(tempPath);
        BigExcelWriter writer = ExcelUtil.getBigWriter(file);
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(list, true);
        SXSSFSheet sheet = (SXSSFSheet) writer.getSheet();
        //上面需要强转SXSSFSheet  不然没有trackAllColumnsForAutoSizing方法
        sheet.trackAllColumnsForAutoSizing();
        //列宽自适应
        writer.autoSizeColumnAll();
        //response为HttpServletResponse对象
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        response.setHeader("Content-Disposition", "attachment;filename=file.xlsx");
        ServletOutputStream out = response.getOutputStream();
        // 终止后删除临时文件
        file.deleteOnExit();
        writer.flush(out, true);
        //此处记得关闭输出Servlet流
        IoUtil.close(out);
    }

    /**
     * 获取文件类型
     *
     * @param type 文件类型
     * @return 返回类型
     */
    public static String getFileType(String type) {
        String documents = "txt";
        String word = "doc docx";
        String excel = "xlsx xls";
        String ppt = "ppt pps pptx";
        String pdf = "pdf";
        String music = "mp3 wav wma mpa ram ra aac aif m4a";
        String video = "avi mpg mpe mpeg asf wmv mov qt rm mp4 flv m4v webm ogv ogg";
        String image = "bmp dib pcp dif wmf gif jpg tif eps psd cdr iff tga pcd mpt png jpeg";
        if (image.contains(type)) {
            return IMAGE;
        } else if (documents.contains(type)) {
            return TXT;
        } else if (music.contains(type)) {
            return MUSIC;
        } else if (video.contains(type)) {
            return VIDEO;
        } else if (word.contains(type)) {
            return WORD;
        } else if (excel.contains(type)) {
            return EXCEL;
        } else if (ppt.contains(type)) {
            return PPT;
        } else if (pdf.contains(type)) {
            return PDF;
        } else {
            return OTHER;
        }
    }

    /**
     * 校验文件大小
     *
     * @param maxSize 文件最大长度
     * @param size    文件实际长度
     */
    public static void validateSize(long maxSize, long size) {
        // 1M
        int len = 1024 * 1024;
        if (size > (maxSize * len)) {
            throw new GlobalException(ResultEnum.FILE_PARAM_ERROR);
        }
    }

    /**
     * 判断两个文件是否相同
     *
     * @param file1 文件1
     * @param file2 文件2
     * @return
     */
    public static boolean validateContentEquals(File file1, File file2) {
        String file1Md5 = SecureUtil.md5(file1);
        String file2Md5 = SecureUtil.md5(file2);
        if (Strings.isNotBlank(file1Md5)) {
            return file1Md5.equals(file2Md5);
        }
        return false;
    }

    /**
     * 下载文件
     *
     * @param request      请求
     * @param response     响应
     * @param file         文件
     * @param deleteOnExit 是否删除
     */
    public static void downloadFile(HttpServletRequest request, HttpServletResponse response, File file, boolean deleteOnExit) {
        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/octet-stream");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
            IOUtils.copy(fis, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            IoUtil.close(fis);
            if (deleteOnExit) {
                file.deleteOnExit();
            }
        }
    }

    /**
     * 去除首尾的斜杠
     *
     * @param pathName
     * @param businessName
     * @return
     */
    public static String checkPathName(String pathName, String businessName) {
        if (Strings.isBlank(pathName)) {
            return businessName;
        }
        String prefix = pathName.substring(0, 1);
        String suffix = pathName.substring(pathName.length() - 1);
        if (prefix.equals("/")) {
            pathName = pathName.substring(1);
        }
        if (suffix.equals("/")) {
            pathName = pathName.substring(0, pathName.length() - 1);
        }
        return businessName + File.separator + pathName + File.separator;
    }
}

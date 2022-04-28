package com.cz.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * @author chaixuhong
 * @date 2019-09-01
 */
public class CodeGeneratedUtil {

    /**
     * 生成UUID
     *
     * @return
     */
    public static String genUUID() {
        return IdUtil.simpleUUID();
    }

    public static String genMD5(String content) {
        return SecureUtil.md5(content);
    }


    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void genCode() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        System.out.println("开始执行代码生产成");
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        String modules = "", modulesName = "";
        while (!modules.equals("y") && !modules.equals("n")) {
            modules = scanner("请输入是否是聚合项目，y/n：");
        }
        if (modules.equals("y")) {
            modulesName = "/" + scanner("请输入module名称：");
        }
        final String path = projectPath + "" + modulesName;
        gc.setOutputDir(projectPath + "" + modulesName + "/src/main/java");
        String pathName = projectPath + "" + modulesName + "/src/main/resources/application-local.yml";
        System.out.println("查找数据库配置文件路径为：" + pathName);
        gc.setAuthor(scanner("请输入作者"));
        gc.setOpen(false);
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();

        File file = new File(pathName);
        while (!file.exists()) {
            System.out.println("配置文件读取失败,确保是resources下的yml文件");
            file = new File(projectPath + "" + modulesName + "/src/main/resources/" + scanner("请输入配置文件名称"));
        }

        FileInputStream fileInputStream = null;
        InputStream in = null;
        System.out.println("============================");
        try {
            fileInputStream = new FileInputStream(file);
            in = new BufferedInputStream(fileInputStream);
            Yaml yaml = new Yaml();
            JSONObject jsonObject = yaml.loadAs(in, JSONObject.class).getJSONObject("spring").getJSONObject("datasource");
            String url = jsonObject.getString("url");
            if (Strings.isNullOrEmpty(url)) {
                dsc.setUrl(scanner("请输入数据库完整连接"));
            } else {
                System.out.println("读取到数据库连接：" + url);
                dsc.setUrl(url);
            }
            String driverClassName = jsonObject.getString("driver-class-name");
            if (Strings.isNullOrEmpty(driverClassName)) {
                dsc.setDriverName(scanner("请输入数据库驱动"));
            } else {
                System.out.println("读取到数据库驱动：" + driverClassName);
                dsc.setDriverName(driverClassName);
            }
            String userName = jsonObject.getString("username");
            if (Strings.isNullOrEmpty(userName)) {
                dsc.setUsername(scanner("请输入数据库用户名"));
            } else {
                System.out.println("读取到数据库用户名：" + userName);
                dsc.setUsername(userName);
            }
            String password = jsonObject.getString("password");
            if (Strings.isNullOrEmpty(password)) {
                dsc.setUsername(scanner("请输入数据库密码"));
            } else {
                System.out.println("读取到数据库密码：" + password);
                dsc.setPassword(password);
            }
            mpg.setDataSource(dsc);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("============================");
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("请输入包名，一级包名com已内置，请输入后续包名"));
        pc.setParent("com");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

//        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();

        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return path + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir(path + "/src/main/resources/mapper");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setChainModel(true);
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);
        mpg.execute();
    }

}

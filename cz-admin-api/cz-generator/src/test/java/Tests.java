import cn.hutool.core.lang.Dict;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.engine.TemplateFactory;
import com.cz.utils.DateUtil;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.Properties;

public class Tests {

    /**
     * 读取模板文件
     */
    @Test
    public void readFiles() {
        try{
            ClassPathResource classPathResource = new ClassPathResource("templates/Entity.ftl");
            InputStream inputStream =classPathResource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String str = null;
            while ((str = reader.readLine())!=null){
                System.out.println(str);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }


    @Test
    public void templates(){
        TemplateEngine templateEngine = TemplateFactory.create();
        templateEngine.init(new TemplateConfig("templates", TemplateConfig.ResourceMode.CLASSPATH));
        Template template = templateEngine.getTemplate("Entity.ftl");
        BufferedWriter writer= new BufferedWriter(new PrintWriter(System.out));
        Map<String,Object> genMap = Maps.newHashMap();
        genMap.put("package", "chaixuhong");
        genMap.put("tableName", "aaaaaaaaaa");
        // 存在 Timestamp 字段
        genMap.put("hasTimestamp", false);
        // 查询类中存在 Timestamp 字段
        genMap.put("queryHasTimestamp", false);
        // 存在 BigDecimal 字段
        genMap.put("hasBigDecimal", false);
        // 查询类中存在 BigDecimal 字段
        genMap.put("queryHasBigDecimal", false);
        // 是否需要创建查询
        genMap.put("hasQuery", false);
        // 自增主键
        genMap.put("auto", false);
        // 存在字典
        genMap.put("hasDict", false);
        // 存在日期注解
        genMap.put("hasDateAnnotation", false);
        // 作者
        genMap.put("author", "as大萨达");
        // 创建日期
        genMap.put("date", LocalDate.now());
        // 表名
        genMap.put("tableName", "tablke");
        template.render(genMap,writer);
    }

    @Test
    public void reader() throws IOException{
        Properties properties = new Properties();

        InputStream inputStream = Tests.class.getClassLoader().getResourceAsStream("generator.properties");
        properties.load(inputStream);
        properties.list(System.out);
        System.out.println("``````````````");
        System.out.println(properties.getProperty("longtext"));
    }

    @Test
    public void testMath(){
        String a = "czAdmin:login:user:94c7cefce8f6fe6fba2cb1238cf31bc6";
        String[] split = a.split(":");
        System.out.println(split[split.length-1]);
    }
}

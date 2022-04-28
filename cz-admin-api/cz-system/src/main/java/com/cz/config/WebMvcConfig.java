package com.cz.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private FilesProperties properties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        FilesProperties.ElPath path = properties.getPath();
        String avatarUtl = "file:" + path.getAvatar().replace("\\", "/");
        String pathUtl = "file:" + path.getPath().replace("\\", "/");
        registry.addResourceHandler("/avatar/**").addResourceLocations(avatarUtl).setCachePeriod(0);
        registry.addResourceHandler("/file/**").addResourceLocations(pathUtl).setCachePeriod(0);
    }
    //解决跨域问题，还需要在security配置中增加此过滤器，放在自定义过滤器之前
//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        //1,允许任何来源
//        corsConfiguration.setAllowedOriginPatterns(Collections.singletonList("*"));
//        //2,允许任何请求头
//        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
//        //3,允许任何方法
//        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
//        //4,允许凭证
//        corsConfiguration.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfiguration);
//        return new CorsFilter(source);
//    }

    /**
     * 消息内容转换配置
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //StringHttpMessageConverter默认使用的字符集是ISO-8859-1,改为输出的JSON字符串为UTF-8字符集
        converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
        //添加fastjson消息转换器
        converters.add(fastJsonHttpMessageConverters());
    }

    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverters() {
        //1、定义一个convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        //2、添加fastjson的配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();

        SerializerFeature[] serializerFeatures = new SerializerFeature[]{
                //    是否输出为null的字段,若为null 则显示该字段
                SerializerFeature.WriteMapNullValue,
                //    数值字段如果为null，则输出为0
//                SerializerFeature.WriteNullNumberAsZero,
                //     List字段如果为null,输出为[],而非null
                SerializerFeature.WriteNullListAsEmpty,
                //    字符类型字段如果为null,输出为"",而非null
                SerializerFeature.WriteNullStringAsEmpty,
                //    Boolean字段如果为null,输出为false,而非null
                SerializerFeature.WriteNullBooleanAsFalse,
                //    Date的日期转换器
                SerializerFeature.WriteDateUseDateFormat,
                //    循环引用（如果不配置有可能会进入死循环）
                SerializerFeature.DisableCircularReferenceDetect,
                //    格式化JSON
                SerializerFeature.PrettyFormat
        };

        fastJsonConfig.setSerializerFeatures(serializerFeatures);
        fastJsonConfig.setCharset(StandardCharsets.UTF_8);

        //3.解决乱码问题。定义响应的MIME类型，设置响应的content-type为application/json;charset=UTF-8
        List<MediaType> fastMediaType = new ArrayList<>();
        fastMediaType.add(MediaType.APPLICATION_JSON);

        //4.converter消息转换器添加配置信息
        fastConverter.setSupportedMediaTypes(fastMediaType);

        //5、在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);

        return fastConverter;
    }

}

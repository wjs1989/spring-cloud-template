package com.wjs.produce.config;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.util.ReflectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wenjs
 */
@Configuration
public class FastJsonConfiguration {
   @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        //1、创建FastJson信息转换对象
        FastJsonHttpMessageConverter fastConverter=new FastJsonHttpMessageConverter();
        //2、创建FastJsonConfig对象并设定序列化规则  序列化规则详见SerializerFeature类中，后面会讲
        FastJsonConfig fastJsonConfig= new FastJsonConfig();
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullListAsEmpty);
        
       //自定义时间格式  时间字段上标记 @JSONField
       fastJsonConfig.setSerializeFilters(new ValueFilter() {
           // Map<class,Map<filed,dateFormat>>
           Map<Class, Map<String, String>> dateFormatCache = new ConcurrentHashMap<>();

           @Override
           public Object process(Object obj, String key, Object value) {

               if (value instanceof Date) {
                   Class<?> objClass = obj.getClass();
                   if (dateFormatCache.get(objClass) == null) {
                       dateFormatCache.putIfAbsent(objClass, new HashMap<>());
                       Map<String, String> newFormatMap = dateFormatCache.get(objClass);
                       ReflectionUtils.doWithFields(obj.getClass(), (field) -> {
                           if (field.getType() == Date.class) {
                               JSONField annotation = field.getAnnotation(JSONField.class);
                               if (annotation != null) {
                                   String format = annotation.format();
                                   if (StringUtils.isNotBlank(format)) {
                                       newFormatMap.putIfAbsent(field.getName(), format);
                                   }
                               }
                           }
                       });
                   }

                   Map<String, String> dateFormatMap = dateFormatCache.get(objClass);
                   if (!dateFormatMap.isEmpty()) {
                       String format = dateFormatMap.get(key);
                       if (StringUtils.isNotBlank(format)) {
                           SimpleDateFormat sdf = new SimpleDateFormat(format);
                           return sdf.format(value);
                       }
                   }
               }

               return value;
           }
       });
        //3、中文乱码解决方案
        List<MediaType> fastMedisTypes = new ArrayList<MediaType>();
        //设定Json格式且编码为utf-8
        fastMedisTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMedisTypes);
        //4、将转换规则应用于转换对象
        fastConverter.setFastJsonConfig(fastJsonConfig);

       //long返回给前端转换为String
       SerializeConfig serializeConfig = fastJsonConfig.getSerializeConfig();
       serializeConfig.put(Long.class, ToStringSerializer.instance);
       serializeConfig.put(Long.TYPE, ToStringSerializer.instance);
       fastConverter.setFastJsonConfig(fastJsonConfig);

       return new HttpMessageConverters(fastConverter);
    }
}

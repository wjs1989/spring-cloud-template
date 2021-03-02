package com.wjs.seckill.config;//package com.cause.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
//import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wenjs
 */
@Configuration
public class MybatisPlusConfig implements MetaObjectHandler {
    /**
     * 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    /**
     * 逻辑删除插件
     * @return
     */
//    @Bean
//    public ISqlInjector sqlInjector(){
//        return new LogicSqlInjector();
//    }

    /**
     * 乐观锁
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }

    /**
     * 插入的时候自动填充 创建日期和更新日期
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
       // this.setFieldValByName("createdTime",new Date(),metaObject);
     //   this.setFieldValByName("updatedTime",new Date(),metaObject);
    }

    /**
     * 修改的时候自动填充更新日期
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
       // this.setFieldValByName("updatedTime",new Date(),metaObject);
    }


}

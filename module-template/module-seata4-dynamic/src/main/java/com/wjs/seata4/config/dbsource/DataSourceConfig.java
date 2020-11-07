package com.wjs.seata4.config.dbsource;

import io.seata.rm.datasource.DataSourceProxy;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(basePackages = "com.wjs.seata4.*.mapper", sqlSessionFactoryRef = "autoSqlSessionFactory", sqlSessionTemplateRef = "autoSqlSessionTemplate")
public class DataSourceConfig {
    static final String MAPPER_LOCATION = "classpath:/mapper/auto/*Mapper.xml";

    @Bean(name = "dataSource1")
    @ConfigurationProperties("spring.datasource.goods")
    public DataSource dataSource1(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dataSource2")
    @ConfigurationProperties("spring.datasource.member")
    public DataSource dataSource2(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dynamicDataSource(){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> map = new HashMap<>();

        map.put("goods", new DataSourceProxy(dataSource1()));
        map.put("member", new DataSourceProxy(dataSource2()));

        dynamicDataSource.setTargetDataSources(map);
        dynamicDataSource.setDefaultTargetDataSource(map.get("goods"));

        return dynamicDataSource;
    }

    @Bean(name = "autoSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dynamicDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(DataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    @Bean(name = "autoSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("autoSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }
//    @Bean
//    JdbcTemplate jdbcTemplate(){
//        return new JdbcTemplate();
//    }
}

package com.wjs.seata.config.dbsource;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import io.seata.rm.datasource.DataSourceProxy;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author wenjs
 * @Description:
 * @date 2020/7/27 18:50
 */
@Configuration
@MapperScan(basePackages = "com.wjs.seata.member.mapper",sqlSessionFactoryRef = "memberSqlSessionFactory",sqlSessionTemplateRef="memberSqlSessionTemplate")
public class MemberDataSourceConfigurer {
    @Bean(name = "memberDataSource")
    @ConfigurationProperties("spring.datasource.member")
    public DataSource masterDataSource(){
        //return DataSourceBuilder.create().build();
        return new DruidDataSource();
    }

    @Bean(name = "memberDataSourceProxy")
    public DataSourceProxy payDataSourceProxy(@Qualifier("memberDataSource") DataSource dataSource) {
        return new DataSourceProxy(dataSource);
    }

    @Bean(name = "memberSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("memberDataSourceProxy") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPlugins(new OptimisticLockerInterceptor());
         //sessionFactoryBean.setMapperLocations( new PathMatchingResourcePatternResolver().getResources("classpath:/member/Mapper/*Mapper.xml") );

        return sessionFactoryBean.getObject();
    }

    @Bean(name = "memberSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("memberSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);

        return sqlSessionTemplate;
    }
}

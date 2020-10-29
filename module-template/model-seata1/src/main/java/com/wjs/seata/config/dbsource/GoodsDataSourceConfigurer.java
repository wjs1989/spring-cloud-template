package com.wjs.seata.config.dbsource;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import io.seata.rm.datasource.DataSourceProxy;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.ObjectProvider;
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
@MapperScan(basePackages = "com.wjs.seata.goods.mapper", sqlSessionFactoryRef = "goodsSqlSessionFactory", sqlSessionTemplateRef = "goodsSqlSessionTemplate")
public class GoodsDataSourceConfigurer {
    @Bean(name = "goodsDataSource")
    @ConfigurationProperties("spring.datasource.goods")
    public DataSource masterDataSource() {
        //return DataSourceBuilder.create().build();
        return new DruidDataSource();
    }

    @Bean(name = "goodsDataSourceProxy")
    public DataSourceProxy payDataSourceProxy(@Qualifier("goodsDataSource") DataSource dataSource) {
        return new DataSourceProxy(dataSource);
    }

    @Bean(name = "goodsSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("goodsDataSourceProxy") DataSource dataSource,
            ObjectProvider<Interceptor[]> interceptorsProvider) throws Exception {
         MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPlugins((Interceptor[])interceptorsProvider.getIfAvailable());

        //sessionFactoryBean.setMapperLocations( new PathMatchingResourcePatternResolver().getResources("classpath:/goods/Mapper/*Mapper.xml") );

//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        try {
//            Resource[] mapperLocaltions = resolver.getResources(mybatisProperties.getMapperLocations()[0]);
//            sessionFactoryBean.setMapperLocations(mapperLocaltions);
//
//            if (StringUtils.isNotBlank(mybatisProperties.getConfigLocation())) {
//                Resource[] resources = resolver.getResources(mybatisProperties.getConfigLocation());
//                sessionFactoryBean.setConfigLocation(resources[0]);
//                sessionFactoryBean.setPlugins( );
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return sessionFactoryBean.getObject();
    }

    @Bean(name = "goodsSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("goodsSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);

        return sqlSessionTemplate;
    }
}

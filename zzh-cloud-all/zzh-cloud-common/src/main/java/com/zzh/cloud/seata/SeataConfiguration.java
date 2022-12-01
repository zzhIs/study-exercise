package com.zzh.cloud.seata;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @descripti seata数据库配置
 * @author: zhangzihao
 * @date: 01/12/2022
 **/
@Configuration
@MapperScan("com.zzh.cloud.mapper")
public class SeataConfiguration {



        /**
         * 从配置文件获取属性构造datasource，注意前缀，这里用的是druid，根据自己情况配置
         * 原生datasource前缀取"spring.datasource"
         *
         * @return
         */
        @Bean
        @ConfigurationProperties(prefix = "spring.datasource")
        public DataSource druidDataSource(){
            DruidDataSource druidDataSource = new DruidDataSource();
            return druidDataSource;
        }

        /**
         * 构造datasource代理对象，替换原来的datasource
         * @param dataSource
         * @return
         */
        @Primary
        @Bean("dataSource")
        public DataSourceProxy dataSourceProxy(DataSource dataSource){
            return new DataSourceProxy(dataSource);
        }


        @Value("${mybatis.mapper-locations}")
        private String mapperScanPath;

        @Bean(name = "sqlSessionFactory")
        public SqlSessionFactory sqlSessionFactoryBean(DataSourceProxy dataSourceProxy) throws Exception{
            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
            //设置代理数据源
            factoryBean.setDataSource(dataSourceProxy);
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            factoryBean.setMapperLocations(resolver.getResources(mapperScanPath));

            org.apache.ibatis.session.Configuration configuration=new org.apache.ibatis.session.Configuration();
            //使用jdbc的getGeneratedKeys获取数据库自增主键值
            configuration.setUseGeneratedKeys(true);
            //使用列别名替换列名
            configuration.setUseColumnLabel(true);
            //自动使用驼峰命名属性映射字段，如userId ---> user_id
            configuration.setMapUnderscoreToCamelCase(true);
            factoryBean.setConfiguration(configuration);
            return factoryBean.getObject();
        }


}

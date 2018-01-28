package com.kinoymir.chat.config;


import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ConditionalOnClass(com.alibaba.druid.pool.DruidDataSource.class)
@ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.alibaba.druid.pool.DruidDataSource", matchIfMissing = true)
public class DruidConfig {
    @SuppressWarnings("unchecked")
    private <T> T createDataSource(DataSourceProperties properties, Class<? extends DataSource> type) {
        return (T) properties.initializeDataSourceBuilder().type(type).build();
    }

    /**
     * @param properties 读入的配置
     * @return DruidDataSource
     * @see org.springframework.boot.autoconfigure.jdbc.DataSourceConfiguration.Tomcat
     * 仿写的你可以去了解
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid")
    public com.alibaba.druid.pool.DruidDataSource dataSource(DataSourceProperties properties) {

        com.alibaba.druid.pool.DruidDataSource dataSource = createDataSource(properties,
                com.alibaba.druid.pool.DruidDataSource.class);

        DatabaseDriver databaseDriver = DatabaseDriver.fromJdbcUrl(properties.determineUrl());

        String validationQuery = databaseDriver.getValidationQuery();
        if (validationQuery != null) {
            dataSource.setTestOnBorrow(true);
            dataSource.setValidationQuery(validationQuery);
        }

        return dataSource;
    }

    /**
     * 注册一个StatViewServlet
     */
    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        // org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),
                "/druid/*");

        // 添加初始化参数：initParams
        // 登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", "druid");
        servletRegistrationBean.addInitParameter("loginPassword", "druid");
        // 是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable", "false");// 禁用HTML页面上的“Reset
        // All”功能
        return servletRegistrationBean;
    }

    /**
     * 注册一个：filterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean druidStatFilter() {

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        filterRegistrationBean.setName("druidWebStatFilter");
        // 添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        // 添加忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}

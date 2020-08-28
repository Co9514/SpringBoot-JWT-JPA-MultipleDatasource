package com.example.demo.config.db;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
        (
                basePackages = "com.example.demo.auth.repository",
                entityManagerFactoryRef = "pgsqlFactoryBean"
        )
public class JdbcPgsqlConfig
{
    @Bean(name = "pgsqlDataSource")
    @ConfigurationProperties(prefix = "bar.datasource")
    public DataSource getDataSource()
    {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "pgsqlFactoryBean")
    public LocalContainerEntityManagerFactoryBean getFactoryBean(EntityManagerFactoryBuilder builder,@Qualifier("pgsqlDataSource") DataSource dataSource )
    {
        return builder
                .dataSource(dataSource)
                .packages("com.example.demo.auth.domain")
                .build();
    }

    @Bean(name = "pgsqlTransactionManager")
    PlatformTransactionManager getTransactionManager(@Qualifier("mysqlFactoryBean") EntityManagerFactory builder)
    {
        return new JpaTransactionManager(builder);
    }
}
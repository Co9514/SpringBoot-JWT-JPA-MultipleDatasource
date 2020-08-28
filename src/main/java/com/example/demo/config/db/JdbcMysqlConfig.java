package com.example.demo.config.db;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
                basePackages = "com.example.demo.account.repository",
                entityManagerFactoryRef = "mysqlFactoryBean"
)
public class JdbcMysqlConfig
{
    @Primary
    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getDataSource()
    {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "mysqlFactoryBean")
    public LocalContainerEntityManagerFactoryBean getFactoryBean(EntityManagerFactoryBuilder builder,@Qualifier("mysqlDataSource") DataSource dataSource )
    {
        return builder
                .dataSource(dataSource)
                .packages("com.example.demo.account.domain")
                .build();
    }

    @Primary
    @Bean(name = "mysqlTransactionManager")
    PlatformTransactionManager getTransactionManager(@Qualifier("mysqlFactoryBean") EntityManagerFactory builder)
    {
        return new JpaTransactionManager(builder);
    }
}

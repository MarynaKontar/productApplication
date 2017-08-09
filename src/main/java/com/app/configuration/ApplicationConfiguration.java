package com.app.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


/**
 * Main system application configuration(Spring).
 */
@Configuration
@ComponentScan(basePackages = {"com.app.dao", "com.app.service"})
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
// To enable JPA data support annotation @EnableJpaRepositories is used!
// Parameters inside indicate packages where DAOs is located.
@EnableJpaRepositories("com.app.dao")
public class ApplicationConfiguration {

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Value("${db.driver}")
    private String driverClass;

    @Value("${db.dialect}")
    private String hibernateDialect;

    //указываю путь в classpath (все,что находится в папке resources - в classpath) к sql для создания таблиц, которые будут создаваться в методе dataSourceInitializer
    @Value("classpath:tables_creation.sql")
    private Resource scriptResource;

    // Initial step everytime is create new datasource for database connection
    @Bean(destroyMethod = "close")
//специально выбераем класс BasicDataSource (а не интерфейс DataSource), п.ч. у него есть метод close. И прописываем destroyMethod, чтобы закрывало BasicDataSource
    public BasicDataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    /**
     * Create now bean as wrapper over {@link javax.persistence.EntityManagerFactory}
     * Используем не интерфейс (EntityManagerFactory), а его реализацию (LocalContainerEntityManagerFactoryBean), т.к.
     * в интерфейсе нет методов, которые здесь используем (setDataSource, setPackagesToScan, ...)
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        /// Create vendor adapter for JPA. In current case Hibernate.
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setDatabasePlatform(hibernateDialect);

        // Create wrapper bean.
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        // Set datasource to entity manager factory. This will allow it to connect to database.
        bean.setDataSource(dataSource);
        // Set to wrapper specific vendor properties.
        bean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        // Set packages where our JPA entities are located.
        bean.setPackagesToScan("com.app.entities");
        return bean;
    }

    // After EntityManagerFactory is set we have to tell Spring how to handle transactions
    // by creation of PlatformTransactionManager
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    // This is bean responsible to population of HSQL Db on start.
    //создаем таблиці в бд с помощью sql, написанного в scriptResource, т.е. в файле tables_creation.sql
    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(scriptResource);

        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(populator);
        return initializer;
    }
}

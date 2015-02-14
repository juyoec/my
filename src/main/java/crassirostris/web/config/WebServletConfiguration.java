package crassirostris.web.config;

import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;
import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by crassirostris on 15. 1. 31..
 */
@Configuration
@EnableAutoConfiguration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan("crassirostris.mybookmark")
@EnableJpaRepositories("com.acme.repositories")
public class WebServletConfiguration {

    @Bean
    public DataSource getDataSource() {
        BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setPoolName("mybookmark");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/collectors?useUnicode=true&characterEncoding=utf8&useServerPrepStmts=true");
        dataSource.setConnectionTestStatement("select * from collector_codes");

        dataSource.setDisableConnectionTracking(true);
        dataSource.setConnectionTimeoutInMs(3000);
        dataSource.setUsername("winter");
        dataSource.setPassword("cndnj!@#");
        dataSource.setPartitionCount(4);
        dataSource.setMaxConnectionsPerPartition(6);
        dataSource.setMinConnectionsPerPartition(2);
        dataSource.setStatisticsEnabled(true);
        dataSource.setIdleConnectionTestPeriodInMinutes(1);
        dataSource.setIdleMaxAgeInMinutes(5);
        dataSource.setLogStatementsEnabled(true);

        return dataSource;
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                                Environment env) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("crassirostris.mybookmark");

        Properties jpaProperties = new Properties();

        //Configures the used database dialect. This allows Hibernate to create SQL
        //that is optimized for the used database.
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

        //Specifies the action that is invoked to the database when the Hibernate
        //SessionFactory is created or closed.
        jpaProperties.put("hibernate.hbm2ddl.auto","create-drop");

        //Configures the naming strategy that is used when Hibernate creates
        //new database objects and schema elements
        jpaProperties.put("hibernate.ejb.naming_strategy","org.hibernate.cfg.ImprovedNamingStrategy");

        //If the value of this property is true, Hibernate writes all SQL
        //statements to the console.
        jpaProperties.put("hibernate.show_sql",false);

        //If the value of this property is true, Hibernate will format the SQL
        //that is written to the console.
        jpaProperties.put("hibernate.format_sql",true);

        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }
    @Bean
    public HandlebarsViewResolver handlebarsViewResolver() {
        HandlebarsViewResolver viewResolver = new HandlebarsViewResolver();
        viewResolver.setOrder(1);
        viewResolver.setFailOnMissingFile(false);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".hbs");
        return viewResolver;
    }

    @Bean
    public ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver() {
        ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver = new ExceptionHandlerExceptionResolver();
        exceptionHandlerExceptionResolver.setOrder(1);
        return exceptionHandlerExceptionResolver;
    }

    @Bean
    public BindingResultAspect bindingResultAspect() {
        return new BindingResultAspect();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebServletConfiguration.class, args);
    }

}

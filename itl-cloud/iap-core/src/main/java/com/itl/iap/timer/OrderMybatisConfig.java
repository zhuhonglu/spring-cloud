package com.itl.iap.timer;

import com.itl.iap.timer.util.MyLogger;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@MapperScan(value="com.itl.iap.timer.mapper", sqlSessionTemplateRef="sqlSession", sqlSessionFactoryRef="sqlSessionFactory")
public class OrderMybatisConfig {

    /**
     * 记录日志对象
     */
    private static final MyLogger log = new MyLogger(
    		OrderMybatisConfig.class);

    @Value("${spring.datasource.type}")  
    private Class<? extends DataSource> dataSourceType;  
  
    @Bean(name="dataSource", destroyMethod = "close", initMethod="init")  
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    public DataSource dataSource() {  
        log.info("-------------------- goodsDataSource init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();  
    }

    @Bean(name="sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {  
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();  
        sqlSessionFactoryBean.setDataSource(dataSource());  
        sqlSessionFactoryBean.setTypeAliasesPackage("com.itl.iap.timer.dao");
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();  
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/*.xml"));
        // 是否启用下划线与驼峰式命名规则的映射(如first_name => firstName)
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
//        addPlugins(sqlSessionFactoryBean);
        return sqlSessionFactoryBean.getObject();
    }

    /**  
     * 配置事务管理器  
     */  
    @Bean(name = "transactionManager")
    @Primary  
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) throws Exception {  
        return new DataSourceTransactionManager(dataSource);  
    }

    @Bean(name = "sqlSession")
    @Primary
    public SqlSessionTemplate sqlSession() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }


    /**
     *
     * @Title: addPlugins
     * @Description: 添加分页插件
     * @param @param sessionFactory    设定文件
     * @return void    返回类型
     * @throws
     */
//    private void addPlugins(SqlSessionFactoryBean sessionFactory) {
//        //分页插件
//        PageInterceptor pageInterceptor = new PageInterceptor();
//        Properties properties = new Properties();
//        // 分页合理化参数，默认值为false。当该参数设置为 true 时，pageNum<=0 时会查询第一页， pageNum>pages（超过总数时），会查询最后一页。默认false 时，直接根据参数进行查询。
//        properties.setProperty("reasonable", "true");
//        // 支持通过 Mapper 接口参数来传递分页参数，默认值false，分页插件会从查询方法的参数值中，自动根据上面 params 配置的字段中取值，查找到合适的值时就会自动分页。 使用方法可以参考测试代码中的 com.github.pagehelper.test.basic 包下的 ArgumentsMapTest 和 ArgumentsObjTest。
//        properties.setProperty("supportMethodsArguments", "false");
//        properties.setProperty("params", "count=countSql");
//        // 默认值为false，该参数对使用 RowBounds 作为分页参数时有效。 当该参数设置为true时，使用 RowBounds 分页会进行 count 查询。
//        properties.setProperty("rowBoundsWithCount", "true");
//        // 分页插件会自动检测当前的数据库链接，自动选择合适的分页方式。 你可以配置helperDialect属性来指定分页插件使用哪种方言
//        properties.setProperty("helperDialect", "mysql");
//        pageInterceptor.setProperties(properties);
//
//        //添加插件
//        sessionFactory.setPlugins(new Interceptor[]{pageInterceptor});
//    }
}  
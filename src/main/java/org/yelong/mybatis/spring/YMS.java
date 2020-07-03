/**
 * 
 */
package org.yelong.mybatis.spring;

import org.yelong.core.model.resolve.AnnotationModelResolver;

/**
 * 
 * <pre>
	application.xml配置：
	<!-- ApplicationContext装饰器，包装了ApplicationContext，可以在没有被Spring管理的类中使用此类来获取Spring容器中的Bean -->
	<bean class="org.yelong.support.spring.ApplicationContextDecorator"></bean>

	单数据源配置：
	<!-- 配置基础的数据库操作接口。 该接口是框架中执行sql的对象。 -->
	<bean id="mysqlDataBaseDataOperation"
		class=
"org.yelong.support.orm.mybaits.mapper.DefaultMyBatisBaseDataBaseOperation">
		<constructor-arg index="0" ref=
"mysqlSqlSession" /><!-- 集成Mybatis。这里使用SqlSession的bean -->
	</bean>
	
	<!-- 配置Model配置。该类用来对Model进行管理。如：解析Model、生成Model的Sql语句 -->
	<bean id="mysqlModelConfiguration"
		class="org.yelong.mybatis.spring.ModelConfigurationAutoConfigure">
		<property name="dialectName" value="mysql"></property>
	</bean>

	<!-- 配置ModelService。对Model进行增删改查操作的接口。 -->
	<bean id="mysqlModelService"
		class="org.yelong.mybatis.spring.MyBatisModelService">
		<constructor-arg index="0" ref=
"mysqlModelConfiguration"></constructor-arg>
		<constructor-arg index="1"
			ref="mysqlDataBaseDataOperation"></constructor-arg>
	</bean>

	通过配置上面3个对象即可使用ModelService对Model进行数据库操作。
	
	Model：
	一个Model对应一个数据库表。框架中默认的Model解析器：{@link AnnotationModelResolver}该解析器使用注解的方式来对Model进行解析。
	
	多数据库源：
	多数据与配置mybatis的多数据源相同。每一套数据源配置一组上面的配置即可。
	
	以下为Oracle的配置
	
	<bean id="oracleDataBaseDataOperation"
		class=
"org.yelong.support.orm.mybaits.mapper.DefaultMyBatisBaseDataBaseOperation">
		<constructor-arg index="0" ref=
"oracleSqlSession" /><!-- 集成Mybatis。这里使用SqlSession的bean -->
	</bean>

	<bean id="oracleModelConfiguration"
		class=
"org.yelong.mybatis.spring.ModelConfigurationAutoConfigure"><!-- 可以注入自定义的一些对于Model管理的类来实现自定义管理Model -->
		<property name="dialectName" value="oracle"></property>
	</bean>

	<bean id="oracleModelService"
		class=
"org.yelong.mybatis.spring.MyBatisModelService"><!-- 自定义实现MyBatisModelService 实现类可以自定义实现Model的操作前后执行一些操作 -->
		<constructor-arg index="0" ref=
"oracleModelConfiguration"></constructor-arg>
		<constructor-arg index="1"
			ref="oracleDataBaseDataOperation"></constructor-arg>
	</bean>
	
	多数据源还是使用多个SqlSession来完成的操作
 * </pre>
 * 
 * @author PengFei
 * @since 1.0.0
 */
public final class YMS {

	private YMS() {
	}

	public static final String VERSION = "1.3.0";

}

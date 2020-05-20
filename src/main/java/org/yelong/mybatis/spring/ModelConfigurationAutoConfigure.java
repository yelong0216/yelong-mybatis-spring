/**
 * 
 */
package org.yelong.mybatis.spring;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.yelong.core.jdbc.dialect.Dialect;
import org.yelong.core.jdbc.dialect.Dialects;
import org.yelong.core.jdbc.sql.condition.support.DefaultConditionResolver;
import org.yelong.core.model.ModelConfiguration;
import org.yelong.core.model.resolve.AnnotationModelResolver;
import org.yelong.core.model.resolve.ModelAndTableManager;
import org.yelong.core.model.sql.DefaultModelSqlFragmentFactory;
import org.yelong.core.model.sql.DefaultSqlModelResolver;

/**
 * ModelConfiguration的自动配置实现
 * @author PengFei
 * @since 1.0.0
 */
public class ModelConfigurationAutoConfigure extends ModelConfiguration implements InitializingBean{

	/**
	 * 方言名字
	 * {@link Dialects}
	 */
	private String dialectName;

	/**
	 * 方言类名（自己实现的方言）
	 */
	private String dialectClassName;

	public ModelConfigurationAutoConfigure() {
		super(null, null, null,null, null);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		//不存在方言时，根据方言名称、类名的顺序进行初始化。
		if( null == getDialect() ) {
			if(StringUtils.isNotBlank(dialectName)) {
				setDialect(Dialects.valueOfByName(dialectName).getDialect());
			} else if(StringUtils.isNotBlank(dialectClassName)) {
				setDialect((Dialect)ClassUtils.getClass(dialectClassName).newInstance());
			}
		}
		Assert.notNull(getDialect(), "Property 'dialect' is required");
		if( null == getModelAndTableManager() ) {
			setModelAndTableManager(new ModelAndTableManager(new AnnotationModelResolver(getModelProperties())));
		}
		if( null == getModelSqlFragmentFactory() ) {
			setModelSqlFragmentFactory(new DefaultModelSqlFragmentFactory(getDialect(), getModelAndTableManager()));
		}
		if( null == getConditionResolver() ) {
			setConditionResolver(new DefaultConditionResolver(getModelSqlFragmentFactory()));
		}
		if( null == getSqlModelResolver() ) {
			setSqlModelResolver(new DefaultSqlModelResolver(getModelAndTableManager(), getConditionResolver()));
		}
	}

	public String getDialectName() {
		return dialectName;
	}

	public void setDialectName(String dialectName) {
		this.dialectName = dialectName;
	}

	public String getDialectClassName() {
		return dialectClassName;
	}

	public void setDialectClassName(String dialectClassName) {
		this.dialectClassName = dialectClassName;
	}
	
}
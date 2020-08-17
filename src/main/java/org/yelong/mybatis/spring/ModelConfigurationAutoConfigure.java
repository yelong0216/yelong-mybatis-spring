package org.yelong.mybatis.spring;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.yelong.core.jdbc.dialect.Dialect;
import org.yelong.core.jdbc.dialect.Dialects;
import org.yelong.core.model.ModelConfiguration;
import org.yelong.core.model.ModelConfigurationBuilder;

public class ModelConfigurationAutoConfigure extends ModelConfiguration implements InitializingBean {

	/**
	 * 方言名字
	 * 
	 * {@link Dialects}
	 */
	private String dialectName;

	/**
	 * 方言类名（自己实现的方言）
	 */
	private String dialectClassName;

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

	@Override
	public void afterPropertiesSet() throws Exception {
		// 不存在方言时，根据方言名称、类名的顺序进行初始化。
		if (null == getDialect()) {
			if (StringUtils.isNotBlank(dialectName)) {
				this.dialect = Dialects.valueOfByName(dialectName).getDialect();
			} else if (StringUtils.isNotBlank(dialectClassName)) {
				this.dialect = (Dialect) ClassUtils.getClass(dialectClassName).newInstance();
			}
		}
		Assert.notNull(getDialect(), "Property 'dialect' is required");
		ModelConfigurationBuilder modelConfigurationBuilder = ModelConfigurationBuilder.create(this);
		modelConfigurationBuilder.build();
	}

}

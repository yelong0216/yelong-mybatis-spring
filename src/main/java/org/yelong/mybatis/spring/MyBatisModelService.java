/**
 * 
 */
package org.yelong.mybatis.spring;

import org.yelong.core.model.ModelConfiguration;
import org.yelong.support.orm.mybaits.mapper.MyBatisBaseDataBaseOperation;
import org.yelong.support.orm.mybaits.pagehelper.AbstractPageHelperModelService;

/**
 * 默认的ModelService实现
 * 
 * @author PengFei
 * @since 1.0.0
 */
public class MyBatisModelService extends AbstractPageHelperModelService{

	private MyBatisBaseDataBaseOperation myBatisBaseDataBaseOperation;
	
	public MyBatisModelService(ModelConfiguration modelConfiguration,MyBatisBaseDataBaseOperation myBatisBaseDataBaseOperation) {
		super(modelConfiguration);
		this.myBatisBaseDataBaseOperation = myBatisBaseDataBaseOperation;
	}

	@Override
	public MyBatisBaseDataBaseOperation getMyBatisBaseDataBaseOperation() {
		return this.myBatisBaseDataBaseOperation;
	}

}

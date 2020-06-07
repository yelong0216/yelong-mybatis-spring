/**
 * 
 */
package org.yelong.mybatis.spring;

import org.springframework.transaction.annotation.Transactional;
import org.yelong.core.jdbc.sql.condition.combination.CombinationConditionSqlFragment;
import org.yelong.core.jdbc.sql.condition.simple.SimpleConditionSqlFragment;
import org.yelong.core.jdbc.sql.sort.SortSqlFragment;
import org.yelong.core.model.service.ModelService;

/**
 * 基础的服务。提供事务以及常用的一些方法
 * 
 * @author PengFei
 * @since 1.0.0
 */
@Transactional//提供事务
public abstract class BaseService {

	/**
	 * 获取组合条件
	 * 
	 * @param modelService model service
	 * @return 组合条件
	 */
	public CombinationConditionSqlFragment createCombinationSqlCondition(ModelService modelService) {
		return modelService.getModelConfiguration().getModelSqlFragmentFactory().createCombinationConditionSqlFragment();
	}
	
	/**
	 * 创建简单条件
	 * 
	 * @param modelService model service
	 * @param condition 条件sql ： username = ?
	 * @param params 条件的参数
	 * @return 条件
	 */
	public SimpleConditionSqlFragment createSimpleSqlCondition(ModelService modelService,String conditionSqlFragment,Object ... params) {
		return modelService.getModelConfiguration().getModelSqlFragmentFactory().createConditionSqlFragment(conditionSqlFragment, params);
	}
	
	/**
	 * 创建排序片段
	 * 
	 * @param modelService model service
	 * @return 排序
	 */
	public SortSqlFragment createSortSqlFragment(ModelService modelService) {
		return modelService.getModelConfiguration().getModelSqlFragmentFactory().createSortSqlFragment();
	}
	
}

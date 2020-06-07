/**
 * 
 */
package org.yelong.mybatis.spring;

import org.apache.ibatis.session.SqlSession;
import org.springframework.transaction.annotation.Transactional;
import org.yelong.support.orm.mybaits.mapper.DefaultMyBatisBaseDataBaseOperation;

/**
 * 只是继承 {@link DefaultMyBatisBaseDataBaseOperation}
 * 方便使用
 * 
 * @author PengFei
 * @since 1.0.0
 */
@Transactional
public class MyBatisBaseDataBaseOperation extends DefaultMyBatisBaseDataBaseOperation{

	public MyBatisBaseDataBaseOperation(SqlSession sqlSession) {
		super(sqlSession);
	}

}

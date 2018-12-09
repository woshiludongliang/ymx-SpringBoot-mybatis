package com.dong.liang.db.common;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.util.Properties;

/**
 * 
 * List l = session.selectList("users.selectUsers", user,new RowBounds(2, 1));
 * 
 * @author lixuli
 *
 */
@Intercepts( { @Signature( type = StatementHandler.class , method = "prepare" , args = { Connection.class, Integer.class } ) } )
public class SelectLimitInterceptor implements Interceptor {

    protected static Log log = LogFactory.getLog(SelectLimitInterceptor.class);

	public static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
	public static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    public static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();
	
    @Override
    public Object intercept(Invocation invocation ) throws Throwable
    {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler,DEFAULT_OBJECT_FACTORY,DEFAULT_OBJECT_WRAPPER_FACTORY,DEFAULT_REFLECTOR_FACTORY);
		RowBounds rowBounds = (RowBounds)metaStatementHandler.getValue("delegate.rowBounds");
		if(rowBounds == null || rowBounds == RowBounds.DEFAULT){
			return invocation.proceed();
		}
		
		String originalSql = (String)metaStatementHandler.getValue("delegate.boundSql.sql");
		metaStatementHandler.setValue("delegate.boundSql.sql", getMySqlLimitString(originalSql, rowBounds.getOffset(), rowBounds.getLimit()) );
		metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET );
		metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT );
		
		if(log.isDebugEnabled()){
			BoundSql boundSql = statementHandler.getBoundSql();
			log.debug("生成分页SQL : " + boundSql.getSql());
		}
		
		return invocation.proceed();
    }

    @Override
    public Object plugin(Object target ) {
        return Plugin.wrap(target , this);
    }

    @Override
    public void setProperties( Properties arg0 ) {
    	
    }

//    private String getOracleLimitString( String sql , int offset , int limit )
//    {
//        sql = sql.trim();
//        StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
//        pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
//        pagingSelect.append(sql);
//        pagingSelect.append(" ) row_ ) where rownum_ >= ").append(offset).append(" and rownum_ < ")
//                .append(offset + limit);
//
//        return pagingSelect.toString();
//    }
    
    private String getMySqlLimitString(String sql , int offset , int limit ) {
    	
    	sql = sql.trim();
    	
    	//当每页记录数为0时不添加分页条件
    	if(limit != 0) {
    		StringBuffer pagingSelect = new StringBuffer(sql.length() + 40);
            pagingSelect.append(sql);
            pagingSelect.append(" limit ").append(offset<0?0:offset).append(" ,").append(limit);
            sql = pagingSelect.toString();
    	}

        return sql;
    }

}

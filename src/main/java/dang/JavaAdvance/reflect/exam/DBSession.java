package dang.JavaAdvance.reflect.exam;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBSession {
	/**
	 * class Session 根据任意对象（而不必经过数据表）通过反射的方法即可返回 sql插入语句
	 * @param object POJO
	 * @return
	 */
	public String getSaveObjectSql(Object object) {
		return "insert into tablename ( id, name, pwd, age) values (id, name, pwd, age)";
	}
	
	/**
	 * class Session 根据任意对象（而不必知晓数据表）来持久化对象
	 * @param object POJO
	 * @throws SQLException 
	 */
	public void saveObject(Object object) throws SQLException {
		String sql = getSaveObjectSql(object);
		//调用Statement的save方法
		Connection connection = ConnFactory.getConnection();
		Statement statement = connection.createStatement();
		statement.execute(sql);
	}
}

package AnalyzeNumber;
import java.sql.ResultSet;  
import java.sql.SQLException;
public class Delete {
	static String sql = null;  
    static DBHelper db1 = null;  
    static ResultSet ret = null;
	static java.sql.Statement stmt = null;
	
	 public void delete() {  
		 sql = "select *from Habit";//SQL语句  
	        db1 = new DBHelper(sql);//创建DBHelper对象  
	  
	        try {  
	        	 stmt=db1.conn.createStatement();
	        	 stmt.executeUpdate("DELETE FROM Habit WHERE 1=1"); 
	            ret = db1.pst.executeQuery();//执行语句，得到结果集 
	            ret.close(); 
	            stmt.close();
	            db1.close();//关闭连接  
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }  
	 }
}


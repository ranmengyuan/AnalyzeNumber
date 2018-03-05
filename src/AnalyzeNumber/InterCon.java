package AnalyzeNumber;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InterCon {
	static String sql = null;  
    static DBHelper db1 = null;  
    static ResultSet ret = null;
	static java.sql.Statement stmt = null;
	
	 public void toCon(String ID,String Name,String TimeAl,String TimeAVA) {  
		 sql = "select *from StudentCon";//SQL语句  
	        db1 = new DBHelper(sql);//创建DBHelper对象
	        try {  
	        	 stmt=db1.conn.createStatement();
	 	         stmt.executeUpdate("INSERT INTO StudentCon(ID,Name,TimeAl,TimeAVA)VALUES('"+ID+"','"+Name+"','"+TimeAl+"','"+TimeAVA+"')");  //添加一条记录
	            ret = db1.pst.executeQuery();//执行语句，得到结果集                 
	            ret.close(); 
	            stmt.close();
	            db1.close();//关闭连接  
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }  
	 }
}

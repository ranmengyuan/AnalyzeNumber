package AnalyzeNumber;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Demo {
    static String sql = null;  
    static DBHelper db1 = null;  
    static ResultSet ret = null;
    public ArrayList<Student> GetInformation() { 
    	ArrayList<Student> informationStudent=new ArrayList<Student>();
        sql = "select *from StudentData";//SQL语句  
        db1 = new DBHelper(sql);//创建DBHelper对象  
  
        try {  
            ret = db1.pst.executeQuery();//执行语句，得到结果集  
            while (ret.next()) {   
                informationStudent.add(new Student(ret.getString(1),ret.getString(2),ret.getString(3),ret.getString(4)));
            }//获取信息              
            ret.close(); 
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return informationStudent;
    }  
  
}  

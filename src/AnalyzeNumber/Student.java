package AnalyzeNumber;


public class Student {
	private String ID;//学号
	private String Name;//姓名
	private String Date;//日期
	private String Time;//签到时间
	  	
	public Student(String ID,String Name,String Date,String Time)//对信息进行赋值
	{
		this.Name=Name;
		this.ID=ID;
		this.Time=Time;
		this.Date=Date;
	}
	public String getName()//获取姓名
	{
		return this.Name;
	}
	public String getID()//获取学号
	{
		return this.ID;
	}
	public String getTime()//获取同一天的签到时间
	{
		return this.Time;
	}
	public String getDate()//获取签到日期
	{
		return this.Date;
	}
	public void setName(String Name){
		this.Name=Name;
	}
	public void setID(String ID){
		this.ID=ID;
	}
	public void setDate(String Date){
		this.Date=Date;
	}
	public void setTime(String Time){
		this.Time=Time;
	}
}


package AnalyzeNumber;


public class StudentHard {
	private String ID;//学号
	private String Name;//姓名
	private int  FirstTime;// 第一个到的次数 
	private int  LastTime;//最后一个走的次数
	public StudentHard(String ID,String Name,int FirstTime,int LastTime)//对信息进行赋值
	{
		this.Name=Name;
		this.ID=ID;
		this.FirstTime=FirstTime;
		this.LastTime=LastTime;
		
	}
	public String getName()//获取姓名
	{
		return this.Name;
	}
	public String getID()//获取学号
	{
		return this.ID;
	}
	public int getFirstTime()//获取最早签到时间
	{
		return this.FirstTime;
	}
	public int getLastTime()//获取最晚签到时间
	{
		return this.LastTime;
	}
	public void setName(String Name){
		this.Name=Name;
	}
	public void setID(String ID){
		this.ID=ID;
	}
	public void setFirstTime(int FirstTime){
		this.FirstTime=FirstTime;
	}
	public void setLastTime(int LastTime){
		this.LastTime=LastTime;
	}

}

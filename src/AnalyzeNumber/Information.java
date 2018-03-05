package AnalyzeNumber;

import java.util.ArrayList;

public class Information {
	private String ID;//学号
	private String Name;//姓名
	private ArrayList<String> Time=new ArrayList<String>();//签到时间
	private String Date;//日期  
	
	public Information(String Name,String ID,ArrayList<String>Time,String Date)//对信息进行赋值
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
	public ArrayList<String> getTime()//获取同一天的签到时间
	{
		return this.Time;
	}
	public String getDate()//获取签到日期
	{
		return this.Date;
	}
}


package AnalyzeNumber;

public class StudentHabit {
	private String Date;//日期
	private int  evening;// 00：00~06：00学习的人数
	private int  forenoon;//06：00~12：00学习的人数
	private int  afternoon;//12：00~18：00学习的人数
	private int  night;//18：00~24：00学习的人数
	public StudentHabit(String Date,int evening,int forenoon,int afternoon,int night)//对信息进行赋值
	{
		this.Date=Date;
		this.evening=evening;
		this.forenoon=forenoon;
		this.afternoon=afternoon;
		this.night=night;
		
	}
	public String getDate()//获取日期
	{
		return this.Date;
	}
	public int getEvening()//获取 00：00~06：00学习的人数
	{
		return this.evening;
	}
	public int getForenoon()//获取06：00~12：00学习的人数
	{
		return this.forenoon;
	}
	public int getAfternoon()//获取12：00~18：00学习的人数
	{
		return this.afternoon;
	}
	public int getNight()//获取18：00~24：00学习的人数
	{
		return this.night;
	}
	public void setDate(String Date){
		this.Date=Date;
	}
	public void setEvening(int evening){
		this.evening=evening;
	}
	public void setForenoon(int forenoon){
		this.forenoon=forenoon;
	}
	public void setAfternoon(int afternoon){
		this.afternoon=afternoon;
	}
	public void setNight(int night){
		this.night=night;
	}
}

package AnalyzeNumber;


public class AnalyzeNumber {
	public static void main(String args[]){
//		ToData data=new ToData();//将excel中的数据整理后存入数据库
//		data.readToDB();
		NumberOperate form=new NumberOperate();//将每人每天的学习时间存入数据库中
//		form.GetForm();
//		form.AnalyzeAl();
		form.StatForm();//统计不同时间断在教室的人数
//		form.FristLast();//统计每个人第一个到的次数
	}
}

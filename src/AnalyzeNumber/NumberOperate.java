package AnalyzeNumber;

import java.util.ArrayList;

public class NumberOperate {
	public ToData number=new ToData();
	public Demo demo=new Demo();
	public InterForm form=new InterForm();
	public Delete delete=new Delete();

	public ArrayList<Student> GetForm(){//统计每个人每天的学习时间
		ArrayList<Student> timeStu=new ArrayList<Student>();
		ArrayList<Student> inStudent=demo.GetInformation();
		int time=0;
		int i=0;
		delete.delete();
		while(i<inStudent.size())
		{
			if(inStudent.get(i).getTime().equals("NULL")==true){//判断是否为从未学习
				form.toForm(inStudent.get(i).getID(), inStudent.get(i).getName(), "NULL", "0 min");
				timeStu.add(new Student(inStudent.get(i).getID(), inStudent.get(i).getName(), "NULL", "0 min"));
				i=i+1;
			}
			else{
				time=time+number.Calculate(inStudent.get(i).getTime(), inStudent.get(i+1).getTime());//统计每个人每天的学习时间
				if(i<inStudent.size()-2){
					if(inStudent.get(i).getDate().equals(inStudent.get(i+2).getDate())==false||inStudent.get(i).getName().equals(inStudent.get(i+2).getName())==false){//张东问题
						form.toForm(inStudent.get(i).getID(), inStudent.get(i).getName(), inStudent.get(i).getDate(), String.valueOf(time)+"min");//判断记录一天的时间
						timeStu.add(new Student(inStudent.get(i).getID(), inStudent.get(i).getName(), inStudent.get(i).getDate(), String.valueOf(time)));
						time=0;
						i=i+2;
					}
					else{
						i=i+2;
					}
				}
				else
				{
					form.toForm(inStudent.get(i).getID(), inStudent.get(i).getName(), inStudent.get(i).getDate(), String.valueOf(time)+"min");
					timeStu.add(new Student(inStudent.get(i).getID(), inStudent.get(i).getName(), inStudent.get(i).getDate(), String.valueOf(time)));
					i=i+2;
				}
			}
		}
		return timeStu;
	}
	public ArrayList<String> FormDate()
	{
		ArrayList<String> date=new ArrayList<String>();
		for(int i=1;i<31;i++){
			if(i>9)
				date.add( "2015/12/"+i);
			else 
				date.add("2015/12/0"+i);
		}
		return date;
	}
	public void FristLast(){//统计每人最早来和最晚走的次数
		InterCheck checkWork=new InterCheck();
		Student tempStudent=new Student("123","abc","2015/12/01","24:00");//定义中间变量，找到最大和最小值
		Student tempStudent1=new Student("123","abc","2015/12/01","00:00");
		ArrayList<StudentHard> form=new ArrayList<StudentHard>(); 
		ArrayList<String> date=FormDate();
		ArrayList<Student> numberAl=demo.GetInformation();
		ArrayList<Student> num=AnalyzeAl();
		for(int i=0;i<num.size();i++){
			form.add(new StudentHard(num.get(i).getID(),num.get(i).getName(),0,0));//赋初值
		}
		for(int i=0;i<30;i++){
			tempStudent=new Student("123","abc","2015/12/01","24:00");
			tempStudent1=new Student("123","abc","2015/12/01","00:00");
			for(int j=0;j<numberAl.size();j++){//找到时间的最大和最小值
				if(date.get(i).equals(numberAl.get(j).getDate())==true){
					if(Compare(tempStudent.getTime(),numberAl.get(j).getTime())>0)
						tempStudent=numberAl.get(j);
					if(Compare(tempStudent1.getTime(),numberAl.get(j).getTime())<0)
						tempStudent1=numberAl.get(j);
				}
			}
			System.out.println(tempStudent.getTime());
			System.out.println("1+"+tempStudent1.getTime());
			for(int k=0;k<form.size();k++){
				
				if(form.get(k).getName().equals(tempStudent.getName())==true)
					form.get(k).setFirstTime(form.get(k).getFirstTime()+1);//整理次数
				if(form.get(k).getName().equals(tempStudent1.getName())==true)
					form.get(k).setLastTime(form.get(k).getLastTime()+1);
			}
			
		}
		for(int j=0;j<form.size();j++){
			checkWork.toCheck(form.get(j).getID(),form.get(j).getName(),form.get(j).getFirstTime(),form.get(j).getLastTime());
		}
	}
	public int Compare(String time1,String time2){//比较两个时间的前后
		int temp=0;
		int hour1,min1,hour2,min2;	
		hour1=Integer.parseInt(time1.substring(0,2));
		min1=Integer.parseInt(time1.substring(3,5));
		hour2=Integer.parseInt(time2.substring(0,2));
		min2=Integer.parseInt(time2.substring(3,5));
		if(hour1>hour2)
			temp=1;
		else if(hour1==hour2&&min1>=min2)
			temp=1;
		else
			temp=-1;
		return temp;
	}
	public void StatForm(){//统计不同时间段的人的数量
		ArrayList<Student> numberAl=demo.GetInformation();
		ArrayList<String> date=FormDate();
		interAnlayze inter=new interAnlayze();
		ArrayList<StudentHabit> tempStudent=new ArrayList<StudentHabit>();//定义中间变量，每天每个时段的人数
		int sum1=0;
		int sum2=0;
		int sum3=0;
		int sum4=0;
		Delete delete=new Delete();
		delete.delete();
		for(int i=0;i<date.size();i++){
			tempStudent.add(new StudentHabit(date.get(i),0,0,0,0));
		}
		for(int i=0;i<numberAl.size()-1;i=i+2){
			if(numberAl.get(i).getDate().equals("NULL")==false){
				if(Compare("06:00",numberAl.get(i).getTime())>0&&Compare("00:00",numberAl.get(i+1).getTime())<0)
					sum1++;
				else if(Compare("12:00",numberAl.get(i).getTime())>0&&Compare("06:00",numberAl.get(i+1).getTime())<0)
					sum2++;
				else if(Compare("18:00",numberAl.get(i).getTime())>0&&Compare("12:00",numberAl.get(i+1).getTime())<0)
					sum3++;
				else if(Compare("24:00",numberAl.get(i).getTime())>0&&Compare("18:00",numberAl.get(i+1).getTime())<0)
					sum4++;
			}
		}
		for(int j=0;j<date.size();j++){
			for(int k=0;k<numberAl.size()-1;k=k+2){
				if(numberAl.get(k).getDate().equals("NULL")==false){
					if(numberAl.get(k).getDate().equals(date.get(j))==true){
						if(Compare("06:00",numberAl.get(k).getTime())>0&&Compare("00:00",numberAl.get(k+1).getTime())<0)
							tempStudent.get(j).setEvening(tempStudent.get(j).getEvening()+1);
						else if(Compare("12:00",numberAl.get(k).getTime())>0&&Compare("06:00",numberAl.get(k+1).getTime())<0)
							tempStudent.get(j).setForenoon(tempStudent.get(j).getForenoon()+1);
						else if(Compare("18:00",numberAl.get(k).getTime())>0&&Compare("12:00",numberAl.get(k+1).getTime())<0)
							tempStudent.get(j).setAfternoon(tempStudent.get(j).getAfternoon()+1);
						else if(Compare("24:00",numberAl.get(k).getTime())>0&&Compare("18:00",numberAl.get(k+1).getTime())<0)
							tempStudent.get(j).setNight(tempStudent.get(j).getNight()+1);
					}
				}
			}
		}
		tempStudent.add(new StudentHabit("ALL",sum1,sum2,sum3,sum4));
		for(int i=0;i<tempStudent.size();i++){
			inter.toHabit(tempStudent.get(i).getDate(),tempStudent.get(i).getEvening(),tempStudent.get(i).getForenoon(),tempStudent.get(i).getAfternoon(),tempStudent.get(i).getNight());
		}
	}
	public ArrayList<Student> AnalyzeAl()//统计每人一个月的总时间，以及每天的平均时间，并按照平均时间排序存入数据库中
	{
		ArrayList<Student> NumberAl=GetForm();
		InterCon inter=new InterCon();
		ArrayList<Student> tempNumber=new ArrayList<Student>();
		int timeAl=0;
		int timeAvra=0;
		int i=0;
		Delete delete=new Delete();
		delete.delete();
		while(i<NumberAl.size()){
			if(NumberAl.get(i).getDate().equals("NULL")==true){//判断是否从未学习
				tempNumber.add(new Student(NumberAl.get(i).getID(), NumberAl.get(i).getName(), "0", "0"));
				i++;
			}
			else{
				timeAl=timeAl+Integer.parseInt(NumberAl.get(i).getTime());//统计每人总的学习时间和平均每天学习时间
				if(i<NumberAl.size()-1){
					if(NumberAl.get(i).getName().equals(NumberAl.get(i+1).getName())==false){
						timeAvra=timeAl/30;
						tempNumber.add(new Student(NumberAl.get(i).getID(), NumberAl.get(i).getName(), String.valueOf(timeAl), String.valueOf(timeAvra)));
						timeAl=0;
						i++;
					}
					else{
						i++;
					}
				}
				else{
					timeAvra=timeAl/30;
					tempNumber.add(new Student(NumberAl.get(i).getID(), NumberAl.get(i).getName(), String.valueOf(timeAl), String.valueOf(timeAvra)));
					i++;
				}
			}
			
		}
		String tempI;//对平均每天的学习时间进行排序
		String tempN;
		String tempT1;
		String tempT2;
		int temp1=0;
		int temp2=0;
		for(int j=0;j<tempNumber.size();j++)
		{
			for(int k=0;k<tempNumber.size()-j-1;k++){
				temp1=Integer.parseInt(tempNumber.get(k).getTime());
				temp2=Integer.parseInt(tempNumber.get(k+1).getTime());
				if(temp1<temp2){
					tempI=tempNumber.get(k).getID();
					tempNumber.get(k).setID(tempNumber.get(k+1).getID());
					tempNumber.get(k+1).setID(tempI);
					
					tempN=tempNumber.get(k).getName();
					tempNumber.get(k).setName(tempNumber.get(k+1).getName());
					tempNumber.get(k+1).setName(tempN);
					
					tempT1=tempNumber.get(k).getDate();
					tempNumber.get(k).setDate(tempNumber.get(k+1).getDate());
					tempNumber.get(k+1).setDate(tempT1);
					
					tempT2=tempNumber.get(k).getTime();
					tempNumber.get(k).setTime(tempNumber.get(k+1).getTime());
					tempNumber.get(k+1).setTime(tempT2);
					
				}
			}
		}
		for(int j=0;j<tempNumber.size();j++){
			inter.toCon(tempNumber.get(j).getID(), tempNumber.get(j).getName(), tempNumber.get(j).getDate()+"min",tempNumber.get(j).getTime()+"min");
		}
		return tempNumber;
	}
	
}

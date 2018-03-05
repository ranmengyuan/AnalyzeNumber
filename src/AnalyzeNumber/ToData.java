package AnalyzeNumber;

import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFCell;


public class ToData {	
	private ArrayList<Information> information;//定义一个类链表，来存放每一个人的每一天的签到情况
	private Inter inter=new Inter();//定义变量来将信息存入数据库
	Delete delete=new Delete();//定义变量对数据库进行更新
	public ToData()
	{
	}
	public int Calculate(String time1,String time2){//计算两个时间的时间间隔
		int time;
		int hour1,min1,hour2,min2;	
		hour1=Integer.parseInt(time1.substring(0,2));
		min1=Integer.parseInt(time1.substring(3,5));
		hour2=Integer.parseInt(time2.substring(0,2));
		min2=Integer.parseInt(time2.substring(3,5));
		time=hour2*60+min2-hour1*60-min1;
		return time;
	}
	public ArrayList<String> Deal(ArrayList<String> Time){//处理重复签到情况
		ArrayList<String> timeAl=new ArrayList<String>();
		ArrayList<String> tempTime=new ArrayList<String>();
		for(int i=0;i<Time.size();i++)
			tempTime.add(Time.get(i));
		for(int i=0;i<tempTime.size()-1;i++){
			if(Calculate(tempTime.get(i),tempTime.get(i+1))<5){//判断是否是无效的签到时间
				tempTime.set(i, "0");
			}
		}
		for(int j=0;j<tempTime.size();j++)
		{
			if(tempTime.get(j).equals("0")==false)
				timeAl.add(tempTime.get(j));
		}
		return timeAl;
	}
	public int judge(ArrayList<Information> inform,String Name){//判断是否从来没学习过
		int flag=0;
		for(int i=0;i<inform.size();i++){
			if(inform.get(i).getName().equals(Name)==true&&inform.get(i).getTime().size()>=2){
				flag=1;
				break;
			}
		}
		return flag;
	}
	public void readToDB(){//将excel文件的信息经过整理放入数据库中
		String Name=null;
		String ID=null;
		String Date=null;
		String tempName="abc";
		ArrayList<String> CheckTime;
		Delete delete=new Delete();
		delete.delete();
		GetExcel readExcel=new GetExcel();//定义变量对excel进行处理
		information = new ArrayList<Information>();
		readExcel.SET("//Users//ranmengyuan//Desktop//","12.xls");//设置excel文件的路径
		for(int i=6;i<124;i++){//从第七行开始获取有用的信息
			if(i%2 == 0){//偶数行为姓名和学号信息
				readExcel.readFile(2, i,2);//第三张表的偶数行的第三列为学号信息  
				ID=readExcel.getStringCellValue();//获取学号信息
				readExcel.readFile(2, i, 10);//第三张表的偶数行的第11列为姓名信息
				Name = readExcel.getStringCellValue();//获取姓名信息
			}
			else {
				for(int j=0;j<31;j++){//奇数行为签到时间
					CheckTime=new ArrayList<String>();//用来存一天的签到时间
					readExcel.readFile(2,i,j);//签到日期
					if(j<9)
						Date="2015/12/0"+(j+1);//构成时间的形式
					else 
						Date="2015/12/"+(j+1);
					if(readExcel.getCellType()==HSSFCell.CELL_TYPE_BLANK){//单元格没有内容
						CheckTime.add("0");//当日无签到信息
					}
					else {//有签到信息
						String temp = readExcel.getStringCellValue();//获取整个一天的签到时间
						String temp2;
						for(int k=0;k<(temp.length()/5);k++){//按5个字符的长度分成多个字符串
							temp2 = temp.substring(5*k, 5*(k+1));
							CheckTime.add(temp2);
						}
					}
					information.add(new Information(Name,ID,CheckTime, Date));//将签到信息加入链表中
				}
			}
		}
		delete.delete();
		for(int i=0;i<information.size();i++)//将信息存入数据库中
		{
			if(tempName.equals(information.get(i).getName())==false&&judge(information,information.get(i).getName())==0){//判断是否为从未学习过
				tempName=information.get(i).getName();
				System.out.println(information.get(i).getName()+"  "+information.get(i).getTime().size()+""+tempName);
				inter.IntoDB(information.get(i).getID(), information.get(i).getName(),"NULL","NULL");
			}
			if(information.get(i).getTime().equals("0")==false){//没有签到的不记录
//				for(int k=0;k<information.get(i).getTime().size();k++)
//					System.out.println(information.get(i).getTime().get(k));
				if(Deal(information.get(i).getTime()).size()%2==0){//处理无效签到时间后，判断是否有来和走时的记录，如只有来，没有走的签到视为无效
					for(int j=0;j<Deal(information.get(i).getTime()).size();j++){
		//				System.out.println(information.get(i).getID()+"  "+information.get(i).getName()+" "+information.get(i).getDate()+"  "+information.get(i).getTime().get(j));
							inter.IntoDB(information.get(i).getID(), information.get(i).getName(), information.get(i).getDate(), Deal(information.get(i).getTime()).get(j));//存入数据库
					}
				}
				else{
					for(int j=0;j<Deal(information.get(i).getTime()).size()-1;j++){
						//				System.out.println(information.get(i).getID()+"  "+information.get(i).getName()+" "+information.get(i).getDate()+"  "+information.get(i).getTime().get(j));
											inter.IntoDB(information.get(i).getID(), information.get(i).getName(), information.get(i).getDate(), Deal(information.get(i).getTime()).get(j));
									}
				}
			}
		}
	}
}

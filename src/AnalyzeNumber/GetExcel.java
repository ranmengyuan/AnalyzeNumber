package AnalyzeNumber;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class GetExcel {
	
	public  InputStream xls;//文件名
	private String path;//文件路径
	private HSSFWorkbook workBook;//xls工作薄
	private HSSFSheet sheet;//xls表格
	private HSSFRow row;//表格行对象
	private HSSFCell cell;//单元格对象
	public GetExcel()
	{
		
	}
	public void SET(String _path,String _excelName)//设置打开的excel文件的路径
	{
		String excelName=_excelName;
		path=_path+excelName;
		try {
			xls = new FileInputStream(path);
			workBook = new HSSFWorkbook(xls);
		} catch (FileNotFoundException e) {
			System.out.println("目标文件不存在");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("读取目标文件错误");
			e.printStackTrace();
		}
	}
	public void readFile(int _sheet,int _row,int _col)
	{
		sheet = workBook.getSheetAt(_sheet);//获得表格对象
		row     = sheet.getRow(_row);//获得表格行对象
		cell   = row.getCell(_col);//获得单元格
	}
	public int getCellType()//获得单元格类型
	{
		return this.cell.getCellType();
	}
	public String getStringCellValue()//获得单元格的内容
	{
		return this.cell.getStringCellValue();
	}
}


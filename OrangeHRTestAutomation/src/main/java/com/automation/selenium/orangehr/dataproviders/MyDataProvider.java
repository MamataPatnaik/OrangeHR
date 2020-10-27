package com.automation.selenium.orangehr.dataproviders;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import com.automation.selenium.orangehr.pojo.UserLoginData;

public class MyDataProvider {	
	
	
	private String dataProviderPath = "C:\\Users\\neebal\\Downloads\\OrangeHRMSTestDatav2.xlsx";
	
	@DataProvider(name = "userloginTestCaseData")
	public Object[][] getGenericDataProvider(Object userDataHolder)
	{
		
		List<Object> dataHolderList = new ArrayList<>();
		List<List<Object>> rowLevelData = new ArrayList<List<Object>>();
		
		List<String> fieldNames = new ArrayList<>();
		try
		{
		
			 FileInputStream file = new FileInputStream(new File(dataProviderPath)); 
			  
			 
	         // Create Workbook instance holding reference to .xlsx file 
	         XSSFWorkbook workbook = new XSSFWorkbook(file); 
	
	         // Get first/desired sheet from the workbook 
	         XSSFSheet sheet = workbook.getSheetAt(0); 
	
	         // Iterate through each rows one by one 
	         Iterator<Row> rowIterator = sheet.iterator(); 
	         
	         boolean endOfTestData = false;
	         
	         int rowIndex = 0;
	         
	       
	         while (rowIterator.hasNext()) 
	         { 
	             Row row = rowIterator.next(); 
	           
	             // For each row, iterate through all the columns 
	             Iterator<Cell> cellIterator = row.cellIterator(); 
	             
	            
	             List<Object> columnData = new ArrayList<>();
	             
	             if (rowIndex > 0)
		         {
	            	 rowLevelData.add(columnData);
	           
		         }
		       
	             rowIndex++;
	             
	             while (cellIterator.hasNext()) { 
	            	 
	                 Cell cell = cellIterator.next();   
	                 if(cell.getRowIndex() == 0 && cell.getColumnIndex() > 0)
	                 {
	                	 fieldNames.add(cell.getStringCellValue());
	                	 
	                 }
	                
	                 if( cell.getColumnIndex() > 0 && cell.getRowIndex() > 0)
	                 {
	                	 
	                	 switch (cell.getCellType()) { 
		                 case STRING: 
		                     
		                	 String cellValue = cell.getStringCellValue();
		                     if( cellValue != null && cellValue.equals("End of test data"))
		                     {
		                    	 endOfTestData = true;
		                    	 break;
		                     }
		                     else
		                     {
			                     columnData.add(cellValue);
			                     break;
		                     }
		                     
		                 case NUMERIC: 
		                     columnData.add(cell.getNumericCellValue());
		                     break;
		             
		                 case _NONE: 
		                     System.out.print(cell.getErrorCellValue()  + " "); 
		                     break;
		                     
						default:
							break; 
		                 } 
	                 }
	                 
	                 if( endOfTestData == true)
	                 {
	                	 break;
	                 }
	            	                 
	             } //end of cell iterator
	            
	             
	         } // end of row iterator
	         
	         workbook.close();
	         file.close(); 
     } 
     catch (Exception e) { 
         e.printStackTrace(); 
     } 
		
	
	convertExcelDataToPOJO(userDataHolder, dataHolderList, rowLevelData, fieldNames);
	
	Object[][] customData = convertPojoToDataProviderFormat(dataHolderList);
	
	return customData;
	}


	private Object[][] convertPojoToDataProviderFormat(List<Object> dataHolderList) {
		Object[][] customData = new Object[dataHolderList.size()][1];
		int rowIndex = 0;
		for(Object dataHolder : dataHolderList)
		{
			customData[rowIndex++][0] = dataHolder;
		}
		return customData;
	}


	private void convertExcelDataToPOJO(Object userDataHolder, List<Object> dataHolderList,
			List<List<Object>> rowLevelData, List<String> fieldNames) {
		for( List<Object> columnDataList : rowLevelData )
		{
			
			try 
			{
				userDataHolder = userDataHolder.getClass().newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int columnDataListIndex = 0;
			for( Object columnData : columnDataList )
			{
				setPojoValue(userDataHolder, fieldNames.get(columnDataListIndex), columnData);
				columnDataListIndex++;
			}
			
			dataHolderList.add(userDataHolder);
		}
	}
	
	
	private void setPojoValue(Object testDataHolder, String fieldName, Object value)
	{
		
		try {
			Field field = testDataHolder.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(testDataHolder,value);
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public static void main (String[] args)
	{
		MyDataProvider myDataProvider = new MyDataProvider();
		Object myData = myDataProvider.getGenericDataProvider(new UserLoginData());
		
		System.out.println("Finished:::" + myData);
	}

}

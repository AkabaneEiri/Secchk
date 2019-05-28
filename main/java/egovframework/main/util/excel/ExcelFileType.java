package egovframework.main.util.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class ExcelFileType {
    /**
     * 
     * 엑셀파일을 읽어서 Workbook 객체에 리턴한다.
     * XLS와 XLSX 확장자를 비교한다.
     * 
     * @param filePath
     * @return
     * 
     */
    public static Workbook getWorkbook(String filePath) {
        System.out.println("filepath in type class : " + filePath);        
        /*
         * FileInputStream은 파일의 경로에 있는 파일을
         * 읽어서 Byte로 가져온다.
         * 
         * 파일이 존재하지 않는다면은
         * RuntimeException이 발생된다.
         */
        FileInputStream fis = null;
        try {
        	System.out.println("try fis");
            fis = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
        	System.out.println("exception fis");
            throw new RuntimeException(e.getMessage(), e);
        }
        System.out.println(fis);
        Workbook wb = null;        
        /*
         * 파일의 확장자를 체크해서 .XLS 라면 HSSFWorkbook에
         * .XLSX라면 XSSFWorkbook에 각각 초기화 한다.
         */
        if(filePath.toUpperCase().endsWith(".XLS")) {
        	System.out.println("in if xls");
            try {
                wb = new HSSFWorkbook(fis);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        else if(filePath.toUpperCase().endsWith(".XLSX")) {
            try {
                wb = new XSSFWorkbook(fis);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        
        System.out.println("after new wb" + wb);
        
        return wb;        
    }
}

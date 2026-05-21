package com.vit.TestingwithNg.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
    
    private Workbook workbook;
    private Sheet sheet;
    
    public ExcelReader(String filePath, String sheetName) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public ExcelReader(String filePath, int sheetIndex) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(sheetIndex);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String getCellData(int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            return "";
        }
        Cell cell = row.getCell(colNum);
        if (cell == null) {
            return "";
        }
        
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }
    
    public int getRowCount() {
        return sheet.getLastRowNum() + 1;
    }
    
    public int getColumnCount() {
        Row row = sheet.getRow(0);
        if (row == null) {
            return 0;
        }
        return row.getLastCellNum();
    }
    
    public List<Map<String, String>> getAllData() {
        List<Map<String, String>> data = new ArrayList<>();
        
        int rowCount = getRowCount();
        int colCount = getColumnCount();
        
        if (rowCount <= 1) {
            return data;
        }
        
        List<String> headers = new ArrayList<>();
        for (int i = 0; i < colCount; i++) {
            headers.add(getCellData(0, i));
        }
        
        for (int i = 1; i < rowCount; i++) {
            Map<String, String> rowData = new HashMap<>();
            for (int j = 0; j < colCount; j++) {
                rowData.put(headers.get(j), getCellData(i, j));
            }
            data.add(rowData);
        }
        
        return data;
    }
    
    public List<String> getColumnData(int colNum) {
        List<String> columnData = new ArrayList<>();
        int rowCount = getRowCount();
        
        for (int i = 1; i < rowCount; i++) {
            columnData.add(getCellData(i, colNum));
        }
        
        return columnData;
    }
    
    public List<String> getColumnData(String columnName) {
        List<String> columnData = new ArrayList<>();
        int colNum = -1;
        
        int colCount = getColumnCount();
        for (int i = 0; i < colCount; i++) {
            if (getCellData(0, i).equalsIgnoreCase(columnName)) {
                colNum = i;
                break;
            }
        }
        
        if (colNum == -1) {
            return columnData;
        }
        
        int rowCount = getRowCount();
        for (int i = 1; i < rowCount; i++) {
            columnData.add(getCellData(i, colNum));
        }
        
        return columnData;
    }
    
    public void close() {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
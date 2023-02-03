package com.example.testreadexcel.utils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.*;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
public class ExcelUtils {
    private FileInputStream fis;
    private FileOutputStream fos;
    private Workbook workbook;
    private Sheet sheet;
    private Cell cell;
    private String excelFilePath;
    private Map<String, Integer> columns = new HashMap<>();
    private File file;

    public void setExcelFile(String ExcelPath, String SheetName) throws Exception {
        try {
            file = new File(ExcelPath);
            if (!file.exists()) {
                System.out.println("File doesn't exist, so created!");
            }
            fis = new FileInputStream(ExcelPath);
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(SheetName);
            if (sheet == null) {
                sheet = workbook.createSheet(SheetName);
            }
            this.excelFilePath = ExcelPath;
            //adding all the column header names to the map 'columns'
            sheet.getRow(0).forEach(cell -> {
                columns.put(cell.getStringCellValue(), cell.getColumnIndex());
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getSheetRowSize(String ExcelPath, String SheetName) throws IOException {
        file = new File(ExcelPath);
        if (!file.exists()) {
            System.out.println("File doesn't exist, so created!");
        }
        fis = new FileInputStream(ExcelPath);
        workbook = WorkbookFactory.create(fis);
        sheet = workbook.getSheet(SheetName);
        if (sheet == null) {
            sheet = workbook.createSheet(SheetName);
        }
        return sheet.getPhysicalNumberOfRows();
    }

    public String getCellData(int rowNum, int colNum) throws Exception {
        try {
            cell = sheet.getRow(rowNum).getCell(colNum);
            String CellData = null;
            switch (cell.getCellType()) {
                case STRING:
                    CellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        CellData = String.valueOf(cell.getDateCellValue());
                    } else {
                        CellData = String.valueOf((long) cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    CellData = Boolean.toString(cell.getBooleanCellValue());
                    break;
                case BLANK:
                    CellData = "";
                    break;
            }
            return CellData;
        } catch (Exception e) {
            return "";
        }
    }

    public String getCellData(String columnName, int rowNum) throws Exception {
        String value = getCellData(rowNum, columns.get(columnName));
        if (value.length() == 0 || value.equals("") || value.equals(" ")) {
            return null;
        }else {
            return value;
        }
    }
}

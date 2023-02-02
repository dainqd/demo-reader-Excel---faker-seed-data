package com.example.testreadexcel.utils;

import com.example.testreadexcel.enums.SheetIndex;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Hepper {
    public static String readDataFromCell(Cell cell) {
        String data;
        switch (cell.getCellType()) {
            case NUMERIC:
                NumberFormat formatter = new DecimalFormat("#0.00");
                data = formatter.format(cell.getNumericCellValue());
                break;
            case STRING:
                data = cell.getStringCellValue();
                break;
            default:
                data = null;
                break;
        }
        return data;
    }

    /**
     * Hàm tìm tên Province/District/Ward dựa vào id trong file excel
     * @param id id ward đọc được từ file excel
     * @return tên địa điểm dựa theo id tìm được trong file excel
     */
    public static String getPlacePathById(double id) throws IOException {
        //load file excel da
        File file = new File(Constants.SEED_URI);
        FileInputStream fileInputStream = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(SheetIndex.WARD.getValue());
        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            //doc cot id text la cot 9
            double idText = row.getCell(8).getNumericCellValue();
            if (id == idText) {
                //đọc côt 6 để lấy full name
                return row.getCell(5).getStringCellValue();
            }
        }
        return null;
    }
}

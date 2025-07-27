package com.caf.automation.web.utils;

import com.caf.automation.web.constants.Constant;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelDataReader {


    public static List<Map<String, String>> readData() {

        String filePath = System.getProperty("user.dir") + "/src/test/resources/data/"+ Constant.TEST_DATA_FILE_NAME;
        List<Map<String, String>> testDataList = new ArrayList<>();

        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet(Constant.TEST_DATA_SHEET_NAME);

            Row rowHeader = sheet.getRow(0);
            int columns = rowHeader.getLastCellNum();

            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) continue;

                Map<String, String> rowData = new HashMap<>();
                for (int colNum = 0; colNum < columns; colNum++) {
                    Cell cell = row.getCell(colNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    Cell cellHeader = rowHeader.getCell(colNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                    String header = getCellValueAsString(cellHeader);
                    String value = getCellValueAsString(cell);

                    rowData.put(header, value);
                }
                testDataList.add(rowData);


            }
            workbook.close();
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        return testDataList;


    }

    private static String getCellValueAsString(Cell cell) {

        switch (cell.getCellType())
        {
            case STRING ->
            {
                return cell.getStringCellValue();
            }
            case NUMERIC ->
            {
                return String.valueOf(cell.getNumericCellValue());
            }
            case BOOLEAN ->
            {
                return String.valueOf(cell.getBooleanCellValue());
            }
            default ->
            {
                return "";
            }
        }
    }



}

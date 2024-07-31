package com.elmiraouy.jwtsecurity.service;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class SharedService {
    public static LocalDateTime handleDate(Map<String, Integer> columnMap, Row currentRow, String columnName, DateTimeFormatter formatter) {
        System.out.println("-- handleDate --");
        LocalDateTime date=null;
        if (columnMap.containsKey(columnName)) {
            CellType cellType = currentRow.getCell(columnMap.get(columnName)).getCellType();
            System.out.println(columnName + " type colone:" + cellType);

            if (cellType == CellType.STRING) {
                String dateString = currentRow.getCell(columnMap.get(columnName)).getStringCellValue();
                System.out.println(columnName + " :" + dateString);

                if (!dateString.equals("null")) {
                    date = LocalDateTime.parse(dateString, formatter);
                }
            } else if (cellType == CellType.NUMERIC) {
                date = currentRow.getCell(columnMap.get(columnName)).getLocalDateTimeCellValue();
                System.out.println(columnName + "  numeric type"+date.toString());

            } else {
                System.out.println("err " + columnName);
            }
        }
        return date;
    }
}

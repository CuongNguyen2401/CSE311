package com.fastfood.utils;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fastfood.dto.OrderDTO;

public class OrderExcelExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<OrderDTO> listOrder;

	public OrderExcelExporter(List<OrderDTO> listOrder) {
		this.listOrder = listOrder;
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("Orders");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "ID", style);
		createCell(row, 1, "Customer Name", style);
		createCell(row, 2, "Address", style);
		createCell(row, 3, "Email", style);
		createCell(row, 4, "PhoneNumber", style);
		createCell(row, 5, "Total Pay", style);
		

	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Long) {
			cell.setCellValue((Long) value);
		} else if (value instanceof Double) {
			cell.setCellValue((Double) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}
	
	private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (OrderDTO dto : listOrder) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, dto.getId(), style);
            createCell(row, columnCount++, dto.getCustomerName(), style);
            createCell(row, columnCount++, dto.getAddress(), style);
            createCell(row, columnCount++, dto.getEmail(), style);
            createCell(row, columnCount++, dto.getPhonenumber(), style);
            createCell(row, columnCount++, dto.getTotalPay(), style);
            
            
             
        }
    }
	public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }

}

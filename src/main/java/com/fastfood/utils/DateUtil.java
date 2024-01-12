package com.fastfood.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DateUtil {
	private List<String> monthsAndYears = new ArrayList<>();
	private LocalDate startDate = LocalDate.of(2022, Month.FEBRUARY, 1);
	private YearMonth currentYearMonth = YearMonth.now();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");

	public void populateMonthsAndYears() {

		monthsAndYears.clear();

		while (startDate.isBefore(currentYearMonth.atEndOfMonth())) {
			monthsAndYears.add(currentYearMonth.format(formatter));
			currentYearMonth = currentYearMonth.minusMonths(1);
		}

		monthsAndYears.add(currentYearMonth.format(formatter));
	}

	public static int getMonthInt(String monthName) {

		monthName = monthName.toUpperCase();

		Month month = Month.valueOf(monthName);

		int monthValue = month.getValue();

		return monthValue;
	}

	 public static Date convertStringToDate(String timestampString) {
	        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	        try {
	            Date date = inputFormat.parse(timestampString);
	            return date;
	        } catch (Exception e) {
	           
	            e.printStackTrace();
	            return null; 
	        }
	    }

}

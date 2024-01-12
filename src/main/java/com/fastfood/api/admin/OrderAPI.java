package com.fastfood.api.admin;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fastfood.dto.OrderDTO;
import com.fastfood.service.IOrderService;
import com.fastfood.utils.DateUtil;
import com.fastfood.utils.OrderExcelExporter;

@RestController(value = "BillAPIOfAdmin")
@RequestMapping("admin/api/v1/orders")
public class OrderAPI {

	@Autowired
	IOrderService orderService;

	@GetMapping
	public ResponseEntity<OrderDTO> getAllOrdersByMonthAndYear(@RequestParam(name = "month", required = false) String month,
			@RequestParam(name = "year", required = false) int year, @RequestParam("page") int page,
			@RequestParam("limit") int limit) {

		DateUtil dateUtil = new DateUtil();

		OrderDTO result = new OrderDTO();
		result.setPage(page);
		result.setLimit(limit);

		Pageable pageable = new PageRequest(page - 1, limit);
		result.setListResult(orderService.findAllByMonthAndYear(dateUtil.getMonthInt(month), year, pageable));
		result.setTotalItem(orderService.getTotalOrder());
		result.setTotalPage((int) Math.ceil((double) result.getTotalItem() / result.getLimit()));

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<OrderDTO> getAllOrders(@RequestParam("page") int page, @RequestParam("limit") int limit) {

		OrderDTO result = new OrderDTO();
		result.setPage(page);
		result.setLimit(limit);

		Pageable pageable = new PageRequest(page - 1, limit);
		result.setListResult(orderService.findAll(pageable));
		result.setTotalItem(orderService.getTotalOrder());
		result.setTotalPage((int) Math.ceil((double) result.getTotalItem() / result.getLimit()));

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/excel")
	public void exportToExcel(HttpServletResponse response ,@RequestParam(name = "month", required = false) String month,
			@RequestParam(name = "year", required = false) int year) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		List<OrderDTO> listUsers = orderService.findAll();
		if(month!=null) {
			DateUtil dateUtil = new DateUtil();
			listUsers=orderService.findAllByMonthAndYear(dateUtil.getMonthInt(month), year);
		}

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=orders_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		
		

		OrderExcelExporter excelExporter = new OrderExcelExporter(listUsers);

		excelExporter.export(response);
	}

}

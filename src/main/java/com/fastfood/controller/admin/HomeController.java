package com.fastfood.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fastfood.dto.OrderDTO;
import com.fastfood.dto.ProductDTO;
import com.fastfood.service.IOrderService;
import com.fastfood.service.impl.CategoryService;
import com.fastfood.service.impl.PieChartService;
import com.fastfood.service.impl.ProductService;
import com.fastfood.utils.DateUtil;
import com.fastfood.utils.MessageUtil;
import com.fastfood.utils.OrderExcelExporter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller(value = "homeControllerOfAdmin")
public class HomeController {
	
	@Autowired
     JavaMailSender emailSender;

	@Autowired
	PieChartService chart;

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	IOrderService orderService;
	
	
	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("admin/home");
		return mav;
	}

	@RequestMapping(value = "/admin/chart", method = RequestMethod.GET)
	public ModelAndView chartPage(ModelMap modelMap) {
		List<List<Map<Object, Object>>> canvasjsDataList = chart.getCanvasjsChartData();
		modelMap.addAttribute("dataPointsList", canvasjsDataList);
		ModelAndView mav = new ModelAndView("admin/chart");
		return mav;
	}
	
	@RequestMapping(value = "/admin/chat", method = RequestMethod.GET)
	public ModelAndView chatPage() {
		
		ModelAndView mav = new ModelAndView("admin/chat");
		return mav;
	}

	@RequestMapping(value = "/admin/order", method = RequestMethod.GET)
	public ModelAndView orderPage() {
		ModelAndView mav = new ModelAndView("admin/order");
		DateUtil dateUtil = new DateUtil();
		dateUtil.populateMonthsAndYears();
		List<String> listMonthAndYear = dateUtil.getMonthsAndYears();
		mav.addObject("listMonthAndYear", listMonthAndYear);
		return mav;
	}

	@RequestMapping(value = "/admin/product/edit", method = RequestMethod.GET)
	public ModelAndView createPage(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/edit");
		ProductDTO product = new ProductDTO();
//		if (id != null) {
//			product = productService.findById(id);
//			String[] listImg = product.getImg().split("_");
//			mav.addObject("listImg", listImg);
//		}

		if (request.getParameter("msg") != null) {
			if (request.getParameter("msg").equals("add_success"))
				mav.addObject("msg", MessageUtil.SUCCESS_ADD);
			else {
				mav.addObject("msg", MessageUtil.ERROR_ADD);
			}
		}

		mav.addObject("product", product);
		mav.addObject("categories",
				categoryService.findAll().stream().map(cate -> cate.getType()).collect(Collectors.toList()));
		return mav;
	}
	
	@ResponseBody
    @RequestMapping("/sendSimpleEmail")
    public String sendSimpleEmail() {

        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setTo("tan.ngo.cit20@eiu.edu.vn");
        message.setSubject("Test Simple Email");
        message.setText("Hello, Im testing Simple Email");

        // Send Message!
        this.emailSender.send(message);

        return "Email Sent!";
    }

	

}

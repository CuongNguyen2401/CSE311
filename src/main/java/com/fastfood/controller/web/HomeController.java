package com.fastfood.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.ProductDTO;
import com.fastfood.entity.AccountEntity;
import com.fastfood.mapper.AccountMapper;
import com.fastfood.repository.UserRepository;
import com.fastfood.service.IAccountService;
import com.fastfood.service.IProductService;
import com.fastfood.utils.SecurityUtils;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

	@Autowired
	private IProductService productService;

	@Autowired
	IAccountService accountService;

	@Autowired
	AccountMapper accountConverter;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView homePage(@ModelAttribute("foodModel") ProductDTO foodModel,
			@ModelAttribute("drinkModel") ProductDTO drinkModel) {
		Pageable pageable = new PageRequest(0, 4);
		ModelAndView mav = new ModelAndView("web/home");
		foodModel.setListResult(productService.findByCategory_idAndStatus(1, pageable, SystemConstant.ACTIVE_STATUS));
//		foodModel.splitImg();
		drinkModel.setListResult(productService.findByCategory_idAndStatus(2, pageable, SystemConstant.ACTIVE_STATUS));
//		drinkModel.splitImg();
		mav.addObject("foodModel", foodModel);
		mav.addObject("drinkModel", drinkModel);
		return mav;
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detailPage(@RequestParam(name = "pid") long pid,
			@ModelAttribute("productDetail") ProductDTO productDetail, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("web/detail");
		productDetail = productService.findById(pid);
		
		mav.addObject("productDetail", productDetail);

		return mav;
	}

	@RequestMapping(value = "/category/**", method = RequestMethod.GET)
	public ModelAndView categoryPage(@ModelAttribute("foodModel") ProductDTO foodModel) {
		Pageable pageable = new PageRequest(0, 4);
		ModelAndView mav = new ModelAndView("web/category");
		
		
		return mav;
	}
	
	@RequestMapping(value = "/about-us", method = RequestMethod.GET)
	public ModelAndView categoryPage() {
		
		ModelAndView mav = new ModelAndView("web/aboutus");
		
		
		return mav;
	}

}

package com.fastfood.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fastfood.dto.AccountDTO;
import com.fastfood.mapper.AccountMapper;
import com.fastfood.repository.UserRepository;
import com.fastfood.service.IAccountService;
import com.fastfood.utils.MessageUtil;
import com.fastfood.utils.SecurityUtils;

@Controller(value = "userControllerOfWeb")
public class UserController {

	@Autowired
	private IAccountService accountService;

	@Autowired
	AccountMapper accountConverter;

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ModelAndView profileUserPage(@PathVariable("id") long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("web/user");

		if (!SecurityUtils.checkCurrentID(id)) {
			mav.setViewName("/error");
			return mav;
		}
		mav.addObject("user", accountService.findById(id));
		
		if (request.getParameter("msg") != null) {
			if (request.getParameter("msg").equals("success_change"))
				mav.addObject("msg", MessageUtil.SUCCESS_CHANGE);
			else {
				mav.addObject("msg", MessageUtil.ERROR_CHANGE);
			}
		}
		
		return mav;
	}
	
//	@RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
//	public ModelAndView changeUser(@PathVariable("id") long id,@RequestBody AccountDTO dto, HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView("web/user");
//		
//
//		if (!SecurityUtils.checkCurrentID(id)) {
//			mav.setViewName("/error");
//			return mav;
//		}
//		request.setAttribute("msg", MessageUtil.SUCCESS_UPDATE);
//		dto.setId(id);
//		mav.addObject("user", accountService.updateAccount(dto));
//		
//		return mav;
//	}

}

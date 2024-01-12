package com.fastfood.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.AccountDTO;
import com.fastfood.dto.OrderDTO;
import com.fastfood.entity.AccountEntity;
import com.fastfood.mapper.AccountMapper;
import com.fastfood.repository.UserRepository;
import com.fastfood.service.IAccountService;
import com.fastfood.service.IOrderService;
import com.fastfood.utils.MessageUtil;
import com.fastfood.utils.SecurityUtils;

@Controller(value = "cartControllerOfWeb")
public class CartController {
	
	@Autowired
	private IOrderService orderService;

	@Autowired
	IAccountService accountService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	AccountMapper accountMapper;
	
	
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public ModelAndView cartPage() {
		ModelAndView mav = new ModelAndView("web/cart");

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		AccountDTO account = null;
		if (authentication instanceof AnonymousAuthenticationToken) {
			account = new AccountDTO();
		} else {
			AccountEntity userEntity = userRepository.findOneByUserNameAndStatus(
					SecurityUtils.getPrincipal().getUsername(), SystemConstant.ACTIVE_STATUS);
			account = accountMapper.mapToDTO(userEntity);
		}

		mav.addObject("account", account);
		return mav;
	}

	@RequestMapping(value = "/cart", method = RequestMethod.POST)
	public ModelAndView cart(HttpServletRequest request, HttpSession session,
			@ModelAttribute("account") AccountDTO account) {
		
		ModelAndView mav = new ModelAndView();

		session = request.getSession();
		OrderDTO orderSession = (OrderDTO) session.getAttribute("cart");

		if (orderSession == null) {
			orderSession = new OrderDTO();

		}
		// check login and empty cart
		if (account.getUsername() == null) {
			request.setAttribute("msg", MessageUtil.ERROR_LOGIN);
		}
		if (orderSession.getItems().size() == 0) {
			request.setAttribute("msg", MessageUtil.ERROR_EMPTYCART);
		} else {
			orderSession.setAccountDTO(account);
			orderSession.setEmail(account.getEmail());
			orderSession.setAddress(account.getAddress());
			orderSession.setPhonenumber(account.getPhoneNumber());
			orderSession.setCustomerName(account.getFullName());
			request.setAttribute("msg", MessageUtil.SUCCESS_ORDER);
			orderService.save(orderSession);
		}
		session.removeAttribute("cart");

		mav.setViewName("web/cart");

		return mav;

	}

}

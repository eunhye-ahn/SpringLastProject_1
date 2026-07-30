package com.sist.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.service.FoodService;
import com.sist.vo.FoodVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FoodController {
	private final FoodService service;
	
	@GetMapping("food/detail_before.do")
	public String food_detail_ok(int no, HttpServletResponse response, RedirectAttributes ra) {
		
		//쿠키 생성 : 쿠키는 매개변수에 string,string
		Cookie cookie = new Cookie("food_"+no, String.valueOf(no));
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		
		response.addCookie(cookie);
		
		//보낼 매개변수가 많을 경우 => 리다이렉트 시, 매개변수 함께 전달
		ra.addAttribute("no",no);
		
		return "redirect:../food/detail.do";
	}
	
	@GetMapping("food/detail.do")
	public String food_detail(int no, Model model) {
		FoodVO vo = service.foodDetailData(no);
		model.addAttribute("vo",vo);
		model.addAttribute("main_jsp","../food/detail.jsp");
		return "main/main";
	}
}

package com.sist.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.service.FoodService;
import com.sist.vo.FoodVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	private final FoodService service;
	
	@GetMapping("main/main.do")
	public String main_main(String page, Model model, HttpServletRequest request) {
		if(page == null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		final int ROWSIZE = 12;
		int start = (curpage*ROWSIZE)-(ROWSIZE-1);
		int end = curpage*ROWSIZE;
		//offset : 0 / rownum : 1
		
		List<FoodVO> list = service.foodListData(start, end);
		int totalpage = service.foodTotalpage();
		
		final int BLOCK = 10;
		int startpage = ((curpage-1)/BLOCK*BLOCK)+1;
		int endpage = ((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endpage>totalpage) {
			endpage = totalpage;
		}
		
		//ÄíÅ°Ãâ·Â
		List<FoodVO> cList = new ArrayList<FoodVO>();
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(int i=cookies.length-1;i>=0;i--) {
				if(cookies[i].getName().startsWith("food_")) {
					if(cookies[i].getName().equals("food_null")) {
						continue;
					}
					String strno = cookies[i].getValue();
					FoodVO vo = service.foodDetailData(Integer.parseInt(strno));
					cList.add(vo);
				}
			}
		}
		
		model.addAttribute("size",cList.size());
		model.addAttribute("cList",cList);

		
		model.addAttribute("list",list);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("curpage",curpage);
		model.addAttribute("startpage",startpage);
		model.addAttribute("endpage",endpage);
		model.addAttribute("main_jsp","../main/home.jsp");
		return "main/main";
	}
}

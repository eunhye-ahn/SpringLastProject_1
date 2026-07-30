package com.sist.web;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.service.GoodsService;
import com.sist.vo.FoodVO;
import com.sist.vo.GoodsVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GoodsController {
	private final GoodsService service;
	
	@GetMapping("goods/list.do")
	public String goods_list(String page, Model model, HttpServletRequest request) {
		if(page == null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		int start = (curpage*12)-12;
		
		List<GoodsVO> list = service.goodsListData(start);
		int totalpage = service.goodsTotalpage();
		
		final int BLOCK = 10;
		int startpage = ((curpage-1)/BLOCK)+1;
		int endpage = ((curpage-1)/BLOCK)+BLOCK;
		if(endpage>totalpage) {
			endpage = totalpage;
		}
		
		//ÄíÅ°Ãâ·Â
		List<GoodsVO> cList = new ArrayList<GoodsVO>(); 
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(int i=cookies.length-1;i>=0;i--) {
				if(cookies[i].getName().startsWith("goods_")) {
					String strno = cookies[i].getValue();
					GoodsVO vo = service.goodsDetailData(Integer.parseInt(strno));
					cList.add(vo);
				}
			}
		}

		model.addAttribute("cList",cList);
		model.addAttribute("count",cList.size());
		
		System.out.println(cList);
		System.out.println(cList.size());
		
		model.addAttribute("list",list);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("curpage",curpage);
		model.addAttribute("startpage",startpage);
		model.addAttribute("endpage",endpage);
		model.addAttribute("main_jsp","../goods/list.jsp");
		return "main/main";
	}
	
	@GetMapping("goods/detail_before.do")
	public String goods_detail_ok(int no, HttpServletResponse response, RedirectAttributes ra) {
		
		Cookie cookie = new Cookie("goods_"+no, String.valueOf(no));
		response.addCookie(cookie);
		
		ra.addAttribute("no",no);
		
		return "redirect:../goods/detail.do";
	}
	
	@GetMapping("goods/detail.do")
	public String goods_detail(int no, Model model) {
		
		GoodsVO vo = service.goodsDetailData(no);
		
		model.addAttribute("vo",vo);
		model.addAttribute("main_jsp","../goods/detail.jsp");
		
		return "main/main";
	}
}

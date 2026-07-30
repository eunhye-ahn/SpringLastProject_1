package com.sist.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.GoodsVO;

public interface GoodsService {
	public List<GoodsVO> goodsListData(int start);
	
	public int goodsTotalpage();
	
	public GoodsVO goodsDetailData(int no);
}

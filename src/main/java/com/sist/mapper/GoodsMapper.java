package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
/**
 * 	private int no;
	private String goods_name,goods_sub,goods_price,goods_discount,
	goods_first_price,goods_delivery,goods_poster;
 *
 */

import com.sist.vo.GoodsVO;
public interface GoodsMapper {
	@Select("select no,goods_name,goods_price,goods_poster "
			+ "from goods_all "
			+ "order by no asc "
			+ "offset #{start} rows fetch next 12 rows only")
	public List<GoodsVO> goodsListData(int start);
	
	@Select("select ceil(count(*)/12.0) from goods_all")
	public int goodsTotalpage();
	
	@Select("select no,goods_name,goods_sub,goods_poster,goods_price,goods_discount,goods_delivery "
			+ "from goods_all "
			+ "where no=#{no}")
	public GoodsVO goodsDetailData(int no);
}

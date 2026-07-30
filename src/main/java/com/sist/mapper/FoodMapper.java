package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.FoodVO;

public interface FoodMapper {
	//인라인 뷰로 페이지네이션하는 방법
	@Select("select no,poster,address,name,num "
			+ "from (select no,poster,address,name,rownum as num "
			+ "from (select no,poster,address,name "
			+ "from food2 order by no asc)) "
			+ "where num between #{start} and #{end}")
	public List<FoodVO> foodListData(@Param("start")int start, @Param("end")int end);
	
	@Select("select ceil(count(*)/12.0) "
			+ "from food2")
	public int foodTotalpage();
	
	@Select("select no,poster,address,name,time,price,score,theme,parking "
			+ "from food2 "
			+ "where no=#{no}")
	public FoodVO foodDetailData(int no);
}

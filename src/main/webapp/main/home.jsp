<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row{
	margin: 0px auto;
	width:960px;
}
p{
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}
</style>
</head>
<body>
<div class="container">
	<div class="row">
		<c:forEach var="vo" items="${list}">
			<div class="col-md-3">
			    <div class="thumbnail">
			      <a href="../food/detail_before.do?no=${vo.no }">
			        <img src="${vo.poster }" title="${vo.address}" style="width:100%">
			        <div class="caption">
			          <p>${vo.name}</p>
			        </div>
			      </a>
			    </div>
			  </div>
		</c:forEach>				
	</div>
	<div class="row text-center" style="margin-top:10px">
		<ul class="pagination">
			<c:if test="${startpage>1 }">
				<li><a href="../main/main.do?page=${startpage-1 }">&laquo;</a></li>
			</c:if>
			<c:forEach var="i" begin="${startpage }" end="${endpage }">
				<li class="${i==curpage ? 'active':'' }"><a href="../main/main.do?page=${i }">${i }</a></li>
			</c:forEach>
			<c:if test="${endpage<totalpage }">
				<li><a href="../main/main.do?page=${endpage+1 }">&raquo;</a></li>
			</c:if>
		</ul>
	</div>
	<div class="row" style="margin-top:10px">
		<h2>최근 본 페이지</h2>
		<c:if test="${size<1 }">
			<h3>방문기록이 없습니다</h3>
		</c:if>
		<c:if test="${size>0 }">
			<c:forEach var="cvo" items="${cList }">
				<div style="width:100px;height:100px;display:inline-block;">
				      <a href="../food/detail_before.do?no=${cvo.no }">
				        <img src="${cvo.poster }" title="${cvo.address}" style="width:100%">
				      </a>
				 </div>
			</c:forEach>
		</c:if>
	</div>
</div>
</body>
</html>
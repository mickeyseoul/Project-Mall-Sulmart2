<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
recommand.jsp<br>

<table border="1">
  <tr>
  	<td>
  		상품 추천
  	</td>
  </tr>
  <tr>
  	<td>
  		<img src="<%=request.getContextPath()%>/resources/${ product.image }" width="50" height="50"><br>
  		${ product.name }<br>
  	</td>
  </tr>
  <tr>
  	<td>
  		<c:if test="${ product.product eq 1 }">
  			이런 안주는 어떠세요?<br>
  		</c:if>
  		<c:if test="${ product.product eq 2 }">
  			이런 술은 어떠세요?<br>
  		</c:if>
  		<c:forEach var="recommand" items="${ lists }">
  			<img src="<%=request.getContextPath()%>/resources/${ recommand.image }" width="100" height="100" title="${ recommand.name }" ><br>
  			${ recommand.name }<br>
  		</c:forEach>
  	</td>
  </tr>
</table>



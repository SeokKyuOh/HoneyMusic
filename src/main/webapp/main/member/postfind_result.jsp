<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function ok(zip,addr){
	// 100-111
	parent.join_frm.member_post1.value=zip.substring(0,3);		/*join.jsp의 join_frm 폼에 입력*/
	parent.join_frm.member_post2.value=zip.substring(4,7);
	parent.join_frm.member_addr1.value=addr;
	parent.Shadowbox.close();
}
</script>
</head>
<body>
	<center>
		<c:if test="${count==0 }">
			<table id="table_content" width=430>
				<tr>
					<td align=center>검색결과가 없습니다.</td>
				</tr>
			</table>
		</c:if>
		<c:if test="${count>0 }">
			<table id="table_content" width=430>
				<tr>
					<th width=15%>우편번호</th>
					<th width=55%>주소</th>
				</tr>
				<c:forEach var="vo" items="${list }">
					<tr class="dataTr">
					<td width="15%" align=center style="color: white">${vo.zipcode }</td>
					<td width="15%" align=center><a href="javascript:ok('${vo.zipcode }','${vo.address }')" class="post">${vo.address }</a>
					</td>
						
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</center>

</body>
</html>
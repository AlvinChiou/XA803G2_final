<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>前端-IBM ActRegister: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>前端-IBM ActRegister: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM ActRegister: Home</p>

<h3>資料查詢:</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<ul>
  <li><a href='listAllActRegister.jsp'>List</a> all ActRegisters. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actRegister/actRegister.do" >
        <b>輸入會員編號 :可查詢該會員報名哪些活動</b>
        <input type="text" name="memNo">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getAll_For_Display_By_MemNo">
    </FORM>
  </li>

  <jsp:useBean id="actRegSvc" scope="page" class="com.actregister.model.ActRegisterService" />
   
<!--   <li> -->
<%--      <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actRegister/actRegister.do" > --%>
<!--        <b>選擇活動編號:可查詢有哪些會員報名</b> -->
<!--        <select size="1" name="actRegNo"> -->
<%--          <c:forEach var="actRegVO" items="${actRegSvc.all}" >  --%>
<%--           <option value="${actRegVO.actRegNo}">${actRegVO.actRegNo} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="submit" value="送出"> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--     </FORM> -->
<!--   </li> -->
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="act.do" > -->
<!--        <b>選擇活動名稱:</b> -->
<!--        <select size="1" name="actRegNo"> -->
<%--          <c:forEach var="actRegVO" items="${actRegSvc.all}" >  --%>
<%--           <option value="${actRegVO.actRegNo}">${actRegVO.actRegName} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="submit" value="送出"> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--      </FORM> -->
<!--   </li> -->
</ul>


<!-- <h3>活動管理</h3> -->

<!-- <ul> -->
<!--   <li><a href='addActRegister.jsp'>Add</a> a new ActRegister.</li> -->
<!-- </ul> -->

</body>

</html>

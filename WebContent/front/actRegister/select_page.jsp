<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>�e��-IBM ActRegister: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>�e��-IBM ActRegister: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM ActRegister: Home</p>

<h3>��Ƭd��:</h3>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
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
        <b>��J�|���s�� :�i�d�߸ӷ|�����W���Ǭ���</b>
        <input type="text" name="memNo">
        <input type="submit" value="�e�X">
        <input type="hidden" name="action" value="getAll_For_Display_By_MemNo">
    </FORM>
  </li>

  <jsp:useBean id="actRegSvc" scope="page" class="com.actregister.model.ActRegisterService" />
   
<!--   <li> -->
<%--      <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actRegister/actRegister.do" > --%>
<!--        <b>��ܬ��ʽs��:�i�d�ߦ����Ƿ|�����W</b> -->
<!--        <select size="1" name="actRegNo"> -->
<%--          <c:forEach var="actRegVO" items="${actRegSvc.all}" >  --%>
<%--           <option value="${actRegVO.actRegNo}">${actRegVO.actRegNo} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="submit" value="�e�X"> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--     </FORM> -->
<!--   </li> -->
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="act.do" > -->
<!--        <b>��ܬ��ʦW��:</b> -->
<!--        <select size="1" name="actRegNo"> -->
<%--          <c:forEach var="actRegVO" items="${actRegSvc.all}" >  --%>
<%--           <option value="${actRegVO.actRegNo}">${actRegVO.actRegName} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="submit" value="�e�X"> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--      </FORM> -->
<!--   </li> -->
</ul>


<!-- <h3>���ʺ޲z</h3> -->

<!-- <ul> -->
<!--   <li><a href='addActRegister.jsp'>Add</a> a new ActRegister.</li> -->
<!-- </ul> -->

</body>

</html>

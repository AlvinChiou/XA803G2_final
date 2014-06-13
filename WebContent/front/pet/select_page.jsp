<%@ page contentType="text/html; charset=Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pet.model.*"%>

<html>
<head><title>IBM Pet: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM Pet: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM Pet: Home</p>

<h3>�d���d��:</h3>
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
  <li><a href='listAllPet.jsp'>List</a> all Pets. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/pet/pet.do" >
        <b>��J�|���s�� (�p7002):</b>
        <input type="text" name="memNo" value="${memNo}">
        <input type="submit" value="�e�X">
        <input type="hidden" name="action" value="getAll_For_Display_From_Member">
    </FORM>
  </li>
  <li>
     <FORM METHOD="post" ACTION="pet.do" >
       <b>�п���d��:</b>
     
       <select size="1" name="petNo">
         <c:forEach var="petVO" items="${memPetVO}" > 
         	<option value="${petVO.petNo}">${petVO.petName}
         </c:forEach>   
       </select>
      
       <input type="submit" value="�e�X">
       <input type="hidden" name="action" value="getOne_For_Display_From_PetNo">
    </FORM>
  </li>
 
  
</ul>


<h3>�d���޲z</h3>

<ul>
  <li><a href='addPet.jsp'>Add</a> a new Pet.</li>
</ul>

</body>

</html>

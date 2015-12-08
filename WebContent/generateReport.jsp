<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>


<script src="jquery-1.6.2.min.js"></script>
<script>
  function redirect(elem){
	     elem.setAttribute("action","appointment.jsp");
	     elem.submit();
	}
  
<script src="jquery-ui-1.8.15.custom.min.js"></script>
<link rel="stylesheet" href="jquery/jqueryCalendar.css">
<title>hams</title>
<style type="text/css">
 tr:nth-child(even) {
    background-color: #00FF00;
}

#testTable tr:nth-child(odd) {
    background-color: #FFFF00;
}

</style>

<script>
 var tableToExcel = (function() {
      var uri = 'data:application/vnd.ms-excel;base64,'
      	, template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
        , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
        , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
      return function(table, name) {
        if (!table.nodeType) table = document.getElementById(table)
        var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
        window.location.href = uri + base64(format(template, ctx));
      }
    })()
</script>
<link rel="stylesheet" type="text/css" href="hams.css">

</head>
<body>
<div style=" margin-top: 0px; margin-right:45px"><b>
      <span style="float:right">
      	<% 
			String username = (String)request.getSession().getAttribute("name"); 
			out.println("Welcome "+username);
		%><br>
		<a href="LogoutServlet">Logout</a>
      </span>

</b>
</div>



<div style="margin-left: 45px; margin-top: 10px; position: absolute;  "><b>
	 <a href="appointment.jsp" onsubmit=" redirect(this);">home</a></b>
</div>

<center>
<div id="login-overlay" class="modal-dialog"  style="width:1000px; margin-top: 100px;">
     <div class="modal-content" id="modal-content" >
          <div class="modal-header" id="modal-header">
<table style = "padding-bottom:10px;  display:block; ">

<tr>


<td style = "padding-right: 324px;">
	<form action="GenerateReportServlet" method="post">
  
  	From 
  		<input type="date" name="initial_date">
  
  
  	To
  		<input type="date" name="final_date">
  		<input type="submit">
  
</form>
</td>
<td>			
		<input type="button"   onclick="tableToExcel('testTable', 'W3C Example Table')" value="Export to Excel">	
</td>
</tr>
<tr></tr>
			
</table>

<form>


	 
	 
	<div id="div2"> 
        <table border="1" id="testTable">
            <tr>
                <tr>
                	<th>id</th>
					<th>Patient_Name</th>
                	<th>Clinic_detail</th>
                	<th>Appointment_time</th>
                	<th>Patient_mobile_number</th>
                	<th>User_name</th>
                	<th>appointment_date</th>
                	<th>appointment_booked_date</th>
                </tr>
                 
               <s:forEach items="${data}" var="item">
    			<tr>
      				<td><s:out value="${item.appointment_id}" /></td>
      				<td><s:out value="${item.patient_name}" /></td>
      				<td><s:out value="${item.clinic_detail}" /></td>
      				<td><s:out value="${item.time}" /></td>
      				<td><s:out value="${item.patient_mobile_number}" /></td>
      				<td><s:out value="${item.user_name}" /></td>
      				<td><s:out value="${item.appointment_date}" /></td>
      				<td><s:out value="${item.appointment_booked_date}" /></td>

      						
    			 </tr>
  				</s:forEach>
  			
  		 </table>
  	</div>
  			
  			<div style="float:right"> Total Appointment : <s:out value="${size}" /> </div>   

</form>
            


</div>
</div>
</div>
</center>

</body>
</html>
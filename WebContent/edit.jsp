<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <script src="validation.js"></script>

<link rel="stylesheet" type="text/css" href="hams.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>hams</title>
</head>
<body>
<div style=" margin-top: 45px; margin-right:45px ;float:right"><b>
      	<% 
			String username = (String)request.getSession().getAttribute("name"); 
			out.println("Welcome "+username);
		%><br>
      	<a href="LogoutServlet">Logout</a>
</b></div>

<img src = "images/HAMSLogo.jpg" alt="hamslogo" id="img1" style=" margin-left: 45px; "/> 
<% 
			Boolean admin_check = (Boolean)request.getSession().getAttribute("hide_report");
			if(admin_check){
			%>	
				<div style="margin-left: 45px; margin-top: 50px; position: absolute;"><b>
					<a href="generateReport.jsp" >Appointment Report</a></b>
				</div>
			<% 
			}
%>

<center>
<div id="login-overlay" class="modal-dialog" style="width:800px; ">
     <div class="modal-content" >
          <div class="modal-header" >

<h1>HAMS</h1>
	<p><b><font size=5>Clinic Appointment</font></b></p>
	<p><font size=1><sup>*</sup> Required Fields</font></p>

<form name="myForm" action="AppointmentServlet" method="post" onsubmit="return validate()">
	<div class="form-group">
<table >

	<tr class="row1">
	 	 <td><b>Patient mobile Number: <sup>*</sup></b> </td>
	 	 <td><input type="text" id="patient_mobile_number" name="patient_mobile_number" value="${sessionScope.patient_mobile_number}" placeholder="mobile_number" size=50 maxlength=20 /></td>
	</tr>

	<tr class="row2">
		<td><b>Patient Name: <sup>*</sup></b></td>
		<td><input type="text" id="patient_name" name="patient_name" value="${sessionScope.patient_name}" placeholder="patient_name" size=50 maxlength=5000 /> </td>
	</tr>

	<tr class="row3"> 
	 	<td><b>Clinic Detail: <sup>*</sup></b> </td>
	 	<td><input type="text" id="clinic_detail" name="clinic_detail" value="${sessionScope.clinic_detail}" placeholder="clinic_detail" size=50 maxlength=5000  /></td>
	 </tr>  

	<tr class="row4">
		<td style="padding-right: 10px"><b>Approx Appointment Time: <sup>*</sup></b></td>
		<td><input type="text" id="time" name="time" value="${sessionScope.time}" placeholder="time" size=50 maxlength=5000 /> </td>
	</tr>
	
	<tr class="row5"> 
	 	<td style="padding-right: 10px"><b>Appointment Date: <sup>*</sup></b> </td> 
	 	<td><input type="date" name="appointment_date" required value="${sessionScope.appointment_date} placeholder="appointment_date" "></td> 
	 </tr>
	
</table>

<input type="submit" name="submit" value="submit" style="margin-top: 20px;  "/>
</div>
</form>
		</div>
	</div>
</div>
</center>
</body>

</html>

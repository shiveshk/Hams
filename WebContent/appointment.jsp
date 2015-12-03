<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <script src="validation.js"></script>
  
  <script>
  function redirect(elem){
	     elem.setAttribute("action","generateReport.jsp");
	     elem.submit();
	}
  </script>
<link rel="stylesheet" type="text/css" href="hams.css">
<meta name="viewport" content="width=device-width, initial-scale=1">


<title>hams</title>
</head>

<body>
<div style=" margin-top: 45px; margin-right:45px"><b>
      <span style="float:right">
      	<% 
			String username = (String)request.getSession().getAttribute("name"); 
			out.println("Welcome "+username);
		%><br>
		<a href="LogoutServlet">Logout</a>
      </span>

</b></div>

	<img src = "images/HAMSLogo.jpg" alt="hamslogo" id="img1" style=" margin-left: 45px; "/>

<div style="margin-left: 45px; margin-top: 100px; position: absolute;  "><b>
	 <a href="generateReport.jsp" onsubmit=" redirect(this);">Appointment Report</a></b>
</div>

<div class="container" >

<div  style= "text-align:center">
	<h3>
<%
	String message = (String)request.getSession().getAttribute("message_response");
	if( message != null)
	{
		out.println(message);
	} 
	session.setAttribute("message_response", "");
%>
	</h3>
</div>

<div  style= "text-align:center">
	<h3>
<%
	String message1 = (String)request.getSession().getAttribute("admin_check_response");
	if( message1 != null)
	{
		out.println(message1);
	} 
	session.setAttribute("admin_check_response", "");
%>
	</h3>
</div>

<center>

<div id="login-overlay" class="modal-dialog"  style="width:800px; margin-bottom: 0px; margin-top: 0px;  ">
     <div class="modal-content" id="modal-content" >
          <div class="modal-header" id="modal-header">
				<h1>HAMS</h1>


					<p><b><font size=5>Clinic Appointment</font></b></p>
					<p><font size=1><b><sup>*</sup></b> Required Fields</font></p>



<form name="myForm" action="AppointmentServlet" method="post"  onsubmit="return validate()" >
 	<div class="form-group">
<table >
 <tbody>
 	
 	 <tr class="row1">
	 	 <td><b>Patient mobile Number: <sup>*</sup></b> </td>
	 	 <td><input type="text" id="patient_mobile_number" name="patient_mobile_number"  size=50 maxlength=20 /></td>
	 </tr>
 	
	 <tr class="row2">
	 	 <td><b>Patient Name: <sup>*</sup></b> </td>
	 	 <td><input type="text" id="patient_name" name="patient_name"  size=50 maxlength=5000 /></td>
	 </tr> 
	 
	 

	 <tr class="row3"> 
	 	<td><b>Clinic Detail: <sup>*</sup></b> </td>
	 	<td><input type="text" id="clinic_detail" name="clinic_detail"  size=50 maxlength=5000  /></td>
	 </tr> 
	 

	 <tr class="row4"> 
	 	<td style="padding-right: 10px"><b>Approx Appointment Time: <sup>*</sup></b> </td> 
	 	<td><input type="text" id="time" name="time"  size=50 maxlength=5000 /></td> 
	 </tr>
	 
	 
	 <tr class="row5"> 
	 	<td style="padding-right: 10px"><b>Appointment Date: <sup>*</sup></b> </td> 
	 	<td><input type="date" id="appointment_date" name="appointment_date"   required></td> 
	 </tr>
	 
 </tbody>	 
	  
</table> 
	<br /><input type="submit" name="submit" value="submit" />
	</div>
</form>
		</div>
	</div>
</div>

</center>
</div>

</body>
</html>
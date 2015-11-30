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

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

<title>hams</title>

<style>
​
.rela {
    position: relative;
    left: 24px;
    
}

.relative {
    position: relative;
    left: 10px;
    border: 1px solid #73AD21;
}

#topcorner{
    position:absolute;
    margin: 0 0 100px 100px;
 }
</style>


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
	
<div style=" margin-left: 45px; margin-top: 15px;">
	<form name="generate_report" action="" method="post" onsubmit="redirect(this);">
		<input type="submit" name="act" value="Generate Report" />
	</form>	 
</div>

<div class="container" >

<div class="rela">
<h3>
<center>

<%
	String message = (String)request.getSession().getAttribute("message_response");
	if( message != null)
	{
		out.println(message);
	} 
	session.setAttribute("message_response", "");
%>

</center>
</h3>
</div>


<center>
<div id="login-overlay" class="modal-dialog"  style="width:800px;">
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
	 	<td><input type="date" name="appointment_date"></td> 
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
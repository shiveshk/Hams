<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css" href="hams.css">

<meta charset="utf-8">
<title>Hams</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="assets/css/bootstrap.css" rel="stylesheet">
<link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
</head>
<body>

<img src = "images/HAMSLogo.jpg" alt="hamslogo" id="img1" style=" margin-left: 45px; "/>


<div style="margin-left: 45px; margin-top: 10px; position: absolute;  "><b>
	 <a href="appointment.jsp">home</a></b>
</div>

<center>

<div id="login-overlay" class="modal-dialog"  style="width:1000px;">
     <div class="modal-content" id="modal-content" >
          <div class="modal-header" >
				<h1>HAMS</h1>
					<p><b><font size=5>Clinic Registration Form</font></b></p>
					<p><font size=1><b><sup>*</sup></b> Required Fields</font></p>
<form name="myForm" action="DoctorRegistrationServlet" method="post" id="myForm">
 	<div class="form-group">
<table >
 <tbody>
 	
 	 <tr class="row1" >
	 	 <td><b>Clinic Name: <sup>*</sup></b> </td>
	 	 <td style="padding-right: 10px"><input type="text" id="clinic_name" name="clinic_name"  size=32 maxlength=5000  placeholder="clinic_name" title="Please enter your Clinic Name" required/></td>
	 	 
	 	 
	 	 	
	 	 <td style="padding-right: 10px"><b>Doctor Experience: <sup>*</sup></b> </td> 
	 	 <td><select name="doctor_experience" type="text" id="doctor_experience" >
  			<option value="0">0</option>
  			<option value="1">1</option>
  			<option value="2">2</option>
  			<option value="3">3</option>
  			<option value="4">4</option>
  			<option value="5">5</option>
  			<option value="6">6</option>
  			<option value="7">7</option>
  			<option value="8">8</option>
  			<option value="9">9</option>
  			<option value="10">10</option>
  			<option value="11">11</option>
  			<option value="12">12</option>
  			<option value="13">13</option>
  			<option value="14">14</option>
  			<option value="15">15</option>
  			<option value="16">16</option>
  			<option value="17">17</option>
  			<option value="18">18</option>
  			<option value="19">19</option>
  			<option value="20">20</option>
  			<option value="21">21</option>
  			<option value="22">22</option>
  			<option value="23">23</option>
  			<option value="24">24</option>
  			<option value="25">25</option>
  			<option value="26">26</option>
  			<option value="27">27</option>
  			<option value="28">28</option>
  			<option value="29">29</option>
  			<option value="30">30</option>
		  </select>
		</td>
	 </tr>
 	
	 <tr class="row2">
	 	 <td><b>Clinic Address: <sup>*</sup></b> </td>
	 	 <td><input type="text" id="address" name="address"  size=32 maxlength=5000 placeholder="clinic_address" title="Please enter Clinic Address" required/></td>
	 	
	 	 <td style="padding-right: 10px"><b>Agreement Mode: <sup>*</sup></b> </td> 
	 	 <td><select name="agreement_mode" type="text" id="agreement_mode" >
  			<option value="Per Patient Visit">Per Patient Visit</option>
  			<option value="Montholy">Montholy</option>
  			<option value="weekly">weekly</option>  			
		  </select>
		 </td>
	 	
	 </tr> 
	
	 
	 <tr class="row3"> 
	 	<td><b>Contact number: <sup>*</sup></b> </td>
	 	<td><input type="text" id="contact_number" name="contact_number"  size=32 maxlength=5000  placeholder="contact_number" title="Please enter Contact Number" required/></td>
	 	
	 	<td style="padding-right: 10px"><b>Payment Cycle: <sup>*</sup></b> </td> 
	 	
	 	<td><select name="payment_cycle" type="text" id="payment_cycle" title="Please enter Payment Cycle" required>
  			<option value="weekly">Weekly</option>
  			<option value="bi_weekly">Bi-Weekly</option>  			
		  </select>
		 </td>
	 </tr> 
	 
	 
	 <tr class="row4"> 
	 	<td style="padding-right: 10px"><b>email: <sup>*</sup></b> </td> 
	 	<td><input type="text" id="email" name="email"  size=32 maxlength=5000 placeholder="email" title="Please enter email" required/></td>
	 	
	 	<td style="padding-right: 10px"><b>Emergency Contact number: <sup>*</sup></b> </td> 
	 	<td><input type="text" id="emergency_contact_number" name="emergency_contact_number"  size=25 placeholder="emergency_contact_number" title="Please enter Emergency Contact number"  ></td> 	 	
	 </tr>
	 
	 <tr class="row5"> 
	 	<td style="padding-right: 10px"><b>Authorised Doctor Name: <sup>*</sup></b> </td> 
	 	<td><input type="text" id="authorised_doctor_name" name="authorised_doctor_name"   size=32 maxlength=5000 placeholder="authorised_doctor_name" title="Please enter Authorised Doctor Name" required ></td> 
	 
	 	<td style="padding-right: 10px"><b>Secondary Contact number: <sup>*</sup></b> </td> 
	 	<td><input type="text" id="secondary_contact_number" name="secondary_contact_number"  size=25 placeholder="secondary_contact_number" title="Please enter Secondary Contact number"  ></td> 	 	
		
	 </tr>
	 
	 <tr class="row6"> 
	 	
	 	
	 	<td style="padding-right: 10px"><b>Service Timing: <sup>*</sup></b> </td> 
	 	<td><input type="text" id="doctor_experience" name="doctor_experience"  size=32 maxlength=5000 placeholder="doctor_experience" title="Please enter Doctor Experience" required/></td>
	 	<td style="padding-right: 10px"><b>Agreement Date:<sup>*</sup></b> </td> 
	 	<td><input type="date" id="agreement_date" name="agreement_date"  size=25 placeholder="agreement_date" title="Please enter Agreement Date" required ></td> 	 	
	 	
	 </tr>
	 
	 <tr class="row7"> 
	 
		 <td style="padding-right: 10px"><b>Doctor Specialization: <sup>*</sup></b> </td> 
	 	 <td><input type="text" id="doctor_specialization" name="doctor_specialization"  size=25 maxlength=5000 placeholder="doctor_specialization" title="Please enter Doctor Specialization" required/></td>  
	 	
	 	
	 	<td style="padding-right: 10px"><b>Clinic-Hams Live Date: <sup>*</sup></b> </td> 
	 	<td><input type="date" id="live_date" name="live_date"  size=25 placeholder="live_date" title="Please enter Live Date" required ></td> 
	 </tr>
	 
	 <tr class="row8">
	 	<td style="padding-right: 10px"><b>Price Quota: <sup>*</sup></b> </td> 
	 	<td><input type="text" id="price_quota" name="price_quota"  size=25 placeholder="price_quota" title="Please enter Price Quota" required ></td> 	
	 
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
</body>
</html>
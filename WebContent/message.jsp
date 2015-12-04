<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <script src="validation.js"></script>
  
<script type="text/javascript">

function redirect(elem){
	     elem.setAttribute("action","appointment.jsp");
	     elem.submit();
	}

function myFunction(){
	document.getElementById("wait_message").innerHTML = "message is being sent please wait";
	
}

function myFunction1(){
	document.getElementById("wait_message").innerHTML = "please wait edit page is loading";
	
}


</script>

<link rel="stylesheet" type="text/css" href="hams.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

<title>hams</title>
<style type="text/css">
#ok {
    width: 200px  ;
}

.rela {
    position: relative;
    left: 24px;
    
}

td:nth-of-type(2) {
   padding-right: 100px;
}

#wait_message
{
    color:black;
    font-weight:bold;
    margin:0px 0px 10px;
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


<div style="margin-left: 45px; margin-top: 10px; position: absolute;  "><b>
	 <a href="appointment.jsp" onsubmit=" redirect(this);">home</a></b>
</div>


<div class="rela">
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




<center>

<div id="wait_message" > </div>


<div id="login-overlay" class="modal-dialog" style="width:800px;">
     <div class="modal-content" >
          <div class="modal-header" >
<div >
	<b>
		<% 
			StringBuilder message1 = (StringBuilder)request.getSession().getAttribute("message"); 
			out.println(message1);	
		%>
	</b>
</div>	

<form action="ConfirmAppointmentServlet" method="post" style="margin-top:20px;">
<div class="form-group">
<table >
 <tbody>
	<tr class="row1">
	
		<td style="padding-right: 10px"><b>to save and send message </b></td>
		<td id="ok" onclick="myFunction()" ><input type="submit" name="act" value="ok" /></td>
		
		
		<td style="padding-right: 10px"><b>to edit message </b></td>
		<td id="edit" onclick="myFunction1()" ><input type="submit" name="act" value="edit" /></td>
	</tr>
	
</tbody>
</table>
</div>
</form>
			</div>
		</div>
</div>
</center>

</body>
</html>
package com.hams.appointment;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


@WebServlet("/AppointmentServlet")


public class AppointmentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static final Logger LOGGER = Logger.getLogger(com.hams.appointment.AppointmentServlet.class);
				
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException ,NullPointerException {
		
		LOGGER.info("entered into AppointmentServlet ");
			
		
		//get data from index.jsp and save in local variables
		String patient_mobile_number = null;
		String patient_name = null;
		String clinic_detail = null;
		String time = null;
		String appointment_date1 = null;
		
		
		patient_mobile_number = request.getParameter("patient_mobile_number");
		patient_name = request.getParameter("patient_name");
		clinic_detail = request.getParameter("clinic_detail");
		time = request.getParameter("time");
		appointment_date1 = request.getParameter("appointment_date");
		
		
		Date appointment_date = null;
		SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		
		
		try {
			appointment_date = originalFormat.parse(appointment_date1);
			
		} catch (ParseException e1) {
			
			e1.printStackTrace();
		}
		
		
		
		//use string builder to build message
		StringBuilder message = null;
		try {
			message = new StringBuilder(" Dear " + patient_name + " Confirmed Appt :  " + clinic_detail + 
					". Approx Appointment time  " + time +  ". Appointment Date "+ appointment_date1 + " . To cancel your appointment SMS CANCEL to 9108053229 .Call 18002700673 ");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
		}
		
		//if message needed to be updated then store it in session to get further it in jsp to edit
		HttpSession session = request.getSession();
		session.setAttribute("patient_mobile_number", patient_mobile_number);
		session.setAttribute("patient_name", patient_name);
        session.setAttribute("clinic_detail", clinic_detail);
        session.setAttribute("time", time);
        session.setAttribute("message", message);
        session.setAttribute("appointment_date", appointment_date1);
        
        
        
        
        //after clicking submit in appointment.jsp control will go to message.jsp to show message 
        String nextJSP = "/message.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		try {
			dispatcher.forward(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
			LOGGER.error(e);
		}
		LOGGER.info("exiting from AppointmentServlet ");
		
	}


}
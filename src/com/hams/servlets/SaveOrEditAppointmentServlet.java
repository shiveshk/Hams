package com.hams.servlets;

 

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.hams.data.Appointment;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;



@WebServlet("/SaveOrEditAppointmentServlet")
public class SaveOrEditAppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOGGER = Logger.getLogger(SaveOrEditAppointmentServlet.class);
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveOrEditAppointmentServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		
		HttpSession session = request.getSession(true);
		String message = session.getAttribute("message").toString();
		String patient_mobile_number = session.getAttribute("patient_mobile_number").toString();
		String patient_name = session.getAttribute("patient_name").toString();
		String clinic_detail = session.getAttribute("clinic_detail").toString();
		String time = session.getAttribute("time").toString();
		
		
		
		String act = request.getParameter("act");
		if(act.equals("edit"))
		{
			//edit button was pressed so we don't need to save data there may be need to edit of data so send user at edit.jsp
			
			String nextJSP = "/edit.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			try {
				dispatcher.forward(request,response);
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
		
		if(act.equals("ok"))
		{
			//send button was pressed so we save data in our database and send message to patient side

	        
	      //creating configuration object  
			Configuration cfg = null;
			try {
				cfg = new Configuration();  
				//populates the data of the configuration file 
				cfg.configure("hibernate.cfg.xml"); 
			} catch (HibernateException e) {
				e.printStackTrace();
				LOGGER.info(e);
				
			}
		    // creating session factory object  
		    SessionFactory factory = null;
			try {
				factory = cfg.buildSessionFactory();
			} catch (HibernateException e) {
				e.printStackTrace();
				LOGGER.info(e);
			}  
		      
		    //creating session object  
		    Session session1 = null;
			try {
				session1 = factory.openSession();
			} catch (HibernateException e) {
				e.printStackTrace();
				LOGGER.info(e);
			}  
		      
		    //creating transaction object  
			Transaction t = null;
			try {
				t = session1.beginTransaction();
			} catch (HibernateException e) {
				e.printStackTrace();
				LOGGER.info(e);
				
			}  
			// 1) create a java calendar instance
			Calendar calendar = Calendar.getInstance();
			 
			// 2) get a java.util.Date from the calendar instance.
			//this date will represent the current instant, or "now".
			java.util.Date now = calendar.getTime();
			 
			// 3) a java current time (now) instance
			java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
					
		    Appointment appointment = new Appointment();  
		    appointment.setClinic_detail(clinic_detail);
		    appointment.setPatient_name(patient_name);
		    appointment.setTime(time);
		    appointment.setPatient_mobile_number(patient_mobile_number);
		    appointment.setTime_stamp(currentTimestamp);
		    
		    
		    //persisting the object
		    try {
				session1.save(appointment);  
			} catch (HibernateException e) {
				
				e.printStackTrace();
				LOGGER.info(e);
			}
		    
		    //transaction is committed 
		    t.commit(); 
		    try {
				session1.close();
			} catch (HibernateException e) {
				
				e.printStackTrace();
				LOGGER.info(e);
			}  
		      
		    System.out.println("successfully saved");
		    
	        
		    /* post method application to request for bhashsms api, so that patient get message  
		    set 7 urlParameters like user ,password ,sender ,phone ,message ,priority and type needed to use api of bhashsms*/
		    
		    String urlParameters  = "user=hamsind&pass=12345&sender=HAMSIN&phone="+patient_mobile_number+"&text="+message+"&priority=ndnd&stype=normal";
		    byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		    //find length of postData
		    
		    int    postDataLength = postData.length;
		    String request1 = "http://bhashsms.com/api/sendmsg.php";
		    URL    url            = new URL( request1 );
		    HttpURLConnection conn = null ;
			try {
				conn = (HttpURLConnection) url.openConnection();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}           
		    conn.setDoOutput( true );
		    conn.setInstanceFollowRedirects( false );
		    conn.setRequestMethod( "POST" );
		    conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		    conn.setRequestProperty( "charset", "utf-8");
		    conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
		    conn.setUseCaches( false );
		    try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
		       wr.write( postData );
		    }

	        
		    /* after sending message to patient side return user to appointment.jsp so user can do appointment for next patient */
	        
		    String nextJSP = "/appointment.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			
			try {
				dispatcher.forward(request,response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch(IOException e ){
				e.printStackTrace();
			}
		    
	        int responseCode = 0;
			try {
				responseCode = conn.getResponseCode();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
	        System.out.println("POST Response Code :: " + responseCode);
	        }
		}
	     
    }



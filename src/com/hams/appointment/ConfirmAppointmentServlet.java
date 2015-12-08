package com.hams.appointment;

 

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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



@WebServlet("/ConfirmAppointmentServlet")


public class ConfirmAppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOGGER = Logger.getLogger(ConfirmAppointmentServlet.class);
	
	//make sessionfactory object static so we will need this only once
	
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	//build session factory
	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	

	
       
   

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		LOGGER.info("entered into ConfirmAppointmentServlet");
		
	//getting data from session to store in database and to use in message
		
		HttpSession session = request.getSession(true);
		String message = session.getAttribute("message").toString();
		String patient_mobile_number = session.getAttribute("patient_mobile_number").toString();
		String patient_name = session.getAttribute("patient_name").toString();
		String clinic_detail = session.getAttribute("clinic_detail").toString();
		String time = session.getAttribute("time").toString();
		String user_name = session.getAttribute("name").toString();
		String appointment_date1 = session.getAttribute("appointment_date").toString();
		
		
		
	//check which button by user is clicked send or edit
		
		String act = request.getParameter("act");
		
	//entered into if construct of ConfirmAppointmentServlet to decide if edit button hit by user from message.jsp and send user to edit page
		
		if(act.equals("edit"))
		{
			LOGGER.info("entered into if construct of ConfirmAppointmentServlet to decide if edit button hit by user from message.jsp and send user to edit page");
			
				//edit button was pressed so we don't need to save data there may be need to edit of data so send user at edit.jsp
			
			
			String nextJSP = "/edit.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			try {
				dispatcher.forward(request,response);
			} catch (ServletException e) {
				e.printStackTrace();
				LOGGER.error(e);
			}
			
			LOGGER.info("exit from if for edit construct of ConfirmAppointmentServlet ");
		}
		
	/*entered into if construct of ConfirmAppointmentServlet to decide if send button hit by user from message.jsp
		and save data entered by user in database and send message to patients*/
		
		if(act.equals("ok"))
		{	
			LOGGER.info("entered into if construct of ConfirmAppointmentServlet as send button clicked so save data and send message");
			
			
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
				LOGGER.info("message is being sent ");
				
				conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput( true );
			    conn.setInstanceFollowRedirects( false );
			    conn.setRequestMethod( "POST" );
			    conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
			    conn.setRequestProperty( "charset", "utf-8");
			    conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
			    conn.setUseCaches( false );
			    
			    
			  // Creates a new data output stream to write data to the connection object conn , to send data at bhassms.
			    
			    try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
				       wr.write( postData );
				    } catch (IOException e)
				    {
				    	e.printStackTrace();
						LOGGER.error(e);
				    }
			    
			    int responseCode = 0;
			    
				try {
					responseCode = conn.getResponseCode();
					
					if(responseCode == 200)
					{
						LOGGER.info("response code is 200 message went successfully ");
						
						session.setAttribute("message_response", "message successfully sent");	
						
						 /* after sending message to patient side return user to appointment.jsp so user can do appointment for next patient */
				        
					    String nextJSP = "/appointment.jsp";
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
						
						try {
							dispatcher.forward(request,response);
						} catch (ServletException e) {
							e.printStackTrace();
							LOGGER.error(e);
						} catch(IOException e ){
							e.printStackTrace();
							LOGGER.error(e);
						}
					}
					
					else
					{
						session.setAttribute("message_response", "message not sent please try again");
						
						 /* if message not sent successfully then tell to send again same message */
				        
					    String nextJSP = "/message.jsp";
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
						
						try {
							dispatcher.forward(request,response);
						} catch (ServletException e) {
							e.printStackTrace();
							LOGGER.error(e);
						} catch(IOException e ){
							e.printStackTrace();
							LOGGER.error(e);
						}
					}
				} catch (IOException e) {
					
					e.printStackTrace();
					LOGGER.error(e);
				}
				
		        
		        
		        }


			 catch (IOException e1) {
				
				e1.printStackTrace();
				LOGGER.error(e1);
			} finally {
			    
			    if (conn != null) {
			        conn.disconnect();
			    }
			}          
			
	        
	        }
			
	//send button was pressed so we save data in our database
	// creating session factory object  
			
			SessionFactory factory = getSessionFactory();
			Session session1 = null;
			try {
				session1 = factory.openSession();
			} catch (HibernateException e2) {
				
				e2.printStackTrace();
				LOGGER.error(e2);

			}
			
			
			
	//creating transaction object  
			
			Transaction t = null;
			try {
				t = session1.beginTransaction();
			} catch (HibernateException e) {
				e.printStackTrace();
				LOGGER.error(e);
				
			}  
			// 1) create a java calendar instance
			
			Calendar calendar = Calendar.getInstance();
			 
			// 2) get a java.util.Date from the calendar instance.
			//this date will represent the current instant, or "now".
			
			java.util.Date now = calendar.getTime();
			
			 
			// 3) a java current time (now) instance
			Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
			
			Appointment appointment = new Appointment();  
		    appointment.setClinic_detail(clinic_detail);
		    appointment.setPatient_name(patient_name);
		    appointment.setTime(time);
		    appointment.setPatient_mobile_number(patient_mobile_number);
		    appointment.setTime_stamp(currentTimestamp);
		    appointment.setUser_name(user_name);
		    appointment.setAppointment_date(appointment_date1);
		    appointment.setAppointment_booked_date(( new SimpleDateFormat( "yyyy-MM-dd" ) ).format( Calendar.getInstance().getTime() ));
		    
		    
		    //persisting the object
		    
		    try {
				session1.save(appointment);  
			} catch (HibernateException e) {
				
				e.printStackTrace();
				LOGGER.error(e);
			}
		    
		    //transaction is committed 
		    
	    t.commit(); 
	    
	    //close session 
	    try {
			session1.close();
		} catch (HibernateException e) {
			
			e.printStackTrace();
			LOGGER.error(e);
		}  
			
			
			

	        
		    
				LOGGER.info("exiting from ConfirmAppointmentServlet ");
		}
}
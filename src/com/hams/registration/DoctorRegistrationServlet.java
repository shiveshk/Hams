package com.hams.registration;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.hams.data.Registration;



/**
 * Servlet implementation class DoctorRegistrationServlet
 */
@WebServlet("/DoctorRegistrationServlet")
public class DoctorRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOGGER = Logger.getLogger(com.hams.registration.DoctorRegistrationServlet.class);
	
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
	
	
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("entered into DoctorRegistrationServlet ");
			
		
		//get data from index.jsp and save in local variables
		String clinic_name = null;
		String address = null;
		String contact_number = null;
		String email = null;
		String authorised_doctor_name = null;
		String payment_cycle = null;
		String agreement_mode = null;
		String price_quota = null;
		String agreement_date = null ;
		String doctor_experience = null ;
		
		
		clinic_name = request.getParameter("clinic_name");
		address = request.getParameter("address");
		contact_number = request.getParameter("contact_number");
		email = request.getParameter("email");
		authorised_doctor_name = request.getParameter("authorised_doctor_name");
		payment_cycle = request.getParameter("payment_cycle");
		agreement_mode = request.getParameter("agreement_mode");
		price_quota = request.getParameter("price_quota");
		agreement_date = request.getParameter("agreement_date");
		doctor_experience = request.getParameter("doctor_experience");
		
		System.out.print("haaha "+doctor_experience);
		//submit button was pressed so we save data in our database
		
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
				Timestamp time_stamp = new java.sql.Timestamp(now.getTime());
				
				Registration registration = new Registration();  
				
				registration.setClinic_name( clinic_name );
				registration.setAddress(address);
				registration.setTime_stamp(time_stamp);
				registration.setAgreement_mode(agreement_mode);
				registration.setAuthorised_doctor_name(authorised_doctor_name);
				registration.setContact_number(contact_number);
				registration.setEmail(email);
				registration.setPayment_cycle(payment_cycle);
				registration.setPrice_quota(price_quota);
				registration.setAgreement_date(agreement_date);
				
				
			    
			    
			    
			    //persisting the object
			    
			    try {
					session1.save(registration);  
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
		    
 /* redirect user to show again doctorDetail registration form to enter detail of other doctor */
        	
            String nextJSP = "/DoctorDetail.jsp";
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
				
				
				

		        
			    
					LOGGER.info("exiting from DoctorRegistrationServlet ");
			}
		
		
	}

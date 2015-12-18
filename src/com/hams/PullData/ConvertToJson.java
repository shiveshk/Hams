package com.hams.PullData;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.json.simple.JSONObject;

import com.hams.data.Appointment;
import com.hams.data.Registration;


/**
 * Servlet implementation class ConvertToJson
 */
@WebServlet("/ConvertToJson")
public class ConvertToJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static final Logger LOGGER = Logger.getLogger(ConvertToJson.class);
	
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
		
		
       @SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		    LOGGER.info("entered into ConvertToJson");
				
				// creating session factory object  
				
				SessionFactory factory = getSessionFactory();
				
				Session session1 = factory.openSession();
				
		 
			    //creating session object  
				
			    try {
					session1 = factory.openSession();
				} catch (HibernateException e) {
					e.printStackTrace();
					LOGGER.error(e);
				}  
				
			      
			    	
				/*SQL_QUERY to get all records from Appointment table which have appointment between 2 dates entered by user to generate report   */
			    String SQL_QUERY =  " from Registration ";
						    
			    Query query = null ;
				try {
					query = session1.createQuery(SQL_QUERY);
				} catch (HibernateException e) {
					e.printStackTrace();
					LOGGER.error(e);
				}
				
				
				List<Registration> models = null;
				
				try {
					models = query.list();
				} catch (HibernateException e1) {
					e1.printStackTrace();
				}
				int length = models.size();
				int i=0;
				
				//create a file where to write data from database into text file
				
				try(	FileWriter file = new FileWriter("F://ham/clinic_registration.txt",true)){
							file.write("[");
		         			for ( Registration registration : models ) {
		         				i++;
		         				
		         				//Json object to extract data from table and put into json object later write it into text file 
		         				
		         				JSONObject obj = new JSONObject();
		         				
		         				obj.put("clinic_id", registration.getClinic_id()+"");
		         				obj.put("clinic_name", registration.getClinic_name());
		             			obj.put("address", registration.getAddress());
		             			obj.put("contact_number", registration.getContact_number());
		             			obj.put("email", registration.getEmail()+"");
		             			obj.put("authorised_doctor_name", registration.getAuthorised_doctor_name());
		             			obj.put("payment_cycle",registration.getPayment_cycle());
		             			obj.put("agreement_mode", registration.getAgreement_mode());
		             			obj.put("price_quota", registration.getPrice_quota());
		             			obj.put("agreement_date", registration.getAgreement_date());
		             			obj.put("doctor_experience", registration.getDoctor_experience());
		             			obj.put("secondary_contact_number", registration.getSecondary_contact_number());
		             			obj.put("emergency_contact_number", registration.getEmergency_contact_number());
		             			obj.put("doctor_specialization", registration.getDoctor_specialization());
		             			obj.put("onboard", registration.getOnboard());
		             			obj.put("user_name", registration.getUser_name());
		             			file.write(obj.toJSONString());
		             			if( i == length )
		             				continue;
		             			file.write(",");
		         				
		        			    }
		       
				    file.write("]");
				}

		         			
		       try {
					session1.close();
				} catch (HibernateException e) {
					e.printStackTrace();
					LOGGER.error(e);
				}
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
				LOGGER.info(" exiting from ConvertToJson ");
				}
       
	
       
	}



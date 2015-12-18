package com.hams.PullData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
 

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hams.appointment.GenerateReportServlet;
import com.hams.data.Appointment;
import com.hams.data.User;


public class ReadExcel {
	static final Logger LOGGER = Logger.getLogger(ReadExcel.class);
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
	
	

    private static final int MY_MINIMUM_COLUMN_COUNT = 19;

	/**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
    	LOGGER.info("entered into generateReport method of  GenerateReportServlet to generate report from database");
		
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
	    String SQL_QUERY =  " from Appointment t ";
    	
	    Query query = null ;
		try {
			query = session1.createQuery(SQL_QUERY);
		} catch (HibernateException e) {
			e.printStackTrace();
			LOGGER.error(e);
		}
		
		List<Appointment> models = null;
		try {
			models = query.list();
		} catch (HibernateException e1) {
			
			e1.printStackTrace();
		}
		
		int length = models.size();
		
		
        //if size of returned data is more than 0 then assign data to variable so that it can be retrieved in jsp
		
        
            
            System.out.println("inside ReadExcel");
           // File file = new File("e://Appointment.txt");
            
         // if file doesnt exists, then create it
//         			if (!file.exists()) {
//         				file.createNewFile();
//         			}
         			
            JSONObject obj = null ;
         			
         			for ( Appointment appointment : models ) {
         			    obj = new JSONObject();
             			obj.put("appointment_id", appointment.getAppointment_id());
             			obj.put("patient_name", appointment.getPatient_name());
             			obj.put("clinic_detail", appointment.getClinic_detail());
             			obj.put("time", appointment.getTime());
             			obj.put("time_stamp", appointment.getTime_stamp());
             			obj.put("patient_mobile_number", appointment.getPatient_mobile_number());
             			obj.put("user_name", appointment.getUser_name());
             			obj.put("appointment_date", appointment.getAppointment_date());
             			obj.put("appointment_booked_date", appointment.getAppointment_booked_date());
        			    }
         			
         		
         			try (FileWriter file = new FileWriter("e://Appointment.txt")) {
         				file.write(obj.toJSONString());
         				System.out.println("Successfully Copied JSON Object to File...");
         				System.out.println("\nJSON Object: " + obj);
         			}
         			
         			
         			

         			

            
        		       
        


        
        try {
			session1.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			LOGGER.error(e);
		}
    	
    	


    	
        	}
}

    	

   

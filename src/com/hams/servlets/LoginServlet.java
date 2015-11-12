package com.hams.servlets;

import java.io.IOException;
import com.hams.data.User;
import java.io.PrintWriter;
import org.apache.log4j.Logger;
import org.apache.commons.codec.binary.*;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOGGER = Logger.getLogger(SaveOrEditAppointmentServlet.class);
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");  
        String password1 = request.getParameter("password");  
        byte[] encodedBytes = Base64.encodeBase64(password1.getBytes());
        String password = new String(encodedBytes);
        System.out.println(password);
        
        PrintWriter out = null ;
		try {
			out = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info(e);
		} 

        //check login if value of authenticateUser(name ,password) will be true then login should be successful otherwise don't give permission
        boolean login_check = authenticateUser(name ,password);
        
        if( login_check == true ){  
       
        HttpSession session = request.getSession();  
        session.setAttribute("name" , name); 
        
        System.out.println("login successful" + name);
        LOGGER.info("login successful");
		String nextJSP = "/appointment.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		
		
		try {
			dispatcher.forward(request,response);
		} catch (ServletException e) {
			
			e.printStackTrace();
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
        }  
        
        else{  
        	/* login unsuccessful because of failed validation */
            
        	out.print("Sorry, username or password error!");  
            
        	/* redirect user again to login page */
        	
            String nextJSP = "/index.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			
			try {
				dispatcher.forward(request,response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch(IOException e ){
				e.printStackTrace();
			}
        	
        }  
        /* Closes the stream and releases any system resources associated with it. Closing a previously closed stream has no effect.
 */
        
        out.close();  
	}
	
	public boolean authenticateUser(String name, String password) {
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
	      
	    /*SQL_QUERY to get list of users from user table with same user_name and password as entered by user */
        String SQL_QUERY = " from User u where u.user_name='" + name + "' and u.password='" + password + "'";
        System.out.println(SQL_QUERY);
        Query query = null ;
		try {
			query = session1.createQuery(SQL_QUERY);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
        
        List<User> list = query.list();
        if (list.size() > 0) {
            session1.close();
            return true;
        }
        try {
			session1.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
        return false;
    	}
	 }


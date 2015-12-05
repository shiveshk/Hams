package com.hams.appointment;

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
	
	static final Logger LOGGER = Logger.getLogger(com.hams.appointment.LoginServlet.class);
	
private static final SessionFactory sessionFactory = buildSessionFactory();
	
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
			
		LOGGER.info("entered into LoginServlet");
		

        String name = request.getParameter("name");  
        String password1 = request.getParameter("password");  
        byte[] encodedBytes = Base64.encodeBase64(password1.getBytes());
        String password = new String(encodedBytes);
                
        PrintWriter out = null ;
		try {
			out = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
		} 
		
		
        //check login if value of authenticateUser(name ,password) will be true then login should be successful otherwise don't give permission
        boolean login_check = authenticateUser(name ,password, request);
        
        if( login_check == true ){  
        	
        LOGGER.info("entered into if of loginServlet to decide user is valid or not");

        	
       
        HttpSession session = request.getSession();  
        session.setAttribute("name" , name); 
        
        LOGGER.info("login successful");
		String nextJSP = "/appointment.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		
		
		try {
			dispatcher.forward(request,response);
		} catch (ServletException e) {
			
			e.printStackTrace();
			LOGGER.error(e);
			
		} catch (IOException e)
		{
			e.printStackTrace();
			LOGGER.error(e);
		}
        }  
        
        else{  
        	/* login unsuccessful because of failed validation */
        	
        	LOGGER.info("entered into else of LoginServlet if user not entered valid credentials");

            
        	out.print("Sorry, username or password error!");  
            
        	/* redirect user again to login page */
        	
            String nextJSP = "/index.jsp";
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
        /* Closes the stream and releases any system resources associated with it. Closing a previously closed stream has no effect.
 */
        
        out.close();  
        
        LOGGER.info(" exiting from LoginServlet ");
	}
	
	@SuppressWarnings("unchecked")
	public boolean authenticateUser(String name, String password, HttpServletRequest request) {
		
		//creating configuration object  
		
		LOGGER.info("entered into authenticateUser method of  LoginServlet to match user credentials with credential saved in database");

		

	    // creating session factory object  
		
		SessionFactory factory = getSessionFactory();
		System.out.println("Session factory object created : " + factory);
		Session session1 = factory.openSession();
		
 
	      
	    //creating session object  
		
	    
	    
		try {
			session1 = factory.openSession();
		} catch (HibernateException e) {
			e.printStackTrace();
			LOGGER.error(e);
		}  
		
	      
	    /*SQL_QUERY to get list of users from user table with same user_name and password as entered by user */
        String SQL_QUERY = " from User u where u.user_name='" + name + "' and u.password='" + password + "'";
        
        Query query = null ;
		try {
			query = session1.createQuery(SQL_QUERY);
		} catch (HibernateException e) {
			e.printStackTrace();
			LOGGER.error(e);
		}
		
        
        List<User> list = null;
		try {
			list = query.list();
		} catch (HibernateException e1) {
			
			e1.printStackTrace();
		}
		
		Boolean hide_report = null;

			for ( User user : list ) {
				hide_report = user.getAdmin();
			    
			    
			}
		
		
		System.out.print(hide_report);
		
        if (list.size() > 0) {
            session1.close();
            
            HttpSession session = request.getSession();  
            session.setAttribute("hide_report" , hide_report );
            
            return true;
        }
        try {
			session1.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			LOGGER.error(e);
		}
        

		LOGGER.info("exiting from authenticateUser method of  LoginServlet ");
		
        return false;
        
        
    	}
}
			

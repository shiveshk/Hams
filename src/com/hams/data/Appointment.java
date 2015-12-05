package com.hams.data;



import java.util.Date;

import javax.persistence.Entity;


@Entity
public class Appointment {
	
	public Appointment() {}
	
	
	private String user_name ; 
	public String getUser_name() {
		return user_name;
	}

	@Override
	  public String toString() {
		
		String testingappointment = new String(" "+ appointment_id + " "+ patient_name + " "+ clinic_detail + 
				" " + time + " "+ time_stamp +" " +patient_mobile_number+ " "+ user_name+" "+appointment_date+" " +appointment_booked_date+" " );
			    return testingappointment ;
	  }
	

	private String appointment_date ;
	private String appointment_booked_date ;
	
	public String getAppointment_date() {
		return appointment_date;
	}

	public void setAppointment_date(String appointment_date1) {
		this.appointment_date = appointment_date1;
	}

	public String getAppointment_booked_date() {
		return appointment_booked_date;
	}

	public void setAppointment_booked_date(String appointment_booked_date) {
		this.appointment_booked_date = appointment_booked_date;
	}


	
	
	
	
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	private String patient_mobile_number;
	public String getPatient_mobile_number() {
		return patient_mobile_number;
	}

	public void setPatient_mobile_number(String patient_mobile_number) {
		this.patient_mobile_number = patient_mobile_number;
	}
	

	private long appointment_id ;
	public long getAppointment_id() {
		return appointment_id;
	}

	public void setAppointment_id(long appointment_id) {
		this.appointment_id = appointment_id;
	}

	private String patient_name ;
	private String clinic_detail ;
	private String time ;
	private java.sql.Timestamp time_stamp ;
	
	
	public String getPatient_name() {
		return patient_name;
	}

	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}

	public String getClinic_detail() {
		return clinic_detail;
	}

	public void setClinic_detail(String clinic_detail) {
		this.clinic_detail = clinic_detail;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public java.sql.Timestamp getTime_stamp() {
		return time_stamp;
	}

	public void setTime_stamp(java.sql.Timestamp time_stamp) {
		this.time_stamp = time_stamp;
	}

	
	
	


	
	


}

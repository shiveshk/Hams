package com.hams.data;

import javax.persistence.Entity;


@Entity
public class Registration {
	
	public Registration() {}
	
	@Override
	  public String toString() {
		
		String registration = new String(" "+ clinic_id + " "+ clinic_name + " "+ address + 
				" " + contact_number + " "+ email +" " + authorised_doctor_name + " "+ payment_cycle +" "+ agreement_mode +" " + price_quota +" "+ time_stamp +" " );
			    return registration ;
	  }
	
	private long clinic_id ;
	private String clinic_name ;
	private String address ;
	private String contact_number ;
	private String email ;
	private String authorised_doctor_name ;
	private String payment_cycle ;
	private String agreement_mode ;
	private String price_quota ;
	private java.sql.Timestamp time_stamp ;
	private String agreement_date ;
	
	public long getClinic_id() {
		return clinic_id;
	}

	public void setClinic_id(long clinic_id) {
		this.clinic_id = clinic_id;
	}

	public String getClinic_name() {
		return clinic_name;
	}

	public void setClinic_name(String clinic_name) {
		this.clinic_name = clinic_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact_number() {
		return contact_number;
	}

	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthorised_doctor_name() {
		return authorised_doctor_name;
	}

	public void setAuthorised_doctor_name(String authorised_doctor_name) {
		this.authorised_doctor_name = authorised_doctor_name;
	}

	public String getPayment_cycle() {
		return payment_cycle;
	}

	public void setPayment_cycle(String payment_cycle) {
		this.payment_cycle = payment_cycle;
	}

	public String getAgreement_mode() {
		return agreement_mode;
	}

	public void setAgreement_mode(String agreement_mode) {
		this.agreement_mode = agreement_mode;
	}

	public String getPrice_quota() {
		return price_quota;
	}

	public void setPrice_quota(String price_quota) {
		this.price_quota = price_quota;
	}

	public java.sql.Timestamp getTime_stamp() {
		return time_stamp;
	}

	public void setTime_stamp(java.sql.Timestamp time_stamp) {
		this.time_stamp = time_stamp;
	}

	public String getAgreement_date() {
		return agreement_date;
	}

	public void setAgreement_date(String agreement_date) {
		this.agreement_date = agreement_date;
	}
}
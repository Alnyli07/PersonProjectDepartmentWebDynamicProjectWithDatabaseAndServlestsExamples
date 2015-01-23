package com.alnyli.dto;

import java.io.Serializable;

public class PhoneDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Id; // phone Id.
	private String num; // phone number.
	
	public PhoneDTO() {
		this.Id = -1;
		
		this.num = "-1";
	}
	/* GETTER */
	public int getId(){
		return this.Id;
	}
	
	public String getNumber(){
		return this.num;
	}
	/* SETTER */
	public void setId(int id){
		this.Id = id;
	}

	public void setNumber(String num){
		this.num = num;
	}
	
	@Override /* return sql komut. */
	public String toString(){
		
		StringBuilder str = new StringBuilder();
		
		str.append("('"+this.num+"')");
		
		return str.toString();
		
	}
	
}

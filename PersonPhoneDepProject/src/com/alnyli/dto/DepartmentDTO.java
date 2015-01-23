package com.alnyli.dto;

import java.io.Serializable;

public class DepartmentDTO implements Serializable{

	private static final long serialVersionUID = 519964267246131223L;
	private int Id;
	private String name;
	
	public DepartmentDTO() {
		// TODO Auto-generated constructor stub
	}
	/* GETTER */
	public int getId(){
		return this.Id;
	}
	
	public String getName(){
		return this.name;
	}
	/* SETTER */
	public void setId(int id){
		this.Id = id;
	}

	public void setName(String name){
		this.name = name;
	}
	@Override /* return sql komut. */
	public String toString(){
		
		StringBuilder str = new StringBuilder();
		
		str.append("('"+this.name+"')");
		
		return str.toString();
		
	}

}

package com.alnyli.dto;

import java.io.Serializable;

public class PersonDTO implements Serializable{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private int Id;  // person Id
		private String name; // person name
		private String sirname; // person surname
		
		public PersonDTO(){
			Id = -1;
			name = "unnamed";
		    sirname = "unsurname";
		}
		/* GETTER */
		public int getId(){
			return Id;
		}
		
		public String getName(){
			return name;
		}
		
		public String getSirname(){
			return sirname;
		}
		/* SETTER */
		public void setId(int id){
			this.Id = id;
		}
		
		public void setName(String name){
			this.name = name;
		}
		
		public void setSirname( String sname){
			this.sirname = sname;
		}
		
		@Override /* return sql komut. */
		public String toString(){
			
			StringBuilder str = new StringBuilder();
			
			str.append("('"+this.name+"','");
			str.append(this.sirname+"')");
			
			return str.toString();
			
		}
		
	}



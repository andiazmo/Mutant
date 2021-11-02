package com.mima.rest.webservices.restfulwebservices.user;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Dna {
	
	@Id
	private long id;
	private String[] dna;
	
	public Dna() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dna(String[] dna) {
		super();
		this.dna = dna;
	}

	public String[] getDna() {
		return dna;
	}

	public void setDna(String[] dna) {
		this.dna = dna;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}

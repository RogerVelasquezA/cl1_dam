package com.cibertec.cl1.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
public class Tema {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idTema;
	
	
	private String nombre;
	
	@OneToMany(mappedBy="tema", cascade=CascadeType.PERSIST)
	private List<Libro> libros = new ArrayList<Libro>();
	
	

	public Tema(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Tema() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdTema() {
		return idTema;
	}

	public void setIdTema(int idTema) {
		this.idTema = idTema;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	
	
}

package com.cibertec.cl1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public class Libro {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idLibro;
	
	@Column(name="titulo")
	private String titulo;
	
	@Column(name="precio")
	private double precio;
	
	@Column(name="cantidad")
	private int cantidad;
	
	@Column(name="origen")
	private String origen;
	
	@ManyToOne
	private Tema tema;
	
	

	public Libro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Libro(String titulo, double precio, int cantidad, String origen, Tema tema) {
		super();
		this.titulo = titulo;
		this.precio = precio;
		this.cantidad = cantidad;
		this.origen = origen;
		this.tema = tema;
	}

	public int getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	@Override
	public String toString() {
		return "Libro [idLibro=" + idLibro + ", titulo=" + titulo + ", precio=" + precio + ", cantidad=" + cantidad
				+ ", origen=" + origen + ", tema=" + tema + "]";
	}
	
	

}

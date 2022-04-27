package com.cibertec.cl1.test;

import java.util.List;

import com.cibertec.cl1.dao.LibroDao;
import com.cibertec.cl1.dao.TemaDao;
import com.cibertec.cl1.model.Libro;
import com.cibertec.cl1.model.Tema;



public class Cl1Test {
	
	public static void main(String[] args) {
		Tema t1 = null;
		Tema t2 = null;
		//Crear las temas
		TemaDao temadao = new TemaDao();
			t1 = new Tema("Ciencias");
			t2 = new Tema("Fantasia");
		temadao.guardarTema(t1);
		temadao.guardarTema(t2);
		System.out.println(t1);
		System.out.println(t2);

		
		//crear Libros
		LibroDao librodao = new LibroDao();
		Libro libro = new Libro("Juego de tronos", 130.00, 10,"Estados unidos",t2);
		Libro libro2 = new Libro("Java 8", 350.00, 10,"España",t1);
		librodao.guardarLibro(libro);
		librodao.guardarLibro(libro2);
		System.out.println(libro);
		System.out.println(libro2);
		
		
		List<Libro> libros = librodao.obtTodosLosLibros();
		libros.forEach(est->System.out.println(est));
		
		List<Tema> temas = temadao.traerTodosTemas();
		temas.forEach(est->System.out.println(est));
	}

	
}

package com.cibertec.cl1.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cibertec.cl1.model.Libro;
import com.cibertec.cl1.util.Cl1Util;


public class LibroDao {

	//1. Guardar Libro
		public void guardarLibro(Libro libro){
			Transaction transaction = null;
			try(Session session = Cl1Util.getSessionFactory().openSession()) {
				transaction = session.beginTransaction();
				session.save(libro);
				transaction.commit();
			} catch (Exception e) {
				// TODO: handle exception
				if(transaction!=null){
					transaction.rollback();
				}
			}
		}
		
		//2. Obtener Todos los Libros
		public List<Libro> obtTodosLosLibros(){
			Transaction transaction = null;
			List<Libro> libros = null;
			try(Session session = Cl1Util.getSessionFactory().openSession()) {
				transaction = session.beginTransaction();
				libros = session.createQuery("from Libro").list();
				transaction.commit();
			} catch (Exception e) {
				// TODO: handle exception
				if(transaction!=null){
					transaction.rollback();
				}
			}
			return libros;
		}
}

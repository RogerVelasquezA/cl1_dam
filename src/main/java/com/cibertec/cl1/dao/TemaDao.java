package com.cibertec.cl1.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cibertec.cl1.model.Tema;
import com.cibertec.cl1.util.Cl1Util;


public class TemaDao {

	
	@SuppressWarnings("unchecked")
	public List<Tema> traerTodosTemas(){
		Transaction transaction = null;
		List<Tema> temas = null;
		try(Session session = Cl1Util.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			temas = session.createQuery("from Tema").list();
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null){transaction.rollback();}
		}
		return temas;
	}
	//guardar tema
	public void guardarTema(Tema tema){
		Transaction transaction = null;
		try(Session session = Cl1Util.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(tema);
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null){transaction.rollback();}
		}
	}
}

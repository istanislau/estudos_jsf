package dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import dominio.EntidadeDominio;
import dominio.Setor;

public class SetorDAO extends AbstractDAO {
	public Setor getSetor(String nome){
		em.getTransaction().begin();
		Setor setor = em.find(Setor.class, nome);
		em.getTransaction().commit();
		emf.close();
		return setor;
	}
	
	@SuppressWarnings("unchecked")
	public List<Setor> Listar (){
		List<Setor> resultado = new ArrayList<Setor>();
		try{
			//abrir conex�o
			em.getTransaction().begin();
			
			Query consulta = em.createQuery("select setore from Setor setor");
			
			resultado = consulta.getResultList();
			
			//commit
			em.getTransaction().commit();
		}catch(RuntimeException e){
			//rollback em caso de erro
			em.getTransaction().rollback();
		}finally {
			//fechar conex�o
			emf.close();
		}
		return resultado;
	}
}

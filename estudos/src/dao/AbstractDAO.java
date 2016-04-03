package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dominio.EntidadeDominio;
import dominio.Setor;

public abstract class AbstractDAO {
	//Entidade Manager Factory
	EntityManagerFactory emf;
	
	//Entidade Manager
	EntityManager em;
	
	public AbstractDAO(){
		// instancia pelo name da session factory no arquivo hibernate.cfg.xml
		emf = Persistence.createEntityManagerFactory("estudos");
		em = emf.createEntityManager();
	}
	
	public void Salvar (EntidadeDominio obj){
		try{
			//abrir conexão
			em.getTransaction().begin();
			
			em.persist(obj);	
			
			//commit
			em.getTransaction().commit();
		}catch(RuntimeException e){
			//rollback em caso de erro
			em.getTransaction().rollback();
			System.out.println("Erro");
		}finally {
			//fechar conexão
			emf.close();
		}
	}
	
	public void Alterar (EntidadeDominio setor){
		try{
			//abrir conexão
			em.getTransaction().begin();
			
			// usando merge em vez do persiste, pois se o objeto ja existir no banco de dados, ele vai ser atualizado
			em.merge(setor);			
			
			//commit
			em.getTransaction().commit();
		}catch(RuntimeException e){
			//rollback em caso de erro
			em.getTransaction().rollback();
		}finally {
			//fechar conexão
			emf.close();
		}	
	}
	
	public void Excluir (EntidadeDominio setor){
		try{
			//abrir conexão
			em.getTransaction().begin();
			
			// remove a tupla do banco
			em.remove(setor);;			
			
			//commit
			em.getTransaction().commit();
		}catch(RuntimeException e){
			//rollback em caso de erro
			em.getTransaction().rollback();
		}finally {
			//fechar conexão
			emf.close();
		}	
	}
	
	
}

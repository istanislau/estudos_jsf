package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dominio.Setor;

public class SetorDAO {
	
	//Entidade Manager Factory
	EntityManagerFactory emf;
	
	//Entidade Manager
	EntityManager em;
	
	public SetorDAO(){
		// instancia pelo name da session factory no arquivo hibernate.cfg.xml
		emf = Persistence.createEntityManagerFactory("estudos");
		em = emf.createEntityManager();
	}
	
	public void Salvar (Setor setor){
		try{
			//abrir conexão
			em.getTransaction().begin();
			
			em.persist(setor);	
			
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
	
	public void Alterar (Setor setor){
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
	
	public void Excluir (Setor setor){
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
	
	@SuppressWarnings("unchecked")
	public List<Setor> Listar (){
		List<Setor> setores = new ArrayList<Setor>();
		try{
			//abrir conexão
			em.getTransaction().begin();
			
			Query consulta = em.createQuery("select setore from Setor setor");
			
			setores = consulta.getResultList();
			
			//commit
			em.getTransaction().commit();
		}catch(RuntimeException e){
			//rollback em caso de erro
			em.getTransaction().rollback();
		}finally {
			//fechar conexão
			emf.close();
		}	
		
		return null;
	}
	
}

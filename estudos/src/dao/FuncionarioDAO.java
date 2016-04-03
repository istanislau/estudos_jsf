package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import dominio.Funcionario;

public class FuncionarioDAO extends AbstractDAO {
	@SuppressWarnings("unchecked")
	public List<Funcionario> Listar (){
		List<Funcionario> resultado = new ArrayList<Funcionario>();
		try{
			//abrir conexão
			em.getTransaction().begin();
			
			Query consulta = em.createQuery("select setore from Setor setor");
			
			resultado = consulta.getResultList();
			
			//commit
			em.getTransaction().commit();
		}catch(RuntimeException e){
			//rollback em caso de erro
			em.getTransaction().rollback();
		}finally {
			//fechar conexão
			emf.close();
		}
		return resultado;
	}
}

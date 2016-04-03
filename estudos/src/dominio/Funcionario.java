package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "funcionario")
public class Funcionario extends EntidadeDominio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9087797444414963879L;
	
	@Column
	private String nome;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "id_setor")
	private Setor setor;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "setor")
public class Setor extends EntidadeDominio{


	/**
	 * 
	 */
	private static final long serialVersionUID = 6518914137539126187L;

	@Column
	private String nome;
	
	@Column
	private Integer ramal;
	
	@Column
	private Boolean flgAtivo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getRamal() {
		return ramal;
	}

	public void setRamal(Integer ramal) {
		this.ramal = ramal;
	}

	public Boolean getFlgAtivo() {
		return flgAtivo;
	}

	public void setFlgAtivo(Boolean flgAtivo) {
		this.flgAtivo = flgAtivo;
	}

}

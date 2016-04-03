package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "maquina")
public class Maquina extends EntidadeDominio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6683455128051399521L;

	@Column
	private String patrimonio;
	
	@Column
	private boolean flgAtivo;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "id_setor")
	private Setor setor;

	public String getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}

	public boolean isFlgAtivo() {
		return flgAtivo;
	}

	public void setFlgAtivo(boolean flgAtivo) {
		this.flgAtivo = flgAtivo;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}
}

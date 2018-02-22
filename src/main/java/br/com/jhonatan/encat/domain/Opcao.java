package br.com.jhonatan.encat.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Opcao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Descrição com preenchimento obrigatório")
	private String descricao;
	
	private Long quantidadeVotos;
	
	@ManyToOne
	@JoinColumn(name="enquete_id")
	private Enquete enquete;
	
	public Opcao() {
		super();
	}
	
	public Opcao(Long id, String descricao, Long quantidadeVotos, Enquete enquete) {
		this.id = id;
		this.descricao = descricao;
		this.quantidadeVotos = quantidadeVotos;
		this.enquete = enquete;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getQuantidadeVotos() {
		return quantidadeVotos;
	}

	public void setQuantidadeVotos(Long quantidadeVotos) {
		this.quantidadeVotos = quantidadeVotos;
	}

	public Enquete getEnquete() {
		return enquete;
	}

	public void setEnquete(Enquete enquete) {
		this.enquete = enquete;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Opcao other = (Opcao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
}

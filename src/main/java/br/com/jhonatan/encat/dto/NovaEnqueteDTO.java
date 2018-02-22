package br.com.jhonatan.encat.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.jhonatan.encat.domain.Enquete;
import br.com.jhonatan.encat.domain.Opcao;

public class NovaEnqueteDTO {

	@NotEmpty(message="Pergunta com preenchimento obrigatório")
	@Length(min=5, max=80, message="O tamanho da pergunta deve ser entre 5 e 80 caracteres")
	private String pergunta;
	
	private List<String> opcoes = new ArrayList<>();

	public NovaEnqueteDTO() {
		super();
	}
	
	public Enquete toEnquete() {
		final List<Opcao> listaOpcoes = opcoes.stream().map(descricao -> new Opcao(null, descricao, 0L, null)).collect(Collectors.toList());
		final Enquete enquete = new Enquete(null, pergunta, new Date());
		enquete.setOpcoes(listaOpcoes);
		return enquete;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public List<String> getOpcoes() {
		return opcoes;
	}

	public void setOpcoes(List<String> opcoes) {
		this.opcoes = opcoes;
	}
	
}

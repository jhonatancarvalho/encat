package br.com.jhonatan.encat.builders;

import br.com.jhonatan.encat.domain.Enquete;
import br.com.jhonatan.encat.domain.Opcao;

public class OpcaoBuilder {

	private Opcao opcao;
	
	private OpcaoBuilder() {}
	
	public static OpcaoBuilder umaOpcao() {
		final OpcaoBuilder opcaoBuilder = new OpcaoBuilder();
		opcaoBuilder.opcao = new Opcao();
		opcaoBuilder.opcao.setDescricao("Sim");
		opcaoBuilder.opcao.setQuantidadeVotos(0L);
		return opcaoBuilder;
	}
	
	public OpcaoBuilder comId(Long id) {
		opcao.setId(id);
		return this;
	}
	
	public OpcaoBuilder comDescricao(String descricao) {
		opcao.setDescricao(descricao);
		return this;
	}
	
	public OpcaoBuilder comQuantidadeVotos(Long id) {
		opcao.setQuantidadeVotos(id);
		return this;
	}
	
	public OpcaoBuilder comEnquete(Enquete enquete) {
		opcao.setEnquete(enquete);
		return this;
	}
	
	public Opcao agora() {
		return opcao;
	}
	
}

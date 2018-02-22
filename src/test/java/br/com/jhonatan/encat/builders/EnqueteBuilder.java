package br.com.jhonatan.encat.builders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import br.com.jhonatan.encat.domain.Enquete;
import br.com.jhonatan.encat.domain.Opcao;

public class EnqueteBuilder {

	private Enquete enquete;

	private EnqueteBuilder() {}

	public static EnqueteBuilder umaEnquete() {
		final EnqueteBuilder enqueteBuilder = new EnqueteBuilder();
		enqueteBuilder.enquete = new Enquete();
		enqueteBuilder.enquete.setPergunta("Você gosta de gatos?");
		enqueteBuilder.enquete.setOpcoes(Arrays.asList(
				OpcaoBuilder.umaOpcao().comDescricao("Sim").comEnquete(enqueteBuilder.enquete).agora(),
				OpcaoBuilder.umaOpcao().comDescricao("Não").comEnquete(enqueteBuilder.enquete).agora()));
		enqueteBuilder.enquete.setDataCriacao(new Date());
		return enqueteBuilder;
	}

	public EnqueteBuilder comId(Long id) {
		enquete.setId(id);
		return this;
	}
	
	public EnqueteBuilder comOpcoes(List<Opcao> opcoes) {
		enquete.setOpcoes(opcoes);
		return this;
	}

	public Enquete agora() {
		return enquete;
	}
}

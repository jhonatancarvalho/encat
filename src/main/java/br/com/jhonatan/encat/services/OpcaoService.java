package br.com.jhonatan.encat.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jhonatan.encat.domain.Opcao;
import br.com.jhonatan.encat.repositories.OpcaoRepository;
import br.com.jhonatan.encat.services.exceptions.OpcaoException;

@Service
public class OpcaoService {

	@Autowired
	private OpcaoRepository opcaoRepository;
	
	public List<Opcao> save(List<Opcao> opcoes) {
		return opcaoRepository.save(opcoes);
	}

	public Opcao adicionarVoto(Long opcaoId) {
		final Opcao opcao = opcaoRepository.findOne(opcaoId);
		if (Objects.isNull(opcao)) {
			throw new OpcaoException("Opção não encontrada");
		}
		
		opcao.setQuantidadeVotos(opcao.getQuantidadeVotos() + 1);
		opcaoRepository.save(opcao);
		return opcao;
	}

}

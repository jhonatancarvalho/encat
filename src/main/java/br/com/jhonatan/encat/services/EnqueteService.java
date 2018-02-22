package br.com.jhonatan.encat.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.jhonatan.encat.domain.Enquete;
import br.com.jhonatan.encat.repositories.EnqueteRepository;
import br.com.jhonatan.encat.services.exceptions.EnqueteException;
import br.com.jhonatan.encat.services.exceptions.OpcoesEnqueteException;

@Service
public class EnqueteService {

	@Autowired
	private EnqueteRepository enqueteRepository;
	
	@Autowired
	private OpcaoService opcaoService;
	
	public Enquete find(Long id) {
		final Enquete enquete = enqueteRepository.findOne(id);
		if (Objects.isNull(enquete)) {
			throw new EnqueteException("Não foi encontrado a enquete com id: " + id);
		}
		
		return enquete;
	}
	
	public Enquete save(Enquete enquete) {
		validaSalvarEnquete(enquete);
		final Enquete enqueteSalva = enqueteRepository.save(enquete);
		opcaoService.save(enqueteSalva.getOpcoes());
		return enqueteSalva;
	}

	private void validaSalvarEnquete(Enquete enquete) {
		if (Objects.isNull(enquete)) {
			throw new EnqueteException("Não é possivel salvar uma enquete nula");
		}
		
		if (Objects.nonNull(enquete.getId())) {
			throw new EnqueteException("Não é possivel salvar uma enquete com id preenchido");
		}
		
		if (Objects.isNull(enquete.getOpcoes())) {
			throw new OpcoesEnqueteException("Não é possivel criar uma enquete sem opções");
		}
		
		if (enquete.getOpcoes().size() <= 1) {
			throw new OpcoesEnqueteException("A enquete deve ter duas ou mais opções");
		}
		
		boolean isTemOpcaoComId = enquete.getOpcoes().stream().anyMatch(opcao -> Objects.nonNull(opcao.getId()));
		if (isTemOpcaoComId) {
			throw new OpcoesEnqueteException("Não é possivel salvar opções com id preenchido");
		}
	}
	
	public Page<Enquete> findPage(Integer page, Integer size, String direction, String orderBy) {
		final PageRequest pageRequest = new PageRequest(page, size, Direction.valueOf(direction), orderBy);
		return enqueteRepository.findAll(pageRequest);
	}

}

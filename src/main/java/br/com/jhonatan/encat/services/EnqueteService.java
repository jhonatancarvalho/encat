package br.com.jhonatan.encat.services;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.jhonatan.encat.domain.Enquete;
import br.com.jhonatan.encat.repositories.EnqueteRepository;

public class EnqueteService {

	@Autowired
	private EnqueteRepository enqueteRepository;
	
	public Enquete save(Enquete enquete) {
		return enqueteRepository.save(enquete);
	}

}

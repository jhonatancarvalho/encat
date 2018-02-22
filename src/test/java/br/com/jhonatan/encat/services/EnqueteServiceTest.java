package br.com.jhonatan.encat.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.jhonatan.encat.domain.Enquete;
import br.com.jhonatan.encat.domain.Opcao;
import br.com.jhonatan.encat.repositories.EnqueteRepository;

public class EnqueteServiceTest {

	@InjectMocks
	private EnqueteService enqueteService;
	
	@Mock
	private EnqueteRepository enqueteRepository;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void deveSalvarEnquete() {
		final Enquete enquete = new Enquete(null, "Você gosta de gatos?", new Date());
		
		final Opcao opcao1 = new Opcao(1L, "SIM", 0L, enquete);
		final Opcao opcao2 = new Opcao(2L, "NAO", 0L, enquete);
		
		enquete.setOpcoes(Arrays.asList(opcao1, opcao2));
		
		when(enqueteRepository.save(enquete)).thenReturn(enquete);
		
		final Enquete enqueteSalva = enqueteService.save(enquete);
		
		assertThat(enqueteSalva.getPergunta(), is("Você gosta de gatos?"));
	}
}

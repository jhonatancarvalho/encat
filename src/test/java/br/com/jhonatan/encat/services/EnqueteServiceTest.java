package br.com.jhonatan.encat.services;

import static br.com.jhonatan.encat.builders.EnqueteBuilder.umaEnquete;
import static br.com.jhonatan.encat.builders.OpcaoBuilder.umaOpcao;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.jhonatan.encat.domain.Enquete;
import br.com.jhonatan.encat.domain.Opcao;
import br.com.jhonatan.encat.repositories.EnqueteRepository;
import br.com.jhonatan.encat.services.exceptions.EnqueteException;
import br.com.jhonatan.encat.services.exceptions.OpcoesEnqueteException;

public class EnqueteServiceTest {

	@InjectMocks
	private EnqueteService enqueteService;
	
	@Mock
	private EnqueteRepository enqueteRepository;
	
	@Mock
	private OpcaoService opcaoService;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void deveSalvarEnquete() {
		final Enquete enquete = umaEnquete().agora();
		
		when(enqueteRepository.save(enquete)).thenReturn(enquete);
		
		final Enquete enqueteSalva = enqueteService.save(enquete);
		
		assertThat(enqueteSalva.getPergunta(), is("Você gosta de gatos?"));
	}
	
	@Test(expected = EnqueteException.class)
	public void naoDeveSalvarEnqueteSemEnquete() {
		enqueteService.save(null);
	}
	
	@Test(expected = EnqueteException.class)
	public void naoDeveSalvarEnqueteComIdPreenchido() {
		final Enquete enquete = umaEnquete().comId(1L).agora();
	
		enqueteService.save(enquete);
	}
	
	@Test(expected = OpcoesEnqueteException.class)
	public void naoDeveSalvarEnqueteSemOpcoes() {
		final Enquete enquete = umaEnquete().comOpcoes(null).agora();
		
		enqueteService.save(enquete);
	}
	
	@Test(expected = OpcoesEnqueteException.class)
	public void naoDeveSalvarEnqueteApenasComUmaOpcao() {
		final Enquete enquete = umaEnquete().comOpcoes(Arrays.asList(umaOpcao().agora())).agora();
		
		enqueteService.save(enquete);
	}
	
	@Test(expected = OpcoesEnqueteException.class)
	public void naoDeveSalvarEnqueteComOpcaoComIdPreenchido() {
		final List<Opcao> opcoes = Arrays.asList(
				umaOpcao().agora(),
				umaOpcao().comId(1L).agora());
		final Enquete enquete = umaEnquete().comOpcoes(opcoes).agora();
		
		enqueteService.save(enquete);
	}
	
}

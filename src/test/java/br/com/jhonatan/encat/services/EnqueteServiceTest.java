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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

import br.com.jhonatan.encat.domain.Enquete;
import br.com.jhonatan.encat.domain.Opcao;
import br.com.jhonatan.encat.repositories.EnqueteRepository;
import br.com.jhonatan.encat.services.exceptions.InvalidArgumentException;
import br.com.jhonatan.encat.services.exceptions.ObjectNotFoundException;

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
	
	@Test(expected = InvalidArgumentException.class)
	public void naoDeveSalvarEnqueteSemEnquete() {
		enqueteService.save(null);
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void naoDeveSalvarEnqueteComIdPreenchido() {
		final Enquete enquete = umaEnquete().comId(1L).agora();
	
		enqueteService.save(enquete);
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void naoDeveSalvarEnqueteSemOpcoes() {
		final Enquete enquete = umaEnquete().comOpcoes(null).agora();
		
		enqueteService.save(enquete);
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void naoDeveSalvarEnqueteApenasComUmaOpcao() {
		final Enquete enquete = umaEnquete().comOpcoes(Arrays.asList(umaOpcao().agora())).agora();
		
		enqueteService.save(enquete);
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void naoDeveSalvarEnqueteComOpcaoComIdPreenchido() {
		final List<Opcao> opcoes = Arrays.asList(
				umaOpcao().agora(),
				umaOpcao().comId(1L).agora());
		final Enquete enquete = umaEnquete().comOpcoes(opcoes).agora();
		
		enqueteService.save(enquete);
	}
	
	@Test
	public void deveBuscarEnquetePorId() {
		Enquete enquete = umaEnquete().comId(1L).agora();
		
		when(enqueteRepository.findOne(enquete.getId())).thenReturn(enquete);
		enquete = enqueteService.find(enquete.getId());
		
		assertThat(enquete.getId(), is(1L));
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void naoDeveRetornarEnqueteVaziaPorId() {
		final Enquete enquete = umaEnquete().comId(1L).agora();
		
		enqueteService.find(enquete.getId());
	}
	
	@Test
	public void deveBuscarEnquetesPaginadas() {
		final List<Enquete> enquetes = Arrays.asList(
				umaEnquete().agora(),
				umaEnquete().agora());
		final PageRequest pageRequest = new PageRequest(0, 24, Direction.ASC, "dataCriacao");
		final Page<Enquete> enquetesPaginadas = new PageImpl<>(enquetes);
		when(enqueteRepository.findAll(pageRequest)).thenReturn(enquetesPaginadas);
		
		final Page<Enquete> enquetesPage = enqueteService.findPage(0, 24, "ASC", "dataCriacao");
		
		assertThat(enquetesPage.getSize(), is(0));
	}
}

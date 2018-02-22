package br.com.jhonatan.encat.services;

import static br.com.jhonatan.encat.builders.OpcaoBuilder.umaOpcao;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.jhonatan.encat.domain.Opcao;
import br.com.jhonatan.encat.repositories.OpcaoRepository;
import br.com.jhonatan.encat.services.exceptions.ObjectNotFoundException;

public class OpcaoServiceTest {

	@InjectMocks
	private OpcaoService opcaoService;
	
	@Mock
	private OpcaoRepository opcaoRepository;
	
	@Rule
	public ErrorCollector errorCollector = new ErrorCollector();
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void deveSalvarOpcao() {
		final List<Opcao> opcoes = Arrays.asList(
				umaOpcao().comDescricao("Sim").agora());
		
		when(opcaoRepository.save(opcoes)).thenReturn(opcoes);
		
		final List<Opcao> opcoesSalvas = opcaoService.save(opcoes);
		errorCollector.checkThat(opcoesSalvas.get(0).getDescricao(), is("Sim"));
		errorCollector.checkThat(opcoesSalvas.size(), is(1));
	}
	
	@Test
	public void deveVotarNaOpcao() {
		Opcao opcao = umaOpcao().comId(1L).agora();
		
		when(opcaoRepository.findOne(opcao.getId())).thenReturn(opcao);
		when(opcaoRepository.save(opcao)).thenReturn(opcao);
		opcao = opcaoService.adicionarVoto(opcao.getId());
			
		assertThat(opcao.getQuantidadeVotos(), is(1L));
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void naoDeveVotarSemOpcao() {
		Opcao opcao = umaOpcao().comId(1L).agora();
		
		when(opcaoRepository.findOne(opcao.getId())).thenReturn(null);

		opcaoService.adicionarVoto(opcao.getId());
	}
	
	@Test
	public void deveBuscarEnquetePorId() {
		Opcao opcao = umaOpcao().comId(1L).agora();
		
		when(opcaoRepository.findOne(opcao.getId())).thenReturn(opcao);
		opcao = opcaoService.find(opcao.getId());
		
		assertThat(opcao.getId(), is(1L));
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void naoDeveRetornarOpcaoVaziaPorId() {
		final Opcao opcao = umaOpcao().comId(1L).agora();
		
		opcaoService.find(opcao.getId());
	}
	
	@Test
	public void deveBuscarOpcoes() {
		List<Opcao> opcoes = Arrays.asList(
				umaOpcao().comId(1L).agora(),
				umaOpcao().comId(2L).agora());
	
		when(opcaoRepository.findAll()).thenReturn(opcoes);
		
		opcoes = opcaoService.findAll();
		
		assertThat(opcoes.size(), is(2));
	}
	
}

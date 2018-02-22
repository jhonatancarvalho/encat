package br.com.jhonatan.encat;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.jhonatan.encat.domain.Enquete;
import br.com.jhonatan.encat.domain.Opcao;
import br.com.jhonatan.encat.services.EnqueteService;

@SpringBootApplication
public class EncatApplication implements CommandLineRunner {

	@Autowired
	private EnqueteService enqueteService;
	
	public static void main(String[] args) {
		SpringApplication.run(EncatApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		final Enquete enquete = new Enquete();
		enquete.setPergunta("Você gosta de gatos?");
		enquete.setDataCriacao(new Date());
		
		final Opcao opcao1 = new Opcao(null, "Sim", 0L, enquete);
		final Opcao opcao2 = new Opcao(null, "Não", 0L, enquete);
		
		enquete.getOpcoes().addAll(Arrays.asList(opcao1, opcao2));
		
		enqueteService.save(enquete);
	}
}

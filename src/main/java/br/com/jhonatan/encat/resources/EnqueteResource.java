package br.com.jhonatan.encat.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.jhonatan.encat.domain.Enquete;
import br.com.jhonatan.encat.services.EnqueteService;
import br.com.jhonatan.encat.services.OpcaoService;

@RestController
@RequestMapping("/enquetes")
public class EnqueteResource {

	@Autowired
	private EnqueteService enqueteService;
	
	@Autowired
	private OpcaoService opcaoService;
	
	@GetMapping("{id}")
	public ResponseEntity<Enquete> findById(@PathVariable Long id) {
		return ResponseEntity.ok(enqueteService.find(id));
	}
	
	@PostMapping
	public ResponseEntity<Void> save(@Valid @RequestBody Enquete novaEnquete) {
		final Enquete enquete = enqueteService.save(novaEnquete);
		final URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(enquete.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/votar/{id}")
	public ResponseEntity<Void> votar(@PathVariable(name="id") Long opcaoId) {
		opcaoService.adicionarVoto(opcaoId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<Enquete>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="size", defaultValue="24") Integer size,
			@RequestParam(value="direction", defaultValue="DESC") String direction,
			@RequestParam(value="orderBy", defaultValue="dataCriacao") String orderBy) {
		return ResponseEntity.ok(enqueteService.findPage(page, size, direction, orderBy));
	}
	
}
package br.com.uepb.projetoweb.controller;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.com.uepb.projetoweb.model.Professor;
import br.com.uepb.projetoweb.model.Projeto;
import br.com.uepb.projetoweb.repository.ProfessorRepository;
import br.com.uepb.projetoweb.repository.ProjetoRepository;

public class ProjetoController {

	@Autowired
       ProjetoRepository p;
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<?> adicionaProjeto(@RequestBody Projeto projeto){
		Projeto projetoSalvo = p.save(projeto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/Projetos").path("/{id}").buildAndExpand(projetoSalvo.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public ResponseEntity<List<Projeto>> list() {
		return ResponseEntity.ok().body(p.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Projeto> getById(@PathVariable Long id) {
		return ResponseEntity.ok().body(p.findById(id).get());
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Optional<Projeto>> deleteById(@PathVariable Long id){
		try {
			p.deleteById(id);
			return new ResponseEntity<Optional<Projeto>>(HttpStatus.OK);
		}catch (NoSuchElementException nsee ) {
			return new ResponseEntity<Optional<Projeto>>(HttpStatus.NOT_FOUND);
		}
	
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Projeto> update(@PathVariable Long id, @RequestBody Projeto newProjeto){
		return p.findById(id)
				.map(projeto ->{
					projeto.setDescricao(newProjeto.getDescricao());
					projeto.setDescricao(newProjeto.getNome());
				
					Projeto projetoSalvo = p.save(projeto);
					return ResponseEntity.ok().body(projeto);
				}).orElse(ResponseEntity.notFound().build());	
		}
			
	


}


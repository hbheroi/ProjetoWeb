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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.uepb.projetoweb.model.Aluno;
import br.com.uepb.projetoweb.repository.AlunoRepository;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
	
	@Autowired
	AlunoRepository alunoRepo;
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<?> adicionaAluno(@RequestBody Aluno aluno){
		Aluno alunoSalvo = alunoRepo.save(aluno);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/alunos").path("/{id}").buildAndExpand(alunoSalvo.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public ResponseEntity<List<Aluno>> listaAlunos() {
		return ResponseEntity.ok().body(alunoRepo.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Aluno> getById(@PathVariable Long id) {
		return ResponseEntity.ok().body(alunoRepo.findById(id).get());
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Optional<Aluno>> deleteById(@PathVariable Long id){
		try {
			alunoRepo.deleteById(id);
			return new ResponseEntity<Optional<Aluno>>(HttpStatus.OK);
		}catch (NoSuchElementException nsee ) {
			return new ResponseEntity<Optional<Aluno>>(HttpStatus.NOT_FOUND);
		}
	
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Aluno> update(@PathVariable Long id, @RequestBody Aluno newAluno){
		return alunoRepo.findById(id)
				.map(aluno ->{
					aluno.setNome(newAluno.getNome());
					aluno.setCurso(newAluno.getCurso());
					Aluno alunoUpdated = alunoRepo.save(aluno);
					return ResponseEntity.ok().body(alunoUpdated);
				}).orElse(ResponseEntity.notFound().build());	
		}
			
	
	
	
}

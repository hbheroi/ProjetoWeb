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
import br.com.uepb.projetoweb.model.Professor;
import br.com.uepb.projetoweb.repository.AlunoRepository;
import br.com.uepb.projetoweb.repository.ProfessorRepository;
	
	@RestController
	@RequestMapping("/professores")
	public class ProfessorController {
		
		@Autowired
		ProfessorRepository professorRepo;
		
		@PostMapping(consumes = "application/json")
		public ResponseEntity<?> adicionaProfessor(@RequestBody Professor professor){
			Professor professorSalvo = professorRepo.save(professor);
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/professores").path("/{id}").buildAndExpand(professorSalvo.getId()).toUri();
			
			return ResponseEntity.created(uri).build();
		}
		
		@GetMapping
		public ResponseEntity<List<Professor>> listaProfessores() {
			return ResponseEntity.ok().body(professorRepo.findAll());
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<Professor> getById(@PathVariable Long id) {
			return ResponseEntity.ok().body(professorRepo.findById(id).get());
		}
		
		@DeleteMapping(path="/{id}")
		public ResponseEntity<Optional<Professor>> deleteById(@PathVariable Long id){
			try {
				professorRepo.deleteById(id);
				return new ResponseEntity<Optional<Professor>>(HttpStatus.OK);
			}catch (NoSuchElementException nsee ) {
				return new ResponseEntity<Optional<Professor>>(HttpStatus.NOT_FOUND);
			}
		
		}
		
		@PutMapping(value="/{id}")
		public ResponseEntity<Professor> update(@PathVariable Long id, @RequestBody Professor newProfessor){
			return professorRepo.findById(id)
					.map(professor ->{
						professor.setMatricula(newProfessor.getMatricula());
						professor.setNome(newProfessor.getNome());
						professor.setAreaAtuacao(newProfessor.getAreaAtuacao());
						professor.setFormacao(newProfessor.getFormacao());
						Professor professorUpdated = professorRepo.save(professor);
						return ResponseEntity.ok().body(professorUpdated);
					}).orElse(ResponseEntity.notFound().build());	
			}
				
		


	}


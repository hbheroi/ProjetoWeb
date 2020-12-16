package br.com.uepb.projetoweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import br.com.uepb.projetoweb.model.Professor;
	
	@Repository
	public interface ProfessorRepository extends JpaRepository<Professor, Long> {}




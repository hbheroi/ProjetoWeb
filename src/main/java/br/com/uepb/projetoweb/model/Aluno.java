package br.com.uepb.projetoweb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Table
@Data
public class Aluno {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	private String curso;
	
}

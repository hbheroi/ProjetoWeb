package br.com.uepb.projetoweb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Table
@Data
public class Projeto {
	
	@Id
	@GeneratedValue
	long id;
	
	String nome;
	String descricao;
	
	

}

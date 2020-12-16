package br.com.uepb.projetoweb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Table
@Data
public class Professor {
	
	@Id
	@GeneratedValue
	private long id;
	
	private int matricula;
	private String nome;
	private String areaAtuacao;
	private String formacao; 
	

}

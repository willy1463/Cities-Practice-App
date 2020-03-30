package com.wmorten.eurovisiontest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "cities")
public class City implements Serializable {

	private static final long serialVersionUID = -5260397228615147222L;
	
	@Id
	private Integer id;
	private String name;
	
}

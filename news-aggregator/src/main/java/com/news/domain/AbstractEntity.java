package com.news.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/**
 * @author 
 * this class contains all needed common entity feilds,
 * annotated with MappedSuperClass, otherwise Hibernate Entity will not find the properties
 */
@MappedSuperclass
public class AbstractEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	

}

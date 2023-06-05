package com.sccon.persons.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sccon.persons.model.PersonEntity;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Integer> {
	
	@Query("SELECT max(person.id) FROM PersonEntity person")
	public Integer getMaxId();
	
}

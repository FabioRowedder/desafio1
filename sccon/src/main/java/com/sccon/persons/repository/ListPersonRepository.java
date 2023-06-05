package com.sccon.persons.repository;

import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sccon.persons.model.PersonEntity;

@Repository
public interface ListPersonRepository extends ListPagingAndSortingRepository<PersonEntity, Integer> {

}

package com.sccon.persons.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sccon.persons.model.SalariosConfigEntity;

@Repository
public interface SalariosConfigRepository extends CrudRepository<SalariosConfigEntity, Integer> { }

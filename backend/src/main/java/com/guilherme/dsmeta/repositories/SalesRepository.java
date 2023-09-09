package com.guilherme.dsmeta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guilherme.dsmeta.entities.Sale;

public interface SalesRepository extends JpaRepository<Sale, Long> {

}

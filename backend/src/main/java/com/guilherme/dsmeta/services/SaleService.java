package com.guilherme.dsmeta.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.dsmeta.entities.Sale;
import com.guilherme.dsmeta.repositories.SalesRepository;

@Service
public class SaleService {
	
	@Autowired
	private SalesRepository salesRepository;
	
	public List<Sale> findSales() {
		return this.salesRepository.findAll();
	}
}

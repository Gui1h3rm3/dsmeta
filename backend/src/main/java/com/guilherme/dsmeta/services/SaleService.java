package com.guilherme.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.guilherme.dsmeta.entities.Sale;
import com.guilherme.dsmeta.repositories.SalesRepository;

@Service
public class SaleService {
	
	@Autowired
	private SalesRepository salesRepository;
	
	public Page<Sale> findSales(String minDate, String maxDate, Pageable pageable) {
		
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		
		LocalDate min = minDate.isBlank() ? today.minusDays(365) : LocalDate.parse(minDate);		
		LocalDate max = maxDate.isBlank() ? today : LocalDate.parse(maxDate);
		
		return this.salesRepository.findSales(min, max, pageable);
	}
}

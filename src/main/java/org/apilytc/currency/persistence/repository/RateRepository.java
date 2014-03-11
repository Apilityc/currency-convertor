package org.apilytc.currency.persistence.repository;

import java.util.List;

import org.apilytc.currency.persistence.domain.Rate;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends
		PagingAndSortingRepository<Rate, String> {

	/**
	 * 
	 */
	List<Rate> findAll();
}

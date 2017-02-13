package org.apilytic.currency.persistence.repository;

import org.apilytic.currency.persistence.domain.Rate;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RateRepository extends
		PagingAndSortingRepository<Rate, String> {

	List<Rate> findAll();
}

package org.apilytic.currency.persistence.repository;

import org.apilytic.currency.persistence.domain.RateTimeTrack;
import org.springframework.data.repository.CrudRepository;

public interface RateTimeTrackRepository extends
		CrudRepository<RateTimeTrack, String> {

}

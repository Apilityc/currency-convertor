package org.apilytic.currency.persistence.repository;

import org.apilytic.currency.persistence.domain.RateTimeTrack;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateTimeTrackRepository extends
		CrudRepository<RateTimeTrack, String> {

}

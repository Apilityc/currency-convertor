package org.apilytc.currency.persistence.repository;

import org.apilytc.currency.persistence.domain.RateTimeTrack;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateTimeTrackRepository extends
		CrudRepository<RateTimeTrack, String> {

}

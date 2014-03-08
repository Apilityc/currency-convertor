package org.apilytc.repository;

import org.apilytc.domain.RateTimeTrack;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateTimeTrackRepository extends
		CrudRepository<RateTimeTrack, String> {

}

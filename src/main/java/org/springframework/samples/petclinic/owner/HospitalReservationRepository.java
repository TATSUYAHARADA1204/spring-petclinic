package org.springframework.samples.petclinic.owner;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface HospitalReservationRepository extends JpaRepository<HospitalReservation, Integer> {

	@Transactional(readOnly = true)
	List<HospitalReservation> findAllByOrderByReservationTimeDesc();

}
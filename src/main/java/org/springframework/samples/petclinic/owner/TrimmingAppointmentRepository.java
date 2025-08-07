package org.springframework.samples.petclinic.owner;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface TrimmingAppointmentRepository extends JpaRepository<TrimmingAppointment, Integer> {

	@Transactional(readOnly = true)
	List<TrimmingAppointment> findAllByOrderByAppointmentTimeDesc();

}
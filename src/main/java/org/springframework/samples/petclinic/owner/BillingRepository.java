package org.springframework.samples.petclinic.owner;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<Billing, Integer> {

}
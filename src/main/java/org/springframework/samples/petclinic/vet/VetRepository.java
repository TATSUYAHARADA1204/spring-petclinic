package org.springframework.samples.petclinic.vet;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class for <code>Vet</code> domain objects All method names are compliant
 * with Spring Data naming conventions so this interface can easily be extended for Spring
 * Data.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 */
public interface VetRepository extends JpaRepository<Vet, Integer> {

}
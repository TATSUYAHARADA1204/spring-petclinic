package org.springframework.samples.petclinic.owner;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class for <code>Pet</code> domain objects All method names are compliant
 * with Spring Data naming conventions so this interface can easily be extended for Spring
 * Data.
 */
public interface PetRepository extends JpaRepository<Pet, Integer> {

	// JpaRepositoryを継承するだけで、findByIdなどの基本的なメソッドが利用可能になります。
	// このインターフェース内に、追加のメソッドを記述する必要はありません。

}
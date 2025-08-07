package org.springframework.samples.petclinic.owner;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.vet.Vet;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "hospital_reservations")
public class HospitalReservation extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "pet_id")
	@NotNull
	private Pet pet;

	@ManyToOne
	@JoinColumn(name = "vet_id")
	@NotNull
	private Vet vet;

	@Column(name = "reservation_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@NotNull
	private LocalDateTime reservationTime;

	@Column(name = "description")
	@NotEmpty
	private String description;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "reservation", fetch = FetchType.EAGER)
	private Set<Prescription> prescriptions = new HashSet<>();

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public Vet getVet() {
		return vet;
	}

	public void setVet(Vet vet) {
		this.vet = vet;
	}

	public LocalDateTime getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(LocalDateTime reservationTime) {
		this.reservationTime = reservationTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(Set<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

}
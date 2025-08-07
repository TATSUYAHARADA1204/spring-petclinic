package org.springframework.samples.petclinic.owner;

import org.springframework.samples.petclinic.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "prescriptions")
public class Prescription extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "reservation_id")
	@NotNull
	private HospitalReservation reservation;

	@Column(name = "medicine_name")
	@NotEmpty
	private String medicineName;

	@Column(name = "quantity")
	@NotNull
	@Min(1)
	private Integer quantity;

	public HospitalReservation getReservation() {
		return reservation;
	}

	public void setReservation(HospitalReservation reservation) {
		this.reservation = reservation;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
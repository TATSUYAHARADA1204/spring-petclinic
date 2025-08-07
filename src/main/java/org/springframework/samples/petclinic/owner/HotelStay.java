package org.springframework.samples.petclinic.owner;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "hotel_stays")
public class HotelStay extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "pet_id")
	@NotNull
	private Pet pet;

	@Column(name = "check_in_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private LocalDate checkInDate;

	@Column(name = "check_out_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private LocalDate checkOutDate;

	@Column(name = "room_number")
	@NotEmpty
	private String roomNumber;

	@OneToOne(mappedBy = "stay", cascade = CascadeType.ALL)
	private Billing billing;

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Billing getBilling() {
		return billing;
	}

	public void setBilling(Billing billing) {
		this.billing = billing;
	}

}
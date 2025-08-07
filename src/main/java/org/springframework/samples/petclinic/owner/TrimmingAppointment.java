package org.springframework.samples.petclinic.owner;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "trimming_appointments")
public class TrimmingAppointment extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "pet_id")
	@NotNull
	private Pet pet;

	@Column(name = "appointment_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@NotNull
	private LocalDateTime appointmentTime;

	@Column(name = "course")
	@NotEmpty
	private String course;

	@Column(name = "trimmer_name")
	@NotEmpty
	private String trimmerName;

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public LocalDateTime getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(LocalDateTime appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getTrimmerName() {
		return trimmerName;
	}

	public void setTrimmerName(String trimmerName) {
		this.trimmerName = trimmerName;
	}

}
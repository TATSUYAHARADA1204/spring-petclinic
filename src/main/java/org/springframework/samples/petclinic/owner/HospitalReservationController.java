package org.springframework.samples.petclinic.owner;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.samples.petclinic.owner.PetRepository;
import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.samples.petclinic.vet.VetRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;

@Controller
class HospitalReservationController {

	private final HospitalReservationRepository reservations;

	private final PetRepository pets;

	private final VetRepository vets;

	public HospitalReservationController(HospitalReservationRepository reservations, PetRepository pets,
			VetRepository vets) {
		this.reservations = reservations;
		this.pets = pets;
		this.vets = vets;
	}

	@ModelAttribute("vets")
	public Collection<Vet> populateVets() {
		return this.vets.findAll();
	}

	@GetMapping("/reservations/hospital")
	public String showReservationList(Map<String, Object> model) {
		model.put("reservations", this.reservations.findAllByOrderByReservationTimeDesc());
		return "reservations/hospitalReservationList";
	}

	@GetMapping("/owners/{ownerId}/pets/{petId}/reservations/hospital/new")
	public String initCreationForm(@PathVariable("petId") int petId, Map<String, Object> model) {
		Pet pet = this.pets.findById(petId).orElseThrow(() -> new IllegalArgumentException("Invalid pet Id:" + petId));
		HospitalReservation reservation = new HospitalReservation();
		reservation.setPet(pet);
		model.put("hospitalReservation", reservation);
		return "reservations/createOrUpdateHospitalReservationForm";
	}

	@PostMapping("/owners/{ownerId}/pets/{petId}/reservations/hospital/new")
	public String processCreationForm(@Valid @ModelAttribute("hospitalReservation") HospitalReservation reservation,
			BindingResult result, @PathVariable("ownerId") int ownerId, @PathVariable("petId") int petId, Model model) {
		if (result.hasErrors()) {
			Pet pet = this.pets.findById(petId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid pet Id:" + petId));
			reservation.setPet(pet);
			model.addAttribute("hospitalReservation", reservation);
			return "reservations/createOrUpdateHospitalReservationForm";
		}
		this.reservations.save(reservation);
		return "redirect:/owners/" + ownerId;
	}

	@GetMapping("/reservations/hospital/{reservationId}/edit")
	public String initUpdateForm(@PathVariable("reservationId") int reservationId, Model model) {
		HospitalReservation reservation = this.reservations.findById(reservationId)
			.orElseThrow(() -> new IllegalArgumentException("Invalid reservation Id:" + reservationId));
		model.addAttribute("hospitalReservation", reservation);
		return "reservations/createOrUpdateHospitalReservationForm";
	}

	@PostMapping("/reservations/hospital/{reservationId}/edit")
	public String processUpdateForm(@Valid @ModelAttribute("hospitalReservation") HospitalReservation reservation,
			BindingResult result, @PathVariable("reservationId") int reservationId) {
		if (result.hasErrors()) {
			return "reservations/createOrUpdateHospitalReservationForm";
		}
		reservation.setId(reservationId);
		this.reservations.save(reservation);
		return "redirect:/reservations/hospital";
	}

}
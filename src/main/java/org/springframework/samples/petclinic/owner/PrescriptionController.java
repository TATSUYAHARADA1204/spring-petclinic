package org.springframework.samples.petclinic.owner;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;

@Controller
class PrescriptionController {

	private final PrescriptionRepository prescriptions;

	private final HospitalReservationRepository reservations;

	public PrescriptionController(PrescriptionRepository prescriptions, HospitalReservationRepository reservations) {
		this.prescriptions = prescriptions;
		this.reservations = reservations;
	}

	@GetMapping("/reservations/hospital/{reservationId}/prescriptions")
	public String showPrescriptionList(@PathVariable("reservationId") int reservationId, Map<String, Object> model) {
		HospitalReservation reservation = this.reservations.findById(reservationId)
			.orElseThrow(() -> new IllegalArgumentException("Invalid reservation Id:" + reservationId));
		model.put("reservation", reservation);
		return "reservations/prescriptionList";
	}

	@GetMapping("/reservations/hospital/{reservationId}/prescriptions/new")
	public String initCreationForm(@PathVariable("reservationId") int reservationId, Map<String, Object> model) {
		HospitalReservation reservation = this.reservations.findById(reservationId)
			.orElseThrow(() -> new IllegalArgumentException("Invalid reservation Id:" + reservationId));
		Prescription prescription = new Prescription();
		prescription.setReservation(reservation);
		model.put("prescription", prescription);
		return "reservations/createOrUpdatePrescriptionForm";
	}

	@PostMapping("/reservations/hospital/{reservationId}/prescriptions/new")
	public String processCreationForm(@Valid @ModelAttribute("prescription") Prescription prescription,
			BindingResult result, @PathVariable("reservationId") int reservationId, Model model) {
		if (result.hasErrors()) {
			HospitalReservation reservation = this.reservations.findById(reservationId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid reservation Id:" + reservationId));
			prescription.setReservation(reservation);
			model.addAttribute("prescription", prescription);
			return "reservations/createOrUpdatePrescriptionForm";
		}
		this.prescriptions.save(prescription);
		return "redirect:/reservations/hospital/" + reservationId + "/prescriptions";
	}

}

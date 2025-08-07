package org.springframework.samples.petclinic.owner;

import java.util.Map;
import org.springframework.samples.petclinic.owner.PetRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;

@Controller
class TrimmingAppointmentController {

	private final TrimmingAppointmentRepository appointments;

	private final PetRepository pets;

	public TrimmingAppointmentController(TrimmingAppointmentRepository appointments, PetRepository pets) {
		this.appointments = appointments;
		this.pets = pets;
	}

	@GetMapping("/reservations/trimming")
	public String showTrimmingList(Map<String, Object> model) {
		model.put("appointments", this.appointments.findAllByOrderByAppointmentTimeDesc());
		return "reservations/trimmingAppointmentList";
	}

	@GetMapping("/owners/{ownerId}/pets/{petId}/reservations/trimming/new")
	public String initCreationForm(@PathVariable("petId") int petId, Map<String, Object> model) {
		Pet pet = this.pets.findById(petId).orElseThrow(() -> new IllegalArgumentException("Invalid pet Id:" + petId)); // ★
																														// 修正
		TrimmingAppointment appointment = new TrimmingAppointment();
		appointment.setPet(pet);
		model.put("trimmingAppointment", appointment);
		return "reservations/createOrUpdateTrimmingAppointmentForm";
	}

	@PostMapping("/owners/{ownerId}/pets/{petId}/reservations/trimming/new")
	public String processCreationForm(@Valid @ModelAttribute("trimmingAppointment") TrimmingAppointment appointment,
			BindingResult result, @PathVariable("ownerId") int ownerId, @PathVariable("petId") int petId, Model model) {
		if (result.hasErrors()) {
			Pet pet = this.pets.findById(petId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid pet Id:" + petId));
			appointment.setPet(pet);
			model.addAttribute("trimmingAppointment", appointment);
			return "reservations/createOrUpdateTrimmingAppointmentForm";
		}
		this.appointments.save(appointment);
		return "redirect:/owners/" + ownerId;
	}

	@GetMapping("/reservations/trimming/{appointmentId}/edit")
	public String initUpdateForm(@PathVariable("appointmentId") int appointmentId, Model model) {
		TrimmingAppointment appointment = this.appointments.findById(appointmentId)
			.orElseThrow(() -> new IllegalArgumentException("Invalid appointment Id:" + appointmentId));
		model.addAttribute("trimmingAppointment", appointment);
		return "reservations/createOrUpdateTrimmingAppointmentForm";
	}

	@PostMapping("/reservations/trimming/{appointmentId}/edit")
	public String processUpdateForm(@Valid @ModelAttribute("trimmingAppointment") TrimmingAppointment appointment,
			BindingResult result, @PathVariable("appointmentId") int appointmentId) {
		if (result.hasErrors()) {
			return "reservations/createOrUpdateTrimmingAppointmentForm";
		}
		appointment.setId(appointmentId);
		this.appointments.save(appointment);
		return "redirect:/reservations/trimming";
	}

}

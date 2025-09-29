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
import org.springframework.ui.ModelMap;

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
		Pet pet = this.pets.findById(petId)
			.orElseThrow(() -> new IllegalArgumentException("Invalid pet Id:" + petId));
		TrimmingAppointment trimmingAppointment = new TrimmingAppointment();
		trimmingAppointment.setPet(pet);
		model.put("trimmingAppointment", trimmingAppointment);
		return "reservations/createOrUpdateTrimmingAppointmentForm";
	}

	@PostMapping("/owners/{ownerId}/pets/{petId}/reservations/trimming/new")
	public String processCreationForm(@PathVariable("petId") int petId, @Valid @ModelAttribute("trimmingAppointment") TrimmingAppointment appointment,
									  BindingResult result, ModelMap model) {
		Pet pet = this.pets.findById(petId).orElseThrow(() -> new IllegalArgumentException("Invalid pet Id:" + petId));
		if (result.hasErrors()) {
			appointment.setPet(pet);
			model.put("trimmingAppointment", appointment);
			return "reservations/createOrUpdateTrimmingAppointmentForm";
		}
		else {
			appointment.setPet(pet);
			this.appointments.save(appointment);
			return "redirect:/owners/{ownerId}";
		}
	}

	@ModelAttribute("trimmingAppointment")
	public TrimmingAppointment appointment(@PathVariable(name = "appointmentId", required = false) Integer appointmentId) {
		if (appointmentId == null) {
			return new TrimmingAppointment();
		}
		return this.appointments.findById(appointmentId)
			.orElseThrow(() -> new IllegalArgumentException("Invalid appointment Id:" + appointmentId));
	}

	@GetMapping("/reservations/trimming/{appointmentId}/edit")
	public String initUpdateForm(@ModelAttribute("trimmingAppointment") TrimmingAppointment appointment, Model model) {
		model.addAttribute("pet", appointment.getPet());
		return "reservations/createOrUpdateTrimmingAppointmentForm";
	}

	@PostMapping("/reservations/trimming/{appointmentId}/edit")
	public String processUpdateForm(@Valid @ModelAttribute("trimmingAppointment") TrimmingAppointment appointment,
									BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("pet", appointment.getPet());
			return "reservations/createOrUpdateTrimmingAppointmentForm";
		}

		this.appointments.save(appointment);
		return "redirect:/reservations/trimming";
	}

	// --- 削除処理 ---
	@PostMapping("/reservations/trimming/{appointmentId}/delete")
	public String deleteAppointment(@PathVariable("appointmentId") int appointmentId) {
		this.appointments.deleteById(appointmentId);
		return "redirect:/reservations/trimming";
	}

}

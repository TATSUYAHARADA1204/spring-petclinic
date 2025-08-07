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
class HotelStayController {

	private final HotelStayRepository stays;

	private final PetRepository pets;

	public HotelStayController(HotelStayRepository stays, PetRepository pets) {
		this.stays = stays;
		this.pets = pets;
	}

	@GetMapping("/reservations/hotel")
	public String showHotelStayList(Map<String, Object> model) {
		model.put("stays", this.stays.findAllByOrderByCheckInDateDesc());
		return "reservations/hotelStayList";
	}

	@GetMapping("/owners/{ownerId}/pets/{petId}/reservations/hotel/new")
	public String initCreationForm(@PathVariable("petId") int petId, Map<String, Object> model) {
		Pet pet = this.pets.findById(petId).orElseThrow(() -> new IllegalArgumentException("Invalid pet Id:" + petId)); // ★
																														// 修正
		HotelStay stay = new HotelStay();
		stay.setPet(pet);
		model.put("hotelStay", stay);
		return "reservations/createOrUpdateHotelStayForm";
	}

	@PostMapping("/owners/{ownerId}/pets/{petId}/reservations/hotel/new")
	public String processCreationForm(@Valid @ModelAttribute("hotelStay") HotelStay stay, BindingResult result,
			@PathVariable("ownerId") int ownerId, @PathVariable("petId") int petId, Model model) {
		if (result.hasErrors()) {
			Pet pet = this.pets.findById(petId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid pet Id:" + petId));
			stay.setPet(pet);
			model.addAttribute("hotelStay", stay);
			return "reservations/createOrUpdateHotelStayForm";
		}
		this.stays.save(stay);
		return "redirect:/owners/" + ownerId;
	}

	@GetMapping("/reservations/hotel/{stayId}/edit")
	public String initUpdateForm(@PathVariable("stayId") int stayId, Model model) {
		HotelStay stay = this.stays.findById(stayId)
			.orElseThrow(() -> new IllegalArgumentException("Invalid stay Id:" + stayId));
		model.addAttribute("hotelStay", stay);
		return "reservations/createOrUpdateHotelStayForm";
	}

	@PostMapping("/reservations/hotel/{stayId}/edit")
	public String processUpdateForm(@Valid @ModelAttribute("hotelStay") HotelStay stay, BindingResult result,
			@PathVariable("stayId") int stayId) {
		if (result.hasErrors()) {
			return "reservations/createOrUpdateHotelStayForm";
		}
		stay.setId(stayId);
		this.stays.save(stay);
		return "redirect:/reservations/hotel";
	}

}
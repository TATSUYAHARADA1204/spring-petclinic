package org.springframework.samples.petclinic.owner;

import java.util.Map;
import org.springframework.samples.petclinic.owner.PetRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
		Pet pet = this.pets.findById(petId).orElseThrow(() -> new IllegalArgumentException("Invalid pet Id:" + petId));
		HotelStay stays = new HotelStay();
		stays.setPet(pet);
		model.put("hotelStay", stays);
		return "reservations/createOrUpdateHotelStayForm";
	}

	@PostMapping("/owners/{ownerId}/pets/{petId}/reservations/hotel/new")
	public String processCreationForm(@PathVariable("petId") int petId,
			@Valid @ModelAttribute("hotelStay") HotelStay stay, BindingResult result,
			@PathVariable("ownerId") int ownerId, ModelMap model) {
		Pet pet = this.pets.findById(petId).orElseThrow(() -> new IllegalArgumentException("Invalid pet Id:" + petId));
		if (result.hasErrors()) {
			stay.setPet(pet);
			model.put("hotelStay", stay);
			return "reservations/createOrUpdateHotelStayForm";
		}
		else {
			stay.setPet(pet);
			this.stays.save(stay);
			return "redirect:/owners/{ownerId}";
		}
	}

	@ModelAttribute("hotelStay")
	public HotelStay stay(@PathVariable(name = "stayId", required = false) Integer stayId) {
		if (stayId == null) {
			return new HotelStay();
		}
		return this.stays.findById(stayId).orElseThrow(() -> new IllegalArgumentException("Invalid stay Id:" + stayId));
	}

	@GetMapping("/reservations/hotel/{stayId}/edit")
	public String initUpdateForm(@ModelAttribute("hotelStay") HotelStay stay, Model model) {
		model.addAttribute("pet", stay.getPet());
		return "reservations/createOrUpdateHotelStayForm";
	}

	@PostMapping("/reservations/hotel/{stayId}/edit")
	public String processUpdateForm(@Valid @ModelAttribute("hotelStay") HotelStay stay, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("pet", stay.getPet());
			return "reservations/createOrUpdateHotelStayForm";
		}

		this.stays.save(stay);
		return "redirect:/reservations/hotel";
	}

}

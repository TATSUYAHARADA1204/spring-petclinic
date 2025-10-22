package org.springframework.samples.petclinic.owner;

import java.util.Collection;
import java.util.Map;

import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.samples.petclinic.vet.VetRepository;
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
class HospitalReservationController {

	private final HospitalReservationService reservationService;

	private final PetRepository petRepository;

	private final VetRepository vetRepository;

	public HospitalReservationController(HospitalReservationService reservationService, PetRepository petRepository,
			VetRepository vetRepository) {
		this.reservationService = reservationService;
		this.petRepository = petRepository;
		this.vetRepository = vetRepository;
	}

	@ModelAttribute("vets")
	public Collection<Vet> populateVets() {
		return this.vetRepository.findAll();
	}

	/**
	 * 病院予約一覧を表示する。予約時間の降順で並べ替えた結果を取得し、画面に渡す。
	 */
	@GetMapping("/reservations/hospital")
	public String showReservationList(Map<String, Object> model) {
		model.put("reservations", this.reservationService.getReservationsWithValidation());
		return "reservations/hospitalReservationList";
	}

	/**
	 * 新規予約フォームの初期表示。
	 */
	@GetMapping("/owners/{ownerId}/pets/{petId}/reservations/hospital/new")
	public String initCreationForm(@PathVariable("petId") int petId, Map<String, Object> model) {
		Pet pet = this.petRepository.findById(petId)
			.orElseThrow(() -> new IllegalArgumentException("Invalid pet Id:" + petId));
		HospitalReservation reservation = new HospitalReservation();
		reservation.setPet(pet);
		model.put("hospitalReservation", reservation);
		return "reservations/createOrUpdateHospitalReservationForm";
	}

	/**
	 * 新規予約フォームの送信処理。
	 */
	@PostMapping("/owners/{ownerId}/pets/{petId}/reservations/hospital/new")
	public String processCreationForm(@PathVariable("petId") int petId,
			@Valid @ModelAttribute("hospitalReservation") HospitalReservation reservation, BindingResult result,
			@PathVariable("ownerId") int ownerId, ModelMap model) {
		Pet pet = this.petRepository.findById(petId)
			.orElseThrow(() -> new IllegalArgumentException("Invalid pet Id:" + petId));
		if (result.hasErrors()) {
			reservation.setPet(pet);
			model.put("hospitalReservation", reservation);
			return "reservations/createOrUpdateHospitalReservationForm";
		}
		else {
			reservation.setPet(pet);
			this.reservationService.save(reservation);
			return "redirect:/owners/{ownerId}";
		}
	}

	/**
	 * 編集対象の予約情報を事前に取得。
	 */
	@ModelAttribute("hospitalReservation")
	public HospitalReservation reservation(
			@PathVariable(name = "reservationId", required = false) Integer reservationId) {
		if (reservationId == null) {
			return new HospitalReservation();
		}
		return this.reservationService.findById(reservationId);
	}

	/**
	 * 予約編集フォームの初期表示。
	 */
	@GetMapping("/reservations/hospital/{reservationId}/edit")
	public String initUpdateForm(@ModelAttribute("hospitalReservation") HospitalReservation reservation, Model model) {
		model.addAttribute("pet", reservation.getPet());
		return "reservations/createOrUpdateHospitalReservationForm";
	}

	/**
	 * 予約編集フォームの送信処理。
	 */
	@PostMapping("/reservations/hospital/{reservationId}/edit")
	public String processUpdateForm(@Valid @ModelAttribute("hospitalReservation") HospitalReservation reservation,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("pet", reservation.getPet());
			return "reservations/createOrUpdateHospitalReservationForm";
		}
		this.reservationService.save(reservation);
		return "redirect:/reservations/hospital";
	}

}
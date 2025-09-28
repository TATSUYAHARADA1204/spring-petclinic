package org.springframework.samples.petclinic.owner;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.samples.petclinic.owner.PetRepository;
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
		Pet pet = this.pets.findById(petId)
			.orElseThrow(() -> new IllegalArgumentException("Invalid pet Id:" + petId));
		HospitalReservation reservation = new HospitalReservation();
		reservation.setPet(pet);
		model.put("hospitalReservation", reservation);
		return "reservations/createOrUpdateHospitalReservationForm";
	}

	@PostMapping("/owners/{ownerId}/pets/{petId}/reservations/hospital/new")
	public String processCreationForm(@PathVariable("petId") int petId,@Valid @ModelAttribute("hospitalReservation") HospitalReservation reservation,
									  BindingResult result, @PathVariable("ownerId") int ownerId, ModelMap model) {
		Pet pet = this.pets.findById(petId).orElseThrow(() -> new IllegalArgumentException("Invalid pet Id:" + petId));
		if (result.hasErrors()) {
			reservation.setPet(pet);
			model.put("hospitalReservation", reservation);
			return "reservations/createOrUpdateHospitalReservationForm";
		}
		else {
			reservation.setPet(pet);
			this.reservations.save(reservation);
			return "redirect:/owners/{ownerId}";
		}
	}

	/**
	 * 編集対象の予約情報を事前に取得し、モデルに"hospitalReservation"という名前で格納します。
	 * このメソッドはinitUpdateFormやprocessUpdateFormよりも先に実行されます。
	 */
	@ModelAttribute("hospitalReservation")
	public HospitalReservation reservation(@PathVariable(name = "reservationId", required = false) Integer reservationId) {
		if (reservationId == null) {
			return new HospitalReservation();
		}
		// DBから予約情報を取得（これにはペット情報も含まれています）
		return this.reservations.findById(reservationId)
			.orElseThrow(() -> new IllegalArgumentException("Invalid reservation Id:" + reservationId));
	}

	@GetMapping("/reservations/hospital/{reservationId}/edit")
	public String initUpdateForm(@ModelAttribute("hospitalReservation") HospitalReservation reservation, Model model) {
		// 事前に準備された予約情報からペット情報を取得し、画面表示用にモデルへ追加
		model.addAttribute("pet", reservation.getPet());
		return "reservations/createOrUpdateHospitalReservationForm";
	}

	@PostMapping("/reservations/hospital/{reservationId}/edit")
	public String processUpdateForm(@Valid @ModelAttribute("hospitalReservation") HospitalReservation reservation,
									BindingResult result, Model model) {
		if (result.hasErrors()) {
			// バリデーションエラーで画面に戻る際も、ペット情報をモデルへ追加
			model.addAttribute("pet", reservation.getPet());
			return "reservations/createOrUpdateHospitalReservationForm";
		}

		this.reservations.save(reservation);
		return "redirect:/reservations/hospital";
	}

}

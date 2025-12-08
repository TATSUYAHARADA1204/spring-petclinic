package org.springframework.samples.petclinic.owner;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class HospitalReservationService {

	private final HospitalReservationRepository reservationRepository;

	public HospitalReservationService(HospitalReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	public List<HospitalReservation> getReservationsWithValidation() {
		List<HospitalReservation> reservations = reservationRepository.findAllByOrderByReservationTimeDesc();

		// Descriptionが全角で入力されるとエラーになるよう変更
		for (HospitalReservation reservation : reservations) {
			if (containsFullWidth(reservation.getDescription())) {
				throw new IllegalArgumentException("全角文字が含まれています: " + reservation.getDescription());
			}
		}

		return reservations;
	}

	private boolean containsFullWidth(String text) {
		// 全角スペースや全角記号・英数字を含むかチェック
		return text != null && text.matches(".*[！-～ｦ-ﾟ　].*");
	}

	public HospitalReservation save(HospitalReservation reservation) {
		return reservationRepository.save(reservation);
	}

	public HospitalReservation findById(Integer reservationId) {
		return reservationRepository.findById(reservationId)
			.orElseThrow(() -> new IllegalArgumentException("Invalid reservation Id: " + reservationId));
	}

}
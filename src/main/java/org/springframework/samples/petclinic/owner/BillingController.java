package org.springframework.samples.petclinic.owner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;

@Controller
class BillingController {

	private final BillingRepository billings;

	private final HotelStayRepository stays;

	private static final BigDecimal DAILY_RATE = new BigDecimal("5000"); // 1泊あたりの料金

	public BillingController(BillingRepository billings, HotelStayRepository stays) {
		this.billings = billings;
		this.stays = stays;
	}

	@GetMapping("/stays/{stayId}/billing")
	public String showBillingForm(@PathVariable("stayId") int stayId, Model model) {
		HotelStay stay = this.stays.findById(stayId)
			.orElseThrow(() -> new IllegalArgumentException("Invalid stay Id:" + stayId));
		Billing billing = new Billing();
		billing.setStay(stay);

		// 宿泊日数から料金を計算
		long nights = ChronoUnit.DAYS.between(stay.getCheckInDate(), stay.getCheckOutDate());
		nights = Math.max(nights, 1); // 最低1泊
		BigDecimal amount = DAILY_RATE.multiply(new BigDecimal(nights));
		billing.setAmount(amount);

		model.addAttribute("billing", billing);
		return "reservations/billingForm";
	}

	@PostMapping("/stays/{stayId}/billing")
	public String processBilling(@Valid @ModelAttribute("billing") Billing billing, BindingResult result,
			@PathVariable("stayId") int stayId) {
		if (result.hasErrors()) {
			return "reservations/billingForm";
		}
		billing.setPaymentDate(LocalDate.now()); // 本日日付で支払い完了とする
		this.billings.save(billing);
		return "redirect:/reservations/hotel";
	}

}
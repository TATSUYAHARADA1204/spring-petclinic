package org.springframework.samples.petclinic.owner;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

	// 小数点以下を含む単価を設定
	private static final BigDecimal DAILY_RATE_WITHOUT_TAX = new BigDecimal("4545.45");

	private static final BigDecimal MEAL_RATE_WITHOUT_TAX = new BigDecimal("2272.72");

	private static final BigDecimal TAX_RATE = new BigDecimal("0.10");

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

		// 宿泊日数の計算
		long nights = ChronoUnit.DAYS.between(stay.getCheckInDate(), stay.getCheckOutDate());
		nights = Math.max(nights, 1);

		// 画面表示用の明細計算
		BigDecimal subtotalHotel = DAILY_RATE_WITHOUT_TAX.multiply(new BigDecimal(nights));
		BigDecimal subtotalMeal = MEAL_RATE_WITHOUT_TAX.multiply(new BigDecimal(nights));
		BigDecimal subtotalWithoutTax = subtotalHotel.add(subtotalMeal);

		// 消費税額と明細合計（税込）を項目ごとに計算
		// 宿泊費の税込額（切り捨て）
		BigDecimal hotelFeeWithTax = DAILY_RATE_WITHOUT_TAX.multiply(BigDecimal.ONE.add(TAX_RATE))
			.setScale(0, RoundingMode.DOWN);
		BigDecimal totalHotelWithTax = hotelFeeWithTax.multiply(new BigDecimal(nights));

		// 食事代の税込額（切り捨て）
		BigDecimal mealFeeWithTax = MEAL_RATE_WITHOUT_TAX.multiply(BigDecimal.ONE.add(TAX_RATE))
			.setScale(0, RoundingMode.DOWN);
		BigDecimal totalMealWithTax = mealFeeWithTax.multiply(new BigDecimal(nights));

		// 明細ごとの税込額を足し合わせて表示用合計とする
		BigDecimal displayedSubtotalWithTax = totalHotelWithTax.add(totalMealWithTax);

		// 総合計（請求金額）の計算（すべての税抜き額を足してから税をかける）
		BigDecimal totalAmount = subtotalWithoutTax.multiply(BigDecimal.ONE.add(TAX_RATE))
			.setScale(0, RoundingMode.HALF_UP);

		// 消費税の計算（表示用）
		BigDecimal displayedTaxAmount = displayedSubtotalWithTax
			.subtract(subtotalWithoutTax.setScale(0, RoundingMode.HALF_UP));

		// 差額を計算
		BigDecimal difference = displayedSubtotalWithTax.subtract(totalAmount);

		// モデルに値を追加
		model.addAttribute("billing", billing);
		model.addAttribute("hotelFeeWithoutTax", DAILY_RATE_WITHOUT_TAX.setScale(0, RoundingMode.HALF_UP));
		model.addAttribute("mealFeeWithoutTax", MEAL_RATE_WITHOUT_TAX.setScale(0, RoundingMode.HALF_UP));
		model.addAttribute("numberOfNights", nights);
		model.addAttribute("subtotalHotel", subtotalHotel);
		model.addAttribute("subtotalMeal", subtotalMeal);
		model.addAttribute("displayedTaxAmount", displayedTaxAmount);
		model.addAttribute("totalAmount", totalAmount);
		model.addAttribute("displayedSubtotalWithTax", displayedSubtotalWithTax);
		model.addAttribute("difference", difference);

		return "reservations/billingForm";
	}

	@PostMapping("/stays/{stayId}/billing")
	public String processBilling(@Valid @ModelAttribute("billing") Billing billing, BindingResult result,
			@PathVariable("stayId") int stayId) {
		if (result.hasErrors()) {
			return "reservations/billingForm";
		}
		billing.setPaymentDate(LocalDate.now());
		this.billings.save(billing);
		return "redirect:/reservations/hotel";
	}

}
package org.springframework.samples.petclinic.owner;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "billings")
public class Billing extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "stay_id")
	@NotNull
	private HotelStay stay;

	@Column(name = "amount")
	@NotNull
	private BigDecimal amount;

	@Column(name = "payment_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate paymentDate;

	public HotelStay getStay() {
		return stay;
	}

	public void setStay(HotelStay stay) {
		this.stay = stay;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

}
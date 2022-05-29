package yanse.airbnb.domain.reservation;

import javax.persistence.Embeddable;
import yanse.airbnb.type.DiscountPolicy;

@Embeddable
public class DetailFee {

	private DiscountPolicy discountPolicy;
	private double cleaningFee;
	private double serviceTax;
	private double roomTax;

}

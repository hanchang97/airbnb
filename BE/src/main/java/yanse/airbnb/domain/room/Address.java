package yanse.airbnb.domain.room;

import javax.persistence.Embeddable;

import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@Getter
public class Address {

	private String city;
	private String region;
	private String district;
	private String detail;
}

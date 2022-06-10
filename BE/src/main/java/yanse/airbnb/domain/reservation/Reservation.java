package yanse.airbnb.domain.reservation;

import static javax.persistence.FetchType.LAZY;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yanse.airbnb.domain.member.Members;
import yanse.airbnb.domain.room.Room;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservation_id")
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "members_id")
	private Members members;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "room_id")
	private Room room;

	private int adultCount;

	private int childCount;

	private int infantCount;

	private int reservationPrice;

	@Embedded
	private DetailFee detailFee;

	private LocalDate checkInDateTime;

	private LocalDate checkOutDateTime;
}

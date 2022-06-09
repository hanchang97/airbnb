package yanse.airbnb.web.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import yanse.airbnb.domain.member.Members;
import yanse.airbnb.domain.reservation.Reservation;
import yanse.airbnb.domain.room.Address;
import yanse.airbnb.domain.room.Room;
import yanse.airbnb.web.dto.image.ImageDto;
import yanse.airbnb.web.dto.image.RoomImageDto;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDto {

    private Long reservationId;

    private String imageUrl;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkIn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkOut;

    private Address address;

    private String roomName;

    public ReservationDto(Reservation reservation) {
        Room room = reservation.getRoom();
        this.reservationId = reservation.getId();
        this.imageUrl = room.getRoomImage().stream().findFirst().orElseThrow(RuntimeException::new).getUrl();
        this.checkIn = reservation.getCheckInDateTime();
        this.checkOut = reservation.getCheckOutDateTime();
        this.address = room.getAddress();
        this.roomName = room.getRoomName();
    }

}

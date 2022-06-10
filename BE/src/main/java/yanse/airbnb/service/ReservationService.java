package yanse.airbnb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yanse.airbnb.domain.image.repository.RoomImageRepository;
import yanse.airbnb.domain.reservation.Reservation;
import yanse.airbnb.domain.reservation.repository.ReservationRepository;
import yanse.airbnb.web.dto.image.RoomImageDto;
import yanse.airbnb.web.dto.reservation.ReservationDetailDto;
import yanse.airbnb.web.dto.reservation.ReservationDto;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomImageRepository roomImageRepository;

    public List<ReservationDto> findReservationList(Long id) {
        return reservationRepository.findAllByReservation(id)
                .stream()
                .map(ReservationDto::new)
                .collect(Collectors.toList());
    }

    public ReservationDetailDto findReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(RuntimeException::new);
        List<RoomImageDto> images = roomImageRepository.findAllByRoomId(id)
                .stream()
                .map(RoomImageDto::new)
                .collect(Collectors.toList());
        return new ReservationDetailDto(reservation, images);
    }
}

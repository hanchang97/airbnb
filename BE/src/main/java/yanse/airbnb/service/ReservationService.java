package yanse.airbnb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yanse.airbnb.domain.reservation.repository.ReservationRepository;
import yanse.airbnb.web.dto.reservation.ReservationDto;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public List<ReservationDto> findReservationList(Long id) {
        return reservationRepository.findAllByReservation(id)
                .stream()
                .map(ReservationDto::new)
                .collect(Collectors.toList());
    }
}

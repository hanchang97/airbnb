package yanse.airbnb.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yanse.airbnb.service.ReservationService;
import yanse.airbnb.web.dto.reservation.ReservationDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("my")
    public List<ReservationDto> reservationList(@RequestParam("memberId") Long memberId){
        return reservationService.findReservationList(memberId);
    }
}
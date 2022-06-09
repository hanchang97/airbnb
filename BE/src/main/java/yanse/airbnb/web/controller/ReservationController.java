package yanse.airbnb.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yanse.airbnb.service.ReservationService;
import yanse.airbnb.web.dto.reservation.ReservationDetailDto;
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

    @GetMapping("my/{id}")
    public ReservationDetailDto reservationDetail(@PathVariable("id") Long id){
        return reservationService.findReservation(id);
    }
}
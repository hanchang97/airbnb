package yanse.airbnb.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yanse.airbnb.service.SearchService;
import yanse.airbnb.web.dto.ResponseDto;
import yanse.airbnb.web.dto.ResponseSearchAddressDto;
import yanse.airbnb.web.dto.RoomDto;

@RequestMapping("/search")
@RequiredArgsConstructor
@RestController
public class SearchController {

	private final SearchService searchService;

	@GetMapping
	public ResponseDto<ResponseSearchAddressDto> searchAddress(@RequestParam String address) {
		return new ResponseDto<>(searchService.findAddress(address));
	}

	@GetMapping("/room/{id}")
	public RoomDto searchRoomDetail(@PathVariable("id") Long id){
		return searchService.findRoomDetail(id);
	}
}

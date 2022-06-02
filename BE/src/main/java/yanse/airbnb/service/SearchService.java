package yanse.airbnb.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yanse.airbnb.domain.room.repository.RoomRepository;
import yanse.airbnb.web.dto.RequestRoomSearchDto;
import yanse.airbnb.web.dto.ResponseRoomDto;
import yanse.airbnb.domain.image.RoomImage;
import yanse.airbnb.domain.image.RoomImageRepository;
import yanse.airbnb.domain.room.Room;
import yanse.airbnb.web.dto.RoomDto;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class SearchService {

	private final RoomRepository roomRepository;
	private final RoomImageRepository roomImageRepository;

	public List<String> findAddress(String address) {
		return roomRepository.findByAddress(address);
	}

	public RoomDto findRoomDetail(Long id){
		//TODO custom 예외처리
		Room room = roomRepository.findById(id).orElseThrow(RuntimeException::new);
		List<RoomImage> roomImages = roomImageRepository.findAllByRoomId(id);
		return new RoomDto(room, roomImages);

	}

	public List<ResponseRoomDto> findCardRoomList(RequestRoomSearchDto dto) {
		return roomRepository.findRoomList(dto).stream()
				.map(room -> new ResponseRoomDto(room, dto.Days()))
				.collect(Collectors.toList());
	}
}

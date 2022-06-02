package yanse.airbnb.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yanse.airbnb.domain.image.RoomImage;
import yanse.airbnb.domain.image.RoomImageRepository;
import yanse.airbnb.domain.room.Room;
import yanse.airbnb.domain.room.RoomRepository;
import yanse.airbnb.web.dto.ResponseSearchAddressDto;
import yanse.airbnb.web.dto.RoomDto;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class SearchService {

	private final RoomRepository roomRepository;
	private final RoomImageRepository roomImageRepository;

	public List<ResponseSearchAddressDto> findAddress(String address) {
		return roomRepository.findByAddress(address);
	}

	public RoomDto findRoomDetail(Long id){
		//TODO custom 예외처리
		Room room = roomRepository.findById(id).orElseThrow(RuntimeException::new);
		List<RoomImage> roomImages = roomImageRepository.findAllByRoomId(id);
		return new RoomDto(room, roomImages);

	}
}

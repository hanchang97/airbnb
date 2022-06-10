package yanse.airbnb.web.dto.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yanse.airbnb.domain.room.Address;
import yanse.airbnb.domain.room.Room;
import yanse.airbnb.domain.room.RoomInfo;

import java.util.List;
import yanse.airbnb.web.dto.image.RoomImageDto;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDetailDto {

	private String roomName;
	private List<RoomImageDto> roomImage;
	private double rating;
	private int reviewCount;
	private Address address;
	private RoomInfo roomInfo;
	private String roomDescription;

	public RoomDetailDto(Room room, List<RoomImageDto> roomImages) {
		this.roomName = room.getRoomName();
		this.roomImage = roomImages;
		this.rating = room.getRating();
		this.reviewCount = room.getReviewCount();
		this.address = room.getAddress();
		this.roomInfo = room.getRoomInfo();
		this.roomDescription = room.getRoomDescription();
	}
}
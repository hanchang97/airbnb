package yanse.airbnb.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yanse.airbnb.domain.image.RoomImage;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomImageListDto {

	private Long id;

	private String url;

	public RoomImageListDto(RoomImage roomImage) {
		id = roomImage.getId();
		url = roomImage.getUrl();
	}

	public RoomImage toEntity() {
		return RoomImage.builder()
			.id(id)
			.url(url)
			.build();
	}
}

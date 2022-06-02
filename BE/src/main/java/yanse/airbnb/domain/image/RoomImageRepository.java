package yanse.airbnb.domain.image;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomImageRepository extends JpaRepository<RoomImage, Long> {
    List<RoomImage> findAllByRoomId(Long id);
}

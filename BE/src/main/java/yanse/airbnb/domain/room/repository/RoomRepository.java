package yanse.airbnb.domain.room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yanse.airbnb.domain.room.Room;

public interface RoomRepository extends JpaRepository<Room, Long>, RoomRepositoryCustom{

}

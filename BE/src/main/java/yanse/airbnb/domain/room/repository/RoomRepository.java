package yanse.airbnb.domain.room.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yanse.airbnb.domain.room.Room;
import yanse.airbnb.web.dto.ResponseSearchAddressDto;

public interface RoomRepository extends JpaRepository<Room, Long>, RoomRepositoryCustom{

}

package yanse.airbnb.domain.room.repository;

import java.util.List;
import org.springframework.data.repository.query.Param;


public interface RoomRepositoryCustom {

	List<String> findByAddress(@Param("address") String address);
}

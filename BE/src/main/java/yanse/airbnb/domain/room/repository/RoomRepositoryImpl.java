package yanse.airbnb.domain.room.repository;

import static yanse.airbnb.domain.room.QRoom.*;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;

public class RoomRepositoryImpl implements RoomRepositoryCustom {

	private final JPAQueryFactory query;

	public RoomRepositoryImpl(EntityManager em) {
		this.query = new JPAQueryFactory(em);
	}

	@Override
	public List<String> findByAddress(String address) {
		return query
			.select(getAddressConcat())
			.from(room)
			.where(getAddressContains(address))
			.fetch();
	}

	private BooleanExpression getAddressContains(String address) {
		return room.address.city.contains(address).or(
			room.address.region.contains(address).or(
				room.address.district.contains(address).or(
					room.address.detail.contains(address))));
	}

	private StringExpression getAddressConcat() {
		return room.address.city.concat(" ")
			.concat(room.address.region.concat(" ")
				.concat(room.address.district.concat(" ")
					.concat(room.address.detail)));
	}
}

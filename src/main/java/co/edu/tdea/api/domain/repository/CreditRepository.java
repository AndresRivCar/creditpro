package co.edu.tdea.api.domain.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.tdea.api.domain.models.Credit;

public interface CreditRepository extends CrudRepository<Credit, String>{
	
	/**
	 * Query credit by credit identifier.
	 * @param creditId {@Link String}
	 * @return {@Link Optional<Credit>}
	 */
	Optional<Credit> findByCreditId(String creditId);
}

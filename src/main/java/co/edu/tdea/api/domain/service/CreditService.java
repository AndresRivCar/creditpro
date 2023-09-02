package co.edu.tdea.api.domain.service;

import java.util.List;

import co.edu.tdea.api.api.dto.CreateCreditDto;
import co.edu.tdea.api.api.dto.CreditResponseDto;
import co.edu.tdea.api.domain.models.Credit;

public interface CreditService {

	/**
	 * Method to create a new credit.
	 * @param credit {@link Credit}
	 */
	void createCredit(CreateCreditDto createCreditDto) throws IllegalArgumentException;
	
	/**
	 * Return all credits.
	 * @return {@link List}
	 */
	List<CreditResponseDto> findAllActiveCredits();
}

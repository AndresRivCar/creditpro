package co.edu.tdea.api.domain.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.tdea.api.api.dto.CreateCreditDto;
import co.edu.tdea.api.api.dto.CreditResponseDto;
import co.edu.tdea.api.domain.models.Credit;
import co.edu.tdea.api.domain.repository.CreditRepository;

@Service
public class CreditServiceImpl implements CreditService {

	@Autowired
	private CreditRepository creditRepository;

	@Override
	public void createCredit(CreateCreditDto createCreditDto) throws IllegalArgumentException {

		String id = UUID.randomUUID().toString();

		if (creditRepository.findByCreditId(createCreditDto.getCreditId()).isPresent()) {
			throw new IllegalArgumentException("The credit identifier already exists.");
		};

		Credit credit = Credit.builder()
				.id(id)
				.creditId(createCreditDto.getCreditId())
				.customerId(createCreditDto.getCustomerId())
				.amount(createCreditDto.getAmount())
				.customerName(createCreditDto.getCustomerName())
				.status(createCreditDto.getStatus())
				.build();

		creditRepository.save(credit);
	}

	public List<CreditResponseDto> findAllActiveCredits() {
	    List<Credit> creditList = (List<Credit>) creditRepository.findAll();
	    
	    if (creditList.isEmpty()) {
	        throw new IllegalArgumentException("There are no records available to display.");
	    }
	    
	    return creditList.stream()
	        .filter(credit -> credit.getStatus().equals("ACTIVE"))
	        .map(credit -> CreditResponseDto.builder()
	            .creditId(credit.getCreditId())
	            .customerId(credit.getCustomerId())
	            .amount(credit.getAmount())
	            .customerName(credit.getCustomerName())
	            .status(credit.getStatus())
	            .build())
	        .toList();
	}

}

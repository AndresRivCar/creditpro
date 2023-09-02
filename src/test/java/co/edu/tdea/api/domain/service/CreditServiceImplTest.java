package co.edu.tdea.api.domain.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Description;

import co.edu.tdea.api.api.dto.CreateCreditDto;
import co.edu.tdea.api.api.dto.CreditResponseDto;
import co.edu.tdea.api.domain.models.Credit;
import co.edu.tdea.api.domain.repository.CreditRepository;

@ExtendWith(MockitoExtension.class)
class CreditServiceImplTest {

	@Mock
	private CreditRepository creditRepository;

	@InjectMocks
	private CreditServiceImpl creditServiceImpl;

	@Test
	@Description("Validate that the credit identifier is unique.")
	void createCreditTest() {
		
		when(creditRepository.findByCreditId(anyString()))
	    	.thenReturn(Optional.of(Credit.builder()
	    			.creditId("SL0003")
	    			.build()));


		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
	        creditServiceImpl.createCredit(
	            CreateCreditDto.builder().creditId("SL0001").amount(1000000.0).customerName("John Doe").build()
	        );
	    });
		
		assertTrue(exception.getMessage().contains("The credit identifier already exists."));
	}

	@Test
	@Description("Validate that the findAll function returns a list of CreditResponseDto.")
	void findAllActiveCreditsTest() {
		List<Credit> creditList = new ArrayList<>();
		creditList.add(Credit.builder().creditId("SL0001").customerId("1032111222").amount(1000000.0)
				.customerName("Juan Diaz").status("ACTIVE").build());
		creditList.add(Credit.builder().creditId("SL0002").customerId("1032111333").amount(1500000.0)
				.customerName("Erika Saenz").status("ACTIVE").build());

		when(creditRepository.findAll()).thenReturn(creditList);

		List<CreditResponseDto> result = creditServiceImpl.findAllActiveCredits();

		assertEquals(creditList.size(), result.size());

		IntStream.range(0, creditList.size()).forEach(i -> {
			Credit credit = creditList.get(i);
			CreditResponseDto responseDto = result.get(i);

			assertEquals(credit.getCreditId(), responseDto.getCreditId());
			assertEquals(credit.getAmount(), responseDto.getAmount());
			assertEquals(credit.getCustomerName(), responseDto.getCustomerName());
		});
	}

	@Test
	@Description("Validate that the findAllActiveCredits function throws an exception when no records are available.")
	void findAllActiveCreditsEmptyListTest() {
		List<Credit> emptyCreditList = new ArrayList<>();
		
		when(creditRepository.findAll()).thenReturn(emptyCreditList);

		assertThrows(IllegalArgumentException.class, () -> {
			creditServiceImpl.findAllActiveCredits();
		});
	}

}

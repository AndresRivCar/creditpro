package co.edu.tdea.api.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditResponseDto {
	
	private String creditId;
	
	private String customerId;
	
    private Double amount;
    
    private String customerName;
    
    private String status;
    
}

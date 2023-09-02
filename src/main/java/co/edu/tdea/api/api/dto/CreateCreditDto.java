package co.edu.tdea.api.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateCreditDto {
	
	@NotBlank(message = "creditId must not be blank.")
	@Size(min = 5, max = 7, message = "must be between 5 and 7 characters.")
	private String creditId;
	
	@NotBlank(message = "must not be blank.")
	private String customerId;
    
	@NotBlank(message = "must not be blank.")
    private String customerName;
	
	@NotNull
	@Min(value = 1000000, message = "must not be less than $1000000.")
    private Double amount;
	
	@NotBlank(message = "must not be blank.")
	@Pattern(regexp = "[A-Z]+", message = "can only have uppercase letters.")
	private String status;
    
}

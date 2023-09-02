package co.edu.tdea.api.api.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.tdea.api.api.dto.CreateCreditDto;
import co.edu.tdea.api.domain.service.CreditService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/credit")
public class CreditController {
	
	@Autowired
	private CreditService creditService;
	
	@PostMapping
	private ResponseEntity<?> createCredit(@Valid @RequestBody CreateCreditDto createCreditDto, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getFieldErrors()
					.stream()
					.map(error -> {
						return "The " + error.getField() + " field " + error.getDefaultMessage();
					})
					.toList();
			
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		try {
			creditService.createCredit(createCreditDto);
		} catch(IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<>("Success", HttpStatus.CREATED);
	}
	
	@GetMapping
	private ResponseEntity<?> findAllCreditsActive() {
		
		try {
			return new ResponseEntity<>(creditService.findAllActiveCredits(), HttpStatus.OK);
		} catch(IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
		}	
	}
}

package com.valise.invoice_generator.dto;

import lombok.Data;

@Data
public class OTPRequest {
    private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
}

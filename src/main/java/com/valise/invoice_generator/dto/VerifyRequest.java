package com.valise.invoice_generator.dto;

public class VerifyRequest {
	
	
	public String email;
    public String code;
    
    // getters and setters
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

}

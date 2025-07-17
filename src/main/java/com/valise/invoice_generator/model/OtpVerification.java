package com.valise.invoice_generator.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OtpVerification {
    @Id @GeneratedValue
    private Long id;
    private String email;
    private String otp;
    private LocalDateTime expiryTime;
    

    public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getOtp() {
		return otp;
	}


	public void setOtp(String otp) {
		this.otp = otp;
	}


	public LocalDateTime getExpiryTime() {
		return expiryTime;
	}


	public void setExpiryTime(LocalDateTime expiryTime) {
		this.expiryTime = expiryTime;
	}


	public OtpVerification(String email, String otp, LocalDateTime localDateTime) {
        this.email=email;
        this.otp=otp;
        expiryTime=localDateTime;
    }
}

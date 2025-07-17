
package com.valise.invoice_generator.controller;

import com.valise.invoice_generator.service.InvoiceNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/invoice")
public class InvoiceNumberController {

    @Autowired
    private InvoiceNumberService invoiceNumberService;

    @PostMapping("/generate-number")
    public ResponseEntity<Map<String, String>> generateInvoiceNumber(@RequestBody Map<String, String> request) {
        String gstNumber = request.get("gstNumber");
        if (gstNumber == null || gstNumber.isBlank()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "GST number is required"));
        }

        String invoiceNo = invoiceNumberService.generateInvoiceNumber(gstNumber);
        return ResponseEntity.ok(Collections.singletonMap("invoiceNo", invoiceNo));
    }
}

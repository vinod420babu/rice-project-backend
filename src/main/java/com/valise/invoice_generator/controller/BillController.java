package com.valise.invoice_generator.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valise.invoice_generator.model.InvoiceRequestDto;
import com.valise.invoice_generator.service.ExcelBillGeneratorService;
import com.valise.invoice_generator.service.InvoiceNumberService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/bill")
public class BillController {
	private final InvoiceNumberService invoiceNumberService;
    private final ExcelBillGeneratorService excelBillGeneratorService;

    @Autowired
    public BillController(InvoiceNumberService invoiceNumberService,ExcelBillGeneratorService excelBillGeneratorService) {
        this.invoiceNumberService = invoiceNumberService;
        this.excelBillGeneratorService = excelBillGeneratorService;
    }
    
    @Autowired
    private ExcelBillGeneratorService billGeneratorService;
//    public ResponseEntity<byte[]> generateBill(@RequestBody Map<String, Object> request) throws IOException {
//        System.out.println("Received JSON: " + request); // Debug line
//
//        byte[] excelData = billGeneratorService.generateBillFromMap(invoiceRe);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        headers.setContentDisposition(ContentDisposition.attachment().filename("Invoice.xlsx").build());
//
//        return new ResponseEntity<>(excelData, headers, HttpStatus.OK);
//    }
//    @PostMapping("/generate")
//    public ResponseEntity<byte[]> generateInvoice(@RequestBody InvoiceRequestDto invoiceRequestDto) throws IOException {
//    	System.out.println("Received InvoiceRequestDto:");
//        System.out.println(invoiceRequestDto); // Make sure to override toString() in DTO
//
//        // Optional: log individual fields for clarity
////        System.out.println("Billing Name: " + invoiceRequestDto.getBillingName());
////        System.out.println("Total Amount: " + invoiceRequestDto.getTotalAmount());
////        System.out.println("Items: " + invoiceRequestDto.getItems());
//        byte[] excelBytes = billGeneratorService.generateBillFromTemplate(invoiceRequestDto);
//        System.out.println("working or not");
//        System.out.println(invoiceRequestDto.getBillingGst()+"billing gst value");
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice.xlsx")
//                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
//                .body(excelBytes);
//    }
    @PostMapping("/generate")
    public ResponseEntity<byte[]> generateInvoice(@RequestBody InvoiceRequestDto requestDto) throws IOException {
    	String gst = requestDto.getBillingGst();
        String invoiceNo = requestDto.getInvoiceNo();

        // ✅ Use frontend invoice number only for specific GST (GST1)
        if ("35BOFPC2762F1ZA".equals(gst)) {
            // Use the invoice number from the frontend — assumed valid
            System.out.println("Using invoice number from frontend: " + invoiceNo);

            // Optional: Validate that it starts from INV-XXXXX-024 or above
            if (invoiceNo != null && !invoiceNo.matches("SAT/\\d{4}/\\d{3}")) {
                return ResponseEntity.badRequest()
                    .body(("Invalid invoice number format for GST 29ABCDE1234F2Z5").getBytes());
            }
        } else {
            // 🔄 For other GSTs, generate a new invoice number if missing
            if (invoiceNo == null || invoiceNo.isEmpty()) {
                invoiceNo = invoiceNumberService.generateInvoiceNumber(gst);
                requestDto.setInvoiceNo(invoiceNo); // Set it back into DTO
                System.out.println("Generated invoice number for GST " + gst + ": " + invoiceNo);
            }
        }
    	byte[] excelBytes = excelBillGeneratorService.generateBillFromTemplate(requestDto);
        String fileName = "invoice-" + System.currentTimeMillis() + ".xlsx";

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(excelBytes);
    }

}
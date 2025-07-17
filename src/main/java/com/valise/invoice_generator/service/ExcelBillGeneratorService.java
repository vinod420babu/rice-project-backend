

package com.valise.invoice_generator.service;//package com.valise.invoice_generator.service;

import com.valise.invoice_generator.model.BillItem;
import com.valise.invoice_generator.model.Invoice;
import com.valise.invoice_generator.model.InvoiceRequestDto;
import com.valise.invoice_generator.model.Payment;
import com.valise.invoice_generator.repositroy.InvoiceRepository;
import com.valise.invoice_generator.repositroy.PaymentRepository;
import org.apache.commons.io.IOUtils;
//import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
//import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
//import org.apache.poi.xssf.usermodel.XSSFColor;
//import org.apache.poi.xssf.usermodel.XSSFFont;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.awt.Color;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.model.ThemesTable;
import org.apache.poi.xssf.model.StylesTable;
//import org.apache.poi.xssf.model.DefaultIndexedColorMap;
@Service
public class ExcelBillGeneratorService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private PaymentService paymentservice;


    public byte[] generateBillFromTemplate(InvoiceRequestDto invoiceRequestDto) throws IOException {
        ClassPathResource templateResource = new ClassPathResource("templates/Kanniga_Parameshwari_Bill_Empty.xlsx");
        InputStream inputStream = templateResource.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        // Create a style to match the existing template's font and size
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 28); // Match existing font size
        font.setFontName("Cambria"); // Match existing font type
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        // Add borders to the style
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
// Common base font
        Font normalFont = workbook.createFont();
        normalFont.setFontHeightInPoints((short) 28);
        normalFont.setFontName("Cambria");

// Bold font
        Font boldFont = workbook.createFont();
        boldFont.setFontHeightInPoints((short) 28);
        boldFont.setFontName("Cambria");
        boldFont.setBold(true);

// Style: Normal with borders
        CellStyle normalStyle = workbook.createCellStyle();
        normalStyle.setFont(normalFont);
        normalStyle.setAlignment(HorizontalAlignment.LEFT);
        normalStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        normalStyle.setBorderTop(BorderStyle.THIN);
        normalStyle.setBorderBottom(BorderStyle.THIN);
        normalStyle.setBorderLeft(BorderStyle.THIN);
        normalStyle.setBorderRight(BorderStyle.THIN);

// Style: Centered for name cell
        CellStyle centerStyle = workbook.createCellStyle();
        centerStyle.cloneStyleFrom(normalStyle);
        centerStyle.setAlignment(HorizontalAlignment.CENTER);

// Style: Bold with borders
        CellStyle boldStyle = workbook.createCellStyle();
        boldStyle.cloneStyleFrom(normalStyle);
        boldStyle.setFont(boldFont);
        Row invoicerow=sheet.getRow(6);
        Cell invoicerowCell = invoicerow.getCell(2);
if (invoicerowCell != null) {
            invoicerowCell.setBlank();
        }
invoicerowCell.setCellValue(invoiceRequestDto.getInvoiceNo());
invoicerowCell.setCellStyle(boldStyle);


        Row daterow=sheet.getRow(7);
        Cell daterowCell = daterow.getCell(3);
        if (daterowCell != null) {
            daterowCell.setBlank();
        }
        Cell datecloumncell = daterow.createCell(2);
        datecloumncell.setCellValue(invoiceRequestDto.getInvoiceDate());
        datecloumncell.setCellStyle(boldStyle);

        Row receivernamerow=sheet.getRow(11);
        Cell receivernamerowcell = receivernamerow.getCell(2);
        if (receivernamerowcell != null) {
            receivernamerowcell.setBlank();
        }
        Cell receivernamecloumncell = receivernamerow.createCell(2);
        receivernamecloumncell.setCellValue(invoiceRequestDto.getBillingName());
        receivernamecloumncell.setCellStyle(boldStyle);

        Row adderssrow1=sheet.getRow(12);
        Cell adderssrow1cell = adderssrow1.getCell(2);
        if (adderssrow1cell != null) {
            adderssrow1cell.setBlank();
        }
        Cell addresscloumncell = adderssrow1.createCell(2);
        addresscloumncell.setCellValue(invoiceRequestDto.getBillingAddress());
        addresscloumncell.setCellStyle(normalStyle);

        Row adderssrow2=sheet.getRow(13);
        Cell adderssrow2cell = adderssrow2.getCell(2);
        if (adderssrow2cell != null) {
            adderssrow2cell.setBlank();
        }
        Cell address2cloumncell = adderssrow2.createCell(2);
        address2cloumncell.setCellValue("address to get the row");
        address2cloumncell.setCellStyle(normalStyle);

        Row phonenumberrow=sheet.getRow(14);
        Cell phonenumberrowcell = phonenumberrow.getCell(3);
        if (phonenumberrowcell != null) {
            phonenumberrowcell.setBlank();
        }
        Cell phonenumbercloumncell = phonenumberrow.createCell(2);
        phonenumbercloumncell.setCellValue(invoiceRequestDto.getBillingPhone());
        phonenumbercloumncell.setCellStyle(boldStyle);

     // Define the merged region for phone number (B5 to I5)
        CellRangeAddress phoneMergeRegion = new CellRangeAddress(4, 4, 1, 8); // Row 5 (index 4), Columns B (1) to I (8)

        // Remove existing merged region if present
        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
            CellRangeAddress existing = sheet.getMergedRegion(i);
            if (existing.formatAsString().equals(phoneMergeRegion.formatAsString())) {
                sheet.removeMergedRegion(i);
                break; // Remove only one matching region
            }
        }

        // Add the merged region
        sheet.addMergedRegion(phoneMergeRegion);

        // Create or get row 5
        Row gstRow = sheet.getRow(3);
        if (gstRow == null) {
        	gstRow = sheet.createRow(3);
        }

        // Create or override the cell in column B (index 1)
        Cell gstCell = gstRow.getCell(1);
        if (gstCell == null) {
        	gstCell = gstRow.createCell(1);
        }
        gstCell.setCellValue(invoiceRequestDto.getBillingGst());
        gstCell.setCellStyle(normalStyle);

        // Set red bold and center-aligned style
        //Workbook workbook = sheet.getWorkbook();
//        CellStyle gstStyle = workbook.createCellStyle();
//        Font redBoldFont = workbook.createFont();
//        redBoldFont.setColor(IndexedColors.RED.getIndex());
        XSSFWorkbook xssfWorkbook = (XSSFWorkbook) sheet.getWorkbook();

     // Create font with custom RGB color
     XSSFFont redBoldFont = xssfWorkbook.createFont();
     CellStyle gstStyle = workbook.createCellStyle();
        //redBoldFont.setColor(new XSSFColor(new Color(192, 0, 0), new DefaultIndexedColorMap()));
     XSSFColor redColor = new XSSFColor(new byte[]{(byte) 192, 0, 0});
     	redBoldFont.setColor(redColor);
        redBoldFont.setBold(true);
        redBoldFont.setFontHeightInPoints((short)28);
        gstStyle.setFont(redBoldFont);
        gstStyle.setAlignment(HorizontalAlignment.CENTER);
        gstStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        
        // Apply style
        gstCell.setCellStyle(gstStyle);

        Row shippingnamerow=sheet.getRow(11);
        Cell shippingnamerowcell = shippingnamerow.getCell(7);
        if (shippingnamerowcell != null) {
            shippingnamerowcell.setBlank();
        }
        Cell shippingnamecloumncell = shippingnamerow.createCell(7);
        shippingnamecloumncell.setCellValue(invoiceRequestDto.getShippingName());
        shippingnamecloumncell.setCellStyle(boldStyle);

        Row shippingadderssrow1=sheet.getRow(12);
        Cell shippingadderssrow1cell = shippingadderssrow1.getCell(7);
        if (shippingadderssrow1cell != null) {
            shippingadderssrow1cell.setBlank();
        }
        Cell shippingaddresscloumncell = adderssrow1.createCell(7);
        shippingaddresscloumncell.setCellValue(invoiceRequestDto.getShippingAddress());
        shippingaddresscloumncell.setCellStyle(normalStyle);

        Row shippingadderssrow2=sheet.getRow(13);
        Cell shippingadderssrowCell = shippingadderssrow2.getCell(7);
        if (shippingadderssrowCell != null) {
            shippingadderssrowCell.setBlank();
        }
        Cell shippingaddress2cloumncell = shippingadderssrow2.createCell(7);
        shippingaddress2cloumncell.setCellValue("Sample Data address to fetch");
        shippingaddress2cloumncell.setCellStyle(normalStyle);

        Row shippingphonenumberrow=sheet.getRow(14);
        Cell shippingphonenumberrowcell = shippingphonenumberrow.getCell(7);
        if (shippingphonenumberrowcell != null) {
            shippingphonenumberrowcell.setBlank();
        }
        Cell shippingphonenumbercloumncell = shippingphonenumberrow.createCell(7);
        shippingphonenumbercloumncell.setCellValue(invoiceRequestDto.getBillingPhone());
        shippingphonenumbercloumncell.setCellStyle(boldStyle);

        Row shippinggstrow=sheet.getRow(15);
        Cell shippinggstrowcell = shippinggstrow.getCell(7);
        if (shippinggstrowcell != null) {
            shippinggstrowcell.setBlank();
        }
        Cell shippinggstrowCreationcell = shippinggstrow.createCell(7);
        shippinggstrowCreationcell.setCellValue(invoiceRequestDto.getShippingGst());
        shippinggstrowCreationcell.setCellStyle(boldStyle);

        Row transportmoderow=sheet.getRow(6);
        Cell transportmoderowcell = transportmoderow.getCell(6);
        if (transportmoderowcell != null) {
            transportmoderowcell.setBlank();
        }
        Cell transportmodecloumncell = transportmoderow.createCell(6);
        transportmodecloumncell.setCellValue("TRANSPORT MODE:"+invoiceRequestDto.getTransportMode());
        transportmodecloumncell.setCellStyle(boldStyle);

        Row vechilenorow=sheet.getRow(7);
        Cell vechilenorowcell = vechilenorow.getCell(6);
        if (vechilenorowcell != null) {
            vechilenorowcell.setBlank();
        }
        Cell vechilenocloumncell = vechilenorow.createCell(6);
        vechilenocloumncell.setCellValue("VEHICLE NO: "+invoiceRequestDto.getVehicleNo());
        vechilenocloumncell.setCellStyle(boldStyle);

        Row referencenorow=sheet.getRow(8);
        Cell referencenorowcell = referencenorow.getCell(6);
        if (referencenorowcell != null) {
            referencenorowcell.setBlank();
        }
        Cell referencenocloumncell = referencenorow.createCell(6);
        referencenocloumncell.setCellValue("REFERENCE NO: "+invoiceRequestDto.getReferenceNo());
        referencenocloumncell.setCellStyle(boldStyle);

        double finalsubtotal=0.0;
        double finaltotal=0.0;
        long totalCount=0;

        int startRow = 18;
        int i = 0;
        List<BillItem> products = invoiceRequestDto.getItems();
        int rowsToInsert = invoiceRequestDto.getItems().size();
        sheet.shiftRows(startRow, sheet.getLastRowNum(), rowsToInsert);


        for (i = 0; i < products.size(); i++) {
            int currentRow = startRow + i;
//            Row row = sheet.getRow(startRow + i);
            Row row = sheet.createRow(startRow + i);

            BillItem item = invoiceRequestDto.getItems().get(i);
            System.out.println("Items received: " + item);


            Cell cell1 = row.createCell(1);
            cell1.setCellValue(i + 1);
            cell1.setCellStyle(style);

            Cell cell2 = row.createCell(2);
            cell2.setCellValue(item.getDescription());
            cell2.setCellStyle(style);

            String qty=item.getQuantity();
            long qtynum = Long.parseLong(qty);
            totalCount+=qtynum;
            System.out.println(qtynum);
            Cell cell3 = row.createCell(3);
            cell3.setCellValue(qtynum);
            cell3.setCellStyle(style);

            String unitkg=item.getUnit();
            long unitkgnum = Long.parseLong(unitkg);
            System.out.println(unitkgnum);
            Cell cell4 = row.createCell(4);
            cell4.setCellValue(unitkg);
            cell4.setCellStyle(style);

            String unitprice=item.getUnitPrice();
            double unitpricenum = Double.parseDouble(unitprice);
            System.out.println(unitpricenum);
            Cell cell5 = row.createCell(5);
            cell5.setCellValue(unitpricenum);
            cell5.setCellStyle(style);

            String taxrate=item.getTaxRate();
            double taxratenum = Double.parseDouble(unitprice);
            System.out.println(unitpricenum);
            
            String taxRateStr = item.getTaxRate(); // may be null or empty
            double taxRate = 0.0;
            if (taxRateStr != null && !taxRateStr.trim().isEmpty()) {
                try {
                    taxRate = Double.parseDouble(taxRateStr);
                } catch (NumberFormatException e) {
                    taxRate = 0.0;
                }
            }
            
            
            Cell cell6 = row.createCell(6);
            cell6.setCellValue(taxrate);
            cell6.setCellStyle(style);

            Double subtotal=qtynum*unitpricenum;
            finalsubtotal+=subtotal;
            System.out.println(subtotal);
            Cell cell7 = row.createCell(7);
            cell7.setCellValue(subtotal);
            cell7.setCellStyle(style);

            double total = subtotal;
            if (taxRate > 0) {
                total += (subtotal * taxRate / 100.0);
            }
            finaltotal += total;
            
//            Double total=subtotal+(subtotal*taxratenum/100);
//            finaltotal+=total;
            Cell cell8 = row.createCell(8);
            cell8.setCellValue(total);
            cell8.setCellStyle(style);
        }

        Row finalsubtotalrow=sheet.getRow(startRow+i+3);
        Cell finalsubtotalrowCell = finalsubtotalrow.getCell(7);
        if (finalsubtotalrowCell != null) {
            finalsubtotalrowCell.setBlank();
        }
        Cell  finalsubtotalcolumnCell= finalsubtotalrow.createCell(7);
        finalsubtotalcolumnCell.setCellValue(finalsubtotal);
        finalsubtotalcolumnCell.setCellStyle(normalStyle);

        Row finaltotalrow=sheet.getRow(startRow+i+3);
        Cell finaltotalrowCell = finaltotalrow.getCell(8);
        if (finaltotalrowCell != null) {
            finaltotalrowCell.setBlank();
        }
        Cell finaltotalcolumnCell = finaltotalrow.createCell(8);
        finaltotalcolumnCell.setCellValue(finaltotal);
        finaltotalcolumnCell.setCellStyle(normalStyle);

//        Row SpecialDiscountRow=sheet.getRow(startRow+i+4);
//        Double specialDiscount=0.0;
//        Cell SpecialDiscountrowCell = SpecialDiscountRow.getCell(8);
//        if (SpecialDiscountrowCell != null) {
//            SpecialDiscountrowCell.setBlank();
//        }
     // ✅ Fetch special discount from the DTO
        Double specialDiscount = Optional.ofNullable(invoiceRequestDto.getSpecialDiscount()).orElse(0.0);

        // ✅ Get or create the row
        Row specialDiscountRow = sheet.getRow(startRow + i + 4);
        if (specialDiscountRow == null) {
            specialDiscountRow = sheet.createRow(startRow + i + 4);
        }

        // ✅ Clear old cell if it exists
        Cell specialDiscountCell = specialDiscountRow.getCell(8);
        if (specialDiscountCell != null) {
            specialDiscountCell.setBlank();
        }

        Cell SpecialDiscountcolumncell = specialDiscountRow.createCell(8);
        SpecialDiscountcolumncell.setCellValue(specialDiscount);
        SpecialDiscountcolumncell.setCellStyle(normalStyle);
     // ✅ Subtract special discount from the final total
        double totalAfterDiscount = finaltotal - specialDiscount;
        if (totalAfterDiscount < 0) {
            totalAfterDiscount = 0.0; // Ensure we don't show negative total
        }
        
        
        Row Subtotallessrow=sheet.getRow(startRow+i+5);
        double subtotalless=finaltotal-specialDiscount;
        Cell SubtotallessrowCell = Subtotallessrow.getCell(8);
        if (SubtotallessrowCell != null) {
            SubtotallessrowCell.setBlank();
        }
        Cell Subtotallesscolumncell = Subtotallessrow.createCell(8);
        Subtotallesscolumncell.setCellValue(subtotalless);
        Subtotallesscolumncell.setCellStyle(normalStyle);

//        Row finaltaxraterow=sheet.getRow(startRow+i+6);
//        Double finaltaxvalue=5.0;
//        Cell finaltaxraterowCell = finaltaxraterow.getCell(8);
//        if (finaltaxraterowCell != null) {
//            finaltaxraterowCell.setBlank();
//        }
//        Cell finaltaxratecloumncell = finaltaxraterow.createCell(8);
//        finaltaxratecloumncell.setCellValue(finaltaxvalue);
//        finaltaxratecloumncell.setCellStyle(normalStyle);

     // ✅ Instead, just calculate total tax properly like this
        Row totaltaxrow = sheet.getRow(startRow + i + 6); // shifted up one row
        if (totaltaxrow == null) {
            totaltaxrow = sheet.createRow(startRow + i + 6);
        }
        Cell totaltaxrowCell = totaltaxrow.getCell(8);
        Double totaltax = finaltotal - finalsubtotal;
        if (totaltaxrowCell != null) {
            totaltaxrowCell.setBlank();
        }
        Cell totaltaxcolumncell = totaltaxrow.createCell(8);
        totaltaxcolumncell.setCellValue(totaltax);
        totaltaxcolumncell.setCellStyle(normalStyle);
        
        
//        Row totaltaxrow=sheet.getRow(startRow+i+7);
//        Cell totaltaxrowCell = totaltaxrow.getCell(8);
//        Double totaltax=finaltotal-finalsubtotal;
//        if (totaltaxrowCell != null) {
//            totaltaxrowCell.setBlank();
//        }
//        Cell totaltaxcolumncell = totaltaxrow.createCell(8);
//        totaltaxcolumncell.setCellValue(totaltax);
//        totaltaxcolumncell.setCellStyle(normalStyle);

//        Row Shippingpricerow=sheet.getRow(startRow+i+8);
//        long shippingprice=totalCount*110;
//        Cell ShippingpricerowCell = Shippingpricerow.getCell(8);
//        if (ShippingpricerowCell != null) {
//            ShippingpricerowCell.setBlank();
//        }
//        Cell Shippingpricecolumncell = Shippingpricerow.createCell(8);
//        Shippingpricecolumncell.setCellValue(shippingprice);
//        Shippingpricecolumncell.setCellStyle(normalStyle);

        
     // ✅ Fetch shipping charge from the DTO
        Double shippingCharge = Optional.ofNullable(invoiceRequestDto.getShippingCharge()).orElse(0.0);

        // ✅ Write to Excel (similar to special discount)
        Row shippingChargeRow = sheet.getRow(startRow + i + 8);
        if (shippingChargeRow == null) {
            shippingChargeRow = sheet.createRow(startRow + i + 8);
        }

        // ✅ Clear existing cell if present
        Cell shippingChargeCell = shippingChargeRow.getCell(8);
        if (shippingChargeCell != null) {
            shippingChargeCell.setBlank();
        }

        // ✅ Create new cell and set value
        Cell shippingChargeColumnCell = shippingChargeRow.createCell(8);
        shippingChargeColumnCell.setCellValue(shippingCharge);
        shippingChargeColumnCell.setCellStyle(normalStyle);

        //double totalAmount = subtotalless + shippingCharge;
//System.out.println(totalAmount +"total amount");

        Row totalAmountCell=sheet.getRow(startRow+i+9);
        Cell balancerowCell = totalAmountCell.getCell(8);
        if (balancerowCell != null) {
            balancerowCell.setBlank();
        }
        Cell balancecloumncell = totalAmountCell.createCell(8);
        double totalAmount=subtotalless+shippingCharge;
        balancecloumncell.setCellValue(totalAmount);
        balancecloumncell.setCellStyle(boldStyle);

//Row pendingBalance=sheet.getRow(startRow+i+9);
//Cell pendingBalanceRow = pendingBalance.getCell(7);
//if (pendingBalanceRow != null) {
//	pendingBalanceRow.setBlank();
//}
//Cell pendingBalanceColumnCell = pendingBalance.createCell(7);
//pendingBalanceColumnCell.setCellValue(invoiceRequestDto.getPendingAmount());
//pendingBalanceColumnCell.setCellStyle(boldStyle);
     
        // Load the image
        InputStream is = new ClassPathResource("images/signature_image.png").getInputStream();
        byte[] bytes = IOUtils.toByteArray(is);
        int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
        is.close();

        // Create drawing
        CreationHelper helper = workbook.getCreationHelper();
        Drawing<?> drawing = sheet.createDrawingPatriarch();

        // Define anchor — top-left column & row, bottom-right column & row
        // Example: From col 6 row 35 to col 7 row 38 (3 rows tall)
        ClientAnchor anchor = helper.createClientAnchor();
        anchor.setCol1(7); // starting column
        anchor.setRow1(startRow+i+11); // starting row
        anchor.setCol2(9); // ending column
        anchor.setRow2(startRow+i+13); // ending row

        // Attach the picture to the anchor
        Picture pict = drawing.createPicture(anchor, pictureIdx);

// Optional: resize automatically (you can tweak manually if needed)
        pict.resize(); // Or omit this if you want to stick to cell size



        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber(invoiceRequestDto.getInvoiceNo());
        invoice.setReceiverName(invoiceRequestDto.getBillingName());
        invoice.setAddressLine1(invoiceRequestDto.getBillingAddress());
        invoice.setAddressLine2("invoiceRequestDto.getAdderssrow2()");
        invoice.setPhone(invoiceRequestDto.getBillingPhone());
        invoice.setBillingGst(invoiceRequestDto.getBillingGst());
        invoice.setShippingName(invoiceRequestDto.getShippingName());
        invoice.setShippingAddress1(invoiceRequestDto.getShippingAddress());
        invoice.setShippingAddress2("invoiceRequestDto.getShippingadderssrow2()");
        invoice.setShippingPhone(invoiceRequestDto.getShippingPhone());
        invoice.setShippingGst(invoiceRequestDto.getShippingGst());
        invoice.setTransportMode(invoiceRequestDto.getTransportMode());
        invoice.setVehicleNo(invoiceRequestDto.getVehicleNo());
        invoice.setReferenceNo(invoiceRequestDto.getReferenceNo());
        invoice.setInvoiceDate(invoiceRequestDto.getInvoiceDate());
        invoice.setPendingAmount(invoiceRequestDto.getPendingAmount());
// Map BillItems and associate with Invoice
        List<BillItem> items = new ArrayList<>();
        for (BillItem itemDto : invoiceRequestDto.getItems()) {
            BillItem item = new BillItem();
            item.setDescription(itemDto.getDescription());
            item.setQuantity(itemDto.getQuantity());
            item.setUnitPrice(itemDto.getUnitPrice());
            item.setTaxRate(itemDto.getTaxRate());
            item.setUnit(itemDto.getUnit());
            item.setInvoice(invoice);
            items.add(item);
        }

        invoice.setItems(items);

        invoiceRepository.save(invoice);

//        Payment payment = new Payment(invoiceRequestDto.getInvoiceNo(), totalAmount);
//       paymentRepository.save(payment);
        paymentservice.saveInitialPayment(invoiceRequestDto.getInvoiceNo(), invoiceRequestDto.getPendingAmount(),totalAmount);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
        inputStream.close();
        return outputStream.toByteArray();
    }


    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public void deleteInvoiceById(Long id) {
        invoiceRepository.deleteById(id);
    }

    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }
}

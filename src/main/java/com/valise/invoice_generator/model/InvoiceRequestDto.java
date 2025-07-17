//package com.valise.invoice_generator.model;
//
//import lombok.Data;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Data
//public class InvoiceRequestDto {
//    private String receivernamecloumncell;
//    private String adderssrow1;
//    private String adderssrow2;
//    private String phonenumberrow;
//    private String gstrow;
//
//    private String shippingnamecloumncell;
//    private String shippingadderssrow1;
//    private String shippingadderssrow2;
//    private String shippingphonenumberrow;
//    private String shippinggstrow;
//
//    private List<BillItem> products = new ArrayList<>();  // Initialize to empty list
//
//    private String transportmoderow;
//    private String vechilenorow;
//    private String referencenorow;
//
//    private Double specialDiscount;
//    private Double finaltaxvalue;
//    
//	@Override
//	public String toString() {
//		return "InvoiceRequestDto [receivernamecloumncell=" + receivernamecloumncell + ", adderssrow1=" + adderssrow1
//				+ ", adderssrow2=" + adderssrow2 + ", phonenumberrow=" + phonenumberrow + ", gstrow=" + gstrow
//				+ ", shippingnamecloumncell=" + shippingnamecloumncell + ", shippingadderssrow1=" + shippingadderssrow1
//				+ ", shippingadderssrow2=" + shippingadderssrow2 + ", shippingphonenumberrow=" + shippingphonenumberrow
//				+ ", shippinggstrow=" + shippinggstrow + ", products=" + products + ", transportmoderow="
//				+ transportmoderow + ", vechilenorow=" + vechilenorow + ", referencenorow=" + referencenorow
//				+ ", specialDiscount=" + specialDiscount + ", finaltaxvalue=" + finaltaxvalue + "]";
//	}
//
//	public String getReceivernamecloumncell() {
//		return receivernamecloumncell;
//	}
//
//	public void setReceivernamecloumncell(String receivernamecloumncell) {
//		this.receivernamecloumncell = receivernamecloumncell;
//	}
//
//	public String getAdderssrow1() {
//		return adderssrow1;
//	}
//
//	public void setAdderssrow1(String adderssrow1) {
//		this.adderssrow1 = adderssrow1;
//	}
//
//	public String getAdderssrow2() {
//		return adderssrow2;
//	}
//
//	public void setAdderssrow2(String adderssrow2) {
//		this.adderssrow2 = adderssrow2;
//	}
//
//	public String getPhonenumberrow() {
//		return phonenumberrow;
//	}
//
//	public void setPhonenumberrow(String phonenumberrow) {
//		this.phonenumberrow = phonenumberrow;
//	}
//
//	public String getGstrow() {
//		return gstrow;
//	}
//
//	public void setGstrow(String gstrow) {
//		this.gstrow = gstrow;
//	}
//
//	public String getShippingnamecloumncell() {
//		return shippingnamecloumncell;
//	}
//
//	public void setShippingnamecloumncell(String shippingnamecloumncell) {
//		this.shippingnamecloumncell = shippingnamecloumncell;
//	}
//
//	public String getShippingadderssrow1() {
//		return shippingadderssrow1;
//	}
//
//	public void setShippingadderssrow1(String shippingadderssrow1) {
//		this.shippingadderssrow1 = shippingadderssrow1;
//	}
//
//	public String getShippingadderssrow2() {
//		return shippingadderssrow2;
//	}
//
//	public void setShippingadderssrow2(String shippingadderssrow2) {
//		this.shippingadderssrow2 = shippingadderssrow2;
//	}
//
//	public String getShippingphonenumberrow() {
//		return shippingphonenumberrow;
//	}
//
//	public void setShippingphonenumberrow(String shippingphonenumberrow) {
//		this.shippingphonenumberrow = shippingphonenumberrow;
//	}
//
//	public String getShippinggstrow() {
//		return shippinggstrow;
//	}
//
//	public void setShippinggstrow(String shippinggstrow) {
//		this.shippinggstrow = shippinggstrow;
//	}
//
//	public List<BillItem> getProducts() {
//		return products;
//	}
//
//	public void setProducts(List<BillItem> products) {
//		this.products = products;
//	}
//
//	public String getTransportmoderow() {
//		return transportmoderow;
//	}
//
//	public void setTransportmoderow(String transportmoderow) {
//		this.transportmoderow = transportmoderow;
//	}
//
//	public String getVechilenorow() {
//		return vechilenorow;
//	}
//
//	public void setVechilenorow(String vechilenorow) {
//		this.vechilenorow = vechilenorow;
//	}
//
//	public String getReferencenorow() {
//		return referencenorow;
//	}
//
//	public void setReferencenorow(String referencenorow) {
//		this.referencenorow = referencenorow;
//	}
//
//	public Double getSpecialDiscount() {
//		return specialDiscount;
//	}
//
//	public void setSpecialDiscount(Double specialDiscount) {
//		this.specialDiscount = specialDiscount;
//	}
//
//	public void setFinaltaxvalue(Double finaltaxvalue) {
//		this.finaltaxvalue = finaltaxvalue;
//	}
//
//	public Double getFinaltaxvalue() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//   
//}


package com.valise.invoice_generator.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class InvoiceRequestDto {

    private String invoiceNo;
    private String invoiceDate;

    private String billingName;
    private String billingAddress;
//    private String billingAddress2;
    private String billingPhone;
    private String billingGst;

    private String shippingName;
    private String shippingAddress;
    private String shippingPhone;
    private String shippingGst;
//    private String ;

    private List<BillItem> Items;

    private String transportMode;
    private String vehicleNo;
    private String referenceNo;
    
    private Double subtotal;
    private Double specialDiscount;
    private Double finaltaxvalue;
    private Double shippingCharge;
    private Double totalAmount;
    private Double pendingAmount;
    
	public Double getPendingAmount() {
		return pendingAmount;
	}
	public void setPendingAmount(Double pendingAmount) {
		this.pendingAmount = pendingAmount;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public Double getSpecialDiscount() {
		return specialDiscount;
	}
	public void setSpecialDiscount(Double specialDiscount) {
		this.specialDiscount = specialDiscount;
	}
	public Double getFinaltaxvalue() {
		return finaltaxvalue;
	}
	public void setFinaltaxvalue(Double finaltaxvalue) {
		this.finaltaxvalue = finaltaxvalue;
	}
	public Double getShippingCharge() {
		return shippingCharge;
	}
	public void setShippingCharge(Double shippingCharge) {
		this.shippingCharge = shippingCharge;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getBillingName() {
		return billingName;
	}
	public void setBillingName(String billingName) {
		this.billingName = billingName;
	}
	public String getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	public String getBillingPhone() {
		return billingPhone;
	}
	public void setBillingPhone(String billingPhone) {
		this.billingPhone = billingPhone;
	}
	public String getBillingGst() {
		return billingGst;
	}
	public void setBillingGst(String billingGst) {
		this.billingGst = billingGst;
	}
	public String getShippingName() {
		return shippingName;
	}
	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public String getShippingPhone() {
		return shippingPhone;
	}
	public void setShippingPhone(String shippingPhone) {
		this.shippingPhone = shippingPhone;
	}
	public String getShippingGst() {
		return shippingGst;
	}
	public void setShippingGst(String shippingGst) {
		this.shippingGst = shippingGst;
	}
	public List<BillItem> getItems() {
		return Items;
	}
	public void setItems(List<BillItem> Items) {
		this.Items = Items;
	}
	public String getTransportMode() {
		return transportMode;
	}
	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	@Override
	public String toString() {
		return "InvoiceRequestDto [invoiceNo=" + invoiceNo + ", invoiceDate=" + invoiceDate + ", billingName="
				+ billingName + ", billingAddress=" + billingAddress + ", billingPhone=" + billingPhone
				+ ", billingGst=" + billingGst + ", shippingName=" + shippingName + ", shippingAddress="
				+ shippingAddress + ", shippingPhone=" + shippingPhone + ", shippingGst=" + shippingGst + ", products="
				+ Items + ", transportMode=" + transportMode + ", vehicleNo=" + vehicleNo + ", referenceNo="
				+ referenceNo + "]";
	}

//    private Double specialDiscount;
//    private Double finaltaxvalue;
}

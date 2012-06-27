package com.credera.java.data.csv;

public class RawProduct {

	public static final String[] columns = new String[]{"ProductId","Name","Description","Price","ProductNumber","VendorName","VendorPartNumber","ExternalId","MSRP","WebName","WebDesc","WebCond","WebType","WebMPN","ManufacturerId"};
	
	private String productId;
	private String name;
	private String description;
	private String price;
	private String productNumber;
	private String vendorName;
	private String vendorPartNumber;
	private String externalId;
	private String msrp;
	private String webName;
	private String webDesc;
	private String webCond;
	private String webType;
	private String webMPN;
	private String manufacturerId;
	private String brent;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorPartNumber() {
		return vendorPartNumber;
	}

	public void setVendorPartNumber(String vendorPartNumber) {
		this.vendorPartNumber = vendorPartNumber;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getMsrp() {
		return msrp;
	}

	public void setMsrp(String msrp) {
		this.msrp = msrp;
	}

	public String getWebName() {
		return webName;
	}

	public void setWebName(String webName) {
		this.webName = webName;
	}

	public String getWebDesc() {
		return webDesc;
	}

	public void setWebDesc(String webDesc) {
		this.webDesc = webDesc;
	}

	public String getWebCond() {
		return webCond;
	}

	public void setWebCond(String webCond) {
		this.webCond = webCond;
	}

	public String getWebType() {
		return webType;
	}

	public void setWebType(String webType) {
		this.webType = webType;
	}

	public String getWebMPN() {
		return webMPN;
	}

	public void setWebMPN(String webMPN) {
		this.webMPN = webMPN;
	}

	public String getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(String manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public String getBrent() {
		return brent;
	}

	public void setBrent(String brent) {
		this.brent = brent;
	}
	
	public String cleanString(String dirty) {
		return dirty.replaceAll("NULL", "").replaceAll("<", "").replaceAll(">", "").replaceAll("&", "and");
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RawProduct [productId=");
		builder.append(productId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", price=");
		builder.append(price);
		builder.append(", productNumber=");
		builder.append(productNumber);
		builder.append(", vendorName=");
		builder.append(vendorName);
		builder.append(", vendorPartNumber=");
		builder.append(vendorPartNumber);
		builder.append(", externalId=");
		builder.append(externalId);
		builder.append(", msrp=");
		builder.append(msrp);
		builder.append(", webName=");
		builder.append(webName);
		builder.append(", webDesc=");
		builder.append(webDesc);
		builder.append(", webCond=");
		builder.append(webCond);
		builder.append(", webMPN=");
		builder.append(webMPN);
		builder.append(", manufacturerId=");
		builder.append(manufacturerId);
		builder.append("]");
		return builder.toString();
	}
	
	public String toXml() {
		StringBuilder builder = new StringBuilder();
		builder.append("<doc><field name='id'>");
		builder.append(cleanString(productId));
		builder.append("</field>");
//		builder.append("<field name='name'>");
//		builder.append(name);
//		builder.append("</field>");
//		builder.append("<field name='description'>");
//		builder.append(description);
//		builder.append("</field>");
		builder.append("<field name='price'>");
		builder.append(cleanString(price));
		builder.append("</field>");
//		builder.append("<field name='productNumber'>");
//		builder.append(productNumber);
//		builder.append("</field>");
		builder.append("<field name='manu'>");
		builder.append(cleanString(vendorName));
		builder.append("</field>");
//		builder.append("<field name='vendorPartNumber'>");
//		builder.append(vendorPartNumber);
//		builder.append("</field>");
//		builder.append("<field name='externalId'>");
//		builder.append(externalId);
//		builder.append("</field>");
//		builder.append("<field name='msrp'>");
//		builder.append(msrp);
//		builder.append("</field>");
		builder.append("<field name='name'>");
		builder.append(cleanString(webName));
		builder.append("</field>");
		builder.append("<field name='description'>");
		builder.append(cleanString(webDesc));
		builder.append("</field>");
		builder.append("<field name='webCond_s'>");
		builder.append(cleanString(webCond));
		builder.append("</field>");
		builder.append("<field name='sku'>");
		builder.append(cleanString(webMPN));
		builder.append("</field>");
//		builder.append("<field name='manufacturerId'>");
//		builder.append(manufacturerId);
//		builder.append("</field>");
		builder.append("</doc>");
	
		return builder.toString();
	}

}

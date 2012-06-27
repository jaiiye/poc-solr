package com.credera.java.domain.search;

public class FacetNumericRange implements FacetRange{
	
	private String field;
	private double lowValue;
	private double highValue;
	private String label;
	
	
	
	public FacetNumericRange(String field, double lowValue, double highValue,
			String label) {
		super();
		this.field = field;
		this.lowValue = lowValue;
		this.highValue = highValue;
		this.label = label;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}
	/**
	 * @param field the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}
	
	/**
	 * @return the lowValue
	 */
	public double getLowValue() {
		return lowValue;
	}
	/**
	 * @param lowValue the lowValue to set
	 */
	public void setLowValue(double lowValue) {
		this.lowValue = lowValue;
	}
	
	/**
	 * @return the highValue
	 */
	public double getHighValue() {
		return highValue;
	}
	/**
	 * @param highValue the highValue to set
	 */
	public void setHighValue(double highValue) {
		this.highValue = highValue;
	}
	
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FacetRange [field=");
		builder.append(field);
		builder.append(", lowValue=");
		builder.append(lowValue);
		builder.append(", highValue=");
		builder.append(highValue);
		builder.append(", label=");
		builder.append(label);
		builder.append("]");
		return builder.toString();
	}
	
	/** 
	 * create query string for facet.query
	 */
	public String toQuery() {
		StringBuilder builder = new StringBuilder();
		builder.append(field);
		builder.append(":[");

		if (lowValue == Double.MIN_VALUE) {
			builder.append("0");
		} else {
			builder.append(lowValue);
		}

		builder.append(" TO ");

		if (highValue == Double.MAX_VALUE) {
			builder.append("*");
		} else {
			builder.append(highValue);
		}
		
		builder.append("]");
		return builder.toString();
	}

}

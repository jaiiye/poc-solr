package com.credera.java.domain.search;

public class QueryField {
	
	private String field;
	private double boost;
	
	public QueryField(String field, double boost) {
		super();
		this.field = field;
		this.boost = boost;
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
	 * @return the boost
	 */
	public double getBoost() {
		return boost;
	}
	/**
	 * @param boost the boost to set
	 */
	public void setBoost(double boost) {
		this.boost = boost;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QueryField [field=");
		builder.append(field);
		builder.append(", boost=");
		builder.append(boost);
		builder.append("]");
		return builder.toString();
	}
	
	/** 
	 * create query string for qf
	 */
	public String toQuery() {
		StringBuilder builder = new StringBuilder();
		builder.append(field);

		if (boost > 0.0) {
			builder.append("^");
			builder.append(boost);
		}

		return builder.toString();
	}

}

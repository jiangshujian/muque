package org.muque.mold.codes.dto;

public class SourceTable {
	private String name;
	private String description;

	private String jName;
	private String jUName;

	String pk = "unknown";
	String pkJType = "unknown_type";
	String pkJName = "unknown";

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

	public String getJName() {
		return jName;
	}

	public void setJName(String jName) {
		this.jName = jName;
	}

	public String getJUName() {
		return jUName;
	}

	public void setJUName(String jUName) {
		this.jUName = jUName;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getPkJType() {
		return pkJType;
	}

	public void setPkJType(String pkJType) {
		this.pkJType = pkJType;
	}

	public String getPkJName() {
		return pkJName;
	}

	public void setPkJName(String pkJName) {
		this.pkJName = pkJName;
	}

}

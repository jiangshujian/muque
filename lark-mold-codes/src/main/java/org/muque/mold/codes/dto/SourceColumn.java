package org.muque.mold.codes.dto;

public class SourceColumn {
	private String name;
	private boolean isNullable;
	private String dataType;
	private String description;
	private boolean isPrimaryKey;

	private String jName;
	private String jUName;
	private String jdbcType;
	private String jType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isNullable() {
		return isNullable;
	}

	public void setNullable(boolean isNullable) {
		this.isNullable = isNullable;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}

	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
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

	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

	public String getJType() {
		return jType;
	}

	public void setJType(String jType) {
		this.jType = jType;
	}

}

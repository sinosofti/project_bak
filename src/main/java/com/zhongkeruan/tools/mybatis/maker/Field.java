package com.zhongkeruan.tools.mybatis.maker;

/**
 * @description
 * @author sunry
 * @create 2011-7-13 ����02:21:01
 */
public class Field {

	private String fieldName;

	private String fieldType;

	private String propertyName;

	private String description;

	private boolean isKey;

	private final int precision;

	private int scale;

	private boolean mandatory;

	public Field(String fieldName, String fieldType, String propertyName, boolean isKey, int precision, int scale) {
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.propertyName = propertyName;
		this.isKey = isKey;
		this.precision = precision;
		this.scale = scale;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public void setKey(boolean isKey) {
		this.isKey = isKey;
	}

	public boolean isKey() {
		return isKey;
	}

	public String getJavaType() {
		if (fieldType.toUpperCase().startsWith("VARCHAR")) {
			return "String";
		} else if (fieldType.toUpperCase().startsWith("CHAR")) {
			return "String";
		} else if (fieldType.toUpperCase().startsWith("CLOB")) {
			return "String";
		} else if (fieldType.toUpperCase().startsWith("INT")) {
			return "Integer";
		} else if (fieldType.toUpperCase().startsWith("DATE")) {
			return "Date";
		} else if (fieldType.toUpperCase().startsWith("NUMBER")) {
			if (scale > 0) {
				return "BigDecimal";
			}
			if (precision == 1) {
				return "boolean";
			}
			if (precision > 9) {
				return "long";
			}
			return "int";
		} else if (fieldType.toUpperCase().startsWith("TIMESTAMP")) {
			return "Date";
		} else if (fieldType.toUpperCase().startsWith("LONG")) {
			return "String";
		} else if (fieldType.toUpperCase().startsWith("BIGINT")) {
			return "Long";
		} else if (fieldType.toUpperCase().startsWith("BLOB")) {
			return "Blob";
		} else if (fieldType.toUpperCase().startsWith("DECIMAL")) {
			return "BigDecimal";
		} else {
			throw new RuntimeException("please implenents jdbc type: " + fieldType + "'s java type mapping!");
		}
	}

	public static String getJdbcType(String javaType) {
		if (javaType.equals("VARCHAR2")) {
			return "VARCHAR2";
		}
		if (javaType.equals("LONG")) {
			return "LONGVARCHAR";
		}
		if (javaType.equals("INT")) {
			return "NUMERIC";
		}
		if (javaType.equals("DATE")) {
			return "TIMESTAMP";
		}
		if (javaType.equals("DATETIME")) {
			return "TIMESTAMP";
		}
		if (javaType.equals("TIMESTAMP")) {
			return "TIMESTAMP";
		}
		if (javaType.equals("CHAR")) {
			return "CHAR";
		}
		if (javaType.equals("CLOB")) {
			return "CLOB";
		}
		if (javaType.equals("BIGINT")) {
			return "LONG";
		}
		if (javaType.equals("DECIMAL")) {
			return "NUMERIC";
		}
		if (javaType.equals("BLOB")) {
			return "Blob";
		}

		throw new RuntimeException("please implenents java type: " + javaType + "'s java type mapping!");
	}

	@Override
	public String toString() {
		return fieldName + "(Key:" + isKey + ")" + "_" + fieldType + "_" + propertyName;
	}

	public String getDescription() {
		if (mandatory) {
			return description + " (Not Null)";
		}
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}
}

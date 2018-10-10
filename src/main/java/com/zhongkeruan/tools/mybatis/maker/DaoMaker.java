package com.zhongkeruan.tools.mybatis.maker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * @description
 * @author sunry
 * @create 2011-7-13 ����09:28:13
 */
public class DaoMaker {

	private ParserInfo parserInfo;

	private static String DIST = "dist";

	public DaoMaker(ParserInfo parserInfo) {
		this.parserInfo = parserInfo;
	}

	public void makeDao(String parentPackageName, String tableName) throws Exception {
		writeContentToFile(createFile(parserInfo.getDaoPackage(), parserInfo.getDaoName(), ".xml"), generateDaoContent());
	}

	public static void writeContentToFile(File file, String content) throws Exception {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(content.getBytes("UTF-8"));
			fos.flush();
		} catch (Exception e) {
			throw e;
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// TODO:
	public static File createFile(String packagee, String fileName, String postFix) {
		String currrntPath = new File("").getAbsolutePath();
		String pkgPath = packagee.replace(".", File.separator);
		String filePath = currrntPath + File.separator + DIST + File.separator + pkgPath + File.separator + fileName + postFix;
		File file = new File(filePath);
		file.getParentFile().mkdirs();
		return file;
	}

	private String generateDaoContent() throws IOException {
		String templateFileContent = getTemplateFileContent("DaoTemplate");
		String daoContent = templateFileContent.replaceAll("#<daoPackage>", parserInfo.getDaoPackage());
		daoContent = daoContent.replaceAll("#<daoName>", parserInfo.getDaoName());
		daoContent = daoContent.replaceAll("#<modelPackage>", parserInfo.getModelPackage());
		daoContent = daoContent.replaceAll("#<modelName>", parserInfo.getModelName());
		daoContent = daoContent.replaceAll("#<resultMapContent>", getResultMapContent());
		daoContent = daoContent.replaceAll("#<fields>", getFields());
		daoContent = daoContent.replaceAll("#<tableName>", parserInfo.getTableName());
		daoContent = daoContent.replaceAll("#<sequenceName>", parserInfo.getSequenceName());
		Field keyField = getKeyField();
		if (keyField != null) {
			daoContent = daoContent.replaceAll("#<keyField>", keyField.getFieldName());
			daoContent = daoContent.replaceAll("#<key>", keyField.getPropertyName());
		}
		daoContent = daoContent.replaceAll("#<insertContent>", getInsertContent());
		daoContent = daoContent.replaceAll("#<updateContent>", getUpdateContent());
		return daoContent.trim();
	}

	public static String getTemplateFileContent(String fileName) throws IOException {
		URL template = DaoMaker.class.getResource(fileName);
		InputStream is = template.openStream();
		StringBuffer buffer = new StringBuffer();
		byte[] b = new byte[1 << 12];
		while (is.read(b) != -1) {
			// TODO ��β�׷�Ӻ���Ŀո�
			buffer.append(new String(b));
		}
		is.close();

		return buffer.toString().trim();
	}

	// #<resultMapContent>
	private String getResultMapContent() {
		List<Field> fields = parserInfo.getFields();
		StringBuffer buffer = new StringBuffer();
		Field field = fields.get(0);
		String resultMapRowContent = getResultMapRowContent(field);
		buffer.append(resultMapRowContent);

		for (int i = 1; i < fields.size(); i++) {
			field = fields.get(i);
			String result = getResultMapRowContent(field);
			buffer.append("\n" + result);
		}
		return buffer.toString();
	}

	private String getResultMapRowContent(Field field) {
		String template = "\t\t<result column=\"#<keyField>\" property=\"#<key>\" />";
		String result = template.replaceAll("#<keyField>", field.getFieldName());
		result = result.replaceAll("#<key>", field.getPropertyName());
		return result;
	}

	// #<updateContent>
	private String getUpdateContent() {
		List<Field> fields = parserInfo.getFields();
		StringBuffer buffer = new StringBuffer();
		Field field = fields.get(0);
		String updateRowContent = getUpdateRowContent(field);
		buffer.append(updateRowContent);

		for (int i = 1; i < fields.size(); i++) {
			field = fields.get(i);
			updateRowContent = getUpdateRowContent(field);
			buffer.append(",\n" + updateRowContent);
		}
		return buffer.toString();
	}

	private String getUpdateRowContent(Field field) {
		String template = "\t\t#<fieldName> = #{#<property>, javaType=#<javaType>, jdbcType=#<jdbcType>}";
		String result = template.replaceAll("#<fieldName>", field.getFieldName());
		result = result.replaceAll("#<property>", field.getPropertyName());
		result = result.replaceAll("#<javaType>", field.getJavaType());
		result = result.replaceAll("#<jdbcType>", Field.getJdbcType(field.getFieldType()));
		return result;
	}

	// #<insertContent>
	private String getInsertContent() {
		List<Field> fields = parserInfo.getFields();
		StringBuffer buffer = new StringBuffer();
		Field field = fields.get(0);
		String insertRowContent = getInsertRowContent(field);
		buffer.append(insertRowContent);

		for (int i = 1; i < fields.size(); i++) {
			field = fields.get(i);
			insertRowContent = getInsertRowContent(field);
			buffer.append(",\n" + insertRowContent);
		}
		return buffer.toString();
	}

	private String getInsertRowContent(Field field) {
		String template = "\t\t#{#<property>, javaType=#<javaType>, jdbcType=#<jdbcType>}";
		String result = template.replaceAll("#<property>", field.getPropertyName());
		result = result.replaceAll("#<jdbcType>", Field.getJdbcType(field.getFieldType()));
		result = result.replaceAll("#<javaType>", field.getJavaType());
		return result;
	}

	// #<fields>
	private String getFields() {
		List<Field> fields = parserInfo.getFields();
		StringBuffer buffer = new StringBuffer();
		buffer.append(fields.get(0).getFieldName());
		for (int i = 1; i < fields.size(); i++) {
			Field field = fields.get(i);
			buffer.append(", " + field.getFieldName());
		}
		return buffer.toString();
	}

	// #<keyField>,#<key>
	private Field getKeyField() {
		List<Field> fields = parserInfo.getFields();
		for (Field field : fields) {
			if (field.isKey()) {
				return field;
			}
		}
		System.out.println("û������" + parserInfo.getTableName());
		return null;
	}
}

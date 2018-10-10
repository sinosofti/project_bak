package com.zhongkeruan.tools.mybatis.maker;

import java.io.IOException;

/**
 * @description
 * @author sunry
 * @create 2011-7-13 ����02:24:56
 */
public class DaoInterfaceMaker {

	private ParserInfo parserInfo;

	public DaoInterfaceMaker(ParserInfo parserInfo) {
		this.parserInfo = parserInfo;
	}

	public void makeInterface() throws Exception, IOException {
		DaoMaker.writeContentToFile(DaoMaker.createFile(parserInfo.getDaoPackage(), parserInfo.getDaoName(), ".java"), generateDaoInterfaceContent());
	}

	private String generateDaoInterfaceContent() throws IOException {
		String templateFileContent = DaoMaker.getTemplateFileContent("JavaTemplate");
		String daoInterfaceContent = templateFileContent.replaceAll("#<modelPackage>", parserInfo.getModelPackage());
		daoInterfaceContent = daoInterfaceContent.replaceAll("#<tableComment>", parserInfo.getTableComment());
		daoInterfaceContent = daoInterfaceContent.replaceAll("#<tableName>", parserInfo.getTableName());
		daoInterfaceContent = daoInterfaceContent.replaceAll("#<daoPackage>", parserInfo.getDaoPackage());
		daoInterfaceContent = daoInterfaceContent.replaceAll("#<daoName>", parserInfo.getDaoName());
		daoInterfaceContent = daoInterfaceContent.replaceAll("#<modelName>", parserInfo.getModelName());
		return daoInterfaceContent.trim();
	}

}

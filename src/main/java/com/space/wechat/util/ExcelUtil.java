package com.space.wechat.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFName;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelUtil {

	private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

	public static Object getExcelValue(HSSFCell cell) {
		if (cell == null) {
			return null;
		}
		Object obj = null;
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC: // 数字
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				obj = cell.getDateCellValue();
				try {
					obj = DateUtil.dateToNormalString(cell.getDateCellValue());

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}

			} else {
				obj = cell.getNumericCellValue();
				obj = StringUtil.formartDecimal(obj);
			}

			break;
		case HSSFCell.CELL_TYPE_STRING: // 字符串
			obj = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
			obj = cell.getBooleanCellValue();
			break;
		case HSSFCell.CELL_TYPE_FORMULA: // 公式
			obj = cell.getCellFormula();
			break;
		case HSSFCell.CELL_TYPE_BLANK: // 空值

			break;

		case HSSFCell.CELL_TYPE_ERROR: // 故障

			break;
		default:
			obj = null;
			break;
		}
		return obj;
	}

	/**
	 * 创建名称
	 * 
	 * @param wb
	 * @param name
	 * @param expression
	 * @return
	 */
	public static HSSFName createName(HSSFWorkbook wb, String name,
			String expression) {
		HSSFName refer = wb.createName();
		refer.setRefersToFormula(expression);
		refer.setNameName(name);
		return refer;
	}

	/**
	 * 设置数据有效性（通过名称管理器级联相关）
	 * 
	 * @param name
	 * @param firstRow
	 * @param endRow
	 * @param firstCol
	 * @param endCol
	 * @return
	 */
	public static HSSFDataValidation setDataValidation(String name,
			int firstRow, int endRow, int firstCol, int endCol) {
		// 设置下拉列表的内容
		logger.info("起始行:" + firstRow + "___起始列:" + firstCol + "___终止行:"
				+ endRow + "___终止列:" + endCol);
		// 加载下拉列表内容
		DVConstraint constraint = DVConstraint
				.createFormulaListConstraint(name);
		// 设置数据有效性加载在哪个单元格上。
		// 四个参数分别是：起始行、终止行、起始列、终止列
		CellRangeAddressList regions = new CellRangeAddressList(
				(short) firstRow, (short) endRow, (short) firstCol,
				(short) endCol);
		// 数据有效性对象
		HSSFDataValidation data_validation = new HSSFDataValidation(regions,
				constraint);
		return data_validation;
	}

	/**
	 * 获取需要导入的字段，该字段设置在excel表格第二行
	 * 
	 * @param row
	 * @return
	 */
	public static List<String> getRowCellColumnName(HSSFRow row) {
		int lastCellNum = row.getLastCellNum();
		List<String> columnNameList = new ArrayList<String>();
		for (int i = 0; i <= lastCellNum; i++) {
			if (row.getCell(i, HSSFRow.RETURN_BLANK_AS_NULL) == null) {
				continue;
			}

			columnNameList.add(StringUtil.getNullStr(getExcelValue(row
					.getCell(i))));
		}
		return columnNameList;
	}

	public static List<String> getRowTitle(HSSFRow row) {
		int lastCellNum = row.getLastCellNum();
		List<String> rowTitleList = new ArrayList<String>();
		for (int i = 0; i <= lastCellNum; i++) {
			if (row.getCell(i, HSSFRow.RETURN_BLANK_AS_NULL) == null) {
				continue;
			}

			rowTitleList.add(StringUtil.getNullStr(getExcelValue(row
					.getCell(i))));
		}

		return rowTitleList;
	}

	public static String getJsonSerialnumber(String serialnumber) {
		StringBuilder columns = new StringBuilder();
		columns.append("\"");
		columns.append("serialnumber");
		columns.append("\"");
		columns.append(":");
		columns.append("\"[");
		columns.append(serialnumber);
		columns.append("]\"");
		return columns.toString();
	}

	public static String getJsonGriddata(String griddata) {
		StringBuilder columns = new StringBuilder();
		columns.append("\"");
		columns.append("griddata");
		columns.append("\"");
		columns.append(":");
		columns.append("\"[");
		columns.append(griddata);
		columns.append("]\"");
		return columns.toString();
	}

	public static String getJsonErrorState(boolean errState) {
		StringBuilder columns = new StringBuilder();
		columns.append("\"");
		columns.append("state");
		columns.append("\"");
		columns.append(":");
		columns.append("\"[");
		columns.append(errState);
		columns.append("]\"");
		return columns.toString();
	}

	public static String getImportRetunGrid(String jsonSerialnumber,
			String JsonGriddata, String errstate) {
		StringBuilder gridStr = new StringBuilder("{");
		gridStr.append(jsonSerialnumber);
		gridStr.append(",");
		gridStr.append(JsonGriddata);
		gridStr.append(",");
		gridStr.append(errstate);
		gridStr.append("}");
		return gridStr.toString();
	}

}

package mrmathami.struts2.actions;

import com.opensymphony.xwork2.ActionSupport;
import mrmathami.struts2.model.bean.User;
import mrmathami.struts2.model.dao.Users;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest httpServletRequest;
	private InputStream inputStream;

	private static void copyCell(Cell newCell, Cell oldCell, boolean needCopyValue) {
		// Copy from old cell to new cell
		newCell.setCellStyle(oldCell.getCellStyle());
		newCell.setCellComment(oldCell.getCellComment());
		newCell.setHyperlink(oldCell.getHyperlink());
		//newCell.setCellType(oldCell.getCellType());

		if (needCopyValue) {
			// Set the cell data value
			switch (oldCell.getCellType()) {
				case NUMERIC:
					newCell.setCellValue(oldCell.getNumericCellValue());
					break;
				case STRING:
					newCell.setCellValue(oldCell.getRichStringCellValue());
					break;
				case FORMULA:
					newCell.setCellFormula(oldCell.getCellFormula());
					break;
				case BLANK:
					newCell.setCellType(CellType.BLANK);
					break;
				case BOOLEAN:
					newCell.setCellValue(oldCell.getBooleanCellValue());
					break;
				case ERROR:
					newCell.setCellErrorValue(oldCell.getErrorCellValue());
					break;
				default:
					throw new IllegalStateException();
			}
		}
	}

	private static void insertRowBelow(Row sourceRow, int numOfCopy, int skipColumnStart, int numOfSkipColumn) {
		final Sheet sheet = sourceRow.getSheet();

		final int sourceRowIndex = sourceRow.getRowNum();
		final int newRowIndexStart = sourceRowIndex + 1;

		if (newRowIndexStart <= sheet.getLastRowNum()) {
			sheet.shiftRows(newRowIndexStart, sheet.getLastRowNum(), numOfCopy);
			// TODO: create an issue for (or even fix) this merged cell bug (or unexpected behaviour?)
			for (final CellRangeAddress cellRange : sheet.getMergedRegions()) {
				if (cellRange.getFirstRow() <= sourceRowIndex && cellRange.getLastRow() > sourceRowIndex) {
					cellRange.setLastRow(cellRange.getLastRow() + numOfCopy);
				}
			}
		}

		// Loop through source columns to add to new row
		for (int newRowIndex = newRowIndexStart; newRowIndex < newRowIndexStart + numOfCopy; newRowIndex++) {
			Row newRow = sheet.getRow(newRowIndex);
			if (newRow == null) newRow = sheet.createRow(newRowIndex);

			// If there are any merged regions in the source row, copy to new row
			for (CellRangeAddress cellRange : sheet.getMergedRegions()) {
				if (cellRange.getFirstRow() == sourceRowIndex && cellRange.getLastRow() == sourceRowIndex) {
					try {
						sheet.addMergedRegion(new CellRangeAddress(
								newRow.getRowNum(),
								newRow.getRowNum(),
								cellRange.getFirstColumn(),
								cellRange.getLastColumn()
						));
					} catch (Exception ignored) {
					}
				}
			}

			for (final Cell oldCell : sourceRow) {
				Cell newCell = newRow.getCell(oldCell.getColumnIndex());
				if (newCell == null) newCell = newRow.createCell(oldCell.getColumnIndex());
				// Copy from old cell to new cell
				copyCell(newCell, oldCell,
						oldCell.getColumnIndex() < skipColumnStart
								|| skipColumnStart + numOfSkipColumn <= oldCell.getColumnIndex());
			}
		}
	}

	private static void createWorkbookFromTemplate(
			InputStream inputStream,
			OutputStream outputStream,
			String sheetName,
			List<Object[]> sheetData
	) {
		// the salt going in here
		try (Workbook workbook = WorkbookFactory.create(inputStream)) {
			int firstRow, firstColumn, numOfColumn;
			{
				// read info
				final Sheet infoSheet = workbook.getSheet("info");
				final Row infoRow = infoSheet.getRow(0);
				firstRow = (int) infoRow.getCell(0).getNumericCellValue();
				firstColumn = (int) infoRow.getCell(1).getNumericCellValue();
				numOfColumn = (int) infoRow.getCell(2).getNumericCellValue();
				workbook.removeSheetAt(workbook.getSheetIndex(infoSheet));
			}

			Sheet sheet = workbook.getSheet("template");

			// set sheet name
			workbook.setSheetName(workbook.getSheetIndex(sheet), sheetName);

			// insert cells
			if (sheetData.size() > 2) {
				insertRowBelow(sheet.getRow(firstRow), sheetData.size() - 2, firstColumn, numOfColumn);
			}

			// filling data
			int rowIndex = firstRow;
			for (Object[] rowData : sheetData) {
				Row row = sheet.getRow(rowIndex);
				rowIndex += 1;
				int columnIndex = firstColumn;
				for (Object obj : rowData) {
					Cell cell = row.getCell(columnIndex);
					columnIndex += 1;
					if (obj instanceof Date)
						cell.setCellValue((Date) obj);
					else if (obj instanceof Boolean)
						cell.setCellValue((Boolean) obj);
					else if (obj instanceof String)
						cell.setCellValue((String) obj);
					else if (obj instanceof Number)
						cell.setCellValue(((Number) obj).doubleValue());
				}
			}
			// force re-calc formula
			workbook.setForceFormulaRecalculation(true);
			workbook.write(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public String doListExcel() {
		final List<User> users = Users.getUsers();
		final List<Object[]> data = new ArrayList<>(users.size());
		for (User user : users) data.add(user.values());

		// the salt going in here
		File inputFile = new File(httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/users.xls"));
		
		try (InputStream fileInputStream = new FileInputStream(inputFile);
		     ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

			createWorkbookFromTemplate(fileInputStream, byteArrayOutputStream, "Users", data);
			inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}
}

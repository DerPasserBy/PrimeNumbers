package cz.vojta.primenumbers.core;

import cz.vojta.primenumbers.iface.FileParser;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of FileParser for excel files.
 */
public class ExcelFileParser implements FileParser {

    private final Workbook workbook;

    public ExcelFileParser(File file) throws IOException {
        FileInputStream fileStream = new FileInputStream(file);
        this.workbook = new XSSFWorkbook(fileStream);
    }

    /**
     * Implementation of findAllWholeNumbers() for excel files.
     */
    @Override
    public List<Integer> findAllWholeNumbers() {
        List<Integer> data = new ArrayList<>();
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.NUMERIC) {
                        addWholeNumberToList(data, cell.getNumericCellValue());
                    } else if (cell.getCellType() == CellType.STRING) {
                        Double value = parseDouble(cell.getStringCellValue());

                        addWholeNumberToList(data, value);
                    }
                }
            }
        }

        return data;
    }

    private void addWholeNumberToList(List<Integer> data, Double value) {
        boolean isWholeNumber = checkIfWholeNumber(value);
        if (isWholeNumber) {
            data.add((int) value.doubleValue());
        }
    }

    private boolean checkIfWholeNumber(Double value) {
        return value != null && (value % 1) == 0;
    }

    private Double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch(NumberFormatException e) {
            return null;
        }
    }
}

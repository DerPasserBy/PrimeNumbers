package cz.vojta.primenumbers.core;

import cz.vojta.primenumbers.exception.NoSuchFileExists;
import cz.vojta.primenumbers.exception.UnsupportedFileType;
import cz.vojta.primenumbers.iface.FileParser;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for finding and working with prime numbers in files. This implementation is limited by the min and max size
 * of an integer
 */
public class PrimeNumbersInFileSearch {

    private FileParser fileParser;

    public PrimeNumbersInFileSearch() {
    }

    public PrimeNumbersInFileSearch(File file) throws IOException, UnsupportedFileType, NoSuchFileExists {
        if (file == null) {
            throw new IllegalArgumentException("File cannot be null");
        }

        if (file.exists() && !file.isDirectory()) {
            String fileExtension = getFileExtension(file.getName());
            this.fileParser = getFileParser(file, fileExtension);
        } else {
            throw new NoSuchFileExists();
        }
    }

    /**
     *  Prints prime numbers from the file
     */
    public void printPrimes() {
        List<Integer> primeNumbers = getPrimes();
        for (Integer primeNumber : primeNumbers) {
            System.out.println(primeNumber);
        }
    }

    /**
     * Returns prime numbers that were found in the file
     *
     * @return prime numbers found in the file
     */
    public List<Integer> getPrimes() {
        List<Integer> values = fileParser.findAllWholeNumbers();
        return values.stream().filter(new PrimeNumberPredicate()).collect(Collectors.toList());
    }

    private static String getFileExtension(String fileName) {
        return FilenameUtils.getExtension(fileName);
    }

    private static FileParser getFileParser(File file, String fileExtensionString) throws IOException, UnsupportedFileType {
        FileExtension fileExtension = FileExtension.resolveFileExtension(fileExtensionString);
        switch (fileExtension) {
            case XLS:
            case XLSX:
                return new ExcelFileParser(file, fileExtension);
            default:
                throw new UnsupportedFileType();
        }
    }
}

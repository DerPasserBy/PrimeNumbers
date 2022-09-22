package cz.vojta.primenumbers;

import cz.vojta.primenumbers.core.PrimeNumbersInFileSearch;
import cz.vojta.primenumbers.exception.NoSuchFileExists;
import cz.vojta.primenumbers.exception.UnsupportedFileType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class MainClass {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("File path is missing");
        } else if (args.length > 1) {
            System.err.println("Only one file path is accepted");
        } else {
            try {
                new PrimeNumbersInFileSearch(new File(args[0])).printPrimes();
            } catch (IOException fileTypeException) {
                System.err.println("Unable to read the file");
                logger.error(fileTypeException);
            } catch (UnsupportedFileType unsupportedFileType) {
                System.err.println("Unsupported file type (or missing file extension)");
            } catch (NoSuchFileExists noSuchFileExists) {
                System.err.println("No such file exists");
            }
        }
    }
}

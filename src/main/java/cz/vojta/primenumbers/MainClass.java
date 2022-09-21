package cz.vojta.primenumbers;

import cz.vojta.primenumbers.core.PrimeNumbersInFileSearch;
import cz.vojta.primenumbers.exception.NoSuchFileExists;
import cz.vojta.primenumbers.exception.UnsupportedFileType;

import java.io.File;
import java.io.IOException;

public class MainClass {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("File path is missing");
        } else if (args.length > 1) {
            System.out.println("Only one file path is accepted");
        } else {
            try {
                new PrimeNumbersInFileSearch(new File(args[0])).printPrimes();
            } catch (IOException fileTypeException) {
                System.out.println("Unable to work with the file due to an IO exception: " + fileTypeException);
            } catch (UnsupportedFileType unsupportedFileType) {
                System.out.println("Unsupported file type (or missing file extension)");
            } catch (NoSuchFileExists noSuchFileExists) {
                System.out.println("No such file exists");
            }
        }
    }
}

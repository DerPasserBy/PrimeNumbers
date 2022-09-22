package cz.vojta.primenumbers.core;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PrimeNumbersInFileSearchTest {

    @Mock
    private ExcelFileParser excelFileParser;

    @InjectMocks
    private PrimeNumbersInFileSearch primeNumbersInFileSearch = new PrimeNumbersInFileSearch();

    @Test
    public void getPrimes() {
        when(excelFileParser.findAllWholeNumbers()).thenReturn(List.of(1, 456, 7, 9, 5645641, 5645657, 15619, 5221652, 9584, 211));

        Assert.assertArrayEquals(primeNumbersInFileSearch.getPrimes().toArray(), List.of(7, 5645657, 15619, 211).toArray());
    }
}

package cz.vojta.primenumbers.iface;

import java.util.List;

/**
 * Parses the provided file and provides capability for working with its content.
 */
public interface FileParser {

    /**
     *  Finds all integers in the parsed file. The order of found values is kept.
     *
     * @return whole numbers from the parsed file
     */
    List<Integer> findAllWholeNumbers();
}

package cz.vojta.primenumbers.core;

import org.apache.commons.math3.primes.Primes;

import java.util.function.Predicate;

/**
 * Predicate for searching for and filtering prime numbers in a list of values
 */
public class PrimeNumberPredicate implements Predicate<Integer> {

    @Override
    public boolean test(Integer value) {
        return value != null && Primes.isPrime(value);
    }
}

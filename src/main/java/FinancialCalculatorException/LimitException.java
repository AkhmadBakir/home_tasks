package FinancialCalculatorException;

public class LimitException extends RuntimeException {

    final int attempts;

    public LimitException(int attempts, String message) {
        super(message);
        this.attempts = attempts;
    }

}

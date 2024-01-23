package Day24.Day24Workshop.service;

import org.springframework.stereotype.Service;

@Service
public class AutoIncrementer {
    private int lastUsedNumber;

    public AutoIncrementer() {
        // Initialize the last used number from your database or file
        // If not available, you can start from 1 by default
        lastUsedNumber = 1;
    }

    public int getNextNumber() {
        // Increment the last used number and return it
        lastUsedNumber++;
        return lastUsedNumber;
    }
}

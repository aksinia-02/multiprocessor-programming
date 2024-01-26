package shared_objects_and_synchronization;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private AtomicInteger value = new AtomicInteger(1);
    private AtomicInteger primeNumbers = new AtomicInteger(0);

    public Counter(){
    }

    private void increaseValue(){
        value.set(value.get() + 1);
    }

    public int getAndIncreaseValue(){
        int result = value.get();
        increaseValue();
        return result;
    }

    public int getCountOfPrimeNumbers(){
        return primeNumbers.get();
    }

    public void increaseCountOfPrimeNumbers(){
        primeNumbers.set(1 + primeNumbers.get());
    }
}

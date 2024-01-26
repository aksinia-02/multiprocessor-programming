package shared_objects_and_synchronization;

public class PrimeRun implements Runnable{
    private final Counter counter;
    private final long upperLimit;
    private final int id;

    public PrimeRun(long upperLimit, int id, Counter counter){
        this.upperLimit = upperLimit;
        this.id = id;
        this.counter = counter;
    }

    @Override
    public void run() {
        long i = counter.getAndIncreaseValue();
        while(i < upperLimit){
            if(isPrime(i)){
                System.out.println("(" + id + ") " + i);
            }
            i = counter.getAndIncreaseValue();
        }
    }

    private boolean isPrime(long number){
        double sqrtNumber = Math.sqrt(number);
        boolean prime = true;
        int i = 2;
        while(prime && i <= sqrtNumber){
            if(number % i == 0)
                prime = false;
            i++;
        }
        if(prime)
            counter.increaseCountOfPrimeNumbers();
        return prime;
    }
}

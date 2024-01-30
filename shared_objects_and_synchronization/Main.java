package shared_objects_and_synchronization;

public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread[] threads = new Thread[10];

        for(int i = 0; i < 10; i++){
            long block = (long) Math.pow(10, 8);
            PrimeRun p = new PrimeRun(block, i + 1, counter);
            threads[i] = new Thread(p);
            threads[i].start();
        }

        for(Thread thread: threads){
            try{
                thread.join();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println(counter.getCountOfPrimeNumbers() + " prime numbers");
    }
}

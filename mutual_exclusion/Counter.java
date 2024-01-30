package mutual_exclusion;

public class Counter {
    private long value;
    private Lock lock;

    public long getAndIncrement(int id){
        lock.lock(id);
        try{
            long temp = value;
            value = temp + 1;
            return temp;
        } finally {
            lock.unlock(id);
        }
    }
}

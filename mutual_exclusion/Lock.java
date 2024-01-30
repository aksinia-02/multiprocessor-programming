package mutual_exclusion;

public interface Lock {
    void lock(int id);
    void unlock(int id);
}

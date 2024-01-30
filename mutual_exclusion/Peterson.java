package mutual_exclusion;

//the Peterson lock algorithm is starvation free
public class Peterson implements Lock{
    private volatile boolean[] flag = new boolean[2];
    private volatile int victim;

    @Override
    public void lock(int id) {
        int i = id;
        int j = 1 - id;
        flag[i] = true;
        victim = i;
        while(flag[j] && victim == i){};
    }

    @Override
    public void unlock(int id) {
        flag[id] = false;
    }
}

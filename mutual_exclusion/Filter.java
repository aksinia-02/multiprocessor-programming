package mutual_exclusion;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

//The Filter Lock algorithm satisfies mutual exclusion
//is starvation free
//doesn't fair (no first come, first served)
public class Filter implements Lock{

    private AtomicInteger[] level;
    private AtomicInteger[] victim;
    int length;

    public Filter(int n){
        level = new AtomicInteger[n];
        victim = new AtomicInteger[n];
        length = n;
        Arrays.fill(level, 0);
    }

    @Override
    public void lock(int id) {
        for(int i = 1; i < length; i++){
            level[id].set(i);
            victim[i].set(id);
            while(ifConflictsExist(i, id)){};
        }
    }

    @Override
    public void unlock(int id) {
        level[id].set(0);
    }

    private boolean ifConflictsExist(int i, int id){
        for(int j = 0; j < length; j++){
            if(j != id && level[j].get() >= i && victim[i].get() == id)
                return true;
        }
        return false;
    };

}

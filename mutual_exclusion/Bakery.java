package mutual_exclusion;

import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

//deadlock free, starvation free, satisfies mutual exclusion
public class Bakery implements Lock{

    private final AtomicBoolean[] flag;
    private final AtomicInteger[] label;
    private final int n;

    public Bakery(int n){
        this.n = n;
        flag = new AtomicBoolean[n];
        label = new AtomicInteger[n];
        for(int i = 0; i < n; i++){
            flag[i] = new AtomicBoolean();
            label[i] = new AtomicInteger();
        }
    }

    @Override
    public void lock(int id) {
        flag[id].set(true);
        label[id].set(findMaxElement() + 1);
        for(int k = 0; k < n; k++){
            //waits until no thread with an earlier number is trying to enter the critical section
            while(ifConflictsExist(k, id)){};
        }
    }

    @Override
    public void unlock(int id) {
        flag[id].set(false);
    }

    private int findMaxElement(){
        int max = Integer.MIN_VALUE;
        for(AtomicInteger element: label){
            if(element.get() > max){
                max = element.get();
            }
        }
        return max;
    }

    private boolean ifConflictsExist(int k, int id){
        return (k != id && flag[k].get() && ((label[k].get() < label[id].get()) || ((label[k].get() == label[id].get()) && k < id)));
    }
}

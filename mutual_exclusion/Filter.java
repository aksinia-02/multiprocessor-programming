package mutual_exclusion;

import java.util.Arrays;

//The Filter Lock algorithm satisfies mutual exclusion
//is starvation free
public class Filter implements Lock{

    int[] level;
    int[] victim;
    int length;

    public Filter(int n){
        level = new int[n];
        victim = new int[n];
        length = n;
        Arrays.fill(level, 0);
    }

    @Override
    public void lock(int id) {
        for(int i = 1; i < length; i++){
            level[id] = i;
            victim[i] = id;
            while(ifConflictsExist(i, id)){};
        }
    }

    @Override
    public void unlock(int id) {
        level[id] = 0;
    }

    private boolean ifConflictsExist(int i, int id){
        for(int j = 0; j < length; j++){
            if(j != id && level[j] >= i && victim[i] == id)
                return true;
        }
        return false;
    };

}

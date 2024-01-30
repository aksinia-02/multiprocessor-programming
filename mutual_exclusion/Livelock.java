package mutual_exclusion;

public class Livelock implements Lock{
    private boolean[] flag = new boolean[2];

    @Override
    public void lock(int id){
        int i = id;
        int j = id - 1;
        flag[i] = true;
        while(flag[j]){
            flag[i] = false;
            while(flag[j]){};
            flag[i] = true;
        }
    }

    @Override
    public void unlock(int id){
        flag[id] = false;
    }
}

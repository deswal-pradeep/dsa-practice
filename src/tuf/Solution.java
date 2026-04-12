package tuf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    static void main() {
        System.out.println(new Solution().canWin("++-+++++++"));
    }
    public boolean canWin(String currentState) {
        // Your code goes here
        Map<String, Boolean> mem = new HashMap<>();
        StringBuilder builder = new StringBuilder("");
        builder.reverse().toString();
        return canWin(currentState.toCharArray(), 1, mem);
    }

    //this returns if 1 can win
    boolean canWin(char[] state, int playerTurn, Map<String, Boolean> mem){
        List<Integer> list = getPlayableIndexes(state);
        if(list.size() == 0){
            return playerTurn == 1 ? false : true;
        }
        String key = new String(state)+"_"+playerTurn;
        Boolean memValue = mem.get(key);
        if(memValue != null)
            return memValue;

        boolean winOne = false;
        boolean winAll = true;
        for(int idx : list){
            state[idx] = '-';
            state[idx+1] = '-';
            if(playerTurn == 1){
                //current player is 1
                winOne = winOne | canWin(state, 2, mem);
            } else {
                //current player is 2
                //player 1 should win all of the states, then it's a gaurantee
                winAll = winAll & canWin(state, 1, mem);
            }
            state[idx] = '+';
            state[idx+1] = '+';
        }
        boolean result = playerTurn == 1 ? winOne : winAll;
        mem.put(key, result);
        return result;
        //if player is playing, there should be one move such that
    }

    List<Integer> getPlayableIndexes(char[] state){
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i < state.length; i++){
            if(state[i] == '+' && state[i-1] == '+'){
                list.add(i-1);
            }
        }
        return list;
    }
}

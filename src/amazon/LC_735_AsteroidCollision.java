package amazon;

import java.util.Stack;

public class LC_735_AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for(int a : asteroids){
            if(a > 0){
                stack.push(a);
                continue;
            }
            while(!stack.isEmpty()
                    && stack.peek() > 0
                    && stack.peek() < Math.abs(a)){
                stack.pop();
            }
            if(stack.isEmpty() || stack.peek() < 0){
                stack.push(a);
                continue;
            }
            int top = stack.pop();
            if(top < Math.abs(a)){
                stack.push(a);
            } else if (top > Math.abs(a)){
                stack.push(top);
            } else {
                //popped both
            }
        }
        int[] arr = new int[stack.size()];
        int i = arr.length-1;
        while(!stack.isEmpty()){
            arr[i--] = stack.pop();
        }
        return arr;
    }
}

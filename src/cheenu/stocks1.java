package cheenu;

public class stocks1 {

    static void main() {


            int[] prices = {7,1,5,3,6,4};
            int low = 1000000,lowPos=0;
            for(int i = 0; i < prices.length -1 ; i++){
                if(prices[i] < low){
                    low = prices[i];
                    lowPos = i;
                }
            }
            int maxPro = 0;
            for(int i = lowPos; i < prices.length; i++){
                if(maxPro < prices[i] - prices[lowPos])maxPro = prices[i] - prices[lowPos];
            }

        System.out.println(maxPro);
        }
}

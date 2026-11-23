package leetcode.daily.y2026.m11;

import java.util.ArrayList;
import java.util.List;

public class LC_23_1352_ProductOfTheLastKNumbers {
    class ProductOfNumbers {
        List<Integer> prefix;
        List<Integer> count;
        int product = 1;
        int zCount = 0;
        public ProductOfNumbers() {
            prefix  = new ArrayList<>();
            count = new ArrayList<>();
            count.add(zCount);
            prefix.add(product);
        }

        public void add(int num) {
            product = product * num;
            product = product == 0 ? 1 : product;
            zCount = zCount + (num == 0 ? 1 : 0);
            count.add(zCount);
            prefix.add(product);
        }

        public int getProduct(int k) {
            int startIndex = prefix.size() - k - 1;
            startIndex = startIndex >= 0 ? startIndex : 0;
            int endIndex = prefix.size() - 1;
            int z = count.get(endIndex) - count.get(startIndex);
            if(z > 0)
                return 0;
            return prefix.get(endIndex) / prefix.get(startIndex);
        }
    }
}

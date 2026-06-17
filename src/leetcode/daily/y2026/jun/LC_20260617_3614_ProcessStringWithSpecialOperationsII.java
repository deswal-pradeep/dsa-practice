package leetcode.daily.y2026.jun;

public class LC_20260617_3614_ProcessStringWithSpecialOperationsII {
    public char processStr(String s, long k) {
        long len = 0;
        char[] arr = s.toCharArray();
        for(char c : arr){
            if(c == '#'){
                len *= 2;
            } else if (c == '%'){
                len = len;
            } else if (c == '*'){
                if(len > 0)
                    len -= 1;
            } else {
                len+=1;;
            }
        }
        if(k >= len){
            return '.';
        }

        for(int j = arr.length-1; j >= 0;j--){
            char c  = arr[j];
            if(c == '#'){
                len /= 2;
                k = k >= len ? k - len : k;
            } else if (c == '%'){
                len = len;
                k = len - k - 1;
            } else if (c == '*'){
                len = len + 1;
            } else {
                len = len - 1;
                if(len == k)
                    return c;
            }
        }
        return '.';
    }

    static void main() {
        /*char ans = new LC_20260617_3614_ProcessStringWithSpecialOperationsII()
                .processStr("%edx#n#lkc####uom##qg#%#b#ek%##%%ocr#m%#fv%i%%#n#u%%#n#q%v#rwvd##t###%#%%%o*##r#gr*gz#dm%ez", 4780);*/
        char ans = new LC_20260617_3614_ProcessStringWithSpecialOperationsII()
                .processStr("cd%#*#", 3);
        System.out.println(ans);

    }
}

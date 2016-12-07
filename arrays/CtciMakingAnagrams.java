package arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by taalyko2 on 26/03/17.
 */
public class CtciMakingAnagrams {

    public static int numberNeeded(String first, String second) {
        Map<String,Integer> firstCharCount = new HashMap();
        Map<String,Integer> secondCharCount = new HashMap();
        updateMap(firstCharCount,first);
        updateMap(secondCharCount,second);


        return (updateFirstToSecond(firstCharCount,secondCharCount) + updateFirstToSecond(secondCharCount,firstCharCount));
    }

    public static int updateFirstToSecond(Map<String,Integer> firstCharCount,Map<String,Integer> secondCharCount) {
        int deleteNum=0;
        for(String key : firstCharCount.keySet()) {
            if(secondCharCount.containsKey(key)) {
                if(firstCharCount.get(key) <= secondCharCount.get(key)) {
                    continue;
                }
                if(firstCharCount.get(key) > secondCharCount.get(key)) {
                    deleteNum = deleteNum + firstCharCount.get(key) - secondCharCount.get(key);
                    continue;
                }
            }
            deleteNum = deleteNum + firstCharCount.get(key);
        }
        return deleteNum;
    }

    public static void updateMap(Map<String,Integer> map, String str) {
        for(int i=0;i<str.length();i++) {
            if(map.containsKey(String.valueOf(str.charAt(i)))) {
                map.put(String.valueOf(str.charAt(i)),map.get(str.charAt(i)) + 1);
            }else {
                map.put(String.valueOf(str.charAt(i)),1);
            }
        }
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        System.out.println(numberNeeded(a, b));
    }
}

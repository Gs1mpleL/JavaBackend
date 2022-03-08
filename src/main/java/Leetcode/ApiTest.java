package Leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试API方法
 * @author wanfeng
 * @created 2022/3/8 8:20
 * @package Leetcode
 */
public class ApiTest {
    public static char firstUniqChar(String s) {
        Map<Character,Integer> m = new HashMap<>();
        for(char i : s.toCharArray()){
            m.put(i,m.getOrDefault(i,0) + 1);
        }
        System.out.println(m.keySet());
        System.out.println(m.values());
        for(char c : s.toCharArray()){
            if(m.get(c) ==  1){
                return c;
            }
        }
        return ' ';
    }

    public static void main(String[] args) {
        System.out.println(firstUniqChar("iiiddili"));
    }
}

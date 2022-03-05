package Leetcode;

import sun.nio.cs.ext.ISCII91;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author wanfeng
 * @created 2022/3/5 12:36
 * @package Leetcode
 */
public class Test {
    public static boolean isChild(String s,String src){
        int srcP = 0;
        int sP = 0;
        while(srcP<src.length()){
            if(s.charAt(sP) == src.charAt(srcP)){
                if(sP == s.length() - 1){
                    return true;
                }
                sP++;
            }
            srcP++;
        }
        return false;
    }

    public static void LinkListDome(){
        LinkedList<Integer>list = new LinkedList<>();
        list.addFirst(100);
        list.addFirst(101);
        list.addFirst(102);
        list.addFirst(103);
        System.out.println(list.getLast());
        System.out.println(list);


        ArrayList<Integer> a = new ArrayList<>();
        a.add(11);
        a.add(12);
        a.add(13);
        a.add(14);
        System.out.println(a.toArray()[0]);
    }

    public static void main(String[] args) {
        System.out.println(isChild("aac","abaacbc"));
        LinkListDome();
    }
}

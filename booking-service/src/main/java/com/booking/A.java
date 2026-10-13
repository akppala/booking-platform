package com.booking;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class A {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> temp=new HashMap<Character,Integer>();
        int max=0;
        int l=0;
        int r=0;
        int len=0;
        while(r<s.length()){
            if(temp.containsKey(s.charAt(r))){
                System.out.println(s.charAt(r) + " " + temp.get(s.charAt(r)));
                if(temp.get(s.charAt(r)) >= l){
                    l=temp.get(s.charAt(r))+1;
                }
            }else{
               len=r-l+1;
               temp.put(s.charAt(r),r); 
               max=Math.max(len,max);
            }
            r++;
        }
        return max;
    }
    record Employee(String name,BigDecimal salary){}
    public Boolean isPalindrome(String s) {
        int l=0;
        int r=s.length()-1;
        while(l<r){
            if(s.charAt(l)!=s.charAt(r)){
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
    public boolean isPalindrome(int n) {
        int rev=0;
        int rem=n;
        while(rem > 0){
            rev=rev*10+rem%10;
            rem= rem/10;
        }
        System.out.println(rev);
        if(rev == n){
            return true;
        }
        return false;
    }
    public int recursivePalindrome(int n, int rev){
        if(n>10){
            rev=rev*10+n;
            return rev;
        }
        rev=rev*10+n%10;
        return recursivePalindrome(n/10,rev);
    }
        
      public static void main(String[] args) {
        A a=new A();
        //System.out.println(a.lengthOfLongestSubstring("abcabcbb"));
        /*Employee emp = new Employee("John", BigDecimal.valueOf(50000));
        List<Employee> employees = new ArrayList<>();
        employees.add(emp);
        employees.add(new Employee("Jane", BigDecimal.valueOf(60000)));
        employees.add(new Employee("Bob", BigDecimal.valueOf(55000)));
        employees.stream()
                .filter(e -> e.salary().compareTo(BigDecimal.valueOf(40000)) > 0)
                .forEach(e -> System.out.println(e.name()));*/

        //System.out.println(a.isPalindrome("aba"));
        //System.out.println(a.isPalindrome(121));
        System.out.println(a.recursivePalindrome(121, 0));
    }
}

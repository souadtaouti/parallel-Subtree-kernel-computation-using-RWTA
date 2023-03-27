import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class Extract_Prefixes {
 public static String prefix(String k) {
       String m = k + ";";
       int i = 2;
       while(i < k.length() - 1) {
         if(k.charAt(i) != '(' && k.charAt(i) != ')') {
           if(k.charAt(i + 1) == ')' || k.charAt(i + 1) == ',') {
             m = m + k.charAt(i) + ";";
           }
           i++;  
         }
         else {
           int j = i + 1;
           if(k.charAt(i) == '(') {
             boolean h = false;
             while(!h && i < k.length()) {
               if(i < k.length()) {
                 if(k.charAt(i) == '(') {
                   h = true;
                 }
                 else {
                   i++;
                 }
               }
               if(i > k.length()){
                 h = true;
               }
             }
             int o = 1;
             int c = 0;
             while(o != c) {
               if(k.charAt(j) == '(') {
                 o++;
               }
               if(k.charAt(j) == ')') {
                 c++;
                 if(o == c) {
                   o = 0;
                   c = 0;
                 }
               }
               j++;
             }
             m = m + prefix(k.substring(i - 1, j));
           }
           i+=j;
         }
       }
      return m;
 }
 public static void main(String[] args) throws Exception {
   try { 
    BufferedReader reader = new BufferedReader(new FileReader("trees.txt"));
    FileWriter writer = new FileWriter("prefixes.txt");
    String r;
    while ((r = reader.readLine()) != null) {
      writer.write(prefix(r) + '\n');
   }
   writer.close();
  } catch (IOException e) {
      e.printStackTrace();
  } 
 }
}


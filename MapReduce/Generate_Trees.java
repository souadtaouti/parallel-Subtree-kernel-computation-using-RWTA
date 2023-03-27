import java.io.IOException;
import java.io.FileWriter;
import java.util.Random;
import java.util.*;

public class Generate_Trees {

 public static int t = 1;
 public static int random (int min, int max) {
   Random random = new Random();
   return random.nextInt(max - min) + min;
 }
 public static String alpha(int a) {
   int h = random(-1, 1);
   if (a == 1) {
     if (h < 0) {
       return "a";
     }
     else {
       return "b";
     }
   }
   if (a == 2) {
     if (h < 0) {
       return "h";
     }
     else {
       return "g";
     }
   }
   if (a == 3) {
     if (h < 0) {
       return "m";
     }
     else {
       return "n";
     }
   }
   if (a == 4) {
     if (h < 0) {
       return "p";
     }
     else {
       return "q";
     }
   }
   if (a == 5) {
     if (h < 0) {
       return "s";
     }
     else {
       return "t";
     }
   }
   return "";
 }
 
 public static void main(String[] args) throws Exception {
    for (int i = 0; i < 100; i++) {
      String s = "f(";
      int a = random(1, 6);
      for (int j = 0; j < a; j++) {
        int b = random(1, 6);
        s = s + alpha(b) + ",";
      }
      s = s.substring(0, s.length() - 1) + ")";
      t = 2;
      while (s.contains("h)") || s.contains("h,") || s.contains("g)") || s.contains("g,") || s.contains("m)") || s.contains("m,") || s.contains("n)") || s.contains("n,") || s.contains("p)") || s.contains("p,") || s.contains("q)") || s.contains("q,") || s.contains("s)") || s.contains("s,") || s.contains("t)") || s.contains("t,")) { 
         if (t < 10) {
           s = s.replaceAll("h,", "h(" + alpha(random(2,6)) + "),");
           s = s.replaceAll("g,", "g(" + alpha(random(2,6)) + "),");
           s = s.replaceAll("m,", "m(" + alpha(random(2,6)) + "," + alpha(random(2, 6)) + "),");
           s = s.replaceAll("n,", "n(" + alpha(random(2,6)) + "," + alpha(random(2, 6)) + "),");
           s = s.replaceAll("p,", "p(" + alpha(random(2,6)) + "," + alpha(random(2, 6)) + "," + alpha(random(2, 6)) + "),");
           s = s.replaceAll("q,", "q(" + alpha(random(2,6)) + "," + alpha(random(2, 6)) + "," + alpha(random(2, 6)) + "),");
           s = s.replaceAll("s,", "s(" + alpha(random(2,6)) + "," + alpha(random(2, 6)) + "," + alpha(random(2, 6)) + "," + alpha(random(2, 6)) + "),");
           s = s.replaceAll("t,", "t(" + alpha(random(2,6)) + "," + alpha(random(2, 6)) + "," + alpha(random(2, 6)) + "," + alpha(random(2, 6)) + "),");
           s = s.replaceAll("h\\)", "h(" + alpha(random(2,6)) + "))");
           s = s.replaceAll("g\\)", "h(" + alpha(random(2,6)) + "))");
           s = s.replaceAll("m\\)", "m(" + alpha(random(2,6)) + "," + alpha(random(2, 6)) + "))");
           s = s.replaceAll("n\\)", "n(" + alpha(random(2,6)) + "," + alpha(random(2, 6)) + "))");
           s = s.replaceAll("p\\)", "p(" + alpha(random(2,6)) + "," + alpha(random(2, 6)) + "," + alpha(random(2, 6)) + "))");
           s = s.replaceAll("q\\)", "q(" + alpha(random(2,6)) + "," + alpha(random(2, 6)) + "," + alpha(random(2, 6)) + "))");
           s = s.replaceAll("s\\)", "s(" + alpha(random(2,6)) + "," + alpha(random(2, 6)) + "," + alpha(random(2, 6)) + "," + alpha(random(2, 6)) + "))");
           s = s.replaceAll("t\\)", "t(" + alpha(random(2,6)) + "," + alpha(random(2, 6)) + "," + alpha(random(2, 6)) + "," + alpha(random(2, 6)) + "))");
         }
         if (t > 50) {
           s = s.replaceAll("h,", "h(" + alpha(1) + "),");
           s = s.replaceAll("g,", "g(" + alpha(1) + "),");
           s = s.replaceAll("m,", "m(" + alpha(1) + "," + alpha(1) + "),");
           s = s.replaceAll("n,", "n(" + alpha(1) + "," + alpha(1) + "),");
           s = s.replaceAll("p,", "p(" + alpha(1) + "," + alpha(1) + "," + alpha(1) + "),");
           s = s.replaceAll("q,", "q(" + alpha(1) + "," + alpha(1) + "," + alpha(1) + "),");
           s = s.replaceAll("s,", "s(" + alpha(1) + "," + alpha(1) + "," + alpha(1) + "," + alpha(1) + "),");
           s = s.replaceAll("t,", "t(" + alpha(1) + "," + alpha(1) + "," + alpha(1) + "," + alpha(1) + "),");
           s = s.replaceAll("h\\)", "h(" + alpha(1) + "))");
           s = s.replaceAll("g\\)", "h(" + alpha(1) + "))");
           s = s.replaceAll("m\\)", "m(" + alpha(1) + "," + alpha(1) + "))");
           s = s.replaceAll("n\\)", "n(" + alpha(1) + "," + alpha(1) + "))");
           s = s.replaceAll("p\\)", "p(" + alpha(1) + "," + alpha(1) + "," + alpha(1) + "))");
           s = s.replaceAll("q\\)", "q(" + alpha(1) + "," + alpha(1) + "," + alpha(1) + "))");
           s = s.replaceAll("s\\)", "s(" + alpha(1) + "," + alpha(1) + "," + alpha(1) + "," + alpha(1) + "))");
           s = s.replaceAll("t\\)", "t(" + alpha(1) + "," + alpha(1) + "," + alpha(1) + "," + alpha(1) + "))");
         }
         t++;
      }
      System.out.println(s);
    }
 }
}

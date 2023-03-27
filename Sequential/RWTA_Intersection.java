import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class RWTA_Intersection {

 public static void main(String[] args) throws Exception {
   long startTime = System.currentTimeMillis();
   try {
     BufferedReader reader = new BufferedReader(new FileReader("subtree.txt"));
     FileWriter writer = new FileWriter("kernel.txt");
     String line;
     String subtree = "";
     int kernel = 0;
     int l = 0;
     Map<String, Integer> x = new HashMap<>();
     Map<String, Integer> y = new HashMap<>();
          Map<String, Integer> u = new HashMap<>();
     while ((line = reader.readLine()) != null) {
       line = line.replace("+", ";");
       if(l == 0) {
         String[] fx = line.split(";");
         for (int i = 0; i < fx.length; i++) {
            String[] part = fx[i].split("(?<=\\d)(?=\\D)");
            x.put(part[1], Integer.parseInt(part[0]));
         }
         l++;
       }
       else {
         String[] fy = line.split(";");
         for (int i = 0; i < fy.length; i++) {
            String[] part = fy[i].split("(?<=\\d)(?=\\D)");
            y.put(part[1], Integer.parseInt(part[0]));
         }
         for (String key: x.keySet()){
            if (y.containsKey(key)){
              u.put(key, x.get(key) * y.get(key)); 
              subtree = subtree + u.get(key) + key + "+";
              kernel = kernel + u.get(key);
            }
         }
       }
     }
     subtree = subtree.substring(0, subtree.length() - 1);
     writer.write(subtree + '\n');
     writer.write(kernel + "" + '\n');
     writer.close();
   } catch (IOException e) {
        e.printStackTrace();
   }
   long endTime = System.currentTimeMillis();
   System.out.println("Execution Time: " + (endTime - startTime) + " ms");
 }
}

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class Conversion_Subtree {

 public static void main(String[] args) throws Exception {
   long startTime = System.currentTimeMillis();
   try {
     BufferedReader reader = new BufferedReader(new FileReader("prefixes.txt"));
     FileWriter writer = new FileWriter("union.txt");
     FileWriter writer1 = new FileWriter("subtree.txt", true);
     String line;
     Map<String, Integer> u = new HashMap<>();
     String subtree = "";
     while ((line = reader.readLine()) != null) {
        String[] fx = line.split(";");
        for (int i = 0; i < fx.length; i++) {
           if (!u.containsKey(fx[i])) {
             u.put(fx[i], 1);
           }
           else {
             u.put(fx[i], u.get(fx[i]) + 1); 
           }
        }
      }
     }
     for (String key: u.keySet()){  
        subtree = subtree + u.get(key) + key + "+";
        String r = "(" + key + ", " + key.charAt(0);
        if (key.contains("(")) {
        r = r + ", " + key.substring(2, key.length() - 1);
        }
        r = r + ")";
        writer.write(r + '\n');
     }
     subtree = subtree.substring(0, subtree.length() - 1);
     writer.write(subtree + '\n');
     writer.close();
     writer1.write(subtree + '\n');
     writer1.close();
   } catch (IOException e) {
        e.printStackTrace();
   }
   long endTime = System.currentTimeMillis();
   System.out.println("Execution Time: " + (endTime - startTime) + " ms");
 }
}

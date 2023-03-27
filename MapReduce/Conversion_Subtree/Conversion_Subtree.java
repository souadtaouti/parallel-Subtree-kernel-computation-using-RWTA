import java.io.IOException;
import java.io.FileWriter;
import java.util.*;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Conversion_Subtree {

  public static class TokenizerMapper
       extends Mapper<Object, Text, Text, IntWritable>{

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
      StringTokenizer itr = new StringTokenizer(value.toString());
      while (itr.hasMoreTokens()) {
        String[] t = itr.nextToken().split(";");
        for (int i = 0; i < t.length; i++){
            word.set(t[i]);
            context.write(word, one);
        }
      }
    }
  }

  public static class IntSumReducer
       extends Reducer<Text,IntWritable,Text,IntWritable> {
    private IntWritable result = new IntWritable();

    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context
                       ) throws IOException, InterruptedException {
      try {
        FileWriter file = new FileWriter("output/automata.txt", true);
        FileWriter file2 = new FileWriter("output/subtree.txt", true);
        int weight = 0;
        String r = "(" + key.toString() + ", " + key.toString().charAt(0);
        if (key.toString().contains("(")) {
          r = r + ", " + key.toString().substring(2, key.toString().length() - 1);
        }
        file.write(r + ")" + "\n");
        file.close();
        for (IntWritable val : values) {
          weight += val.get();
        }
        file2.write(weight + key.toString() + ";");
        file2.close();
       // result.set(sum);
       // context.write(key, result);
      } catch (IOException e) {
          e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "Conversion_Subtree");
    job.setJarByClass(Conversion_Subtree.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setCombinerClass(IntSumReducer.class);
    job.setReducerClass(IntSumReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    long startTimef = System.currentTimeMillis();
    System.out.println(job.waitForCompletion(true) ? 0 : 1);
    long endTimef = System.currentTimeMillis();
    System.out.println("Execution Time: " + (endTimef - startTimef) + " ms");
    System.exit(0);
  }
}

import java.io.IOException;
import java.io.FileWriter;
import java.util.StringTokenizer;
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

public class RWTA_Intersection {

    public static int kernel = 0;

    public static class TokenizerMapper
       extends Mapper<Object, Text, Text, IntWritable>{

    private Text word = new Text();
    private Text weight = new Text();

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
      StringTokenizer itr = new StringTokenizer(value.toString());
      while (itr.hasMoreTokens()) {
        String[] t = itr.nextToken().split(";");
        for (int i = 0; i < t.length; i++) {
           String[] part = t[i].split("(?<=\\d)(?=\\D)");
           String s = part[1];
           int w = Integer.parseInt(part[0]);
           IntWritable one = new IntWritable(w);
         //  String w = part[0];
          // weight = new Text(w);
           word = new Text(s);
           context.write(word, one);
        }
      }
    }
  }

  public static class IntSumReducer
       extends Reducer<Text,IntWritable,Text,IntWritable>{
    private Text result = new Text();

    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context
                       ) throws IOException, InterruptedException {
      try{
        FileWriter myWriter = new FileWriter("output/subtree.txt", true);
        FileWriter file = new FileWriter("output/kernel.txt");
        List<String> s = new ArrayList<String>();
        int mult = 1;
        int sum = 0;
        for (IntWritable val : values) {
          mult *= val.get();
          s.add(key.toString());
          sum = s.size();
        }
        if (s.size() == 2) {
          myWriter.write(mult + key.toString() + ";");
          kernel += mult;
          file.write(kernel + "\n");
        }
        myWriter.close();
        file.close();
      } catch (IOException e) {
          e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "RWTA_Intersection");
    job.setJarByClass(RWTA_Intersection.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setCombinerClass(IntSumReducer.class);
    job.setReducerClass(IntSumReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}

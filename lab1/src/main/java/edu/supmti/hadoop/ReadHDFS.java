package edu.supmti.hadoop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class ReadHDFS {
    public static void main(String[] args) {
        
        if (args.length != 1) {
            System.err.println("Usage: ReadHDFS <chemin_fichier>");
            System.exit(1);
        }
        
        Configuration conf = new Configuration();
        
        try {
            FileSystem fs = FileSystem.get(conf);
            Path filepath = new Path(args[0]);
            
            if (!fs.exists(filepath)) {
                System.err.println("File does not exist: " + args[0]);
                System.exit(1);
            }
            
            FSDataInputStream inStream = fs.open(filepath);
            InputStreamReader isr = new InputStreamReader(inStream);
            BufferedReader br = new BufferedReader(isr);
            
            System.out.println("=== CONTENT OF " + filepath.getName() + " ===\n");
            
            String line;
            while((line = br.readLine()) != null) {
                System.out.println(line);
            }
            
            br.close();
            inStream.close();
            fs.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
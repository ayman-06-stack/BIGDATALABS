package edu.supmti.hadoop;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class WriteHDFS {
    public static void main(String[] args) {
        
        if (args.length != 2) {
            System.err.println("Usage: WriteHDFS <chemin_fichier> <contenu>");
            System.exit(1);
        }
        
        Configuration conf = new Configuration();
        
        try {
            FileSystem fs = FileSystem.get(conf);
            Path filepath = new Path(args[0]);
            
            if (fs.exists(filepath)) {
                System.out.println("File already exists. Overwriting...");
                fs.delete(filepath, false);
            }
            
            FSDataOutputStream outStream = fs.create(filepath);
            outStream.writeUTF("Bonjour tout le monde !");
            outStream.writeUTF(args[1]);
            outStream.close();
            
            System.out.println("✓ File written successfully: " + args[0]);
            
            fs.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
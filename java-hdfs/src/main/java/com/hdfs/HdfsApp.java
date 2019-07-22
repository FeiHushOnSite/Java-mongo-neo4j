package com.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * get file System
 * @return
 * @throw Exception
 */
public class HdfsApp {
    public static FileSystem getFileSystem() throws Exception{
        //read configuration
        //core-site.xml, core-default-site.xml, hdfs-site.xml, hdfs-default-site.xml
        Configuration conf = new Configuration();
        //create file system
        FileSystem  fileSystem = FileSystem.get(conf);
        return fileSystem;
    }

    public static void read(String fileName) throws Exception{
        //read path
        Path readPath = new Path(fileName);
        //get file System
        FileSystem fileSystem = getFileSystem();
        //open file
        FSDataInputStream inStream = fileSystem.open(readPath);
        try {
            //read file
            IOUtils.copyBytes(inStream,System.out,4096,false);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //io close
            IOUtils.closeStream(inStream);
        }
    }

    public static void upload(String inFileName, String outFileName) throws Exception{
        //file input stream, local file
        FileInputStream inStream = new FileInputStream(new File(inFileName));

        //get file system
        FileSystem fileSystem = getFileSystem();
        //write path, hdfs file system
        Path writePath = new Path(outFileName);

        //output stream
        FSDataOutputStream outStream = fileSystem.create(writePath);
        try {
            //write file
            IOUtils.copyBytes(inStream,outStream,4096,false);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //io close
            IOUtils.closeStream(inStream);
            IOUtils.closeStream(outStream);
        }
    }

    public static void main(String[] args) {
        
    }
}

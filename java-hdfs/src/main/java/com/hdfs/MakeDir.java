package com.hdfs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.io.IOUtils;

public class MakeDir {
    public static void main(String[] args) throws IOException {
        String HDFS_PATH = "hdfs://192.168.56.101:9000"; //要连接的hadoop
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", HDFS_PATH);
        //连接文件系统,FileSystem用来查看文件信息和创建文件
        FileSystem fileSystem = FileSystem.get(configuration);

        //操作文件io,用来读写
        configuration.set("fs.hdfs.impl", DistributedFileSystem.class.getName());  //设置处理分布式文件系统
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory(configuration));
        //FsUrlStreamHandlerFactory()  不加参数连接会无法识别的hdfs协议,原因是hadoop在获取处理hdfs协议的控制器时获取了configuration的fs.hdfs.impl值

        //获取Hadoop文件
        InputStream inputStream = new URL(HDFS_PATH + "/user/hadoop/hello.txt").openStream();
        String result = readStream(inputStream);
        System.out.println(result);
    }

    /**
     * 读取 InputStream 到 String字符串中
     */
    public static String readStream(InputStream in) {
        try {
            //<1>创建字节数组输出流，用来输出读取到的内容
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //<2>创建缓存大小
            byte[] buffer = new byte[1024]; // 1KB
            //每次读取到内容的长度
            int len = -1;
            //<3>开始读取输入流中的内容
            while ((len = in.read(buffer)) != -1) { //当等于-1说明没有数据可以读取了
                baos.write(buffer, 0, len);   //把读取到的内容写到输出流中
            }
            //<4> 把字节数组转换为字符串
            String content = baos.toString();
            //<5>关闭输入流和输出流
            in.close();
            baos.close();
            //<6>返回字符串结果
            return content;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}

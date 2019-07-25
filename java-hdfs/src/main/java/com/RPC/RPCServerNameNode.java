package com.RPC;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.Server;

public class RPCServerNameNode implements ClientProtocal{

    public static void main(String[] args) throws Exception{
        Server server = new RPC.Builder(new Configuration()).setInstance(new RPCServerNameNode())
                .setProtocol(ClientProtocal.class).setBindAddress("192.168.56.101").setPort(9123).build();
        server.start();
    }

    @Override
    public String findMetaDataByName(String fileName) {
        System.out.println("Looking for memory" + fileName + "meta data info");
        return fileName + "meta data";
    }
}

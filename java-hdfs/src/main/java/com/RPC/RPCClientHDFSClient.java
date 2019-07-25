package com.RPC;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.net.InetSocketAddress;

public class RPCClientHDFSClient {
    public static void main(String[] args) throws Exception{
            ClientProtocal cp = RPC.getProxy(ClientProtocal.class,123123,
                    new InetSocketAddress("192.168.56.101",9123),
                    new Configuration());
        String msg = cp.findMetaDataByName("words.txt");
        System.out.println(msg);
        RPC.stopProxy(cp);
    }
}

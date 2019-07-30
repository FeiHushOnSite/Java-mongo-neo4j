package com.conn;


import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.InetAddress;


@Component
public class EsClient {

    /*本机IP地址*/
    public final static String HOST = "192.168.56.101";
    /*端扣号*/
    public final static int PORT = 9300;
    /*节点名，安装好后默认的节点名*/
    public final static String CLUSTERNAME = "elasticsearch";

    //private  EsClient client = new EsClient();
    private TransportClient client = null;

    public TransportClient EsClient() throws Exception {
        Settings settings = Settings.builder()
                .put("client.transport.sniff", true)
                .put("cluster.name", CLUSTERNAME)
                .build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddresses(new TransportAddress(InetAddress.getByName(HOST), PORT));
        return client;
    }

    public TransportClient getConnection() {

        if (client == null) {
            synchronized (EsClient.class) {
                if (client == null) {
                    new EsClient();
                }
            }
        }
        return client;

    }

}

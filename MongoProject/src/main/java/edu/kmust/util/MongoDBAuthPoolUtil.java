package edu.kmust.util;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.Arrays;

/**
 * @Author BunnyAndOak0
 * @Description 支持用户认证的池连
 * @Date 2020/11/12 8:13
 **/
public class MongoDBAuthPoolUtil {
    private static MongoClient client = null;

    static {
        if (client == null){
            MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
            builder.connectionsPerHost(10);      //每个地址的最大连接数  默认为10
            builder.connectTimeout(5000);   //设置链接超时时间  单位是毫秒
            builder.socketTimeout(5000);    //设置读写操作的操作时间   单位是毫秒

            MongoCredential credential = MongoCredential.createCredential("bunny", "develop", "bunny".toCharArray());

            ServerAddress address = new ServerAddress("39.101.201.68", 27017);
            client = new MongoClient(Arrays.asList(address), credential, builder.build());
        }
    }

    //获取MongoDB中的数据库
    public static MongoDatabase getDataBase(String dbName){
        return client.getDatabase(dbName);
    }

    //获取MongoDB中的集合
    public static MongoCollection getCollection(String dbName, String collName){
        MongoDatabase dataBase = getDataBase(dbName);
        return dataBase.getCollection(collName);
    }
}

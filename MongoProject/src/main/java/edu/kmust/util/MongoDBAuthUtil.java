package edu.kmust.util;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.Arrays;

/**
 * @Author BunnyAndOak0
 * @Description 创建MongDB链接---使用用户验证
 * @Date 2020/11/12 7:51
 **/
public class MongoDBAuthUtil {
    private static MongoClient client = null;

    static {
        if (client ==null){
            //创建一个封装用户认证信息的对象
            MongoCredential credential = MongoCredential.createCredential("bunny", "develop", "bunny".toCharArray());
            //封装MongoDB的地址与端口
            ServerAddress address = new ServerAddress("39.101.201.68", 27017);
            client = new MongoClient(address, Arrays.asList(credential));
        }
    }

    public static MongoDatabase getDatabase(String dbName){
        return client.getDatabase(dbName);
    }

    public static MongoCollection getCollection(String dbName, String collName){
        MongoDatabase database = getDatabase(dbName);
        return database.getCollection(collName);
    }
}

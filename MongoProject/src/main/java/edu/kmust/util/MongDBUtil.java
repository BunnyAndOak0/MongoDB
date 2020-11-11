package edu.kmust.util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * @Author BunnyAndOak0
 * @Description
 * @Date 2020/11/11 8:21
 **/
public class MongDBUtil {
    private static MongoClient client = null;

    static {
        if (client == null){
            client = new MongoClient("39.101.201.68", 27017);
        }
    }

    //获取MongoDB中的数据库
    public static MongoDatabase getDataBase(String dbName){
        return client.getDatabase(dbName);
    }

    //获取MongoDB中的集合

}

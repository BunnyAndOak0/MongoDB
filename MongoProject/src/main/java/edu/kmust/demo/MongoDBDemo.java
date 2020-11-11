package edu.kmust.demo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * @Author BunnyAndOak0
 * @Description
 * @Date 2020/11/11 8:16
 **/
public class MongoDBDemo {
    public static void main(String[] args) {
        //创建链接对象
        MongoClient client = new MongoClient("39.101.201.68", 27017);
        //获取mongodb的数据库
        MongoDatabase database = client.getDatabase("develop");
        //获取MongDb中的集合
        MongoCollection collection = database.getCollection("dev");

        System.out.println("ok......");
    }
}

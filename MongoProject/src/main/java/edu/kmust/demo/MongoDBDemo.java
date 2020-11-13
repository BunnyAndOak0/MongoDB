package edu.kmust.demo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.kmust.util.MongDBUtil;
import edu.kmust.util.MongoDBAuthPoolUtil;
import edu.kmust.util.MongoDBAuthUtil;
import edu.kmust.util.MongoDBPoolUtil;
import org.bson.Document;

/**
 * @Author BunnyAndOak0
 * @Description
 * @Date 2020/11/11 8:16
 **/
public class MongoDBDemo {
    public static void main(String[] args) {
//        //创建链接对象
////        MongoClient client = new MongoClient("39.101.201.68", 27017);
////        //获取mongodb的数据库
////        MongoDatabase database = client.getDatabase("develop");
////        //获取MongDb中的集合
////        MongoCollection collection = database.getCollection("dev");
////
////        System.out.println("ok......");

//        MongoCollection collection = MongDBUtil.getCollection("develop", "dev");
//        System.out.println("ok......");


//        MongoCollection collection1 = MongoDBAuthUtil.getCollection("develop", "dev");
//        System.out.println("ok2......");

//        MongoCollection collection3 = MongoDBPoolUtil.getCollection("develop", "dev");
//        System.out.println("ok3......");

//        MongoCollection collection4 = MongoDBAuthPoolUtil.getCollection("develop", "dev");
//        System.out.println("ok4......");


        /**
         * @Author BunnyAndOak0
         * @Description 操作集合
         * @Date 0:47 2020/11/13
         **/
//        //创建链接对象
//        MongoClient client = new MongoClient("39.101.201.68", 27017);
//        //获取mongodb的数据库
//        MongoDatabase database = client.getDatabase("develop");
//        //获取MongDb中的集合
//        MongoCollection collection = database.getCollection("dev");
//
//        database.createCollection("test");
//        System.out.println("ok......");

//        MongoDatabase database = MongoDBAuthPoolUtil.getDataBase("develop");
//        database.createCollection("test");

        //取出集合
//        MongoCollection coll = database.getCollection("dev");
//        System.out.println(coll.getNamespace());

//        //删除集合
//        coll.drop();

        MongoDBAuthPoolUtil.createCollection("develop", "devtest");

    }
}

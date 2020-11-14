package edu.kmust.demo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import edu.kmust.util.MongoDBAuthPoolUtil;
import org.bson.Document;

import java.util.logging.Filter;

/**
 * @Author BunnyAndOak0
 * @Description 更新文档
 * @Date 2020/11/14 1:21
 **/
public class UpdateDocument {
    public static void main(String[] args) {
        UpdateDocument updateDocument = new UpdateDocument();
        updateDocument.updateSingleDocumentSingleKey();
        updateDocument.updateSingleDocumentManyKey();
        updateDocument.updateManyDocumentSingleKey();
        updateDocument.updateManyDocumentManyKey();
        updateDocument.updateDocumentArray();

    }

    /**
     * @Author BunnyAndOak0
     * @Description 更新单个文档的单个键
     **/
    public void updateSingleDocumentSingleKey(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");
        //更新文档
        //Filters时封装了条件的工具类
        collection.updateOne(Filters.eq("username", "lisi"), new Document("$set", new Document("userage", 28)));
    }
    
    /**
     * @Author BunnyAndOak0
     * @Description 更新单个文档的多个键
     **/
    public void updateSingleDocumentManyKey(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");
        collection.updateOne(Filters.eq("username", "zhangsan"),
                new Document("$set", new Document("uesrage", 18).append("userdesc", "very good")));
    }

    /**
     * @Author BunnyAndOak0
     * @Description 更新多个文档的多个键
     **/
    public void updateManyDocumentSingleKey(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");
        collection.updateMany(Filters.ne("username", null),
                new Document("$set", new Document("userdesc", "perfect")));
    }

    /**
     * @Author BunnyAndOak0
     * @Description 更新多个文档的多个键
     **/
    public void updateManyDocumentManyKey(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");
        collection.updateMany(Filters.ne("username", null),
                new Document("$set", new Document("userdesc", "OK").append("userage", 20)));
    }
    
    /**
     * @Author BunnyAndOak0
     * @Description 更新文档中的数组
     **/
    public void updateDocumentArray(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("devvelop", "dev");
        collection.updateOne(Filters.eq("username", "lisi"),
                new Document("$push", new Document("userlike", "Art")));
    }
}

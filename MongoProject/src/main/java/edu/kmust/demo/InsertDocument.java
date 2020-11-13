package edu.kmust.demo;

import com.mongodb.client.MongoCollection;
import edu.kmust.util.MongoDBAuthPoolUtil;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author BunnyAndOak0
 * @Description
 * @Date 2020/11/13 8:10
 **/
public class InsertDocument {
    public static void main(String[] args) {
        InsertDocument insertDocument = new InsertDocument();
//        insertDocument.insertSingleDocument();
        insertDocument.insertManyDocument();
    }

    //添加单个文档
    public void insertSingleDocument(){
        //获取集合
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");
        //{}对应Document对象
        Document docu = new Document();
        docu.append("username", "list").append("userage", 26)
                .append("userdesc", "very good")
                .append("userlike", Arrays.asList(new String[]{"Music", "GAME", "eat"}));
        collection.insertOne(docu);
    }

    //文档的批量添加
    public void insertManyDocument(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");
        List<Document> list = new ArrayList<Document>();
        for(int i = 0; i < 5; i++){
            Document docu = new Document();
            docu.append("username", "lisi" + i)
                    .append("userage", 20 + i)
                    .append("userlike", Arrays.asList(new String[]{"Music", "GAME", "eat"}));
            list.add(docu);
        }
        collection.insertMany(list);
    }
}

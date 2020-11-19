package edu.kmust.demo;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import edu.kmust.util.MongoDBAuthPoolUtil;
import org.bson.Document;

/**
 * @Author BunnyAndOak0
 * @Description 实现聚合操作
 * @Date 2020/11/19 8:19
 **/
public class AggregateOperDemo {
    public static void main(String[] args) {

    }

    /**
     * @Author BunnyAndOak0
     * @Description 查询集合中文档的数量
     * db.dev.aggregate([{$group:{_id:null, count:{$sum:1}}}])
     **/
    public void selectDocumentAggregaetCount(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");

        //文档嵌套
        Document sum = new Document();
        sum.put("$sum", 1);

        Document count = new Document();
        count.put("_id", null);
        count.put("count", sum);

        Document group = new Document();
        group.put("$group", count);


        AggregateIterable iterable = collection.aggregate();
    }
}

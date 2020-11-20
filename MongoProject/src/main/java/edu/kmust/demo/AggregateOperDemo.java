package edu.kmust.demo;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import edu.kmust.util.MongoDBAuthPoolUtil;
import org.bson.Document;

import javax.swing.event.DocumentEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author BunnyAndOak0
 * @Description 实现聚合操作
 * @Date 2020/11/19 8:19
 **/
public class AggregateOperDemo {
    public static void main(String[] args) {
        AggregateOperDemo aggregateOperDemo = new AggregateOperDemo();
        aggregateOperDemo.selectDocumentAggregaetCount();
        aggregateOperDemo.selectDocumentAggregateSum();
        aggregateOperDemo.selectDocumentAggregateGroupBySum();
        aggregateOperDemo.selectDocumentAggregateGroupByWhere();
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

        List<Document> list = new ArrayList<Document>();
        list.add(group);

        AggregateIterable iterable = collection.aggregate(list);
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            System.out.println(docu.get("count"));
        }
    }

    /**
     * @Author BunnyAndOak0
     * @Description 查询集合中所有size键中值的总和
     * db.dev.aggregate([{$group:{_id:null, totalSize:{$sum:"$size"}}}])
     **/
    public void selectDocumentAggregateSum(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");
        Document sum = new Document();
        sum.put("$sum", "$size");

        Document totalSize = new Document();
        totalSize.put("_id", null);
        totalSize.put("totalSize", sum);

        Document group = new Document();
        group.put("$group", totalSize);

        List<Document> list = new ArrayList<Document>();
        list.add(group);

        AggregateIterable iterable = collection.aggregate(list);
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            System.out.println(docu.get("totalSize"));
        }

    }

    /**
     * @Author BunnyAndOak0
     * @Description 对title进行分组，计算每组中size的总和
     * db.dev.aggregate([{$group:{_id:"$title", totalSize:{$sum:"$size"}}}])
     **/
    public void selectDocumentAggregateGroupBySum(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");

        Document sum = new Document();
        sum.put("$sum", "$size");

        Document totalSize = new Document();
        totalSize.put("_id", "$title");
        totalSize.put("totalSize", sum);

        Document group = new Document();
        group.put("$group", totalSize);

        List<Document> list = new ArrayList<Document>();
        list.add(group);

        AggregateIterable iterable = collection.aggregate(list);
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            System.out.println(docu.get("totalSize"));
        }
    }

    /**
     * @Author BunnyAndOak0
     * @Description 查询dev集合有多少文档的size大于200
     * db.dev.aggregate([{$match:{size:{$gt:200}}}, {$group:{_id:null, totalSize:{$sum:1}}}])
     **/
    public void selectDocumentAggregateGroupByWhere(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");

        Document gt = new Document();
        gt.put("$gt", 200);

        Document size = new Document();
        size.put("size", gt);

        Document match = new Document();
        match.put("$match", size);

        Document sum = new Document();
        sum.put("$sum", 1);

        Document totalSize = new Document();
        totalSize.put("_id", null);
        totalSize.put("totalSize", sum);

        Document group = new Document();
        group.put("$group", totalSize);

        List<Document> list = new ArrayList<Document>();
        list.add(match);
        list.add(group);

        AggregateIterable iterable = collection.aggregate(list);
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            System.out.println(docu.get("totalSize"));
        }
    }

}

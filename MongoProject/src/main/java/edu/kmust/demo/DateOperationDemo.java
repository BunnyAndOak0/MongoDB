package edu.kmust.demo;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import edu.kmust.util.DateUtil;
import edu.kmust.util.MongoDBAuthPoolUtil;
import org.bson.Document;

import java.util.Arrays;
import java.util.Date;

/**
 * @Author BunnyAndOak0
 * @Description 日期操作
 * @Date 2020/11/18 7:58
 **/
public class DateOperationDemo {
    public static void main(String[] args) {
        DateOperationDemo dateOperationDemo = new DateOperationDemo();
        dateOperationDemo.insertDocumentSystemDate();
        dateOperationDemo.insertDocumentCustoDate();
        dateOperationDemo.selectDocumentDateUseEq();
        dateOperationDemo.selectDocumentDateUseGt();
    }

    /**
     * @Author BunnyAndOak0
     * @Description 插入系统当前日期
     **/
    public void insertDocumentSystemDate(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");
        Document docu = new Document();
        docu.put("username", "wangwu");
        docu.put("userage", 22);
        docu.put("userdesc", "Very Good");
        docu.put("userlike", Arrays.asList(new String[]{"Music", "Art"}));
        //因为new Date()是按照当前处于东八区的位置来给定的日期，而不是UTC得时间来给定，因此会少八个小时
        //查询的时候如果还是使用java.util下面的Date()的话，时间会自动转换，因此会正常显示
        docu.put("userbirth", new Date());
        collection.insertOne(docu);
    }

    /**
     * @Author BunnyAndOak0
     * @Description 插入指定日期
     **/
    public void insertDocumentCustoDate(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");

        Date date = DateUtil.StringToDate("yyyy-MM-dd HH:mm:ss", "2020-11-18 07:45:21");

        Document docu = new Document();
        docu.put("username", "zhaoliu");
        docu.put("userage", 24);
        docu.put("userdesc", "Very Good");
        docu.put("userlike", Arrays.asList(new String[]{"Music", "Art"}));
        docu.put("userbirth", date);
        collection.insertOne(docu);
    }

    /**
     * @Author BunnyAndOak0
     * @Description 查询日期 查询生日为2020-11-18 07:45:21的用户的信息
     **/
    public void selectDocumentDateUseEq(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");

        Date date = DateUtil.StringToDate("yyyy-MM-dd HH:mm:ss", "2020-11-18 07:45:21");

        FindIterable<Document> iterable = collection.find(Filters.eq("userbirth", date));
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            String temp = DateUtil.dateToString("yyyy-MM-dd HH:mm:ss", (Date)docu.get("userbirth"));
            System.out.println(docu.get("username" + "\t" + docu.get("userage") + "\t"
                    + docu.get("userdesc") + "\t" + docu.get("userlike") + "\t" +
                    docu.get("userbirth") + "\t" + temp));
        }
    }

    /**
     * @Author BunnyAndOak0
     * @Description 查询日期 查询生日大于查询生日为2020-11-18 00:00:00的用户的信息的用户的信息
     **/
    public void selectDocumentDateUseGt(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");
        Date date = DateUtil.StringToDate("yyyy-MM-dd HH:mm:ss", "2020-11-18 00:00:00");
        FindIterable<Document> iterable = collection.find(Filters.gt("userbirth", date));
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            String temp = DateUtil.dateToString("yyyy-MM-dd HH:mm:ss", (Date)docu.get("userbirth"));
            System.out.println(docu.get("username" + "\t" + docu.get("userage") + "\t"
                    + docu.get("userdesc") + "\t" + docu.get("userlike") + "\t" +
                    docu.get("userbirth") + "\t" + temp));
        }
    }

}

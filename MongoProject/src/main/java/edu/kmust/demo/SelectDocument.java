package edu.kmust.demo;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import edu.kmust.util.MongoDBAuthPoolUtil;
import edu.kmust.util.MongoDBAuthUtil;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.logging.Filter;
import java.util.regex.Pattern;

/**
 * @Author BunnyAndOak0
 * @Description 查询文档
 * @Date 2020/11/14 8:53
 **/
public class SelectDocument {
    public static void main(String[] args) {
        SelectDocument selectDocument = new SelectDocument();
        selectDocument.selectDocumentAll();
        selectDocument.selectDocuemtnById();
        selectDocument.selectDocumentConditionByGt();
        selectDocument.selectDocumentConditionByType();
        selectDocument.selectDocumentConditionByIn();
        selectDocument.selectDocumentConditionByNin();
        selectDocument.selectDocumentConditionByRegex();
        selectDocument.selectDocumentConditionUseAnd();
        selectDocument.selectDocumentConditionUseOr();
        selectDocument.selectDocumentConditionAndOr();
        selectDocument.selectDocumentSort();
    }

     /**
     * @Author BunnyAndOak0
     * @Description 查询全部文档
     **/
    public void selectDocumentAll(){
        MongoCollection collection = MongoDBAuthUtil.getCollection("develop", "dev");
        //返回一个文档的迭代器
        FindIterable<Document> iterable = collection.find();
        //拿出来的是一个游标
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            //每调一次next方法移动一次指针
            Document docu = cursor.next();
            System.out.println(docu.get("username" + "\t" + docu.get("userage") + "\t"
                    + docu.get("userdesc") + "\t" + docu.get("userlike")));
        }
    }

    /**
     * @Author BunnyAndOak0
     * @Description 根据_id查询文档
     **/
    public void selectDocuemtnById(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");
        FindIterable<Document> iterable = collection.find(Filters.eq("_id", new ObjectId("5f91a923da6db601a79cdcd4")));
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            System.out.println(docu.get("username" + "\t" + docu.get("userage") + "\t"
                    + docu.get("userdesc") + "\t" + docu.get("userlike")));
        }
    }

    /**
     * @Author BunnyAndOak0
     * @Description 根据年龄查询文档 年龄大于19岁
     **/
    public void selectDocumentConditionByGt(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");
        FindIterable<Document> iterable = collection.find(Filters.gt("userage", 19));
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            System.out.println(docu.get("username" + "\t" + docu.get("userage") + "\t"
                    + docu.get("userdesc") + "\t" + docu.get("userlike")));
        }
    }

    /**
     * @Author BunnyAndOak0
     * @Description 根据年龄查询文档 年龄的数值是整数类型（number）
     **/
    public void selectDocumentConditionByType(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");
        FindIterable<Document> iterable = collection.find(Filters.type("userage", "number"));
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            System.out.println(docu.get("username" + "\t" + docu.get("userage") + "\t"
                    + docu.get("userdesc") + "\t" + docu.get("userlike")));
        }
    }

    /**
     * @Author BunnyAndOak0
     * @Description 查询用户的名字为 zhangsan1，zhangsan2
     **/
    public void selectDocumentConditionByIn(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");
        FindIterable<Document> iterable = collection.find(Filters.in("username", "zhangsan1", "zhangsan2"));
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            System.out.println(docu.get("username" + "\t" + docu.get("userage") + "\t"
                    + docu.get("userdesc") + "\t" + docu.get("userlike")));
        }
    }

    /**
     * @Author BunnyAndOak0
     * @Description 查询用户的名字不是 zhangsan1，zhangsan2
     **/
    public void selectDocumentConditionByNin(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");
        FindIterable<Document> iterable = collection.find(Filters.nin("username", "zhangsan1", "zhangsan2"));
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            System.out.println(docu.get("username" + "\t" + docu.get("userage") + "\t"
                    + docu.get("userdesc") + "\t" + docu.get("userlike")));
        }
    }

    /**
     * @Author BunnyAndOak0
     * @Description 查询用户的名字是z开头，2结尾的
   **/
    public void selectDocumentConditionByRegex(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop",  "dev");
        FindIterable<Document> iterable = collection.find(Filters.regex("username", Pattern.compile("^z.*2$")));
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            System.out.println(docu.get("username" + "\t" + docu.get("userage") + "\t"
                    + docu.get("userdesc") + "\t" + docu.get("userlike")));
        }
    }

    /**
     * @Author BunnyAndOak0
     * @Description 查询用户username是zhangsan1并且年龄为20岁的用户
     **/
    public void selectDocumentConditionUseAnd(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("devvelop", "dev");
        FindIterable<Document> iterable = collection.find(Filters.and(Filters.eq("username", "zhangsan1"),
                Filters.eq("userage", "20"), Filters.eq("userdesc", "OK")));
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            System.out.println(docu.get("username" + "\t" + docu.get("userage") + "\t"
                    + docu.get("userdesc") + "\t" + docu.get("userlike")));
        }
    }

    /**
     * @Author BunnyAndOak0
     * @Description 查询用户要求用户username是lisi 或者年龄是20 或者userdesc是Very Good
     **/
    public void selectDocumentConditionUseOr(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");
        FindIterable<Document> iterable = collection.find(Filters.or(Filters.eq("username", "lisi"), Filters.eq("userage", "20"),
                Filters.eq("userdesc", "Very Good")));
        MongoCursor<Document> cursor = iterable.iterator();
        while(cursor.hasNext()){
            Document docu = cursor.next();
            System.out.println(docu.get("username" + "\t" + docu.get("userage") + "\t"
                    + docu.get("userdesc") + "\t" + docu.get("userlike")));
        }
    }

    /**
     * @Author BunnyAndOak0
     * @Description 查询文档中username为lisi并且年龄为20岁，或者userdesc为Very Good
     **/
    public void selectDocumentConditionAndOr(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");
        FindIterable<Document> iterable = collection.find(Filters.or(Filters.and(Filters.eq("username", "lisi"),
                Filters.eq("userage", 20)), Filters.eq("userdesc", "Very Good")));
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            System.out.println(docu.get("username" + "\t" + docu.get("userage") + "\t"
                    + docu.get("userdesc") + "\t" + docu.get("userlike")));
        }
    }

    /**
     * @Author BunnyAndOak0
     * @Description 查询文档中username是z开头，
     * 根据username对结果做降序排序   1为升序排序  -1为降序排序
     **/
    public void selectDocumentSort(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");
        FindIterable<Document> iterable = collection.find(Filters.regex("username",
                Pattern.compile("^z"))).sort(new Document("username", -1));
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            System.out.println(docu.get("username" + "\t" + docu.get("userage") + "\t"
                    + docu.get("userdesc") + "\t" + docu.get("userlike")));
        }
    }



}


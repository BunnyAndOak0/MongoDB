package edu.kmust.demo;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import edu.kmust.util.MongoDBAuthPoolUtil;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * @Author BunnyAndOak0
 * @Description MongoDB分页查询
 * @Date 2020/11/27 0:43
 **/
public class SelectDocumentByPage {
    public static void main(String[] args) {
        SelectDocumentByPage selectDocumentByPage = new SelectDocumentByPage();
        selectDocumentByPage.selectDocumentByPageUseSkipAndLimit(1);
        selectDocumentByPage.selectDocumentByPageCondition(1, 2, null);
    }

    /**
     * @Author BunnyAndOak0
     * @Description 通过skip和limit方法实现分页
     **/
    public void selectDocumentByPageUseSkipAndLimit(int indecPage){
        int page = (indecPage - 1) * 2;

        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");
        Document condition = new Document("size", new Document("$ne", null));

        long countNum = collection.countDocuments(condition);
        System.out.println(countNum);
        FindIterable iterable = collection.find(condition).skip(page).limit(2);
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            System.out.println(docu);
        }
    }

    /**
     * @Author BunnyAndOak0
     * @Description 通过条件判断实现分页
     **/
    public void selectDocumentByPageCondition(int pageIndex, int pageSize, String lastId){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");
        Document condition = new Document("$ne", null);
        long countNum = collection.countDocuments(condition);
        System.out.println(countNum);

        FindIterable findIterable = null;
        if(pageIndex == 1){
            //表示查询的是第一页
            findIterable = collection.find(condition).limit(pageSize);
        }else{
            if(lastId != null){
                condition.append("_id", new Document("$gt", new ObjectId(lastId)));
                findIterable = collection.find(condition).limit(pageSize);
            }
        }
        MongoCursor<Document> cursor = findIterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            System.out.println(docu);
        }
    }
}

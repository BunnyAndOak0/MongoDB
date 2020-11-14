package edu.kmust.demo;

import com.mongodb.client.MongoCollection;
import edu.kmust.util.MongoDBAuthUtil;

/**
 * @Author BunnyAndOak0
 * @Description 查询文档
 * @Date 2020/11/14 8:53
 **/
public class SelectDocument {
    public static void main(String[] args) {

    }

    /**
     * @Author BunnyAndOak0
     * @Description 查询全部文档
     **/
    public void selectDocumentAll(){
        MongoCollection collection = MongoDBAuthUtil.getCollection("develop", "dev");
        
    }
}


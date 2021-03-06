package edu.kmust.demo;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import edu.kmust.util.MongoDBAuthPoolUtil;
import org.bson.Document;

import javax.swing.event.DocumentEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author BunnyAndOak0
 * @Description 投影操作
 * @Date 2020/11/25 0:24
 **/
public class ProjectOperDemo {
    public static void main(String[] args) {
        ProjectOperDemo projectOperDemo = new ProjectOperDemo();
        projectOperDemo.selectDocumentProject();
        projectOperDemo.selectDocumentProjectConcat();
        projectOperDemo.selectDocumentProjectAdd();
        projectOperDemo.selectDocumentProjectDate();
    }
    
    /**
     * @Author BunnyAndOak0
     * @Description 查询dev集合，将数组中的内容拆分显示，并只显示title键和tag键的值
     * db.dev.aggregate([{$unwind:"$tags"},{$project:{_id:0,"tags:$tags",title:"$title"}}])
     **/
    public void selectDocumentProject(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");

        Document unwind = new Document();
        unwind.put("$unwind", "$tags");

        Document pro = new Document();
        pro.put("_id", 0);
        pro.put("tags", "$tags");
        pro.put("title", "$title");

        Document project = new Document();
        project.put("$project", pro);

        List<Document> list = new ArrayList<Document>();
        list.add(unwind);
        list.add(pro);

        AggregateIterable iterable = collection.aggregate(list);
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            System.out.println(cursor.next());
        }
    }

    /**
     * @Author BunnyAndOak0
     * @Description 查询dev集合，将数组中的内容拆分显示，将title字段和tags字段的值拼接为一个完整的字符串并在Title_Tags字段中显示
     * db.dev.aggregate([{$unwind:"$tags"},{$project:{_id:0,Title_Tags:{$concat:["$title","-","$tags"]}}}])
     **/
    public void selectDocumentProjectConcat(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");

        Document unwind = new Document();
        unwind.put("$unwind", "$tags");

        Document concat = new Document();
        concat.put("$concat", Arrays.asList(new String[]{"$title", "-", "$tags"}));

        Document title = new Document();
        title.put("_id", 0);
        title.put("Title_Tags", concat);

        Document project = new Document();
        project.put("$project", title);

        List<Document> list = new ArrayList<Document>();
        list.add(unwind);
        list.add(project);

        AggregateIterable iterable = collection.aggregate(list);
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            System.out.println(cursor.next());
        }
    }

    /**
     * @Author BunnyAndOak0
     * @Description 查询dev集合中数据，显示title和size字段，为size字段数据做加1处理，显示字段命名为New_Size,排除那些没有size键的文档
     * db.dev.aggregate([{$match:{$size:{$ne:null}}},{$project:{_id:0,title:1,New_Size:{$add:["$size",1]}}}])
     **/
    public void selectDocumentProjectAdd(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "dev");

        Document ne = new Document();
        ne.put("$ne", null);

        Document size = new Document();
        size.put("size", ne);

        Document match = new Document();
        match.put("$match", size);

        Document add = new Document();
        add.put("$add", Arrays.asList(new Object[]{"$size", 1}));

        Document new_size = new Document();
        new_size.put("_id", 0);
        new_size.put("title", 1);
        new_size.put("New_Size", add);

        Document project = new Document();
        project.put("$project", new_size);

        List<Document> list = new ArrayList<Document>();
        list.add(match);
        list.add(project);

        AggregateIterable iterable = collection.aggregate(list);
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            Document docu = cursor.next();
            System.out.println(cursor.next());
        }
    }

    /**
     * @Author BunnyAndOak0
     * @Description 查询devtest集合，查询哪些有生日的用户，并按照YYYY年mm月dd日  HH:MM:SS格式显示日期
     * db.devtest.aggregate([{$match:{userbirth:{$ne:null},{$project:{自定义日期格式:{$dataToString:{formate:"%Y年%m月%d日 %H:%M:%S",data:"$userbirth"}}}}}}])
     * 如果是直接在MongoDB中做日期的格式化处理，那么就是按照UTC时间来处理，会少八个小时，建议在程序中通过java.util.Date
     * 来做日期的转换
     **/
    public void selectDocumentProjectDate(){
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("develop", "devtest");
        Document ne = new Document();
        ne.put("$ne", null);

        Document birth = new Document();
        birth.put("userbirth", ne);

        Document match = new Document();
        match.put("$match", birth);

        Document format = new Document();
        format.put("format", "%Y年%m月%d日 %H:%M:%S");
        format.put("data", "$userbirth");

        Document dateToString = new Document();
        dateToString.put("$dateToString", format);

        Document custoDate = new Document();
        custoDate.put("自定义日期格式", dateToString);

        Document project = new Document();
        project.put("$project", custoDate);

        List<Document> list = new ArrayList<Document>();
        list.add(match);
        list.add(project);
        AggregateIterable iterable = collection.aggregate(list);
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()){
            System.out.println(cursor.next());
        }
    }

}

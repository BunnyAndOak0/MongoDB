<div align='center'><font size='6'>MongoDB</font></div>

## MongoDB简介

MongoDB是一个基于分布式文件存储的数据库，由C++语言编写，在为WEB应用提供可扩展的高性能数据存储解决方案。是一个介于**关系数据库和非关系数据库**之间的产品，是非关系数据库中功能最丰富，最像关系数据库的。

特点：查询语言非常强大，几乎可以实现类似关系数据库表单查询的绝大部分功能，而且还支持对数据库简历索引。

Nosql数据库的分类

1. 键值(Key-Value)存储数据库

   使用哈希表，表中有一个特定的键和一个指针指向特定的数据。例如：redis

2. 列存储数据库

   用来应对分布式存储的海量数据，指向了多个列，这些列由家族来安排，例如：HBase

3. 文档型存储数据库

   文档型数据库比键值数据库的查询效率更高，例如：MongoDB，CouchDB

4. 图形（Graph）数据库

   使用灵活的图形模型，可以拓展到多个服务器上。例如：Neo4J，InfoGrid

 ## MongoDB与关系型数据库对比

### 术语对比

| SQL术语概念  | MongoDB术语概念 |               解释说明               |
| :----------: | :-------------: | :----------------------------------: |
|   database   |    database     |                数据库                |
|    table     |   collection    |            数据库表/集合             |
|     row      |    document     |           数据记录行/文档            |
|    column    |      field      |             数据字段/域              |
|    index     |      index      |                 索引                 |
| table  joins |                 |        表连接，MongoDB不支持         |
| primary  key |  primary  key   | 主键，MongoDB自动将_id字段设置为主键 |



### 存储数据对比

![存储数据对比](D:\Blog\images\IMG\images\MongoDB\存储数据对比.PNG)

（用BSON类型存储）



### RDBMS与MongoDB对应的术语

| RDBMS  |            Mongodb            |
| :----: | :---------------------------: |
| 数据库 |            数据库             |
|  表格  |             集合              |
|   行   |             文档              |
|   列   |             字段              |
| 表联合 |           嵌入文档            |
|  主键  | 主键（MongoDB提供了key为_id） |

数据库服务和客户端

| 关系型数据库的数据库服务和客户端 | Mongodb的数据库和客户端 |
| -------------------------------- | ----------------------- |
| MysqlId / Oracle                 | mongod                  |
| mysql / sqlplus                  | mongo                   |



## MongoDB的数据类型

|      数据类型       |                             描述                             |
| :-----------------: | :----------------------------------------------------------: |
|       String        | 字符串，存储数据常用的数据类型，在MongoDB中，UTF-8编码的字符串才是合法 |
|       Integer       | 整形数值，用于存储数据，根据所采用的服务器可以为32位或者64位 |
|       Boolean       |              布尔值，用于存储布尔值（真 / 假）               |
|       Double        |   将一个值与BSON（二进制的JSON）元素的最低值和最高值相对比   |
|   Min / Max  keys   |           用于将数组或者列表或者多个值存储为一个键           |
|        Array        |           用于将数组或者列表或者多个值存储为一个键           |
|      Timestamp      |            时间戳，记录文档修改或者添加的具体时间            |
|       Object        |                         用于内嵌文档                         |
|        Null         |                         用于创建空指                         |
|       Symbol        | 符号，该数值类型基本上等同于字符串类型，但不同的是，它一般采用特殊符号类型的语言 |
|        Date         | 日期时间，用UNIX事件格式来存储当前日期或者时间，你可以制定自己的日期时间：创建Date对象，传入年月日信息 |
|     Object  ID      |                   对象ID，用于创建文档的ID                   |
|    Binary  Data     |                二进制数据，用于存储二进制数据                |
|        Code         |           代码类型，用于在文档中存储JavaScript代码           |
| Regular  expression |              正则表达式类型，用于存储正则表达式              |



## MongoDB的启动

### 前置启动

​		MongoDB在启动时默认的查找数据库的路径为：/data/db，如果路径有变化，需要在该命令中通过--dbpath参数来指定db目录的路径（该路径可以是绝对路径也可以是相对路径）

例如：

```java
./mongod --dbpath /usr/local/mongodb/data/db
```

通过**mongod**的命令来启动



### 后置启动

​		以守护进程的方式来启动MongoDB，需要在命令参数中添加--fork参数，需要注意的是--fork参数需要配合--logpath或者是--syslog参数使用，需要制定具体的文件（二者指的是MongoDB的日志文件）。

 例如：

```java
./mongod --dbpath=/usr/local/mongodb/data/db --logpath=/usr/local/mongodb/log/mongodb.log 
```



### 常见的启动参数

|    参数     |                             用法                             |
| :---------: | :----------------------------------------------------------: |
|   --quiet   |                           安静输出                           |
|   --port    |              指定服务端口号，默认端口为：27017               |
|   --bind    | 绑定服务IP，若绑定127.0.0.1，则只能本机访问（改为0.0.0.0表示所有的都可以进行访问） |
|  --logpath  |       绑定MongoDB日志文件，注意是指定日志文件不是目录        |
| --logappend |                     使用追加的方式写日志                     |
|   --fork    |          守护进程的方式运行MongoDB，创建服务器进程           |
|   --auth    |                           启用验证                           |
|  --config   |        指定配置文件的路径，注意是指定配置文件不是目录        |
|  --journal  | 启用日志选项，MongoDB的数据操作将会写入到journal文件夹的文件里 |



#### 通过配置文件来其进行后置启动

​		在执行MongoDB时通过--config参数来指定需要加载的配置文件。

例如：

配置文件为：

```java
dbpath=/usr/local/mongodb/data/db
logpath=/usr/local/mongodb/log/mongodb.log
port=27017
bind_ip=0.0.0.0
fork=true
```

（关闭后置启动的MongoDB：./mongod --shutdown --dbpath /usr/local/mongodb/data/db/）

以配置文件加载的方式启动：

```java
 ./mongod --config /usr/local/mongodb/etc/mongodb.conf 
```



### MongoDB的关闭

1. 使用Ctrl + C关闭

   ​		只可以关闭基于前置启动的MongoDB，是安全的关闭方式，不会出现数据丢失或者数据损坏。

2. 使用kill命令关闭

   ​		需要删除**data/db**目录中的mongod.lock文件，否则下次无法启动，所以不推荐使用，会造成数据损坏

3. 使用MongoDB的函数关闭

   1. db.shutdownServer()

   2. db.runCommand("shutdown")

      都需要在admin库中执行，并且都是安全的关闭

4. 使用mongod命令关系MongoDB

   mongod --shutdown --dbpath<数据库路径>

   mongod命令的shutdown选项可以安全的关闭MongoDB服务

 

## MongoDB的用户与权限管理

​		通过创建用户的方式降低风险

用户权限表

|         用户         |                             权限                             |
| :------------------: | :----------------------------------------------------------: |
|         Read         |                    允许用户读取指定数据库                    |
|      readWrite       |                    允许用户读写指定数据库                    |
|       dbAdmin        | 允许用户在指定数据库中执行管理函数，例如：索引创建、删除、查看统计或者访问system.profile |
|      userAdmin       | 允许用户向system.users集合写入，可以找指定数据库里创建、删除和管理用户 |
|     clusterAdmin     | 只在admin数据库中可用，赋予用户所有分片和复制集相关函数的管理权限 |
|   readAnyDatabase    |      只在admin数据库中可用，赋予用户所有数据库的读权限       |
| readWriteAnyDatabase |     只在admin数据库中可用，赋予用户所有数据库的读写权限      |
| userAdminAnyDatabase |   只在admin数据库中可用，赋予用户所有数据库的userAdmin权限   |
|  dbAdminAnyDatabase  |    只在admin数据库中可用，赋予用户所有数据库的dbAdmin权限    |
|         root         |          只在admin数据库中可用，超级账号，超级权限           |



### 用户使用

​		mongoDB有一个用户管理机制，也就是说有一个管理用户组，是专门为普通用户设置的，也就是可以称之为管理员。管理员没有数据库的读写权限，只有操作用户的权限，因此只需要赋予userAdminAnyDatabase角色即可。

 ```java
./mongo
可以进行客户端的使用
 ```

1. 查看admin中的用户

   db.system.users.find()函数可以查看admin库中所有用户的信息

2. 添加用户

   ```java
   >db.createUser({user:"admin",pwd:"admin",roles:[{role:"userAdminAnyDatabase",db:"admin"}]})
   ```

   管理员账户创建完成后需要重启MongoDB，并且开启验证

   之后就可以查询到admin中的用户

   在默认情况下MongoDB是不开启用户认证的，如果添加用户，就需要开启用户认证机制，通过修改mongodb.conf的配置文件，在文件中修改auth=true即可

    ```java
   dbpath=/usr/local/mongodb/data/db
   logpath=/usr/local/mongodb/log/mongodb.log
   port=27017
   bind_ip=0.0.0.0
   fork=true
   auth=true
    ```

    进入客户端之后进行验证，否则无法操作数据库

   ```java
   db.auth("admin", "admin")
   ```

   返回1就是认证成功，返回0就是验证失败

   

1. 创建普通用户

   普通用户由管理员创建，需要指定操作某个数据库，所以需要先用管理员账户登录数据库

   ```java
   > use admin
   > db.auth("admin", "admin")
   ```

2. 创建数据库

   如果该数据不存在，就会创建数据库

   ```java
   > use kmust
   ```

    创建普通用户：

   ```java
   > db.createUser({user:"user",pwd:"user",roles:[{role:"readWrite",db:"kmust"}]})
   ```

   需要先认证

   ```jav
   > db.auth("user","user")
   1
   ```

   进行数据插入

   ```java
   > db.kmust.insert({id:"1000"})
   WriteResult({ "nInserted" : 1 })
   ```

   查看

   ```java
   > db.kmust.find()
   { "_id" : ObjectId("5f88e81d1715172f1469f34a"), "id" : "1000" }
   ```

5.更新用户角色

   ​		可以使用db.updateUser()函数来更新用户角色，该函数当前用户具有**userAdminDatabase**或者更高的权限。

   更新用户必须是在用户所在库下面进行更新

   ```java
   > db.updateUser("kmust",{roles:[{"role":"userAdminAnyDatabase","db":"kmust"},{"role":"dbAdminAnyDatabase","db":"kmust"}]})
   ```

   通过show users命令查看用户的角色发生的变化

6.用户更新密码

无论用哪种方式都需要使用管理员的用户进行更新

需要切换到指定的库

1. 使用db.updateUser()函数更新密码--->可以更新除了密码之外的其他信息

   ```java
   > db.updateUser("admin",{"pwd":"123456"})
   ```

   ```java
   > db.auth("admin","admin")
   Error: Authentication failed.
   0
   > db.auth("admin","123456")
   1
   ```

   

   

2. 使用db.changeUserPassword()函数更新密码--->只能更新用户密码

   ```java
   > db.changeUserPassword("user","user")
   ```


7.删除用户

​		通过db.dropUser()函数可以删除指定的用户，删除成功后可以返回true，在删除用户的时候需要切换到创建用户时所指定的数据库才可以删除。ps:需要使用具有userAdminAnyDatabase的角色管理员用户才可以删除其他用户。

先切换数据库，切换过后再进行删除

```java
> use kmust
switched to db kmust
> db.dropUser("user")
true
```



## MongoDB的数据库操作

### 创建数据库

可以不做用户认证

1. 在MongoDB中创建数据库的命令使用的是use命令，有两层含义：

   a.切换到指定的数据库

   b.如果切换的数据库不存在，就会创建该数据库

   ```java
   > use kmusttest
   switched to db kmusttest
   ```



### 查看所有数据库

 		如果开启了用户认证，则需要先登录才可以查看到结果，否则不显示任何信息，如果使用的是**具备数据库管理员角色**的用户，就可以看到**所有的数据库**，如果只是**普通用户登录**，只能**查询到该用户所拥有的数据库**。

 ```java
> use admin
switched to db admin
> db.auth("admin","123456")
1
> show dbs
admin   0.000GB
config  0.000GB
kmust   0.000GB
local   0.000GB
 ```

**show dbs只显示使用过的数据库**，不显示不含仍型新的数据库

（在那哪个库下面创建的用户，就要在哪个库下面登录）

 ```java
> use admin
switched to db admin
> db.auth("user","user")
1
> show dbs
kmust  0.000GB
 ```



### 删除数据库

使用db.dropDatabase()函数来删除数据库，也就是在删除之前需要**使用具备dbAdminAnyDatabase角色的管理员用户登录**，然后**切换到需要删除的数据库**，执行删除函数

 ```java
> use kmusttest
switched to db kmusttest
> db.dropDatabase()
{
	"ok" : 0,
	"errmsg" : "not authorized on kmusttest to execute command { dropDatabase: 1.0, lsid: { id: UUID(\"ec14c347-94a8-472c-9d08-f2ba1a5a5481\") }, $db: \"kmusttest\" }",
	"code" : 13,
	"codeName" : "Unauthorized"
}
 ```



### MongoDB的集合操作

集合对应的也就是关系型数据库里面的表

1. 创建集合

   MongoDB使用db.createCollection()函数来创建集合

   语法：db.createCollection(name,options)

   name：要创建的集合的名称

   options：可选参数，指定有关内存的大小以及索引的选项

   option可以是如下参数：

|    字段     | 类型 |                             描述                             |
| :---------: | :--: | :----------------------------------------------------------: |
|   capped    | 布尔 | （可选）如果位true，则创建固定集合，固定集合是指有着固定大小的集合，当达到最大的时候，会自动覆盖最早的文档，为true的时候，必须指定size参数 |
| autoindexid | 布尔 |    （可选）如果为true，自动在_id字段创建索引，默认为false    |
|    size     | 数值 | （可选）为固定集合指定一个最大值（以字节计）。如果capped为true，也需要指定该字段 |
|     max     | 数值 |            （可选）指固定集合中包含文档的最大数量            |



**使用默认集合**

​		在MongoDB中，也可以不用创建集合，当插入一些数据的时候，会自动创建集合，并且会使用数据库的名字作为集合的名字。

```java
> use admin
switched to db admin
> db.auth("admin","123456")
1
> use develop
switched to db develop
> db.createUser({user:"bunny",pwd:"bunny",roles:[{role:"readWrite",db:"develop"}]}
```

切换并登录

```java
> use develop
switched to db develop
> db.auth("bunny","bunny")
1
```

插入数据：

使用insert()函数，必须给定集合名字，如果没有给集合名字会创建一个和数据库相同名字的集合

```java
> db.develop.insert({bunnyId:"bunny1"})
WriteResult({ "nInserted" : 1 })
> show collections
develop
```

1. 创建不带参数的集合

    如果开启了权限验证，需要使用具有数据库管理员权限的用户来创建集合。

    ```java
   > use develop
   switched to db develop
   > db.createCollection("dev")
   ```

   

   

2. 创建带参数的集合

    ```java
   > db.createCollection("dev2",{capped:true,autoIndexId:true,size:2000000,max:1000})
   ```




**查看集合**

使用show collections或者show tables命令

```java
> use develop
switched to db develop
> db.auth("bunny","bunny")
1
> show collections
develop
```

```java
> show tables
develop
```



#### 删除集合

需要先切换到删除集合所在的数据库，使用drop()函数删除集合即可。

```java
> db.develop.drop()
true
```



### MongoDB的文档操作

​		其实指的就是数据，MongoDB中的文档的数据结构和JSON基本一样，所有存储在集合中的数据都是BSON格式，BSON就是一种类似JSON的二进制形式的存储格式，是Binary JSON的简称。

#### 插入文档

1. 插入单个文档

   1. insert函数

      ```java
      > db.dev.insert({totle:"测试mongo文档插入",decription:"测试",url:"www.baidu.com",tag:["java","python","大数据"]})
      WriteResult({ "nInserted" : 1 })
      ```

      查看文档数据

      ```java
      > db.dev.find()
      { "_id" : ObjectId("5f911c88b4981255db0d1fd8"), "totle" : "测试mongo文档插入", "decription" : "测试", "url" : "www.baidu.com", "tag" : [ "java", "python", "大数据" ] }
      ```

   2. save函数

      ```java
      > db.dev.save({title:"save测试",decription:"save",url:"www.baidu.com",tag:["java","python","web"]})
      WriteResult({ "nInserted" : 1 })
      ```

      查看数据

      ```java
      > db.dev.find()
      { "_id" : ObjectId("5f911c88b4981255db0d1fd8"), "totle" : "测试mongo文档插入", "decription" : "测试", "url" : "www.baidu.com", "tag" : [ "java", "python", "大数据" ] }
      { "_id" : ObjectId("5f911d1bb4981255db0d1fd9"), "title" : "save测试", "decription" : "save", "url" : "www.baidu.com", "tag" : [ "java", "python", "web" ] }
      ```

   3. insertOne函数

      在MongoDB3.2之后的版本中，提供了insertOne()函数用于插入文档

      ```java
      > db.dev.insertOne({title:"insertOne测试",description:"insertOne",url:"www.baidu.com",tag:["go","web",".net"]})
      {
      	"acknowledged" : true,
      	"insertedId" : ObjectId("5f911dfab4981255db0d1fda")
      }
      ```

      查看数据

      ```java
      > db.dev.find()
      { "_id" : ObjectId("5f911c88b4981255db0d1fd8"), "totle" : "测试mongo文档插入", "decription" : "测试", "url" : "www.baidu.com", "tag" : [ "java", "python", "大数据" ] }
      { "_id" : ObjectId("5f911d1bb4981255db0d1fd9"), "title" : "save测试", "decription" : "save", "url" : "www.baidu.com", "tag" : [ "java", "python", "web" ] }
      { "_id" : ObjectId("5f911dfab4981255db0d1fda"), "title" : "insertOne测试", "description" : "insertOne", "url" : "www.baidu.com", "tag" : [ "go", "web", ".net" ] }
      ```

      insertOne和其他两个返回的插入结果是不一样的，会返回成功还是失败

      

2. 插入多个文档

   1. insert或者save函数

      ```java
      > db.dev.insert([{title:"java",tags:["javase","javaee","javame"]},{title:"orm",tags:["mybatis","hibernate"]},{title:"springmvc",tags:["springboot","springcloud"]}])
      BulkWriteResult({
      	"writeErrors" : [ ],
      	"writeConcernErrors" : [ ],
      	"nInserted" : 3,
      	"nUpserted" : 0,
      	"nMatched" : 0,
      	"nModified" : 0,
      	"nRemoved" : 0,
      	"upserted" : [ ]
      })
      ```

      save也是同样的用法

      查询数据

      ```java
      > db.dev.find()
      { "_id" : ObjectId("5f911c88b4981255db0d1fd8"), "totle" : "测试mongo文档插入", "decription" : "测试", "url" : "www.baidu.com", "tag" : [ "java", "python", "大数据" ] }
      { "_id" : ObjectId("5f911d1bb4981255db0d1fd9"), "title" : "save测试", "decription" : "save", "url" : "www.baidu.com", "tag" : [ "java", "python", "web" ] }
      { "_id" : ObjectId("5f911dfab4981255db0d1fda"), "title" : "insertOne测试", "description" : "insertOne", "url" : "www.baidu.com", "tag" : [ "go", "web", ".net" ] }
      { "_id" : ObjectId("5f914ce4b4981255db0d1fdb"), "title" : "java", "tags" : [ "javase", "javaee", "javame" ] }
      { "_id" : ObjectId("5f914ce4b4981255db0d1fdc"), "title" : "orm", "tags" : [ "mybatis", "hibernate" ] }
      { "_id" : ObjectId("5f914ce4b4981255db0d1fdd"), "title" : "springmvc", "tags" : [ "springboot", "springcloud" ] }
      ```

      

   2. insertMany()函数

      在MongoDB3.2之后的版本中提供

      ```java
      > db.dev.insertMany([{title:"web",tag:["jsp","servelt"]},{title:"rpc",tags:["rmi","dubbo"]},{title:"databse",tags:["oracle","mysql"]}])
      {
      	"acknowledged" : true,
      	"insertedIds" : [
      		ObjectId("5f914e3eb4981255db0d1fde"),
      		ObjectId("5f914e3eb4981255db0d1fdf"),
      		ObjectId("5f914e3eb4981255db0d1fe0")
      	]
      }
      ```

      返回值和insert不同

      ```java
      > db.dev.find()
      { "_id" : ObjectId("5f911c88b4981255db0d1fd8"), "totle" : "测试mongo文档插入", "decription" : "测试", "url" : "www.baidu.com", "tag" : [ "java", "python", "大数据" ] }
      { "_id" : ObjectId("5f911d1bb4981255db0d1fd9"), "title" : "save测试", "decription" : "save", "url" : "www.baidu.com", "tag" : [ "java", "python", "web" ] }
      { "_id" : ObjectId("5f911dfab4981255db0d1fda"), "title" : "insertOne测试", "description" : "insertOne", "url" : "www.baidu.com", "tag" : [ "go", "web", ".net" ] }
      { "_id" : ObjectId("5f914ce4b4981255db0d1fdb"), "title" : "java", "tags" : [ "javase", "javaee", "javame" ] }
      { "_id" : ObjectId("5f914ce4b4981255db0d1fdc"), "title" : "orm", "tags" : [ "mybatis", "hibernate" ] }
      { "_id" : ObjectId("5f914ce4b4981255db0d1fdd"), "title" : "springmvc", "tags" : [ "springboot", "springcloud" ] }
      { "_id" : ObjectId("5f914e3eb4981255db0d1fde"), "title" : "web", "tag" : [ "jsp", "servelt" ] }
      { "_id" : ObjectId("5f914e3eb4981255db0d1fdf"), "title" : "rpc", "tags" : [ "rmi", "dubbo" ] }
      { "_id" : ObjectId("5f914e3eb4981255db0d1fe0"), "title" : "databse", "tags" : [ "oracle", "mysql" ] }
      ```

3. 通过变量插入文档

   Mongo Shell工具允许我们定义变量，所有的变量类型为var类型，也可以忽略变量类型。**变量存在缓存中**，只在当前有效，如果重启客户端就会消失。

   1. 通过变量插入单个文档

      ```java
      > document=({title:"springcloud", tags:["spring cloud netfilx","spring cloud security","spring cloud consul"]})
      {
      	"title" : "springcloud",
      	"tags" : [
      		"spring cloud netfilx",
      		"spring cloud security",
      		"spring cloud consul"
      	]
      }
      ```

   2. 通过变量插入多个文档

       ```java
      > docu=([{title:"spring data",tags:["spring data redis","spring datemongodb"]},{title:"spring security",tags:["spring security oauth","spring security saml"]},{title:"spring session",tags:["spring sesison mongodb"]}])
      [
      	{
      		"title" : "spring data",
      		"tags" : [
      			"spring data redis",
      			"spring datemongodb"
      		]
      	},
      	{
      		"title" : "spring security",
      		"tags" : [
      			"spring security oauth",
      			"spring security saml"
      		]
      	},
      	{
      		"title" : "spring session",
      		"tags" : [
      			"spring sesison mongodb"
      		]
      	}
      ]
      > db.dev.insert(docu)
      BulkWriteResult({
      	"writeErrors" : [ ],
      	"writeConcernErrors" : [ ],
      	"nInserted" : 3,
      	"nUpserted" : 0,
      	"nMatched" : 0,
      	"nModified" : 0,
      	"nRemoved" : 0,
      	"upserted" : [ ]
      })
      ```

      **所声明的变量只是在当前有效**，再次启动变量就会没有，因为是存在缓存中

4. 





#### 删除集合中的所有文档

1. 使用ermove函数删除集合中的所有文档

   ```java
   > db.dev.remove({})
   ```

2. 使用deleteMany函数删除所有文档

   ```java
   > db.dev.deleteMany({})
   ```

#### 查询文档

1. find()函数

   使用find()函数查询文档

   find({查询条件(可选)}，{指定投影的键(可选)})

   如果没有给定参数则表示查询所有数据

   pretty()函数可以使用格式化的方式来显示所有文档

   ```java
   > db.dev.find().pretty()
   {
   	"_id" : ObjectId("5f911c88b4981255db0d1fd8"),
   	"totle" : "测试mongo文档插入",
   	"decription" : "测试",
   	"url" : "www.baidu.com",
   	"tag" : [
   		"java",
   		"python",
   		"大数据"
   	]
   }
   ```

   查询想要的文档

   ```java
   > db.dev.find({title:"dev"}).pretty()
   {
   	"_id" : ObjectId("5f95448e5cf5404067a22cbd"),
   	"title" : "dev",
   	"desc" : "test1"
   }
   {
   	"_id" : ObjectId("5f9544915cf5404067a22cbe"),
   	"title" : "dev",
   	"desc" : "test2"
   }
   {
   	"_id" : ObjectId("5f9544945cf5404067a22cbf"),
   	"title" : "dev",
   	"desc" : "test3"
   }
   ```

2. findOne()函数

   findOne()函数只返回满足条件的第一条数据，如果没有做投影操作该方法则自带格式化功能，做投影的话就不带格式化功能。

   findOne({查询条件(可选)}，{投影操作(可选)})

   ```java
   > db.dev.findOne() 
   {
   	"_id" : ObjectId("5f911c88b4981255db0d1fd8"),
   	"totle" : "测试mongo文档插入",
   	"decription" : "测试",
   	"url" : "www.baidu.com",
   	"tag" : [
   		"java",
   		"python",
   		"大数据"
   	]
   }
   ```

   给定条件也只查询第一条

   ```java
   > db.dev.findOne({title:"dev"})
   {
   	"_id" : ObjectId("5f95448e5cf5404067a22cbd"),
   	"title" : "dev",
   	"desc" : "test1"
   }
   ```

3. 模糊查询

   可以使用//与^$实现模糊查询，使用模糊查询的时候查询条件**不能放到双引号或者单引号中**

   ```java
   > db.dev.find({title:/e/})
   { "_id" : ObjectId("5f911d1bb4981255db0d1fd9"), "title" : "save测试", "decription" : "save", "url" : "www.baidu.com", "tag" : [ "java", "python", "web" ] }
   { "_id" : ObjectId("5f911dfab4981255db0d1fda"), "title" : "insertOne测试", "description" : "insertOne", "url" : "www.baidu.com", "tag" : [ "go", "web", ".net" ] }
   ```

   使用^表示起始位置，查询文档中title的值以d开头的内容。

   ```java
   > db.dev.find({title:/^d/})
   { "_id" : ObjectId("5f914e3eb4981255db0d1fe0"), "title" : "databse", "tags" : [ "oracle", "mysql" ] }
   { "_id" : ObjectId("5f95448e5cf5404067a22cbd"), "title" : "dev", "desc" : "test1" }
   { "_id" : ObjectId("5f9544915cf5404067a22cbe"), "title" : "dev", "desc" : "test2" }
   { "_id" : ObjectId("5f9544945cf5404067a22cbf"), "title" : "dev", "desc" : "test3" }
   ```

   使用$表示结尾位置，查询文档中title的值

   ```java
   > db.dev.find({title:/v$/})
   { "_id" : ObjectId("5f95448e5cf5404067a22cbd"), "title" : "dev", "desc" : "test1" }
   { "_id" : ObjectId("5f9544915cf5404067a22cbe"), "title" : "dev", "desc" : "test2" }
   { "_id" : ObjectId("5f9544945cf5404067a22cbf"), "title" : "dev", "desc" : "test3" }
   ```

#### 投影操作

1. find()函数投影操作

   在find函数中可以指定投影键

   对查询到的内容做key的选择

   1表示显示，0表示不显示，默认是显示

   ```java
   > db.dev.find({},{title:1})
   { "_id" : ObjectId("5f911c88b4981255db0d1fd8") }
   { "_id" : ObjectId("5f911d1bb4981255db0d1fd9"), "title" : "save测试" }
   { "_id" : ObjectId("5f911dfab4981255db0d1fda"), "title" : "insertOne测试" }
   ```

   id默认是会显示出来的

   指定不显示

   ```java
   > db.dev.find({},{title:1,_id:0})
   {  }
   { "title" : "save测试" }
   { "title" : "insertOne测试" }
   { "title" : "java" }
   { "title" : "orm" }
   { "title" : "springmvc" }
   { "title" : "web" }
   ```

2. findOne()函数操作投影

   与find()函数相同

   

#### 条件操作符

条件操作符用于比较两个表达式并且从mongoDB集合中获取数据。

1. $gt

   大于操作符

   ```java
   > db.dev2.find({size:{$gt:300}})
   { "_id" : ObjectId("5f954abd5cf5404067a22cc3"), "title" : "test4", "size" : 400 }
   { "_id" : ObjectId("5f954ac45cf5404067a22cc4"), "title" : "test5", "size" : 500 }
   ```

2. $lt

   小于操作符

   ```java
   > db.dev2.find({size:{$lt:400}})
   { "_id" : ObjectId("5f954aa65cf5404067a22cc0"), "title" : "test1", "size" : 200 }
   { "_id" : ObjectId("5f954ab05cf5404067a22cc1"), "title" : "test2", "size" : 200 }
   { "_id" : ObjectId("5f954ab55cf5404067a22cc2"), "title" : "test3", "size" : 300 }
   ```

3. $gte

   大于等于可以对数字或者日期进行判断

    ```java
   > db.dev2.find({size:{$gte:300}})
   { "_id" : ObjectId("5f954ab55cf5404067a22cc2"), "title" : "test3", "size" : 300 }
   { "_id" : ObjectId("5f954abd5cf5404067a22cc3"), "title" : "test4", "size" : 400 }
   { "_id" : ObjectId("5f954ac45cf5404067a22cc4"), "title" : "test5", "size" : 500 }
    ```

   

4. $lte

   小于等于

   ```java
   > db.dev2.find({size:{$lte:300}})
   { "_id" : ObjectId("5f954aa65cf5404067a22cc0"), "title" : "test1", "size" : 200 }
   { "_id" : ObjectId("5f954ab05cf5404067a22cc1"), "title" : "test2", "size" : 200 }
   { "_id" : ObjectId("5f954ab55cf5404067a22cc2"), "title" : "test3", "size" : 300 }
   ```

5. $eq

   ==等于操作符，做相等的条件判断

   ```java
   > db.dev2.find({size:{$eq:300}})
   { "_id" : ObjectId("5f954ab55cf5404067a22cc2"), "title" : "test3", "size" : 300 }
   ```

   

6. $ne

   !=操作符

   ```java
   > db.dev2.find({size:{$ne:300}})
   { "_id" : ObjectId("5f924841b937e09074db6ce4"), "title" : "java" }
   { "_id" : ObjectId("5f924a16b937e09074db6ce5"), "title" : "dev2", "size" : "500", "num" : 1 }
   { "_id" : ObjectId("5f92c3c7b937e09074db6ce9"), "title" : "hhh", "desc" : "test3" }
   { "_id" : ObjectId("5f92c3cab937e09074db6cea"), "title" : "hhh", "desc" : "test4" }
   { "_id" : ObjectId("5f92c51fb937e09074db6ceb"), "title" : "hhh", "desc" : "test5" }
   { "_id" : ObjectId("5f954aa65cf5404067a22cc0"), "title" : "test1", "size" : 200 }
   { "_id" : ObjectId("5f954ab05cf5404067a22cc1"), "title" : "test2", "size" : 200 }
   { "_id" : ObjectId("5f954abd5cf5404067a22cc3"), "title" : "test4", "size" : 400 }
   { "_id" : ObjectId("5f954ac45cf5404067a22cc4"), "title" : "test5", "size" : 500 }
   ```

7. $and

   使用$and操作符来表示多条件间的并且关系

   条件之间的关系默认为and关系,，同一个属性的不同判断

   ```java
   > db.dev2.find({size:{$gt:200,$lt:500}})
   { "_id" : ObjectId("5f954ab55cf5404067a22cc2"), "title" : "test3", "size" : 300 }
   { "_id" : ObjectId("5f954abd5cf5404067a22cc3"), "title" : "test4", "size" : 400 }
   ```

   使用$and指定

   ```java
   > db.dev2.find({$and:[{size:{$gt:300}},{size:{$lt:500}}]})
   { "_id" : ObjectId("5f954abd5cf5404067a22cc3"), "title" : "test4", "size" : 400 }
   
   > db.dev2.find({$and:[{title:"test4"},{size:400}]})
   { "_id" : ObjectId("5f954abd5cf5404067a22cc3"), "title" : "test4", "size" : 400 }
   ```

   

8. $or

   表示多条关系间的或者关系

   查询title为test2或者size大于300的文档

   ```java
   > db.dev2.find({$or:[{title:"test2"},{size:{$gt:300}}]})
   { "_id" : ObjectId("5f954ab05cf5404067a22cc1"), "title" : "test2", "size" : 200 }
   { "_id" : ObjectId("5f954abd5cf5404067a22cc3"), "title" : "test4", "size" : 400 }
   { "_id" : ObjectId("5f954ac45cf5404067a22cc4"), "title" : "test5", "size" : 500 }
   ```

9. $and与$or联合使用

   ```java
   > db.dev2.find({$or:[{$and:[{title:{$eq:"test5"}},{size:500}]},{size:{$lt:400}}]})
   { "_id" : ObjectId("5f954aa65cf5404067a22cc0"), "title" : "test1", "size" : 200 }
   { "_id" : ObjectId("5f954ab05cf5404067a22cc1"), "title" : "test2", "size" : 200 }
   { "_id" : ObjectId("5f954ab55cf5404067a22cc2"), "title" : "test3", "size" : 300 }
   { "_id" : ObjectId("5f954ac45cf5404067a22cc4"), "title" : "test5", "size" : 500 }
   ```

10. $type操作符

    基于BSON类型来检查集合中匹配的数据类型并返回结果

     ```java
    > db.dev2.find({title:{$type:"number"}})
    { "_id" : ObjectId("5f955a675cf5404067a22cc5"), "title" : 100, "size" : 400 }
     ```

    

#### Limit函数和Skip函数

1. Limit函数

   可以决定获取满足条件的记录数量

   ```java
   > db.dev2.find({},{title:1}).limit(3)
   { "_id" : ObjectId("5f924841b937e09074db6ce4"), "title" : "java" }
   { "_id" : ObjectId("5f924a16b937e09074db6ce5"), "title" : "dev2" }
   { "_id" : ObjectId("5f92c3c7b937e09074db6ce9"), "title" : "hhh" }
   ```

2. Skip函数

   跳过指定数据量的数据，决定从第几条开始取

   ```java
   > db.dev2.find({},{title:1}).skip(3).limit(2)
   { "_id" : ObjectId("5f92c3cab937e09074db6cea"), "title" : "hhh" }
   { "_id" : ObjectId("5f92c51fb937e09074db6ceb"), "title" : "hhh" }
   ```

   limit()和skip()的顺序可以不同

   可以使用skip函数与limit函数实现MongoDB的分页查询，但是官方不推荐这种方法，因为会扫描全部文档然后返回结果，效率过低

#### MongoDB排序

使用sort()函数来对查询到的文档进行排序额，sort()函数可以通过参数指定排序的字段，并使用1和-1来指定排序的方式，**1为升序排列，-1用于降序排列**

1. 升序排序

    ```java
   > db.dev2.find({size:{$type:"number"}},{title:1,size:1,_id:0}).sort({size:1})
   { "title" : "test1", "size" : 200 }
   { "title" : "test2", "size" : 200 }
   { "title" : "test3", "size" : 300 }
   { "title" : "test4", "size" : 400 }
   { "title" : 100, "size" : 400 }
   { "title" : "test5", "size" : 500 }
   ```

2. 降序排列

   ```java
   > db.dev2.find({size:{$type:"number"}},{title:1,size:1,_id:0}).sort({size:-1})
   { "title" : "test5", "size" : 500 }
   { "title" : "test4", "size" : 400 }
   { "title" : 100, "size" : 400 }
   { "title" : "test3", "size" : 300 }
   { "title" : "test1", "size" : 200 }
   { "title" : "test2", "size" : 200 }
   ```

   

### 索引

1. 创建索引

   在MongoDB中会自动为文档的_id（文档的主键）键创建索引，与关系型数据的主键索引类似。

   使用createIndex()函数来为其他的键创建索引，在创建索引的时候需要按照指定的排序规则。1按照升序规则创建索引，-1按照降序规则创建索引。

   ​    db.COLLECTINO_NAME.createIndex({创建索引的键：排序规则，......}，{创建索引的参数(可选参数)})

   在创建索引时，需要使用具有dbAdmin或者dbAdminAnyDatabase角色的用户。 

   参数说明

   |          参数           | 数据类型 | 默认值 |                             功能                             |
   | :---------------------: | :------: | :----: | :----------------------------------------------------------: |
   |       background        | Boolean  | false  |             后台建立索引时不阻止其他数据库的活动             |
   |         unique          |  Boolea  | false  |                         创建唯一索引                         |
   |          name           |  String  |        | 指定索引名称，如果未指定，MongoDB会生成一个索引字段的名称和排序顺序串联 |
   | partialFilterExpression | document |        |            如果指定Mongo指挥满足过滤表达式的记录             |
   |         sparse          | Boolean  | false  |             对文档中的不存在的字段数据不启用索引             |
   |   expireAfterSeconds    | Integer  |        |                      指定索引的过期时间                      |
   |      stoageEngine       |          |        |          document类型允许用户配置索引的额外存储引擎          |

   ```java
   > db.dev2.createIndex({title:1},{background:true})
   ```

2. 查看索引

   使用getIndexes()或者getIndexSpecs()函数查看集合中所有索引的信息
   
   ```java
   > db.dev2.getIndexse()
   ```
   
3. 查看索引键

   getIndexKeys()函数来查看集合的索引键

   ```java
   > db.dev.getIndexKeys()
   ```

4. 查看索引大小

   通过totalIndexSize()函数查看当前集合中索引的大小，单位为字节

   db.COLLECTION_NAME.totalIndexSize([detail]`(`可选参数`)`)

   detail可选参数，传入除了0或者false以外的任意数据，就会显示该集合中每个索引的大小以及集合中索引的总大小，如果传入0或者是false则只显示该集合中所有索引的总大小，默认值为false

   ```java
   > db.dev.totalIndexSize([1])
   ```

5. 修改索引

   MongoDB没有单独的修改索引函数，如果要修改某个索引，需要先删除旧的索引，再创建新的索引

6. 删除索引

   通过dropIndex()函数删除指定的索引

   db.COLLECTION_NAME.dropIndex("索引名称")

7. 删除集合中的全部索引

   使用dropIndexes()函数删除集合中的全部索引，_id键的索引除外

   db.COLLECTION_NAME.dropIndexes()

8.  重建索引

   可以使用reIndex()函数重建索引，重建索引之后可以减少索引的存储空间，减少索引碎片优化索引查询效率，重建索引时删除原索引重新创建的过曾，不建议反复使用。

   db.COLLECTION_NAME.redIndex()

9. 复合索引

   针对多个字段联合创建索引，先按照第一个字段排序，第一个字段相同的按照第二个字段拍学，以此类推。

   db.COLLECTION_NAME.createIndex({索引键名:排序规则，索引键名:排序规则,......})

10.  多key索引

    当索引的字段为数组的时候，创建出的索引称为多key索引，多可以索引会为数组的每个元素建立一条索引

    db.COLLECTION_NAME.createIndex({数组键名：排序规则})

    只对数组类型生效

#### 索引的额外属性

1. 唯一属性

   会保证索引对应的键不会出现相同的值，比如id索引就是唯一索引

   db.COLLECTION_NAME.createIndex({索引键名：排序规则},{unique:true})

   如果唯一索引所在的字段有重复的数据写入，抛出异常

2. 部分索引

   只是针对某个符合特定条件的文档建立的索引，3.2版本才支持该特性。

   有较低的存储需求，降低了索引创建和维护的性能要求。简单点说，也就是带有过滤条件的索引，即索引只存在于某些文档之上。

   db.CONNCTION_NAME.createIndex({索引键名：排序规则}，{partFilterExpression:{键名：{匹配条件：条件值}}})

   ```java
   > db.dev.createIndex({size:1},{partialFilterExpression:{size:{$gt:300}}})
   {
   	"createdCollectionAutomatically" : false,
   	"numIndexesBefore" : 1,
   	"numIndexesAfter" : 2,
   	"ok" : 1
   }
   ```

   查看索引

   ```java
   > db.dev.getIndexes()
   [
   	{
   		"v" : 2,
   		"key" : {
   			"_id" : 1
   		},
   		"name" : "_id_",
   		"ns" : "develop.dev"
   	},
   	{
   		"v" : 2,
   		"key" : {
   			"size" : 1
   		},
   		"name" : "size_1",
   		"ns" : "develop.dev",
   		"partialFilterExpression" : {
   			"size" : {
   				"$gt" : 300
   			}
   		}
   	}
   ]
   ```

   如果即制定了partiFilterExpression和唯一约束，那么唯一约束只适用于满足筛选条件的文档，具有唯一约束的部分索引，不会阻止不符合唯一约束且不符合过滤条件的文档的插入。

3. 覆盖索引查询

   1. 所有的查询字段是索引的一部分
   2. 所有的查询返回字段再同一个索引中

   

#### 正则查询

MongDB中查询条件也可以使用正则表达式作为匹配约束

db.COLLECTION_NAME.find(字段名：{正则表达式})

db.COLLECTION_NAME.find({字段名：{$regex:正则表达式，$options:正则选项}});

 正则表达式格式：/xxx/

正则选项：

i-不区分大小写以及匹配大小写的情况

m-多行查找，如果内容不存在换行符号（例如\n）或者条件上没有（start\end），该选项没有任何效果

x-设置x选项后，正则表达式中的非转义的空白字符将被忽略，需要$regex与options语法

s-允许点字符（即.）匹配包括换行符在内的所有字符，需要$regex与$options语法

i，m，x，s可以组合使用

 例如：查询集合中以d开头的数据

```java
> db.dev.find({title:/^d/})
{ "_id" : ObjectId("5f914e3eb4981255db0d1fe0"), "title" : "databse", "tags" : [ "oracle", "mysql" ] }
{ "_id" : ObjectId("5f95448e5cf5404067a22cbd"), "title" : "dev", "desc" : "test1" }
{ "_id" : ObjectId("5f9544915cf5404067a22cbe"), "title" : "dev", "desc" : "test2" }
{ "_id" : ObjectId("5f9544945cf5404067a22cbf"), "title" : "dev", "desc" : "test3" }
```

```java
> db.dev.find({title:{$regex:/^d/}})
{ "_id" : ObjectId("5f914e3eb4981255db0d1fe0"), "title" : "databse", "tags" : [ "oracle", "mysql" ] }
{ "_id" : ObjectId("5f95448e5cf5404067a22cbd"), "title" : "dev", "desc" : "test1" }
{ "_id" : ObjectId("5f9544915cf5404067a22cbe"), "title" : "dev", "desc" : "test2" }
{ "_id" : ObjectId("5f9544945cf5404067a22cbf"), "title" : "dev", "desc" : "test3" }
```

结尾

```java
> db.dev.find({title:/v$/})
{ "_id" : ObjectId("5f95448e5cf5404067a22cbd"), "title" : "dev", "desc" : "test1" }
{ "_id" : ObjectId("5f9544915cf5404067a22cbe"), "title" : "dev", "desc" : "test2" }
{ "_id" : ObjectId("5f9544945cf5404067a22cbf"), "title" : "dev", "desc" : "test3" }
```

```java
> db.dev.find({title:{$regex:/v$/}})
{ "_id" : ObjectId("5f95448e5cf5404067a22cbd"), "title" : "dev", "desc" : "test1" }
{ "_id" : ObjectId("5f9544915cf5404067a22cbe"), "title" : "dev", "desc" : "test2" }
{ "_id" : ObjectId("5f9544945cf5404067a22cbf"), "title" : "dev", "desc" : "test3" }
```

中间包含

```java
> db.dev.find({title:/e/})
{ "_id" : ObjectId("5f911d1bb4981255db0d1fd9"), "title" : "save测试", "decription" : "save", "url" : "www.baidu.com", "tag" : [ "java", "python", "web" ] }
{ "_id" : ObjectId("5f911dfab4981255db0d1fda"), "title" : "insertOne测试", "description" : "insertOne", "url" : "www.baidu.com", "tag" : [ "go", "web", ".net" ] }
```

(//中间的值不能写成字符串)

正则表达式的写法：

```java
> db.dev.find({title:{$regex:/e/}})
{ "_id" : ObjectId("5f911d1bb4981255db0d1fd9"), "title" : "save测试", "decription" : "save", "url" : "www.baidu.com", "tag" : [ "java", "python", "web" ] }
```

以某字母开头，并且忽略大小写

```java
> db.dev.find({title:/^s/i})
{ "_id" : ObjectId("5f911d1bb4981255db0d1fd9"), "title" : "save测试", "decription" : "save", "url" : "www.baidu.com", "tag" : [ "java", "python", "web" ] }
```

 ```java
> db.dev.find({title:{$regex:/^s/i}})
{ "_id" : ObjectId("5f911d1bb4981255db0d1fd9"), "title" : "save测试", "decription" : "save", "url" : "www.baidu.com", "tag" : [ "java", "python", "web" ] }
 ```

```java
> db.dev.find({title:{$regex:/^s/,$options:"i"}})
{ "_id" : ObjectId("5f911d1bb4981255db0d1fd9"), "title" : "save测试", "decription" : "save", "url" : "www.baidu.com", "tag" : [ "java", "python", "web" ] }
```

（i需要写到双引号或者单引号当中）

以xxx开头，xxx结尾

```java
> db.dev.find({title:/^d.*v$/})
{ "_id" : ObjectId("5f95448e5cf5404067a22cbd"), "title" : "dev", "desc" : "test1" }
{ "_id" : ObjectId("5f9544915cf5404067a22cbe"), "title" : "dev", "desc" : "test2" }
{ "_id" : ObjectId("5f9544945cf5404067a22cbf"), "title" : "dev", "desc" : "test3" }
```

使用正则

```java
> db.dev.find({title:{$regex:/^s.*g$/}})
{ "_id" : ObjectId("5fa16f9c0387751cba516738"), "title" : "spring" }
```

查询dev集合中，title字段以d开头或者s开头

```java
> db.dev.find({title:{$in:[/^d/,/^s/]}})
{ "_id" : ObjectId("5f911d1bb4981255db0d1fd9"), "title" : "save测试", "decription" : "save", "url" : "www.baidu.com", "tag" : [ "java", "python", "web" ] }
```

查询dev集合中，title字段不以s开头

```java
> db.dev.find({title:{$not:/^s/}})
{ "_id" : ObjectId("5f911c88b4981255db0d1fd8"), "totle" : "测试mongo文档插入", "decription" : "测试", "url" : "www.baidu.com", "tag" : [ "java", "python", "大数据" ] }
```

不以s或者d开头的（使用**$nin**操作符）

```java
> db.dev.find({title:{$nin:[/^d/,/^s/]}})
{ "_id" : ObjectId("5f911c88b4981255db0d1fd8"), "totle" : "测试mongo文档插入", "decription" : "测试", "url" : "www.baidu.com", "tag" : [ "java", "python", "大数据" ] }
```



#### 聚合查询

通过aggregate()函数完成后一些聚合函数，可以用于处理一些诸如统计，平均值，求和等，并返回计算后的数据结果。

语法：db.CLLECTION_NAME.aggregated([{$group:{_id:"$分组键名"，"$分组键名"，...，别名：{聚合运算："$运算列"}}}，{条件筛选：{键名:{运算条件：运算值}}}])

常见的mongo的聚合操作和mysql的查询类比

| sql操作/函数 |   mongodb聚合操作   |
| :----------: | :-----------------: |
|    where     |       $match        |
|   group by   |       $group        |
|    having    |       $match        |
|    select    |      $project       |
|   order by   |        $sort        |
|    limit     |       $limit        |
|    sum()     |        $sum         |
|   count()    |        $sum         |
|     join     | $loopup（v3.2新增） |

如果$match是在group的前侧就当作where处理，在后侧就当作having处理，

1. 求和 -$sum

   查询dev集合中一共有多少个文档

     ```java
   > db.dev.aggregate([{$group:{_id:null,count:{$sum:1}}}])
   { "_id" : null, "count" : 17 }
     ```

   查询dev2集合中所有size键中值得总和

   ```java
   > db.dev2.aggregate([{$group:{_id:null,totalSize:{$sum:"$size"}}}])
   { "_id" : null, "totalSize" : 2000 }
   ```

   对每一个title进行分组并计算每组中的size的总和

   ```java
   > db.dev2.aggregate([{$group:{_id:"$title",totalSize:{$sum:"$size"}}}])
   { "_id" : "test5", "totalSize" : 500 }
   { "_id" : "test4", "totalSize" : 400 }
   { "_id" : "java", "totalSize" : 0 }
   { "_id" : "test2", "totalSize" : 200 }
   { "_id" : "dev2", "totalSize" : 0 }
   { "_id" : "test3", "totalSize" : 300 }
   { "_id" : "hhh", "totalSize" : 0 }
   { "_id" : 100, "totalSize" : 400 }
   { "_id" : "test1", "totalSize" : 200 }
   ```

2. 条件筛选-$match

   查询dev2集合中size大于200（相当于where）

   ```java
   > db.dev2.aggregate({$match:{size:{$gt:200}}},{$group:{_id:null,totalSize:{$sum:1}}})
   { "_id" : null, "totalSize" : 4 }
   ```

   查询dev2集合，根据title分组计算出每组的size总和，并过滤掉总和小于等于200的文档（相当于having）

    ```java
   > db.dev2.aggregate([{$group:{_id:"$title",totalSize:{$sum:"$size"}}},{$match:{totalSize:{$gte:200}}}])
   { "_id" : "test5", "totalSize" : 500 }
   { "_id" : "test4", "totalSize" : 400 }
   { "_id" : "test2", "totalSize" : 200 }
   { "_id" : "test3", "totalSize" : 300 }
   { "_id" : 100, "totalSize" : 400 }
   { "_id" : "test1", "totalSize" : 200 }
    ```

3. 最大值-$max

   查询dev2中size最大的文档

   ```java
   > db.dev2.aggregate([{$group:{_id:null,maxSize:{$max:"$size"}}}])
   { "_id" : null, "maxSize" : "500" }
   ```

4. $min最小值

   查询dev集合中size最小的文档

   ```java
   > db.dev2.aggregate([{$group:{_id:null,minSize:{$min:"$size"}}}])
   { "_id" : null, "minSize" : 200 }
   ```

5. 平均值-$avg

   ```java
   > db.dev2.aggregate([{$group:{_id:null,sizeAvg:{$avg:"$size"}}}])
   { "_id" : null, "sizeAvg" : 333.3333333333333 }
   ```

6. 统计结果返回-$push

   查询dev2集合，按照size分组并返回title，如果size相同，则使用数组返回他们的title，

   ```java
   > db.dev2.aggregate([{$group:{_id:"$size",titleArray:{$push:"$title"}}}])
   { "_id" : 500, "titleArray" : [ "test5" ] }
   { "_id" : null, "titleArray" : [ "java", "hhh", "hhh", "hhh" ] }
   { "_id" : 400, "titleArray" : [ "test4", 100 ] }
   { "_id" : "500", "titleArray" : [ "dev2" ] }
   { "_id" : 200, "titleArray" : [ "test1", "test2" ] }
   { "_id" : 300, "titleArray" : [ "test3" ] }
   ```

7.  数组字段拆分-$unwind

   查询集合dev2，将数组中的内容拆分显示

   ```java
   > db.dev.aggregate([{$unwind:"$tags"}])
   { "_id" : ObjectId("5f914ce4b4981255db0d1fdb"), "title" : "java", "tags" : "javase" }
   { "_id" : ObjectId("5f914ce4b4981255db0d1fdb"), "title" : "java", "tags" : "javaee" }
   ```

8. 管道操作

   将MongDB的文档在一个管道处理完毕后将结果传递给下一个管道处理，管道操作是可以重复的，管道操作是按照书写顺序依次执行的，每个操作符都会接受一连串的文档，，对这些文档做一些类型转换，最后将转换后的文档作为结果传递给下一个操作符（对于最后一个管道操作符，是将结果返回给客户端），成为流式工作方式。

   管道操作符：$match、$group、$rout、$limit、$skip、$unwind

   管道操作符，只能用于计算机当前聚合管道的文档，**不能处理其他的文档**

9. $project

   1. 聚合投影约束

      $project操作符：可以使用$project操作符做聚合投影操作

      查询dev2集合，将数组中的内容拆分显示，并只显示title键和tags键的值

      ```java
      > db.dev.aggregate([{$unwind:"$tags"},{$project:{_id:0,title:"$title",tags:"$tags"}}])
      { "title" : "java", "tags" : "javase" }
      { "title" : "java", "tags" : "javaee" }
      { "title" : "java", "tags" : "javame" }
      { "title" : "orm", "tags" : "mybatis" }
      ```

   2. 字符串处理

      通过MongDB的字符串操作符对投影的内容做字符串的处理
      
      将title转为大写和小写
      
      ```java
      > db.dev.aggregate([{$unwind:"$tags"},{$project:{_id:0,New_Titlte:{$toLower:"$title"},New_Tags:{$toUpper:"$tags"}}}])
      { "New_Titlte" : "java", "New_Tags" : "JAVASE" }
      { "New_Titlte" : "java", "New_Tags" : "JAVAEE" }
      { "New_Titlte" : "java", "New_Tags" : "JAVAME" }
      ```
      
      拼接title何tags字段
      
      ```java
      > db.dev.aggregate([{$unwind:"$tags"},{$project:{_id:0,Titlte_Tags:{$concat:["$title","-","$tags"]}}}])
      { "Titlte_Tags" : "java-javase" }
      { "Titlte_Tags" : "java-javaee" }
      { "Titlte_Tags" : "java-javame" }
      ```
      
      将数组内容拆分显示，只显示title字段的前三个字符，命名为Title_Prefix
      
      ```java
      > db.dev.aggregate([{$unwind:"$tags"},{$project:{_id:0,Titlt_Prefix:{$substr:["$title",0,3]}}}])
      { "Titlt_Prefix" : "jav" }
      { "Titlt_Prefix" : "jav" }
      { "Titlt_Prefix" : "jav" }
      ```
      
      对于汉字部分，$substr只能匹配ASCII的数据，对于中文要使用$substrCP
      
   3. 算术运算
   
      在$project中，可以通过MongDB的算数作符对投影的内容做运算处理。
   
      查询dev中集合的数据，显示title何size字段，为size字段数据做加一操作，显示字段命名为New_Size
   
      ```java
      > db.dev2.aggregate([{$project:{_id:0,title:"$title",New_Size:{$add:["$size",1]}}}])
      ```
   
      排除没有size键的文档
   
      ```java
      > db.dev2.aggregate([{$match:{size:{$ne:null}}},{$project:{_id:0,title:1,New_Size:{$add:["$size",1]}}}])
          
   ```
      
   4. 日期操作
   
      插入当前时间db.dev.insert({date:new Date()})
   
      MongoDB中的事件会比系统当前时间少8个消失(它使用的是UTC时间)。
   
       ```java
      > db.dev.insert({time:new Date()})
      WriteResult({ "nInserted" : 1 })
      > db.dev.find({time:{$ne:null}})
      { "_id" : ObjectId("5fa96243f55eb7efe9be5b3d"), "time" : ISODate("2020-11-09T15:37:39.053Z") }
       ```
   
      1. 方式一
   
         ```java
         > db.dev.insert({time:new Date("2020-11-10T12:48:56Z")})
         WriteResult({ "nInserted" : 1 })
         ```
   
         用new Date()插入日期，必须使用标准的日期格式
   
      2. 方式二
   
         用ISODate()函数插入
   
         ```java
         > db.dev.insert({time:new Date("2020-11-10T12:48:56Z")})
         WriteResult({ "nInserted" : 1 })
         ```
   
         没有标准日期格式（T、Z不存在）
   
      查询时间
   
      ```java
      > db.dev.find({time:{$eq:new Date("2020-11-10T12:48:56Z")}})
      { "_id" : ObjectId("5fa97328f55eb7efe9be5b3e"), "time" : ISODate("2020-11-10T12:48:56Z") }
      ```
   
      （注意标准格式）
   
      大于：
   
      ```java
      > db.dev.find({time:{$gt:new Date("2020-05-01")}})
      { "_id" : ObjectId("5fa96243f55eb7efe9be5b3d"), "time" : ISODate("2020-11-09T15:37:39.053Z") }
      { "_id" : ObjectId("5fa97328f55eb7efe9be5b3e"), "time" : ISODate("2020-11-10T12:48:56Z") }
      ```
   
      没有给出时分秒会默认为0时0分0秒
   
      ```java
      > db.dev.find({time:{$gt:ISODate("20200501 14:20:30")}})
      { "_id" : ObjectId("5fa96243f55eb7efe9be5b3d"), "time" : ISODate("2020-11-09T15:37:39.053Z") }
      { "_id" : ObjectId("5fa97328f55eb7efe9be5b3e"), "time" : ISODate("2020-11-10T12:48:56Z") }
      ```
   
      























***

Tips：

```java
ps aux | grep mongodb
可以查看当前mongodb的启动状态
```














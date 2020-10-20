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

   

   





















***

Tips：

```java
ps aux | grep mongodb
可以查看当前mongodb的启动状态
```














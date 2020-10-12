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



































+++

Tips：

```jav
ps aux | grep mongodb
可以查看当前mongodb的启动状态
```














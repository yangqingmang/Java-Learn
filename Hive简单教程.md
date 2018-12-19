# Hive简单教程

## Hive是什么

Hive 是一个基于Apache Hadoop的数据仓库基础架构。Hadoop为商用硬件上的数据存储和处理提供了大规模的扩展和容错能力。

Hive 旨在实现轻松的数据汇总、即时查询和分析大量数据。它提供了SQL，使用户可以轻松的进行查询、汇总和数据分析。同时，Hive的SQL给予了用户多种方式来集成自己的功能，然后做定制化的查询，例如用户自定义函数（User Define Functions，UDFs）。

##### Hive不适合做什么

Hive 不是为在线事务设计的，它最适合用于传统的数据仓库任务。

## Hive的优点

- 简单容易上手：提供了类SQL查询语言HSQL

- 可扩展：为超大数据集设计了计算/扩展能力（MR作为计算引擎，HDFS作为存储系统）。一般情况下不需要重启服务Hive可以自由的扩展集群的规模。

- 提供统一的元数据管理

- 延展性：Hive支持用户自定义函数，用户可以根据自己的需求来实现自己的函数

- 容错：良好的容错性，节点出现问题SQL仍可完成执行

## Hive系统简单介绍

#### 数据单元

- 数据库：命名空间，用于避免表、视图、分区、列等等命名冲突。数据库也可以用于加强用户和用户组的安全。

- 表：相同schema的同类数据单元。

- 分区：每个表可以有一个或多个用于确定数据存储位置的分区键。分区（除存储单元外）也允许用户有效的识别满足制定条件的行

- 桶：每个分区的数据，基于表的一些列的哈希函数值，又被分割成桶。

#### 系统类型

1. 原始类型

- 整型
  - TINYINT －1位的整型
  - SMALLINT －2位的整型
  - INT －4位的整型
  - BIGINT －8位的整型
- 布尔型
  - BOOLEAN －TRUE/FALSE

- 浮点型
  - FLOAT －单精度
  - DOUBLE －双精度
- 定点数
  - DECIMAL －用户可以指定范围和小数点位数
- 字符串
  - STRING －在特定的字符集中的一个字符串序列
  - VARCHAR －在特定的字符集中的一个有最大长度限制的字符串序列
  - CHAR －在特定的字符集中的一个指定长度的字符串序列
- 日期和时间
  - TIMESTAMP －一个特定的时间点，精确到纳秒
  - DATE －一个日期
- 二进制
  - BINARY －一个二进制位序列

1. 复杂类型

- 结构体类型 (Struct)：使用(.)来访问类型内部的元素。例如，有一列`c`，它是一个结构体类型{a INT; b INT}，字段a可以使用表达式`c.a`来访问。
- Map(key-value键值对)：使用[‘元素名’]来访问元素。例如，有一个Map`M`，包含`'group'->gid`的映射，则gid的值可以使用`M['group']`来访问。 
- 数组：数组中的元素是相同的类型。可以使用n来访问数组元素，n是数组下标，以0开始。例如有一个数组A，有元素`['a','b','c']`，则`A[1]`返回`'b'`。

#### 内置运算符

- 关系运算

  A=B、A!=B、A<B、A<=B、A>B、A>=B、A IS NULL、A IS NOT NULL、A LIKE B、A RLIKE B、A REGEXP B

- 数学运算

  A+B、A-B、A*B、A/B、A%B、A&B、A|B、A^B、~A

- 逻辑运算

  A AND B、A && B、A OR B、A || B、NOT A、! A

- 复杂类型运算

  A[n]、M[key]、S.x

#### 内置函数

- 内置函数

  round(double a)、floor(double a)、ceil(double a)、rand()、rand(int seed)、concat(string A,string B,……)、substr(string A,int start)、substr(string A,int start,int length)、upper(string A)、ucase(string A)、lower(string A)、lcase(string A)、trim(string A)、ltrim(string A)、rtrim(string A)、regexp_replace(string A,string B,string C)、size(Map<K.V>)、size(Array<T>)、case(<expr> as <type>)、from_unixtime(int unixtime)、to_date(string timestamp)、year(string date)、month(string date)、day(string date)、get_json_object(string json_string,string path)

- 聚合函数

  count(*)、count(expr)、count(DISTINCT expr[,expr_.])、sum(col)、sum(DISTINCT col)、avg(col)、avg(DISTINCT col)、min(col)、max(col)

#### 语言能力

Hive’s SQL提供了基本的SQL操作。这些操作工作于表或分区上。这些操作是： 

    - 可以使用WHERE从表中筛选行 
    - 可以使用SELECT从表中查询指定的列 
    - 两个表之间可以join 
    - 可以在多个group by的列上使用聚合 
    - 可以存储查询结构到另一个表 
    - 可以下载表的内容到本地目录 
    - 可以存储查询结果到hadoop的分布式文件系统目录上 
    - 可以管理表和分区（创建，删除和修改） 
    - 可以使用自定义的脚本，来定制map/reduce作业

## 使用教程

#### 下载安装

hive运行在hadoop上，所以如果要使用hive，首先要先安装hadoop

###### 下载安装hadoop

1. Java

    Hadoop 2.7 以及后续版本需要 Java 7 以上版本的支持，所以最好直接用 Java 8 。

    安装略。

2. SSH

    首先在系统里打开远程登录，位置在 系统偏好设置 -> 共享 中，左边勾选 远程登录，右边选择 所有用户。

    然后配置SSH免密码登录

        ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa
        cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
        chmod 0600 ~/.ssh/authorized_keys

    接下来测试一下是否成功

    ssh localhost

3. 下载hadoop

    1. brew 直接安装

      brew install hadoop

     2. 下载安装包解压

    官网文档参考[地址](https://hadoop.apache.org/docs/current/)。

    这将显示 hadoop 脚本的使用文档。 现在您已准备好以三种受支持模式之一启动Hadoop集群：

    - Local (Standalone) Mode 独立式
    - Pseudo-Distributed Mode 伪分布式
    - Fully-Distributed Mode 全分布式

    我们建立第二种，让 Hadoop 做为后台应用运行在本地机器，模拟小集群。

    Hadoop也可以在伪分布模式下的单节点上运行，其中每个 Hadoop 守护进程都在单独的 Java 进程中运行。

    a) 输入以下代码看看你把 Java 装到哪里了：

        /usr/libexec/java_home
    打开 hadoop-env.sh 文件（位置 etc/hadoop/），找到 #export JAVA_HOME=，改参数如下：
   
        export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_161.jdk/Contents/Home

    b) vi etc/hadoop/core-site.xml

        <configuration>
            <property>
                <name>fs.defaultFS</name>
                <value>hdfs://localhost:9000</value>    
            </property>
        </configuration>

    c) vi etc/hadoop/hdfs-site.xml

        <configuration>
            <property>
                <name>dfs.replication</name>
                <value>1</value>
            </property>
        </configuration>
        
    你可以通过设置几个参数并运行 ResourceManager 守护进程和 NodeManager 守护进程，以伪分布模式在 YARN 上运行 MapReduce 作业。
    
    a）vi etc/hadoop/mapred-site.xml
   
        <configuration>
            <property>
                <name>mapreduce.framework.name</name>
                <value>yarn</value>
            </property>
        </configuration>
        <!--<configuration>
            <property>
                <name>mapreduce.application.classpath</name>
                <value>$HADOOP_MAPRED_HOME/share/hadoop/mapreduce/*:$HADOOP_MAPRED_HOME/share/hadoop/mapreduce/lib/*</value>
            </property>
        </configuration>-->
    b) vi etc/hadoop/yarn-site.xml

        <configuration>
            <property>
                <name>yarn.nodemanager.aux-services</name>
                <value>mapreduce_shuffle</value>
            </property>
            <property>
                <name>yarn.nodemanager.env-whitelist</name>
                <value>JAVA_HOME,HADOOP_COMMON_HOME,HADOOP_HDFS_HOME,HADOOP_CONF_DIR,CLASSPATH_PREPEND_DISTCACHE,HADOOP_YARN_HOME,HADOOP_MAPRED_HOME</value>
                </property>
            </configuration>
            
    c) 格式化文件系统
   
        bin/hdfs namenode -format
        
    d) 启动NameNode守护进程和DataNode守护进程
   
        ./sbin/start-dfs.sh
        
    如果启动第二个节点之后直接终止，则从logs日志文件中查看是否是非法的uri，如果是非法的uri，则在hdfs-site.xml中加入以下信息
        
        <property>
            <name>dfs.secondary.http.address</name>
            <value>127.0.0.1:50090</value>
        </property>
        
    启动之后访问如下链接就可以看到界面了：
    
    	http://localhost:9870/
        
    启动 ResourceManager 守护进程和 NodeManager 守护进程

        ./sbin/start-yarn.sh
        
    现在你应该可以在浏览器中打开下面的链接看到亲切的 All Applications 界面了：

        http://localhost:8088
        
    *PS：或者可以直接启动 ./sbin/start-all.sh ，就不用启动两个了*

    玩累了就输入以下代码停止：

        ./sbin/stop-dfs.sh
        ./sbin/stop-yarn.sh

###### 下载安装Hive

1. 同样，下载安装可以用brew或直接下载压缩包解压。

2. hive默认用derby作为元数据库。这里我们改为hive
    
    新建一个数据库hive，设置用户名密码（具体过程略）。
    
3. 进入hive安装目录，找到conf文件夹下的hive-site文件（如果没有复制hive-default.xml文件修改一下文件名即可）
    修改其中的内容：
    
        <property>
          <name>javax.jdo.option.ConnectionURL</name>
          <value>jdbc:mysql://localhost/metastore</value>
        </property>
        
        <property>
          <name>javax.jdo.option.ConnectionDriverName</name>
          <value>com.mysql.jdbc.Driver</value>
        </property>
        
        <property>
          <name>javax.jdo.option.ConnectionUserName</name>
          <value>hive(填上述mysql中创建的用户名)</value>
        </property>
        
        <property>
          <name>javax.jdo.option.ConnectionPassword</name>
          <value>123456(填上述mysql中创建的用户密码)</value>
        </property>
        
        <property>
          <name>hive.exec.local.scratchdir</name>
          <value>/tmp/hive</value>
        </property>
        
        <property>
          <name>hive.querylog.location</name>
          <value>/tmp/hive</value>
        </property>
        
        <property>
          <name>hive.downloaded.resources.dir</name>
          <value>/tmp/hive</value>
        </property>
        
        <property>
          <name>hive.server2.logging.operation.log.location</name>
          <value>/tmp/hive</value>
        </property>
        
    下载拷贝mysql-connector jar文件到hive的lib目录下。
    
    初始化metastore库
    
        $ schematool -initSchema -dbType mysql
        
    成功之后我们可以看到hive用usr/local/Cellar/hive/2.1.1/libexec/scripts/metastore/upgrade/mysql/hive-schema-2.1.0.mysql.sql
    的初始化sql脚本在我们配置的元数据库中初始化好了如下表：
        
        mysql> show tables;
            具体截图略。
            
    之后就可以访问我们的hive数据库进行操作。
    
#### 操作hive数据库

首先启动hadoop
    
    $ ./sbin/start-all.sh

接下来访问hive数据库

    $ ./bin/hive
    
*以下操作基于命令行，如果想用客户端mac用户可以参考[https://blog.csdn.net/snow327646777/article/details/78230116](https://blog.csdn.net/snow327646777/article/details/78230116)，需要先下载hive-jdbc jar文件
[https://repo.maven.apache.org/maven2/org/apache/hive/hive-jdbc/3.1.1/](https://repo.maven.apache.org/maven2/org/apache/hive/hive-jdbc/3.1.1/)*    

*如果有启动问题，请访问[https://blog.csdn.net/sunnyyoona/article/details/51648871](https://blog.csdn.net/sunnyyoona/article/details/51648871)*

Hive 有几种启动方式：

- 直接CLI命令行访问，使用本地的metastore。
- 使用远程的metastore，启动前需要先配置远程metastore
	
	```
    	<property>
      		<name>hive.metastore.uris</name>
       	<value>thrift://metastore_server_ip:9083</value><!--此处是服务器1的ip-->
    	</property>
	
- 启动hiveserver2，使其他服务可以通过thrift接入hive

	hadoop的core-site.xml文件中配置hadoop代理用户
	
		<property>
		    <name>hadoop.proxyuser.g2.hosts</name>
		    <value>*</value>
		</property>
		<property>
		    <name>hadoop.proxyuser.g2.groups</name>
		    <value>*</value>
		</property>
		
	启动hiveserver2
	
		./bin/hive --service hiveserver2
		
	beeline工具测试使用jdbc方式连接
	
		./bin/beeline
		!connect jdbc:hive2://127.0.0.1:10000/default
	
	hiveserver端口号默认是10000 
	
	hiveserver2会同时启动一个webui，端口号默认为10002，可以通过http://localhost:10002/访问
	
- 使用HCatalog访问hive

	HCatalog是基于Apache Hadoop之上的数据表和存储管理服务，支持跨数据处理工具，如Pig，	Mapreduce，Streaming，Hive。 使用HCatalog，则hive的元数据也可以为其他基于Hadoop的工具	所使用。无论用户用哪个数据处理工	具，通过HCatalog，都可以操作同一个数据。

	可以通过以下命令启动HCatalog
	
		sbin/hcat_server.sh start

	可以通过以下命令启动HCatalog的cli界面
	
		bin/hcat
		
	另外，HCatalog的WebHCat 也提供一套REST API接口访问hive数据,可以通过以下命令启动WebHCat
	
		sbin/webhcat_server.sh start	
	
	
#### 创建和删除数据库
创建数据库是用来创建数据库在Hive中语句。在Hive数据库是一个命名空间或表的集合。此语法声明如下：

	CREATE DATABASE|SCHEMA [IF NOT EXISTS] <database name>
	
在这里，IF NOT EXISTS是一个可选子句，通知用户已经存在相同名称的数据库。可以使用SCHEMA 在DATABASE的这个命令。下面的查询执行创建一个名为userdb数据库：

	hive> CREATE DATABASE [IF NOT EXISTS] userdb;
	
或
	
	hive> CREATE SCHEMA userdb;
	
下面的查询用于验证数据库列表：

	hive> SHOW DATABASES;
	default
	userdb
	
DROP DATABASE是删除所有的表并删除数据库的语句。它的语法如下：

	DROP DATABASE StatementDROP (DATABASE|SCHEMA) [IF EXISTS] database_name 
	[RESTRICT|CASCADE];
	
下面的查询用于删除数据库。假设要删除的数据库名称为userdb：

	hive> DROP DATABASE IF EXISTS userdb;
	
以下是使用CASCADE查询删除数据库。这意味着在删除数据库之前要全部删除相应的表。

	hive> DROP DATABASE IF EXISTS userdb CASCADE;
	
以下使用SCHEMA查询删除数据库。

	hive> DROP SCHEMA userdb;

#### 创建表
以下例子创建一个page_view表
	
	CREATE TABLE page_view(viewTime INT, userid BIGINT,
                page_url STRING, referrer_url STRING,
                ip STRING COMMENT 'IP Address of the User')
	COMMENT 'This is the page view table'
	PARTITIONED BY(dt STRING, country STRING)
	STORED AS SEQUENCEFILE;

查看一下新创建的表：

	hive> show tables;
	OK
	page_view
	Time taken: 0.075 seconds, Fetched: 1 row(s)
	hive>
	
结果中已经有我们新创建的表了。

加载数据到分区：
	
	load data local inpath'/home/partition_table.dat' into table partition_tablepartition (daytime='2013-02-01',city='bj');

在这个例子中，表的字段被指定为相应的类型。备注（Comments)可以基于列级别，也可以是表级别。另外，使用PARTITIONED关键词定义的分区列与数据列是不同的，分区列实际上不存储数据。当使用这种方式创建表的时候，我们假设数据文件的内容，字段之间以ASCII 001（ctrl-A)分隔，行之间以换行分隔。

如果数据不是以上述格式组织的，我们也可以指定分隔符，如下：
	
	CREATE TABLE page_view(viewTime INT, userid BIGINT,
                page_url STRING, referrer_url STRING,
                ip STRING COMMENT 'IP Address of the User')
	COMMENT 'This is the page view table'
	PARTITIONED BY(dt STRING, country STRING)
	ROW FORMAT DELIMITED
	        FIELDS TERMINATED BY '1'
	STORED AS SEQUENCEFILE;
	
行delimintor当前无法更改，因为它不是由Hive确定的，而是由Hadoop分隔符决定的。

#### 查看表

列出数据库里的所有以page开头的表：
	
	hive> show tables 'page.*';
	OK
	page_view
	Time taken: 0.059 seconds, Fetched: 1 row(s)
	hive>

列出表的分区：

	hive> SHOW PARTITIONS page_view;
	OK
	Time taken: 0.286 seconds
	hive>
	
列出表的字段和字段类型：

	hive> DESCRIBE page_view;
	OK  
	viewtime		int
	userid			bigint
	page_url 		string
	referrer_url	string
	ip				string			IP Address of the User 
	dt				string
	country			string
	
	# Partition Information
	# col_name	data_type	comment
	dt				string
	country      	string
	hive>
	
列出表的列和表的其他属性:
	
	DESCRIBE EXTENDED page_view;
	
这会打印很多信息，且输出的风格不是很友好，通常用于调试。

列出列和分区的所有属性:
	
	DESCRIBE EXTENDED page_view PARTITION (ds='2016-08-08');
	
####修改表

对已有的表进行重命名。如果表的新名字存在，则报错：

	hive> alter table page_view rename to new_page_view;
	OK
	Time taken: 0.488 seconds
	hive> show tables;
	OK
	new_page_view
	Time taken: 0.055 seconds, Fetched: 1 row(s)
	hive>
	
对已有表增加列：

	hive> describe new_page_view;
	OK
	viewtime            	int
	userid              	bigint
	page_url            	string
	referrer_url        	string
	ip                  	string              	IP Address of the User
	dt                  	string
	country             	string
	
	# Partition Information
	# col_name            	data_type           	comment
	dt                  	string
	country             	string
	Time taken: 0.296 seconds, Fetched: 12 row(s)
	hive> alter table new_page_view add columns(c1 int comment 'add new column1',c2 int comment 'add new column2');
	OK
	Time taken: 0.15 seconds
	hive> describe new_page_view;
	OK
	viewtime            	int
	userid              	bigint
	page_url            	string
	referrer_url        	string
	ip                  	string              	IP Address of the User
	c1                  	int                 	add new column1
	c2                  	int                 	add new column2
	dt                  	string
	country             	string
	
	# Partition Information
	# col_name            	data_type           	comment
	dt                  	string
	country             	string
	Time taken: 0.116 seconds, Fetched: 14 row(s)
	hive>
	
对已有表的列名进行重命名。要确保使用相同的列类型，且要包含对每个已存在列的一个入口(也就是说，就算不修改其他列的列名，也要把此列另上，否则，此列会丢失）：

	ALTER TABLE old_table_name REPLACE COLUMNS (col1 TYPE, ...);
	
*PS:模式的改变（例如增加列），保留了表的老分区，以免它是一个分区表。所有对这些列或老分区的查询都会隐式地返回一个null值或这些列指定的默认值。*

#### 删除表和分区
删除表相当简单。表的删除会删除已经建立在表上的任意索引：

	DROP TABLE pv_users;
	
删除表的分区。修改表进行删除分区：

	ALTER TABLE pv_users DROP PARTITION (ds='2016-08-08');
	
*注意：此表或分区的任意数据都将被删除，而且可能无法恢复。*

### 加载数据
要加载数据到Hive表有许多种方式。用户可以创建一个“外部表”来指向一个特定的HDFS路径。用这种方法，用户可以使用HDFSput或copy命令，复制一个文件到指定的位置，并且附上相应的行格式信息创建一个表指定这个位置。一旦完成，用户就可以转换数据和插入他们到任意其他的Hive表中。例如，如果文件/tmp/pv_2016-06-08.txt包含逗号分隔的页面访问记录。这需要以合适的分区加载到表page_view，以下命令可以完成这个目标：

	CREATE EXTERNAL TABLE page_view_stg(viewTime INT, userid BIGINT,
                page_url STRING, referrer_url STRING,
                ip STRING COMMENT 'IP Address of the User',
                country STRING COMMENT 'country of origination')
	COMMENT 'This is the staging page view table'
	ROW FORMAT DELIMITED FIELDS TERMINATED BY '44' LINES TERMINATED BY '12'
	STORED AS TEXTFILE
	LOCATION '/user/data/staging/page_view';
	
	hadoop dfs -put /tmp/pv_2016-06-08.txt /user/data/staging/page_view
	
	FROM page_view_stg pvs
	INSERT OVERWRITE TABLE page_view PARTITION(dt='2016-06-08', country='US')
	SELECT pvs.viewTime, pvs.userid, pvs.page_url, pvs.referrer_url, null, null, pvs.ip
	WHERE pvs.country = 'US';
	
其中，‘44’是逗号的ASCII码，‘12’是换页符（NP from feed,new page）。null是作为目标表中的数组和map类型插入，如果指定了合适的行格式，这些值也可以来自外部表。

如果在HDFS上有一些历史数据，用户想增加一些元数据，以便于可以使用Hive来查询和操纵这些数据，这个方法是很有用的。

另外，系统也支持直接从本地文件系统上加载数据到Hive表。表的格式与输入文件的格式需要相同。如果文件/tmp/pv_2016-06-08包含了US数据，然后我们不需要像前面例子那样的任何筛选，这种情况的加载可以使用以下语法完成：

	LOAD DATA LOCAL INPATH /tmp/pv_2016-06-08_us.txt INTO TABLE page_view PARTITION(date='2016-06-08', country='US')

路径参数可以是一个目录（这种情况下，目录下的所有文件将被加载），一个文件，或一个通配符（这种情况下，所有匹配的文件会上传）。如果参数是目录，它不能包含子目录。同样，通配符只匹配文件名。

在输入文件/tmp/pv_2016-06-08.txt非常大的情况下，用户可以采用并行加载数据的方式（使用Hive的外部工具）。只要文件在HDFS上－以下语法可以用于加载数据到Hive表：
	
	LOAD DATA INPATH '/user/data/pv_2016-06-08_us.txt' INTO TABLE page_view PARTITION(date='2016-06-08', country='US')

对于这个例子，我们假设数组和map在文件中的值为null。

*更多关于加载数据到表的信息，请参考Hive Data Manipulation Language,创建外部表的另外一个例子请参考External Tables。*

#### 查询和插入数据
- 简单的查询(Simple Query)
- 基于分区的查询(Partition Based Query)
- 链接(Joins)
- 聚合(Aggregations)
- 多表/文件插入(Multi Table/File Inserts)
- 动态分区插入(Dynamic-Partition Insert)
- 插入到本地文件(Inserting into Local Files)
- 抽样(Sampling)
- Union All
- 数组操作(Array Operations)
- Map(关联数组)操作(Map(Associative Arrays)Operations)
- 定制Map/Reduce脚本(Custom Map/Reduce Scripts)
- Co-Groups

###### 简单插入和查询
先新建了一个employee表。

	hive> create table employee(id int,name string,age int) partitioned by(sex string) row format delimited fields terminated by ',';
	OK
	Time taken: 1.885 seconds
	hive>
	
接下来在表中插入数据

	hive> insert into employee values(1,'zhangsan',20,'man');
	Query ID = g2_20181218105133_1044a249-7e23-43b2-9ceb-85a4be57b756
	Total jobs = 3
	Launching Job 1 out of 3
	Number of reduce tasks not specified. Estimated from input data size: 1
	In order to change the average load for a reducer (in bytes):
	  set hive.exec.reducers.bytes.per.reducer=<number>
	In order to limit the maximum number of reducers:
	  set hive.exec.reducers.max=<number>
	In order to set a constant number of reducers:
	  set mapreduce.job.reduces=<number>
	Starting Job = job_1545100822562_0001, Tracking URL = http://G2deMacBook-Air-9.local:8088/proxy/application_1545100822562_0001/
	Kill Command = /Users/g2/hadoop/hadoop-3.1.1/bin/mapred job  -kill job_1545100822562_0001
	Hadoop job information for Stage-1: number of mappers: 1; number of reducers: 1
	2018-12-18 10:51:53,859 Stage-1 map = 0%,  reduce = 0%
	2018-12-18 10:52:02,248 Stage-1 map = 100%,  reduce = 0%
	2018-12-18 10:52:09,539 Stage-1 map = 100%,  reduce = 100%
	Ended Job = job_1545100822562_0001
	Stage-4 is selected by condition resolver.
	Stage-3 is filtered out by condition resolver.
	Stage-5 is filtered out by condition resolver.
	Moving data to directory hdfs://localhost:9000/user/hive/warehouse/employee/.hive-staging_hive_2018-12-18_10-51-33_402_3085415550512544150-1/-ext-10000
	Loading data to table default.employee partition (sex=null)
	
	
		 Time taken to load dynamic partitions: 0.208 seconds
		 Time taken for adding to write entity : 0.002 seconds
	MapReduce Jobs Launched:
	Stage-Stage-1: Map: 1  Reduce: 1   HDFS Read: 17620 HDFS Write: 293 SUCCESS
	Total MapReduce CPU Time Spent: 0 msec
	OK
	Time taken: 39.092 seconds
	hive>

执行了很长一段代码。

之后就可以查询表中的数据：
	
	hive> select * from employee;
	OK
	1	zhangsan	20	man
	Time taken: 0.473 seconds, Fetched: 1 row(s)
	hive>
	
*PS：插入数据时必须填写上所有的字段*

还可以使用insert into ... select ... from 的格式进行插入。

下面先创建第二个表 employee2.

	hive> create table employee2(id int,name string,age int) partitioned by(sex string) row format delimited fields terminated by ',';
	OK
	Time taken: 0.257 seconds
	hive>
	
接下来向employee2中插入employee的数据。

	insert into employee2 select id,name,age,sex from employee;
	
执行成功之后就可以查询employe2的数据。

	hive> select * from employee2;
	OK
	1	zhangsan	20	man
	Time taken: 0.2 seconds, Fetched: 1 row(s)
	hive>
	
*PS：hive的插入比较慢，我们从插入等待过程中打印的命令可以看出，hive的sql会转为map reduce后在hdfs中执行*


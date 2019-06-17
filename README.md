# bigdata(整理项目中....)
 先展示一下作品部分截图以及代码



├─douyu-spark   --基于sparkstreaming+kafka的斗鱼直播热度分析的计算支持

├─douyu-springboot -- 后台springboot 数据及业务交互

├─douyu-vue  --前端:vue+echart  数据可视化+页面交互





## 1. 基于hive的数据仓库(迷你版)

 ### (1)迷你数据仓库目前功能规划



![迷你数据仓库设计图](https://github.com/xzyterry/bigdata/blob/master/doc/image/迷你数据仓库设计图.png)

 ###  (2) 技术栈

1> 集群:

- HadoopHA 

- Hive

- Spark(后面会将Hive引擎从MR换成Spark)

- Azkaban 任务调度

- Kafka(当前仅用于斗鱼直播热度分析) 

- **DataX 阿里开源异构数据源同步框架**

  

目前部署架构,部分例如HBase,ES等,目前还没有用到,先部署着



|           | bdp-dc-002      | bdp-dc-003      | bdp-dc-004  |
| --------- | --------------- | --------------- | ----------- |
| Zookeeper | Zookeeper       | Zookeeper       | Zookeeper   |
| HDFS      | NameNode        | NameNode        |             |
|           | DataNode        | DataNode        | DataNode    |
| YARN      | ResourceManager | ResourceManager |             |
|           | NodeManager     | NodeManager     | NodeManager |
| HIVE      | Hive            |                 |             |
| Kafka     | Kafka           | Kafka           | Kafka       |
| Spark     | Master          | Slaver          | Slaver      |
| Hbase     | Master          | Slaver          | Slaver      |
| Mysql     |                 | Mysql           |             |
| Hue       | Hue             |                 |             |



2>后台(Resful)

- springboot

- Hibernate(Mysql) 数据持久化

- Quartz 定时调度框架

- JSSH Java远程SSH连接服务器,并执行shell脚本

  

3>前端

- VUE 单页面开发框架 
- Element-ui 饿了么前端UI
- V-chart echart 用于图表可视化





 ###  (3) 作品截图

- 数据源

![p2](https://github.com/xzyterry/bigdata/blob/master/doc/image/p2.png)

- 数据同步

  ![p2](https://github.com/xzyterry/bigdata/blob/master/doc/image/p3.png)

- 数据建模

![p2](https://github.com/xzyterry/bigdata/blob/master/doc/image/p4.png)

- 图表可视化

![p2](https://github.com/xzyterry/bigdata/blob/master/doc/image/p6.png)







## 2. 基于sparkstreaming+kafka的斗鱼直播热评分析 



先上个图,再慢慢整理吧

![p2](https://github.com/xzyterry/bigdata/blob/master/doc/image/p5.png)


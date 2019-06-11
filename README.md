# bigdata
目前正在整理项目中.... 先展示一下作品部分截图以及代码



├─douyu-spark   --基于sparkstreaming+kafka的斗鱼直播热评分析的计算支持

├─douyu-springboot -- 后台springboot 数据及业务交互

├─douyu-vue  --前端:vue+echart  数据可视化+页面交互





## 1. 基于hive的数据仓库(迷你版)(vue+springboot)

 ### (1)迷你数据仓库目前功能规划



![迷你数据仓库设计图](https://github.com/xzyterry/bigdata/blob/master/doc/image/迷你数据仓库设计图.png)

 ###  (2) 代码结构



├─main
│  ├─java
│  │  └─com
│  │      └─jawnho
│  │          └─douyuspringboot
│  │              ├─common
│  │              ├─config
│  │              │  └─datax
│  │              │      └─Mysql2Hive
│  │              ├─Conn
│  │              ├─controller
│  │              ├─dao
│  │              ├─entity
│  │              │  ├─dto
│  │              │  ├─model
│  │              │  ├─po
│  │              │  └─vo
│  │              ├─hive
│  │              ├─response
│  │              │  └─exception
│  │              │      └─handler
│  │              ├─schedule
│  │              ├─service
│  │              │  └─Impl
│  │              ├─spark
│  │              ├─ssh
│  │              └─util
│  └─resources
└─test
    └─java
        └─com
            └─jawnho
                └─douyuspringboot



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


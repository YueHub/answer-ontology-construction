
## 介绍
> answer-ontology-construction 是 Answer 系统中的本体半自动构建模块
从百度百科中爬取数据建立本体知识库，构建包括拟人（真实人物、虚拟人物、动物等）、电影、音乐、动画、漫画、地区、院校、公司
八大一级类，剩下的数据归到"其它"这一类中．

## 调试开发
* 在本地磁盘中创建一个目录用做放置项目所需的各类文件的根目录，例如在 Linux 的 /opt 下创建 Test.
* 将 data 目录中的 AnswerDict、Images_Test、Ontologies 复制到第一步创建的根目录中 
* 在ontology-construction.properties中进行本体保存路径等相关修改，相关设置如下： 
```
\# 本配置文件中的路径的根目录，根目录+其他路径=绝对路径
rootPath=/opt/Test/

\# PIZZA_NS 可不变
pizzaNs=http://www.semanticweb.org/narutoku/ontologies/2016/3/my-ontology#

\# 本体文件保存路径 必须将初始 Answer_Ontology_Origin.owl 文件复制到指定路径
ontologyPath=Ontologies/Answer_Ontology_Origin.owl

\# 实体词典保存路径，程序可自动创建
individualDictPath=AnswerDict/Answer_Dict_Test.txt

\# 图片保存地址
picSavePath=Images_Test

\# 要抓取的页面数量
pageNum=20
```

## 运行
cn.lcy.ontologyconstruction.crawler 下的 OntologyConstructionLauncher 为程序入口

## 原理

<img src="https://github.com/YueHub/answer-ontology-construction/blob/master/docs/本体构建模块框架图.png" width="520px">

[简书](https://www.jianshu.com/p/97445e45c0ae)

[博客](http://yuehub.gitee.io/categories/项目/Answer-语义搜索/)

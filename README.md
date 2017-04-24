## 介绍
>answer-ontology-construction是Answer系统中的本体半自动构建模块;从百度百科中爬取数据建立本体知识库，构建包括拟人（真实人物、虚拟人物、动物等）、电影、音乐、动画、漫画、地区、院校、公司
八大一级类，剩下的数据归到其它这一类中．

## 使用
首先需要将项目docs下的Answer_Ontology_Origin.owl文件拷贝到本地磁盘中，并在ontology-construction.properties中设置本地路径．
### 配置文件
在ontology-construction.properties中进行本体保存路径等相关修改，相关设置如下：
```
\# 本配置文件中的路径的根目录，根目录+其他路径=绝对路径
rootPath=/opt/Test/
\# PIZZA_NS 可不变
pizzaNs=http://www.semanticweb.org/narutoku/ontologies/2016/3/my-ontology#
\# 本体文件保存路径 必须将初始Answer_Ontology_Origin.owl文件复制到指定路径
ontologyPath=Ontologies/Answer_Ontology_Origin.owl
\# 实体词典保存路径 程序可自动创建
individualDictPath=AnswerDict/Answer_Dict_Test.txt
\# 图片保存地址
picSavePath=Images_Test
```
### 运行

## 原理
![image](https://github.com/YueHub/answer-ontology-construction/blob/master/docs/本体构建模块框架图.png)
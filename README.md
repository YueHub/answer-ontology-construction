## 介绍
>answer-ontology-construction是Answer系统中的本体半自动构建模块;从百度百科中爬取数据建立本体知识库，构建包括拟人（真实人物、虚拟人物、动物等）、电影、音乐、动画、漫画、地区、院校、公司
八大一级类，剩下的数据归到其它这一类中．

## 使用
* 在本地磁盘中创建一个目录用做放置项目所需的各类文件的根目录.
* 在根目录下创建Ontologies目录，将项目docs下的Answer_Ontology_Origin.owl文件，拷贝到Ontologies.
* 创建AnswerDict目录，并在该目录下新建一个空白文件Answer_Dict_Test.txt.
* 将docs下的Answer_Test目录拷贝到根目录下.

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

\# 要抓取的页面数量
pageNum=20
```
### 运行
cn.lcy.ontologyconstruction.crawler下的OntologyConstructionLauncher为程序入口
## 原理
![image](https://github.com/YueHub/answer-ontology-construction/blob/master/docs/本体构建模块框架图.png)
待续...
package cn.lcy.ontologyconstruction.crawler;

import us.codecraft.webmagic.Spider;

public class OntologyConstructionLauncher {

    public static Long pageCount = 0L;

    public static void main(String args[]) {
        // 创建自定义 PageProcessor
        OntologyConstructionPageProcessor constructionPageProcessor = new OntologyConstructionPageProcessor();
        // 创建自定义 Pipeline
        OntologyConstructionPipeline constructionPipeline = new OntologyConstructionPipeline();
        // 启动爬虫并做相关设置
        Spider.create(constructionPageProcessor)
                // 周星驰：https://baike.baidu.com/item/周星驰
                // 实体: https://baike.baidu.com/item/实体/同名实体编号.htm
                .addUrl("https://baike.baidu.com/view/5081.htm")
                .addPipeline(constructionPipeline)
                //开启 1 个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }

}

package cn.lcy.ontologyconstruction.crawler;

import java.util.List;

import cn.lcy.ontologyconstruction.config.Config;
import cn.lcy.ontologyconstruction.enums.OntologyClassEnum;
import cn.lcy.ontologyconstruction.model.BaikePage;
import cn.lcy.ontologyconstruction.service.ConstructionServiceI;
import cn.lcy.ontologyconstruction.util.LemmaClassify;
import cn.lcy.ontologyconstruction.util.StringFilter;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class OntologyConstructionPipeline implements Pipeline {
	
	/*private ConstructionDAOI constructionDAO = new ConstructionDAOImpl(); */
	
	@Override
	public void process(ResultItems resultItems, Task task) {
		// 词条的URL
		String url = resultItems.getRequest().getUrl();
		/*String urlFilter = StringFilter.urlFilter(url);*/
		
		// 词条的图片地址
		String picSrc = resultItems.get("picSrc");
		
		// 内容一：词条标题
		String lemmaTitle = resultItems.get("lemmaTitle");
		String lemmaTitleFilter = StringFilter.nonBreakingSpaceFilter(lemmaTitle);
		// 内容二：词条描述
		String lemmaSummary = resultItems.get("lemmaSummary");
		
		// 内容三：同名实体和歧义解释
		List<String> polysemants =  resultItems.get("polysemants");
		String polysemantExplain =resultItems.get("polysemantExplain");
	
		// 内容四：所有人物关系和关系值
		List<String> relationNames = resultItems.get("relationNames");
		List<String> relationValues = resultItems.get("relationValues");
		List<String> relationUrls = resultItems.get("relationUrls");
		// URL过滤 过滤带哦URL中的%
		/*List<String> relationUrlsFilter = StringFilter.urlsFilter(relationUrls);*/
		
		// 内容五：所有属性名和属性值
		List<String> parameterNames = resultItems.get("parameterNames");
		List<String> parameterValues = resultItems.get("parameterValues");
		List<String> parameterHasUrlValues = resultItems.get("parameterHasUrlValues");
    	List<String> parameterHasUrl = resultItems.get("parameterHasUrl");
		// 过滤属性名和属性值 去除160不间断空格等特殊字符
		List<String> parameterNamesFilter = StringFilter.nonBreakingSpaceFilter(parameterNames);
		List<String> parameterValuesFilter = StringFilter.nonBreakingSpaceFilter(parameterValues);
    	List<String> parameterNamesFilterAgain = StringFilter.parameterNameFilter(parameterNamesFilter);
		
		BaikePage baikePage = new BaikePage();
		baikePage.setUrl(url);
		/*baikePage.setUrl(urlFilter);*/
		baikePage.setPicSrc(picSrc);
		baikePage.setLemmaTitle(lemmaTitleFilter);
		baikePage.setLemmaSummary(lemmaSummary);
		baikePage.setPolysemants(polysemants);
		baikePage.setPolysemantExplain(polysemantExplain);
		baikePage.setRelationNames(relationNames);
		baikePage.setRelationValues(relationValues);
		baikePage.setRelationUrls(relationUrls);
		/*baikePage.setRelationUrls(relationUrlsFilter);*/
		baikePage.setParameterNames(parameterNamesFilterAgain);
		baikePage.setParameterValues(parameterValuesFilter);
		baikePage.setParameterHasUrlValues(parameterHasUrlValues);
		baikePage.setParameterHasUrl(parameterHasUrl);
		// TODO 输出测试
//		System.out.print("获取网页: " + resultItems.getRequest().getUrl());
//		System.out.println("抓取数据：" + baikePage.toString());
		
		// 根据属性特征进行分类 返回的是类型枚举
		OntologyClassEnum ontologyClassEnum = LemmaClassify.classify(parameterNamesFilter);
		// 根据分类结果获取对应类型的本体构建对象
		ConstructionServiceI constructionService = LemmaClassify.classify(ontologyClassEnum);
		
		try {
			// 开始构建本体
			constructionService.construction(baikePage);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		++OntologyConstructionLauncher.pageCount;
		
		if(OntologyConstructionLauncher.pageCount % 10 == 0) {
    	   System.out.println("已经处理:" + OntologyConstructionLauncher.pageCount + "个页面");
		}
       
		if(OntologyConstructionLauncher.pageCount == Config.pageNum) {
    	   System.out.println("处理完成:总处理" + OntologyConstructionLauncher.pageCount + "个页面");
    	   System.exit(0);
		}
	}
}

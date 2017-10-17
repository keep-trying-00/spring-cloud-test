package demo.com.test.controller;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Before;
import org.junit.Test;

import demo.com.test.vo.TUser;

public class SolrCloudController {

	private String zkHost = "localhost:2281,localhost:2282,localhost:2283";
	private CloudSolrClient solrClient = null;
	@Before
	public void init() {
		// 创建一个和solr集群的连接
		// 参数就是zookeeper的地址列表，使用逗号分隔
		 solrClient = new CloudSolrClient(zkHost);
	}
	@Test
    public  <T> void testQueryAll() {
        SolrQuery params = new SolrQuery();
        // 查询关键词，*:*代表所有属性、所有值，即所有index
        params.set("q", "*:*");
        // 分页，start=0就是从0开始，rows=5当前返回5条记录，第二页就是变化start这个值为5就可以了。
        //params.set("start", 0);
        //params.set("rows", "5");
        // 排序，如果按照id排序，那么 写为： id desc(or asc)
        params.set("sort", "id asc");
        QueryResponse response = null;
        try {
        	solrClient.setDefaultCollection("collection2");
            response = solrClient.query(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (response != null) {
            System.out.println("Search Results: ");
            SolrDocumentList listx = response.getResults();
            
           @SuppressWarnings("unchecked")
           List<T> list =  (List<T>) toBeanList(listx, TUser.class);
            for (T user : list) {
				System.out.println(user.toString());
			}
        }

    }
	
	@Test
	public void testAddDocument() throws Exception {
		// 设置默认的collection
		solrClient.setDefaultCollection("collection2");
		// 创建一个文档对象
		SolrInputDocument document = new SolrInputDocument();
		// 向文档中添加域
		document.addField("id", new Random().nextInt());
		document.addField("name", "zhangsan");
		document.addField("age", 56);
		document.addField("city", "hlj");
		document.addField("sex", "man");
		// 把文档添加到索引库
		solrClient.add(document);
		// 提交
		solrClient.commit();
	}
	
	public static  Object toBean( SolrDocument record , Class clazz){
        
	       Object o = null;
	       try {
	           o = clazz.newInstance();
	       } catch (InstantiationException e1) {
	           e1.printStackTrace();
	       } catch (IllegalAccessException e1) {
	           e1.printStackTrace();
	       }
	        Field[] fields =   clazz.getDeclaredFields();
	        for(Field field:fields){
	        	String fieldName = field.getName();
	            Object value = record.get(fieldName);
	            System.out.println("name:"+fieldName+",value:"+value);
	            try {
	               BeanUtils.setProperty(o, field.getName(), value);
	           } catch (IllegalAccessException e) {
	               e.printStackTrace();
	           } catch (InvocationTargetException e) {
	               e.printStackTrace();
	           }
	        }
	       return o;
	   }
	    
	   public static Object toBeanList(SolrDocumentList records, Class  clazz){
	       List  list = new ArrayList();
	       for(SolrDocument record : records){
	           list.add(toBean(record,clazz));
	       }
	       return list;
	   }

}

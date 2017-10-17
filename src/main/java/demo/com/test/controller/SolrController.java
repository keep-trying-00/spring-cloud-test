package demo.com.test.controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Before;
import org.junit.Test;

import demo.com.test.vo.TUser;

public class SolrController {
	
	private static final String URL = "http://localhost:8983/solr/mycore";
	private static HttpSolrClient solrClient = null;

	@Before
	public void init() {
		solrClient = new HttpSolrClient(URL);
		solrClient.setConnectionTimeout(3000);
	}
	 // 查询
	@Test
    public  <T> void testQueryAll() {
        SolrQuery params = new SolrQuery();
        // 查询关键词，*:*代表所有属性、所有值，即所有index
        params.set("q", "id:96264313");
        // 分页，start=0就是从0开始，rows=5当前返回5条记录，第二页就是变化start这个值为5就可以了。
        //params.set("start", 0);
        //params.set("rows", "5");
        // 排序，如果按照id排序，那么 写为： id desc(or asc)
        params.set("sort", "id asc");
        QueryResponse response = null;
        try {
            response = solrClient.query(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (response != null) {
            System.out.println("Search Results: ");
            TUser xx = (TUser) response.getBeans(TUser.class);
            
           /*@SuppressWarnings("unchecked")
           List<T> list =  (List<T>) toBeanList(listx, TUser.class);
            for (T user : list) {
				System.out.println(user.toString());
			}*/
        }

    }
    
	public void addData() {
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", "xxoo");
		doc.addField("name", "xxoo");
		doc.addField("age", "xxoo");
		doc.addField("text", "xxoo");
		try {
			solrClient.add(doc);
			solrClient.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	public void delete() {
		try {
			solrClient.deleteById("92b7fa55-2e73-41ad-bd2d-bf3500894d7a");
			//删除姓名为张三的文档
			solrClient.deleteByQuery("name:张三");
			//删除所有文档，不建议使用
			solrClient.deleteByQuery("*:*");
			//solrClient.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void addBeen(){
		
		TUser user = new TUser();
		//user.setAge(new Random().nextInt(500));
		//user.setName("张三"+new Random().nextInt(500));
		try {
			UpdateResponse response = solrClient.addBean(user);
			solrClient.commit();
	        System.err.println(response.getStatus());// 响应状态
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
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

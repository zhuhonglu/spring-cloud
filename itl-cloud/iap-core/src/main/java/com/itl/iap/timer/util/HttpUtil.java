package com.itl.iap.timer.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;


public class HttpUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);  
	
	/**
	 * get请求获得json
	 * 
	 * @param URL
	 * @return
	 */
	public static String javahttpGet(String URL) {

		try {
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);// 设置连接超时时间为2秒（连接初始化时间）
			// final Date date = new Date();
			if (URL.indexOf('?') > 0) {
				URL += "&";
			} else {
				URL += "?";
			}
			GetMethod postMethod = new GetMethod(URL);
			client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
			// 执行并返回状态
			int status = client.executeMethod(postMethod);
			String getStr = "";
			if (status == HttpStatus.SC_OK) {
				getStr = postMethod.getResponseBodyAsString();
			} else {
				 logger.info(postMethod.getResponseBodyAsString());
			}
			client.getHttpConnectionManager().closeIdleConnections(1);
			return getStr;
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "";
		}
		finally {
		}
	}
	
	/**
	 * get请求获得json
	 * 
	 * @param URL
	 * @return
	 */
	public static String javahttpGet_old(String URL) {

		try {
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);// 设置连接超时时间为2秒（连接初始化时间）
			GetMethod getMethod = new GetMethod(URL);
			client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
			// 执行并返回状态
			int status = client.executeMethod(getMethod);
			String getStr = "";
			if (status == HttpStatus.SC_OK) {
				getStr = getMethod.getResponseBodyAsString();
			} else {
				logger.debug(getMethod.getResponseBodyAsString());
			}
			client.getHttpConnectionManager().closeIdleConnections(1);
			return getStr;
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "";
		}
		finally {
		}
	}
	
	/**
	 * @param paramName 参数名称
	 * @param xmlInfo发送的json数据
	 * @param URL访问地址
	 * @return
	 */
	public static String javahttpPost(String paramName, String xmlInfo, String URL) {

		try {
			/** post方式 */
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);// 设置连接超时时间为2秒（连接初始化时间）
			PostMethod postMethod = new PostMethod(URL);
			//postMethod.setRequestBody(xmlInfo);			// 参数设置
			//postMethod.setParameter(paramName, xmlInfo);
			postMethod.addRequestHeader("Content-type","application/json; charset=utf-8");  
			postMethod.addRequestHeader("Accept", "application/json");  
			//postMethod.setRequestEntity(new RequestEntity(xmlInfo, Charset.forName("UTF-8"))); 
			RequestEntity requestEntity = new StringRequestEntity(xmlInfo);
			postMethod.setRequestEntity(requestEntity);
			client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
			// 执行并返回状态
			int status = client.executeMethod(postMethod);
			String getStr = "";
			if (status == HttpStatus.SC_OK) {
				getStr = postMethod.getResponseBodyAsString();
			} else {
				logger.debug(postMethod.getResponseBodyAsString());
			}
			client.getHttpConnectionManager().closeIdleConnections(1);
			return getStr;
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "";
		}
		finally {
		}
	}
	
	/**
	 * @param xmlInfo发送的json数据
	 * @param URL访问地址
	 * @return
	 */
	public static String javahttpPost_old(String xmlInfo, String URL) {

		try {
			/** post方式 */
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);// 设置连接超时时间为2秒（连接初始化时间）
			PostMethod postMethod = new PostMethod(URL);
			// 参数设置
			postMethod.setParameter("jsonString", xmlInfo);
			client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
			// 执行并返回状态
			int status = client.executeMethod(postMethod);
			String getStr = "";
			if (status == HttpStatus.SC_OK) {
				getStr = postMethod.getResponseBodyAsString();
			} else {
				logger.debug(postMethod.getResponseBodyAsString());
			}
			client.getHttpConnectionManager().closeIdleConnections(1);
			return getStr;
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "";
		}
		finally {
		}
	}
	
	/**
	 * 实现多个参数传递，进行远程访问并获取返回值
	 * 
	 * @param mapInfo 调用时的参数集合map
	 * @param URL访问地址
	 * @param outTime 超时时间 毫秒
	 * @return
	 */
	public static String javahttpPost(Map<String, Object> mapInfo, String URL,int outTime) {

		try {
			String getStr = "";
			/** post方式 */
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(outTime);// 设置连接超时时间为2秒（连接初始化时间）
			PostMethod postMethod = new PostMethod(URL);
			// 参数设置
			// 遍历map
			if (mapInfo != null) {
				Iterator<String> iter = mapInfo.keySet().iterator();
				while (iter.hasNext()) {
					String key = iter.next();
					Object value = mapInfo.get(key);
					postMethod.setParameter(key, value + "");
				}
				// 执行postMethod
				client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
				// 执行并返回状
				int status = client.executeMethod(postMethod);
				if (status == HttpStatus.SC_OK) {					
					getStr = postMethod.getResponseBodyAsString();
				}
			}
			client.getHttpConnectionManager().closeIdleConnections(1);
			return getStr;
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "";
		}
		finally {
		}
	}
	
	public static String javahttpPost(Map<String, Object> mapInfo, String URL){
		
		return javahttpPost(mapInfo,URL,10000);
	}
	
	/**
	 * 实现多个参数传递，进行远程访问并获取返回值
	 * 
	 * @updateDate 2015-10-9 上午11:27:52
	 * @updater hxo
	 * @updateRemark 闲置旧方法，新请求方法新增令牌
	 * @param mapInfo 调用时的参数集合map
	 * @param
	 * @return
	 */
	public static String javahttpPost_old(Map<String, Object> mapInfo, String URL) {

		try {
			String getStr = "";
			/** post方式 */
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(4000);// 设置连接超时时间为2秒（连接初始化时间）
			PostMethod postMethod = new PostMethod(URL);
			// 参数设置
			// 遍历map
			if (mapInfo != null) {
				Iterator<String> iter = mapInfo.keySet().iterator();
				while (iter.hasNext()) {
					String key = iter.next();
					Object value = mapInfo.get(key);
					postMethod.setParameter(key, value + "");
				}
				// 执行postMethod
				client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
				// 执行并返回状
				int status = client.executeMethod(postMethod);
				if (status == HttpStatus.SC_OK) {
					
					getStr = postMethod.getResponseBodyAsString();
				}
			}
			client.getHttpConnectionManager().closeIdleConnections(1);
			return getStr;
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "";
		}
		finally {
		}
	}
	
//	@SuppressWarnings({ "deprecation", "resource" })
//	public static void main(String args[]) throws Exception {	
//		Map map = new HashMap();
//		
//		String str1 = "JavaScript\\u7a0b\\u5e8f\\u8bbe\\u8ba1\\u57fa\\u7840\\u6559\\u7a0b(21\\u4e16";
//		
//		str1 = unicode2String(str1);
//		
//		System.out.println("str1 = " + str1);		
//		//String url = "http://211.140.7.179:8096/data-processing/";
//		String url = "http://127.0.0.1:8080/jd-businessapi-portal/";
//		//String url = "http://211.140.7.179:8096/jd-businessapi-portal/";
//		// 新增书
//		String method = "third.product.info.create";
//		// 新增上下架
//		//String method = "third.product.status.create";
//		// 库存
//		//String method = "third.product.stock.create";	
//		
//		String name= "中文字符我的天";
//		
//		name = string2Unicode(name);
//		
//		map.put("method", method);
//		// 新增书
//		String  paramJson = "{\"sku\":\"346\",\"name\":\"\\u7ba1\\u7406\\u4fe1\\u606f\\u7cfb\\u7edf(\\u5341\\u4e8c\\u4e94\\u804c\\u4e1a\\u6559\\u80b2**\\u89c4\\u5212\\u6559\\u6750)\\\",\"isbn\":\"1111-32233\","
//				+ "\"tagPrice\":\"400\",\"totalPrice\":\"300\",\"stocks\":\"230\","
//				+ "\"publishers\":\"testfbz\",\"publishTime\":\"2017-03-27\","
//				+ "\"remark\":\"bz\",\"originalPrice\":\"120\",\"preferentialPrice\":\"220\","
//				+ " \"defaultDiscount\":\"56\",\"keywords\":\"mywords\",\"paperBookWords\":\"1500\","
//				+ " \"barCode\":\"233\",\"binding\":\"333\",\"author\":\"\\u7f16\\u8005:\\u5f20\\u732e\\u82f1\\\\u51af\\u5fd7\\u6ce2\\u738b\\u4fca\\u5bc6\\u957f\\u6797\",\"editer\":\"efe\","
//				+ "\"transfer\":\"dfe\",\"proofreader\":\"jd\",\"sheet\":\"kb\",\"pages\":\"33\","
//				+ "\"batchNo\":\"45\",\"prStringgerTime\":\"2017-04-20\",\"prStringgerNo\":\"fe\","
//				+ "\"packNum\":\"34\",\"language\":\"zw\",\"papers\":\"34\",\"brand\":\"pp\",\"comments\":\"com\","
//				+ "\"contentDesc\":\"efef\",\"relatedProducts\":\"ffe\",\"editerDesc\":\"cd\", "
//				+ "\"catalogue\":\"fefef\",\"bookAbstract\":\"efefef\",\"authorDesc\":\"eef\",\"stringergerroduction\":\"fef\",\"image\":\"http://images.bookuu.com/book_t/37/35/28/3735283.jpg\"}";//
//		
//	//	String paramJson = "{\"sku\":\"3271048\",\"name\":\"\\u5916\\u79d1\\u5b66(\\u7b2c3\\u7248\\u9ad8\\u7b49\\u533b\\u5b66\\u9662\\u6821\\u975e\\u4e34\\u5e8a\\u533b\\u5b66\\u4e13\\u4e1a\\u6559\\u6750)\",\"author\":\"\\u7f16\\u8005:\\u6c64\\u6587\\u6d69\",\"isbn\":\"9787564157333\",\"publishers\":\"\\u4e1c\\u5357\\u5927\\u5b66\",\"sheet\":\"16\\u5f00      \",\"pages\":\"636\",\"publishTime\":\"2015-06-01\",\"batchNo\":\"3\",\"prStringgerTime\":\"2015-06-01\",\"prStringgerNo\":\"1\",\"stocks\":\"6\",\"tagPrice\":\"86\",\"originalPrice\":\"67.9\",\"barcode\":\"9787564157333\",\"editerDesc\":\"\",\"contentDesc\":\"    \\u6c64\\u6587\\u6d69\\u4e3b\\u7f16\\u7684\\u300a\\u5916\\u79d1\\u5b66\\uff08\\u7b2c3\\u7248\\uff09\\u300b\\u662f\\u4e3a\\u975e\\u4e34\\u5e8a\\n\\u533b\\u5b66\\u4e13\\u4e1a\\u533b\\u5b66\\u751f\\u7f16\\u5199\\u7684\\u4e00\\u672c\\u6559\\u79d1\\u4e66\\u3002\\u9002\\u7528\\u4e8e\\u9884\\u9632\\u533b\\u5b66\\n\\u3001\\u62a4\\u7406\\u3001\\u68c0\\u9a8c\\u3001\\u53e3\\u8154\\u548c\\u751f\\u7269\\u533b\\u5b66\\u5de5\\u7a0b\\u7b49\\u5916\\u79d1\\u5b66\\u5b66\\u65f6\\u8f83\\n\\u77ed\\u7684\\u4e13\\u4e1a\\u3002\\u7531\\u4e8e\\u5916\\u79d1\\u5b66\\u603b\\u8bba\\u90e8\\u5206\\u548c\\u666e\\u901a\\u5916\\u79d1\\u90e8\\u5206\\u662f\\u5b66\\n\\u597d\\u5916\\u79d1\\u5b66\\u7684\\u57fa\\u7840\\uff0c\\u56e0\\u6b64\\uff0c\\u672c\\u6559\\u6750\\u5728\\u7f16\\u5199\\u4e2d\\u5f3a\\u5316\\u4e86\\u5916\\u79d1\\n\\u5b66\\u603b\\u8bba\\u90e8\\u5206\\uff08\\u5916\\u4f24\\u3001\\u611f\\u67d3\\u548c\\u80bf\\u7624\\u7b49\\u7ae0\\u8282\\uff09\\u7684\\u7bc7\\u5e45\\uff0c\\u7565\\n\\u53bb\\u4e86\\u795e\\u7ecf\\u5916\\u79d1\\u3001\\u5fc3\\u80f8\\u5916\\u79d1\\u3001\\u6ccc\\u5c3f\\u5916\\u79d1\\u548c\\u9aa8\\u5916\\u79d1\\u7b49\\u7ae0\\u8282\\n\\u3002\\u529b\\u6c42\\u7a81\\u51fa\\u5916\\u79d1\\u5b66*\\u57fa\\u672c\\u7684\\u5185\\u5bb9\\uff0c\\u4ee5\\u4fbf\\u8fd9\\u4e9b\\u975e\\u4e34\\u5e8a\\u533b\\n\\u5b66\\u7c7b\\u4e13\\u4e1a\\u7684\\u533b\\u5b66\\u751f\\u80fd\\u5f88\\u597d\\u5730\\u628a\\u63e1\\u5916\\u79d1\\u5b66\\u7684\\u6838\\u5fc3\\u5185\\u5bb9\\uff0c\\n\\u4e3a\\u5916\\u79d1\\u5b66\\u7684\\u7ee7\\u7eed\\u5b66\\u4e60\\u6253\\u4e0b\\u826f\\u597d\\u7684\\u57fa\\u7840\\u3002\\u672c\\u4e66\\u7684\\u6b63\\u6587\\u6709\\n\\u5b8b\\u4f53\\u548c\\u6977\\u4f53\\u4e24\\u79cd\\u5b57\\u4f53\\uff0c\\u6977\\u4f53\\u662f\\u5f3a\\u8c03\\u7684\\u5185\\u5bb9\\uff0c\\u4fbf\\u4e8e\\u6559\\u5e08\\n\\u548c\\u5b66\\u751f\\u638c\\u63e1\\u91cd\\u70b9\\u548c\\u96be\\u70b9\\u3002\\n\",\"catalogue\":\"\\u4e0a\\u7bc7  \\u603b\\u8bba\\n  **\\u7ae0  \\u7eea\\u8bba\\n    **\\u8282  \\u73b0\\u4ee3\\u5916\\u79d1\\u5b66\\u7b80\\u53f2\\n    \\u7b2c\\u4e8c\\u8282  \\u5916\\u79d1\\u5b66\\u7684\\u8303\\u7574\\n    \\u7b2c\\u4e09\\u8282  \\u6211\\u56fd\\u5916\\u79d1\\u5b66\\u7684\\u53d1\\u5c55\\n    \\u7b2c\\u56db\\u8282  \\u5b66\\u597d\\u5916\\u79d1\\u5b66\\u7684\\u57fa\\u672c\\u8981\\u6c42\\n  \\u7b2c2\\u7ae0  \\u65e0\\u83cc\\u672f\\n    **\\u8282  \\u624b\\u672f\\u5668\\u68b0\\u3001\\u7269\\u54c1\\u3001\\u6577\\u6599\\u7684\\u706d\\u83cc\\u3001\\u6d88\\u6bd2\\u6cd5\\n    \\u7b2c\\u4e8c\\u8282  \\u624b\\u672f\\u4eba\\u5458\\u548c\\u75c5\\u4eba\\u624b\\u672f\\u533a\\u57df\\u7684\\u51c6\\u5907\\n    \\u7b2c\\u4e09\\u8282  \\u624b\\u672f\\u90e8\\u4f4d\\u611f\\u67d3\\u7684\\u9884\\u9632\\n    \\u9644\\uff1a\\u7f8e\\u56fd\\u624b\\u672f\\u90e8\\u4f4d\\u611f\\u67d3\\u9884\\u9632\\u6307\\u5357\\uff081999\\u5e74\\u7248\\uff09\\n  \\u7b2c3\\u7ae0  \\u5916\\u79d1\\u75c5\\u4eba\\u7684\\u4f53\\u6db2\\u5931\\u8c03\\n    **\\u8282  \\u6982\\u8ff0\\n    \\u7b2c\\u4e8c\\u8282  \\u4f53\\u6db2\\u4ee3\\u8c22\\u5931\\u8c03\\n    \\u7b2c\\u4e09\\u8282  \\u9178\\u78b1\\u4ee3\\u8c22\\u5931\\u8c03\\n    \\u7b2c\\u56db\\u8282  \\u4f53\\u6db2\\u5931\\u8c03\\u7684\\u4e34\\u5e8a\\u5904\\u7406\\n  \\u7b2c4\\u7ae0  \\u5916\\u79d1\\u6b62\\u8840\\u548c\\u8f93\\u8840\\n    **\\u8282  \\u6b62\\u8840\\u8fc7\\u7a0b\\n    \\u7b2c\\u4e8c\\u8282  \\u6b62\\u8840\\u529f\\u80fd\\u7684\\u672f\\u524d\\u4f30\\u8ba1\\n    \\u7b2c\\u4e09\\u8282  \\u51fa\\u8840\\u4e0d\\u6b62\\u7684\\u75be\\u75c5\\n    \\u7b2c\\u56db\\u8282  \\u672f\\u4e2d\\u51fa\\u8840\\n    \\u7b2c\\u4e94\\u8282  \\u8f93\\u8840\\u7684\\u9002\\u5e94\\u8bc1\\u3001\\u8f93\\u8840\\u6280\\u672f\\u548c\\u6ce8\\u610f\\u4e8b\\u9879\\n    \\u7b2c\\u516d\\u8282  \\u8f93\\u8840\\u53cd\\u5e94\\u548c\\u5e76\\u53d1\\u75c7\\n    \\u7b2c\\u4e03\\u8282  \\u81ea\\u8eab\\u8f93\\u8840\\n    \\u7b2c\\u516b\\u8282  \\u5168\\u8840\\u3001\\u8840\\u6db2\\u6210\\u5206\\u548c\\u8840\\u6d46\\u589e\\u91cf\\u5242\\n  \\u7b2c5\\u7ae0  \\u5916\\u79d1\\u4f11\\u514b\\n    **\\u8282  \\u6982\\u8bba\\n    \\u7b2c\\u4e8c\\u8282  \\u4f4e\\u8840\\u5bb9\\u91cf\\u6027\\u4f11\\u514b\\n    \\u7b2c\\u4e09\\u8282  \\u611f\\u67d3\\u6027\\u4f11\\u514b\\n  \\u7b2c6\\u7ae0  \\u5916\\u79d1\\u91cd\\u75c7\\u533b\\u7597\\n    **\\u8282  \\u547c\\u5438\\u7cfb\\u7edf\\n    \\u7b2c\\u4e8c\\u8282  \\u6d88\\u5316\\u7cfb\\u7edf\\n    \\u7b2c\\u4e09\\u8282  \\u6025\\u6027\\u80be\\u8870\\u7aed\\n    \\u7b2c\\u56db\\u8282  \\u5185\\u5206\\u6ccc\\u7cfb\\u7edf\\n    \\u7b2c\\u4e94\\u8282  \\u591a\\u5668\\u5b98\\u529f\\u80fd\\u969c\\u788d\\n  \\u7b2c7\\u7ae0  \\u5fc3\\u80ba\\u8111\\u590d\\u82cf\\n    **\\u8282  \\u6210\\u4eba\\u57fa\\u672c\\u751f\\u547d\\u652f\\u6301\\n    \\u7b2c\\u4e8c\\u8282  \\u5c0f\\u513f\\u57fa\\u672c\\u751f\\u547d\\u652f\\u6301\\n    \\u7b2c\\u4e09\\u8282  \\u7279\\u6b8a\\u60c5\\u51b5\\u4e0b\\u7684\\u57fa\\u672c\\u751f\\u547d\\u652f\\u6301\\n    \\u7b2c\\u56db\\u8282  \\u8111\\u6b7b\\u4ea1\\u4e34\\u5e8a\\u8bca\\u65ad\\n  \\u7b2c8\\u7ae0  \\u56f4\\u624b\\u672f\\u671f\\u5904\\u7406\\n    **\\u8282  \\u672f\\u524d\\u51c6\\u5907\\n    \\u7b2c\\u4e8c\\u8282  \\u672f\\u540e\\u5904\\u7406\\n    \\u7b2c\\u4e09\\u8282  \\u672f\\u540e\\u5e76\\u53d1\\u75c7\\u7684\\u9632\\u6cbb\\n  \\u7b2c9\\u7ae0  \\u5916\\u79d1\\u75c5\\u4eba\\u7684\\u8425\\u517b\\u652f\\u6301\\n    **\\u8282  \\u8425\\u517b\\u7269\\u8d28\\u53ca\\u5176\\u4ee3\\u8c22\\n    \\u7b2c\\u4e8c\\u8282  \\u8425\\u517b\\u72b6\\u6001\\u7684\\u8bc4\\u4f30\\n    \\u7b2c\\u4e09\\u8282  \\u8425\\u517b\\u652f\\u6301\\u6cbb\\u7597\\n  **0\\u7ae0  \\u5916\\u79d1\\u611f\\u67d3\\n    **\\u8282  \\u6982\\u8bba\\n    \\u7b2c\\u4e8c\\u8282  \\u6d45\\u90e8\\u7ec4\\u7ec7\\u7684\\u5316\\u8113\\u6027\\u611f\\u67d3\\n    \\u7b2c\\u4e09\\u8282  \\u624b\\u90e8\\u6025\\u6027\\u5316\\u8113\\u6027\\u611f\\u67d3\\n    \\u7b2c\\u56db\\u8282  \\u5168\\u8eab\\u6027\\u5916\\u79d1\\u611f\\u67d3\\n    \\u7b2c\\u4e94\\u8282  \\u6709\\u82bd\\u5b62\\u538c\\u6c27\\u83cc\\u611f\\u67d3\\n    \\u7b2c\\u516d\\u8282  \\u5916\\u79d1\\u5e94\\u7528\\u6297\\u83cc\\u836f\\u7684\\u539f\\u5219\\n  **1\\u7ae0  \\u521b\\u53e3\\u548c\\u521b\\u53e3\\u6108\\u5408\\n    **\\u8282  \\u521b\\u53e3\\u6108\\u5408\\n    \\u7b2c\\u4e8c\\u8282  \\u521b\\u53e3\\u6108\\u5408\\u7684\\u5206\\u7c7b\\n    \\u7b2c\\u4e09\\u8282  \\u521b\\u53e3\\u7684\\u5904\\u7406\\n    \\u7b2c\\u56db\\u8282  \\u5e38\\u7528\\u6577\\u6599\\u53ca\\u5176\\u9009\\u62e9\\n  **2\\u7ae0  \\u521b\\u4f24\\n    **\\u8282  \\u6982\\u8bba\\n    \\u7b2c\\u4e8c\\u8282  \\u5c0f\\u513f\\u521b\\u4f24\\n    \\u7b2c\\u4e09\\u8282  \\u5b55\\u5987\\u521b\\u4f24\\n    \\u7b2c\\u56db\\u8282  \\u635f\\u5bb3\\u63a7\\u5236\\u5916\\u79d1\\n    \\u7b2c\\u4e94\\u8282  \\u6d45\\u90e8\\u8f6f\\u7ec4\\u7ec7\\u521b\\u4f24\\n    \\u7b2c\\u516d\\u8282  \\u9885\\u8111\\u5916\\u4f24\\n    \\u7b2c\\u4e03\\u8282  \\u9888\\u90e8\\u5916\\u4f24\\n    \\u7b2c\\u516b\\u8282  \\u80f8\\u90e8\\u5916\\u4f24\\n    \\u7b2c\\u4e5d\\u8282  \\u8179\\u90e8\\u5916\\u4f24\\n    \\u7b2c\\u5341\\u8282  \\u6ccc\\u5c3f\\u751f\\u6b96\\u7cfb\\u635f\\u4f24\\n    \\u7b2c\\u5341\\u4e00\\u8282  \\u521b\\u4f24\\u9aa8\\u79d1\\u4e0e\\u9aa8\\u76c6\\u5916\\u4f24\\n    \\u7b2c\\u5341\\u4e8c\\u8282  \\u810a\\u67f1\\u548c\\u810a\\u9ad3\\u5916\\u4f24\\n    \\u7b2c\\u5341\\u4e09\\u8282  \\u56db\\u80a2\\u4f24\\n    \\u9644\\uff1a\\u8179\\u8154\\u5ba4\\u7efc\\u5408\\u5f81\\n  **3\\u7ae0  \\u70e7\\u4f24\\u548c\\u51b7\\u4f24\\n    **\\u8282  \\u70ed\\u70e7\\u4f24\\n    \\u7b2c\\u4e8c\\u8282  \\u5438\\u4eba\\u6027\\u70e7\\u4f24\\n    \\u7b2c\\u4e09\\u8282  \\u5316\\u5b66\\u70e7\\u4f24\\n    \\u7b2c\\u56db\\u8282  \\u7535\\u70e7\\u4f24\\n    \\u7b2c\\u4e94\\u8282  \\u51b7\\u4f24\\n    \\u7b2c\\u516d\\u8282  \\u690d\\u76ae\\u672f\\n  **4\\u7ae0  \\u80bf\\u7624\\n    **\\u8282  \\u6982\\u8bba\\n    \\u7b2c\\u4e8c\\u8282  \\u80bf\\u7624\\u7684\\u7597\\u6548\\u8bc4\\u4ef7\\n    \\u7b2c\\u4e09\\u8282  \\u5e38\\u89c1\\u4f53\\u8868\\u80bf\\u7624\\u4e0e\\u80bf\\u5757\\n    \\u7b2c\\u56db\\u8282  \\u54e8\\u5175\\u6dcb\\u5df4\\u7ed3\\u6d3b\\u68c0\\uff08SLNB\\uff09\\n  **5\\u7ae0  \\u5668\\u5b98\\u79fb\\u690d\\n    **\\u8282  \\u6982\\u8bba\\n    \\u7b2c\\u4e8c\\u8282  \\u79fb\\u690d\\u514d\\u75ab\\n    \\u7b2c\\u4e09\\u8282  \\u540c\\u79cd\\u5f02\\u4f53\\u79fb\\u690d\\u6392\\u65a5\\n    \\u7b2c\\u56db\\u8282  \\u7ec4\\u7ec7\\u914d\\u578b\\u8bd5\\u9a8c\\u548cHLA\\u9884\\u81f4\\u654f\\u72b6\\u6001\\u7684\\u7b5b\\u67e5\\n    \\u7b2c\\u4e94\\u8282  \\u514d\\u75ab\\u6291\\u5236\\u6cbb\\u7597\\n    \\u7b2c\\u516d\\u8282  \\u514d\\u75ab\\u6291\\u5236\\u7684\\u5e76\\u53d1\\u75c7\\n    \\u7b2c\\u4e03\\u8282  \\u5668\\u5b98\\u6350\\u8d60\\n    \\u7b2c\\u516b\\u8282  \\u810f\\u5668\\u79fb\\u690d\\n  \\u9644\\u5f55\\u4e00  \\u6a21\\u62df\\u9009\\u62e9\\u9898\\uff08\\u603b\\u8bba\\u90e8\\u5206\\uff09\\n  \\u9644\\u5f55\\u4e8c  \\u6a21\\u62df\\u9009\\u62e9\\u9898\\u7b54\\u6848\\n\\u4e0b\\u7bc7  \\u5404\\u8bba\\n  **6\\u7ae0  \\u7532\\u72b6\\u817a\\u75be\\u75c5\\n    **\\u8282  \\u80da\\u80ce\\u3001\\u89e3\\u5256\\u548c\\u751f\\u7406\\u6982\\u8981\\n    \\u7b2c\\u4e8c\\u8282  \\u5355\\u53d1\\u6027\\u7532\\u72b6\\u817a\\u7ed3\\u8282\\u7684\\u8bca\\u65ad\\u548c\\u5904\\u7406\\u539f\\u5219\\n    \\u7b2c\\u4e09\\u8282  \\u7532\\u72b6\\u817a\\u80bf\\n    \\u7b2c\\u56db\\u8282  \\u7532\\u72b6\\u817a\\u80bf\\u529f\\u80fd\\u4ea2\\u8fdb\\u75c7\\u7684\\u5916\\u79d1\\u6cbb\\u7597\\n    \\u7b2c\\u4e94\\u8282  \\u7532\\u72b6\\u817a\\u708e\\n    \\u7b2c\\u516d\\u8282  \\u7532\\u72b6\\u817a\\u817a\\u7624\\n    \\u7b2c\\u4e03\\u8282  \\u7532\\u72b6\\u817a\\u764c\\n    \\u7b2c\\u516b\\u8282  \\u7532\\u72b6\\u817a\\u764c\\u5207\\u9664\\u672f\\n  **7\\u7ae0  \\u4e73\\u623f\\u75be\\u75c5\\n    **\\u8282  \\u89e3\\u5256\\u751f\\u7406\\u6982\\u8981\\n    \\u7b2c\\u4e8c\\u8282  \\u4e73\\u623f\\u75be\\u75c5\\u7684\\u8bca\\u65ad\\n    \\u7b2c\\u4e09\\u8282  \\u4e73\\u623f\\u764c\\u9ad8\\u5371\\u75c5\\u4eba\\u7684\\u8bc6\\u522b\\u548c\\u5904\\u7406\\n    \\u7b2c\\u56db\\u8282  \\u4e73\\u623f\\u826f\\u6027\\u75be\\u75c5\\n    \\u7b2c\\u4e94\\u8282  \\u4e73\\u623f\\u764c\\n  **8\\u7ae0  \\u8179\\u5916\\u759d\\n    **\\u8282  \\u6982\\u8bba\\n    \\u7b2c\\u4e8c\\u8282  \\u8179\\u80a1\\u6c9f\\u759d\\n    \\u7b2c\\u4e09\\u8282  \\u5207\\u53e3\\u759d\\n  **9\\u7ae0  \\u6025\\u8179\\u75c7\\n    **\\u8282  \\u89e3\\u5256\\u751f\\u7406\\u6982\\u8981\\n    \\u7b2c\\u4e8c\\u8282  \\u75c5\\u53f2\\u91c7\\u96c6\\n    \\u7b2c\\u4e09\\u8282  \\u4f53\\u683c\\u68c0\\u67e5\\n    \\u7b2c\\u56db\\u8282  \\u8bc4\\u4f30\\u548c\\u8bca\\u65ad\\n    \\u7b2c\\u4e94\\u8282  \\u6025\\u8bca\\u624b\\u672f\\u7684\\u51c6\\u5907\\n    \\u7b2c\\u516d\\u8282  \\u4e0d\\u5178\\u578b\\u75c5\\u4eba\\n  \\u7b2c20\\u7ae0  \\u6025\\u6027\\u5316\\u8113\\u6027\\u8179\\u819c\\u708e\\n    **\\u8282  \\u89e3\\u5256\\u751f\\u7406\\u6982\\u8981\\n    \\u7b2c\\u4e8c\\u8282  \\u6025\\u6027\\u5f25\\u6f2b\\u6027\\u8179\\u819c\\u708e\\n    \\u7b2c\\u4e09\\u8282  \\u8179\\u8154\\u8113\\u80bf\\n  \\u7b2c21\\u7ae0  \\u80c3\\u548c\\u5341\\u4e8c\\u6307\\u80a0\\u75be\\u75c5\\n    **\\u8282  \\u89e3\\u5256\\u751f\\u7406\\u6982\\u8981\\n    \\u7b2c\\u4e8c\\u8282  \\u8f85\\u52a9\\u68c0\\u67e5\\n    \\u7b2c\\u4e09\\u8282  \\u5e7d\\u95e8\\u87ba\\u6746\\u83cc\\n    \\u7b2c\\u56db\\u8282  \\u80c3\\u708e\\n    \\u7b2c\\u4e94\\u8282  \\u6d88\\u5316\\u6027\\u6e83\\u75a1\\n    \\u7b2c\\u516d\\u8282  \\u80c3\\u764c\\n    \\u7b2c\\u4e03\\u8282  \\u80c3\\u606f\\u8089\\n    \\u7b2c\\u516b\\u8282  \\u80c3\\u80a0\\u95f4\\u8d28\\u7624\\n  \\u7b2c22\\u7ae0  \\u5c0f\\u80a0\\u75be\\u75c5\\n    **\\u8282  \\u89e3\\u5256\\u751f\\u7406\\u6982\\u8981\\n    \\u7b2c\\u4e8c\\u8282  \\u80a0\\u6897\\u963b\\n    \\u7b2c\\u4e09\\u8282  \\u80a0\\u606f\\u8089\\u53ca\\u80a0\\u606f\\u8089\\u75c5\\n  \\u7b2c23\\u7ae0  \\u9611\\u5c3e\\u708e\\n    **\\u8282  \\u89e3\\u5256\\u751f\\u7406\\u6982\\u8981\\n    \\u7b2c\\u4e8c\\u8282  \\u6025\\u6027\\u9611\\u5c3e\\u708e\\n    \\u7b2c\\u4e09\\u8282  \\u5c0f\\u513f\\u6025\\u6027\\u9611\\u5c3e\\u708e\\n    \\u7b2c\\u56db\\u8282  \\u8001\\u4eba\\u6025\\u6027\\u9611\\u5c3e\\u708e\\n    \\u7b2c\\u4e94\\u8282  \\u598a\\u5a20\\u6025\\u6027\\u9611\\u5c3e\\u708e\\n    \\u7b2c\\u516d\\u8282  \\u6162\\u6027\\u9611\\u5c3e\\u708e\\n  \\u7b2c24\\u7ae0  \\u7ed3\\u80a0\\u3001\\u76f4\\u80a0\\u548c\\u809b\\u7ba1\\u75be\\u75c5\\n    **\\u8282  \\u89e3\\u5256\\u751f\\u7406\\u6982\\u8981\\n    \\u7b2c\\u4e8c\\u8282  \\u76f4\\u80a0\\u809b\\u7ba1\\u75be\\u75c5\\u7684\\u8bca\\u65ad\\u548c\\u672f\\u524d\\u80a0\\u9053\\u51c6\\u5907\\n    \\u7b2c\\u4e09\\u8282  \\u809b\\u88c2\\n    \\u7b2c\\u56db\\u8282  \\u76f4\\u80a0\\u809b\\u7ba1\\u5468\\u56f4\\u8113\\u80bf\\n    \\u7b2c\\u4e94\\u8282  \\u809b\\u7618\\n    \\u7b2c\\u516d\\u8282  \\u75d4\\n    \\u7b2c\\u4e03\\u8282  \\u7ed3\\u76f4\\u80a0\\u764c\\n  \\u7b2c25\\u7ae0  \\u809d\\u810f\\u75be\\u75c5\\n    **\\u8282  \\u6982\\u8ff0\\n    \\u7b2c\\u4e8c\\u8282  \\u95e8\\u9759\\u8109\\u9ad8\\u538b\\u75c7\\n    \\u7b2c\\u4e09\\u8282  \\u6162\\u6027\\u809d\\u810f\\u75be\\u75c5\\n    \\u7b2c\\u56db\\u8282  \\u809d\\u810f\\u611f\\u67d3\\u6027\\u75be\\u75c5\\n    \\u7b2c\\u4e94\\u8282  \\u809d\\u810f\\u80bf\\u7624\\n  \\u7b2c26\\u7ae0  \\u80c6\\u9053\\u75be\\u75c5\\n    **\\u8282  \\u89e3\\u5256\\u751f\\u7406\\u6982\\u8981\\n    \\u7b2c\\u4e8c\\u8282  \\u8f85\\u52a9\\u68c0\\u67e5\\n    \\u7b2c\\u4e09\\u8282  \\u80c6\\u56ca\\u548c\\u80c6\\u7ba1\\u7684\\u5148\\u5929\\u6027\\u5f02\\u5e38\\n    \\u7b2c\\u56db\\u8282  \\u80c6\\u77f3\\u75c5\\n    \\u7b2c\\u4e94\\u8282  \\u80c6\\u56ca\\u708e\\u548c\\u80c6\\u7ba1\\u708e\\n    \\u7b2c\\u516d\\u8282  \\u80c6\\u56ca\\u5207\\u9664\\u672f\\n    \\u7b2c\\u4e03\\u8282  \\u80c6\\u9053\\u5bc4\\u751f\\u866b\\u611f\\u67d3\\n    \\u7b2c\\u516b\\u8282  \\u80c6\\u9053\\u80bf\\u7624\\n  \\u7b2c27\\u7ae0  \\u80f0\\u817a\\u75be\\u75c5\\n    **\\u8282  \\u89e3\\u5256\\u751f\\u7406\\u6982\\u8981\\n    \\u7b2c\\u4e8c\\u8282  \\u8f85\\u52a9\\u68c0\\u67e5\\n    \\u7b2c\\u4e09\\u8282  \\u80f0\\u817a\\u5148\\u5929\\u6027\\u5f02\\u5e38\\n    \\u7b2c\\u56db\\u8282  \\u6025\\u6027\\u80f0\\u817a\\u708e\\n    \\u7b2c\\u4e94\\u8282  \\u6162\\u6027\\u80f0\\u817a\\u708e\\n    \\u7b2c\\u516d\\u8282  \\u80f0\\u817a\\u764c\\n  \\u7b2c28\\u7ae0  \\u8f6f\\u7ec4\\u7ec7\\u8089\\u7624\\n    **\\u8282  \\u6613\\u60a3\\u56e0\\u7d20\\u548c\\u5206\\u5b50\\u9057\\u4f20\\u5b66\\n    \\u7b2c\\u4e8c\\u8282  \\u75c5\\u7406\\u5b66\\u8bc4\\u4f30\\n    \\u7b2c\\u4e09\\u8282  \\u4e34\\u5e8a\\u8bc4\\u4f30\\u548c\\u8bca\\u65ad\\n    \\u7b2c\\u56db\\u8282  \\u75c5\\u7076\\u8303\\u56f4\\u7684\\u8bc4\\u4f30\\n    \\u7b2c\\u4e94\\u8282  \\u5206\\u671f\\n    \\u7b2c\\u516d\\u8282  \\u5904\\u7406\\n    \\u7b2c\\u4e03\\u8282  \\u9884\\u540e\\u56e0\\u7d20\\u548c\\u7ed3\\u679c\\n\\u7d22\\u5f15\\n\",\"authorDesc\":\"\",\"image\":\"http:\\\/\\\/images.bookuu.com\\\/book_t\\\/32\\\/71\\\/04\\\/3271048.jpg\"}";
//		
//		// 新增上下架
//		//String paramJson = "{\"productId\":\"901\",\"status\":\"1\"};
//		// 库存
//		//String paramJson = "{\"skuId\":\"333\",\"remainNum\":\"33\"}";
//		map.put("param_json", paramJson);
//		String sign = "3b16c9d4c4c856ce7fff405e3b6c43abappkey10001method"+method+"param_json";
//		sign += paramJson;
//		sign += "timestamp2017-04-25 10:17:173b16c9d4c4c856ce7fff405e3b6c43ab";	
//		sign = HttpUtil.md5AndToHex(sign, false);		
//		System.out.println("sign="+sign);
//		map.put("sign", sign);
//		map.put("appkey", "10001");
//		map.put("timestamp", "2017-04-25 10:17:17");
////		map.put("method", "test.info.get");
////		map.put("param_json", "");
//		//String method = "third.product.status.create";
//		//String paramJson = "{\"cpNo\": \"BK\", \"productId\":\"901\", \"status\":\"1\"}";
//		
//		//String str = HttpUtil.javahttpPost(map, url);
//		//System.out.println(str);
////		OTA user = new OTA();
////		user.setMacAddr("1.0");
////		user.setVersion("3.9.3");
////		user.setSize("33");
////    	user.setMd5("3333");
////    	user.setName("32323");
////    	//user.setFWversion(GlobalConstant.Fwversion);
////    	//user.setHWversion(GlobalConstant.HWversion);
////    	user.setiDCard(GlobalConstant.iDCard);
////    	user.setInfo(GlobalConstant.info);
////    	user.setLanguage(GlobalConstant.language);
////    	user.setMsg(GlobalConstant.msg);
////    	user.setProducttype(GlobalConstant.producttype);
////    	user.setPassword(GlobalConstant.password);
////    	user.setRet(GlobalConstant.ret);
////    	user.setPath("32323232332");
////    	
////    	XStream xstream = new XStream();//使用xstream转换pojo和xml字符串
////		
////		org.apache.http.client.HttpClient  httpClient = new DefaultHttpClient(); 
////		HttpPost httpPost=new HttpPost("http://localhost:8080/springMVC/update/ota.do");	
////		List<NameValuePair> params = new ArrayList<NameValuePair>();
////		params.add(new BasicNameValuePair("user",JSON.toJSONString(user)));
////		UrlEncodedFormEntity entity= new UrlEncodedFormEntity(params,"utf-8");
////		//entity.setContentType("json");		
////		httpPost.setEntity(entity);
////		HttpResponse httpResponse = httpClient.execute(httpPost); 
////		if (httpResponse.getStatusLine().getStatusCode() == 200) { 
////			HttpEntity entity1 = httpResponse.getEntity();// 获取到一个HttpEntity实例 
////			String response = EntityUtils.toString(entity1, "utf-8");// 用EntityUtils.toString()这个
////			System.out.println(response);
////		}		
////		String str = HttpUtil.javahttpPost("data", JSON.toJSONString(user), "http://localhost:8080/springMVC/update/ota.do");
////		System.out.println(str);
//	}
	
	/** 
	    * Get XML String of utf-8 
	    *  
	    * @return XML-Formed string 
	    */  
	    public static String getUTF8XMLString(String xml) {  
		    // A StringBuffer Object  
		    StringBuffer sb = new StringBuffer();  
		    sb.append(xml);  
		    String xmString = "";  
		    String xmlUTF8="";  
		    try {  
		    xmString = new String(sb.toString().getBytes("UTF-8"));  
		    //xmlUTF8 = URLEncoder.encode(xmString, "UTF-8");  
		    System.out.println("utf-8 编码：" + xmlUTF8) ;  
		    } catch (UnsupportedEncodingException e) {  
		    // TODO Auto-generated catch block  
		    e.printStackTrace();  
		    }  
		    // return to String Formed  
		    return xmString;  
	    }  
	
	
	public static String md5AndToHex(String s, boolean toUpper) {
		String result = new Md5Hash(s).toHex();
		if (toUpper) {
			result = result.toUpperCase();
		}
		return result;
	}
	
	/**
	 * 字符串转换unicode
	 */
	public static String string2Unicode(String string) {
	 
	    StringBuffer unicode = new StringBuffer();
	 
	    for (int i = 0; i < string.length(); i++) {
	 
	        // 取出每一个字符
	        char c = string.charAt(i);
	 
	        // 转换为unicode
	        unicode.append("\\u" + Integer.toHexString(c));
	    }
	 
	    return unicode.toString();
	}
	
	/**
	 * unicode 转字符串
	 */
	public static String unicode2String(String unicode) {	 
	    StringBuffer string = new StringBuffer();
	 
	    String[] hex = unicode.split("\\\\u");
	 
	    for (int i = 1; i < hex.length; i++) {	 
	    	//try {
		        // 转换出每一个代码点
	    		String code = hex[i].substring(0, 4);	    			    		
		        int data = Integer.parseInt(code, 16);
		        if (hex[i].length() > 4) {
		        	string.append((char) data + hex[i].substring(4));
		        } else {
		        	string.append((char) data);
		        }		        
		        // 追加成string
		        
	    	//} catch(Exception ex) {	    		
	    	//}
	    }
	 
	    return string.toString();
	}
	
}

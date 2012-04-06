package com.diwayou.pig;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.diwayou.pig.analysis.AnalysisService;
import com.diwayou.pig.analysis.DoubanAnalysisTask;
import com.diwayou.pig.analysis.TaoHuabaoAnalysis;
import com.diwayou.pig.store.StoreService;
import com.diwayou.util.Config;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
    	HttpClient hc = new DefaultHttpClient();
    	try {
	    	List<NameValuePair> formparams = new ArrayList<NameValuePair>();
	    	formparams.add(new BasicNameValuePair("form_email", "diwayou@163.com"));
	    	formparams.add(new BasicNameValuePair("form_password", "198508"));
	    	formparams.add(new BasicNameValuePair("remember", "0"));
	    	UrlEncodedFormEntity entity = null;
	    	try {
				 entity = new UrlEncodedFormEntity(formparams, HTTP.UTF_8);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	HttpPost post = new HttpPost("http://accounts.douban.com/login");
	    	post.setEntity(entity);
	    	
	    	HttpResponse response = null;
	    	HttpContext localContext = new BasicHttpContext();
	    	try {
				response = hc.execute(post, localContext);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	HttpEntity en = response.getEntity();
	    	Header[] headers = response.getAllHeaders();
	    	for (Header header : headers) {
	    		System.out.println(header.toString());
	    	}
	    	try {
				System.out.println(EntityUtils.toString(en));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	System.out.println(localContext.getAttribute(ClientContext.USER_TOKEN));
    	} finally {
    		hc.getConnectionManager().shutdown();
    	}
    }
}

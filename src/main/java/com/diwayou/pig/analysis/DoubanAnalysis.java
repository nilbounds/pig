/**
 * 
 */
package com.diwayou.pig.analysis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import com.diwayou.pig.store.DoubanStoreTask;
import com.diwayou.pig.store.StoreService;

/**
 * @author diwayou
 *
 */
public class DoubanAnalysis implements Analysis {

	private String storePath;
	
	public DoubanAnalysis() {
		this("/");
	}
	
	public DoubanAnalysis(String storePath) {
		this.storePath = storePath;
		if (this.storePath == null) {
			this.storePath = "/";
		}
	}
	
	public String nextUrl(String url) {
		String result = "";
		
		try {
			URL u = new URL(url);
			BufferedReader reader = new BufferedReader(new InputStreamReader(u.openStream()));
			
			String line;
			boolean isNextUrl = false;
			boolean isImage = false;
			while ((line = reader.readLine()) != null) {
				if (!isNextUrl && (line.indexOf("mainphoto") >= 0)) {
					int b = line.indexOf("href=");
					b += 6;
					int e = line.indexOf("/\" ");
					result = line.substring(b, e+1);
					
					isNextUrl = true;
					isImage = true;
					
					continue;
				}
				if (isImage) {
					int b = line.indexOf("src=");
					b += 5;
					int e = line.indexOf("\" ");
					
					String imgUrl = line.substring(b, e);
					
					//System.out.println(imgUrl);
					StoreService.getInstance().execute(new DoubanStoreTask(imgUrl, storePath));
					isImage = false;
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public String getStorePath() {
		return storePath;
	}

	public void setStorePath(String storePath) {
		this.storePath = storePath;
	}

}

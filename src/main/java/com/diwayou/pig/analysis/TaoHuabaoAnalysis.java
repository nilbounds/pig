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
import com.diwayou.pig.store.TaoHuabaoStoreTask;

/**
 * @author Administrator
 *
 */
public class TaoHuabaoAnalysis implements Analysis {
	
	private String storePath;
	
	public TaoHuabaoAnalysis() {
		this("/");
	}
	
	public TaoHuabaoAnalysis(String storePath) {
		this.storePath = storePath;
		if (this.storePath == null) {
			this.storePath = "/";
		}
	}

	/* (non-Javadoc)
	 * @see com.diwayou.pig.analysis.Analysis#nextUrl(java.lang.String)
	 */
	public String nextUrl(String url) {
		String result = "";
		
		try {
			URL u = new URL(url);
			BufferedReader reader = new BufferedReader(new InputStreamReader(u.openStream()));
			
			String line;

			while ((line = reader.readLine()) != null) {
				int b = line.indexOf("\"picSrc");
				if (b >= 0) {
					b += 10;
					int e = line.lastIndexOf("\"");
					String imgUrl = line.substring(b, e);
					
					//System.out.println(imgUrl);
					StoreService.getInstance().execute(new TaoHuabaoStoreTask(imgUrl, storePath));
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

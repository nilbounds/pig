/**
 * 
 */
package com.diwayou.pig.store;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author diwayou
 *
 */
public class DoubanStoreTask extends AbstractStoreTask {

	public DoubanStoreTask(String url, String storePath) {
		super(url, storePath);
	}
	
	public void run() {
		String fileName = this.url.substring(this.url.lastIndexOf("/p") + 1, this.url.length());
		
		BufferedInputStream bIn = null;
		BufferedOutputStream bOut = null;
		try {
			URL u = new URL(this.url);
			int len;
			
			bIn = new BufferedInputStream(u.openStream());
			bOut = new BufferedOutputStream(new FileOutputStream(new File(storePath + fileName)));
			
			while ((len = bIn.read(buff)) >= 0) {
				bOut.write(buff, 0, len);
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (bIn != null)
					bIn.close();
				if (bOut != null)
					bOut.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}

}

/**
 * 
 */
package com.diwayou.pig.store;

import java.io.File;

import com.diwayou.util.Config;

/**
 * @author diwayou
 *
 */
public abstract class AbstractStoreTask implements Runnable {

	private final static int BUFF_SIZE = 8192;
	
	protected String url;
	protected byte[] buff;
	protected String storePath;
	
	public AbstractStoreTask() { }
	
	public AbstractStoreTask(String url, String storePath) {
		this.url = url;
		this.buff = new byte[BUFF_SIZE];
		this.storePath = storePath;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStorePath() {
		return storePath;
	}

	public void setStorePath(String storePath) {
		this.storePath = storePath;
	}
}

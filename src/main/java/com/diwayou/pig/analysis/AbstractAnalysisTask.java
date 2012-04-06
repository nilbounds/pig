/**
 * 
 */
package com.diwayou.pig.analysis;

/**
 * @author diwayou
 *
 */
public abstract class AbstractAnalysisTask implements Runnable {

	protected String url;
	protected String storePath;
	
	public AbstractAnalysisTask(String url) {
		this(url, "/");
	}
	
	public AbstractAnalysisTask(String url, String storePath) {
		this.url = url;
		this.storePath = storePath;
	}

}

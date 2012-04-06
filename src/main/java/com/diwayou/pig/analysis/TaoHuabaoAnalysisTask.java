/**
 * 
 */
package com.diwayou.pig.analysis;

/**
 * @author diwayou
 *
 */
public class TaoHuabaoAnalysisTask extends AbstractAnalysisTask {

	public TaoHuabaoAnalysisTask(String url) {
		super(url);
	}
	
	public TaoHuabaoAnalysisTask(String url, String storePath) {
		super(url, storePath);
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		new TaoHuabaoAnalysis(storePath).nextUrl(url);
	}

}

/**
 * 
 */
package com.diwayou.pig.analysis;

/**
 * @author diwayou
 *
 */
public class DoubanAnalysisTask extends AbstractAnalysisTask {

	public DoubanAnalysisTask(String url) {
		super(url);
	}
	
	public DoubanAnalysisTask(String url, String storePath) {
		super(url, storePath);
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		String nextUrl = url;
		
		Analysis analysis = new DoubanAnalysis(storePath);
		int p = 1;
		
		try {
			do {
				System.out.print(p++);
				System.out.print(" ");
				if (p%20 == 0)
					System.out.println();
				nextUrl = analysis.nextUrl(nextUrl);
				
				Thread.sleep(5 * 1000);
			} while (!nextUrl.equals(url));
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

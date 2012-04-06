/**
 * 
 */
package com.diwayou.pig.analysis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author diwayou
 *
 */
public class AnalysisService {

	private final static AnalysisService INSTANCE = new AnalysisService();
	
	private ExecutorService pool = Executors.newFixedThreadPool(5);
	
	private AnalysisService() { }
	
	public void execute(AbstractAnalysisTask task) {
		pool.execute(task);
	}
	
	public void shutdown() {
		pool.shutdown();
	}
	
	public static AnalysisService getInstance() {
		return INSTANCE;
	}
}

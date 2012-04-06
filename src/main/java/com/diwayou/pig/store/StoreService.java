/**
 * 
 */
package com.diwayou.pig.store;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author diwayou
 *
 */
public class StoreService {

	private final static StoreService INSTANCE = new StoreService();
	
	ExecutorService pool = Executors.newSingleThreadExecutor();
	private StoreService() {
		
	}
	
	public static StoreService getInstance() {
		return INSTANCE;
	}
	
	public void execute(AbstractStoreTask task) {
		pool.execute(task);
	}
	
	public void shutdown() {
		pool.shutdown();
	}
}

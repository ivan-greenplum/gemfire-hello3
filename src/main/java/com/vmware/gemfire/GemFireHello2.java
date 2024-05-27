package com.vmware.gemfire;

import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.distributed.ConfigurationProperties;
import org.apache.geode.cache.Region;

public class GemFireHello2 {

    public static void main(String[] args) {
        
        String p_value;
        ClientCache cache;
	ClientCacheFactory factory;
        Region<Integer, String> region;

	factory = new ClientCacheFactory();

        // Specify Locator Host and Port that is running
        // change logging to be less verbose then default INFO
	factory.addPoolLocator("127.0.0.1", 10334);
	factory.set(ConfigurationProperties.LOG_LEVEL, "warn");
        cache = factory.create();

        // configure and create local proxy Region named example
        region = cache.<Integer, String>createClientRegionFactory( ClientRegionShortcut.PROXY).create("presidents");

        System.out.println("Cache with Local Proxy Region for 'presidents' created successfully");

        // create data
        region.put(14, "James Madison");
        region.put(15, "James Monroe");
        region.put(16, "John Quincy Adams");
        region.put(17, "Andew Jackson");
        region.put(18, "Martin Van Buren");
        region.put(19, "William Henry Harrison");
        region.put(110, "John Tyler");

        System.out.println("Data Inserted to Gemfire Successfully");

	for (int j = 1; j < 100; j++){
    		System.out.println("LOOP #" + j);
		for (int i = 1; i <= 20; i++) {
        		p_value = region.get(i);
 	   		System.out.println("President #" + i + " was " + p_value);
		}
		try {
			   Thread.sleep(100);
		} catch (InterruptedException e) {
			System.err.println("Sleep was interrupted");	
		}
	}
        cache.close();
    }
}

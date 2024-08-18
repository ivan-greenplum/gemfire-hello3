package com.vmware.gemfire;

import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.distributed.ConfigurationProperties;
import org.apache.geode.cache.Region;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GemFireHello3 {

  public Map<Integer, String> readPresidentsFromFile(String csvFile) {
        String line = "";
        String csvSplitBy = ",";
        Map<Integer, String> presidentsMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Skip the header line
            br.readLine();

            while ((line = br.readLine()) != null) {
                // Use comma as separator
                String[] president = line.split(csvSplitBy);

                // Convert the first element to integer
                int presidentNumber = Integer.parseInt(president[0]);

                // Second element is the name
                String name = president[1];

                // Add to map
                presidentsMap.put(presidentNumber, name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return presidentsMap;
    }

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
        region = cache.<Integer, String>createClientRegionFactory( ClientRegionShortcut.CACHING_PROXY).create("presidents");

        System.out.println("Cache with Local Proxy Region for 'presidents' created successfully");

	String csvFile = "./us_presidents.csv";
	GemFireHello3 reader = new GemFireHello3();
	Map<Integer, String> presidentsMap = reader.readPresidentsFromFile(csvFile);

	for (Map.Entry<Integer, String> entry : presidentsMap.entrySet()) {
		Integer presno = entry.getKey();
		String  presname = entry.getValue();
		try {
			System.out.println("Put President Number: " + presno + ", Name: " + presname);
			region.put(presno, presname);
			System.out.println("Successfully put President Number: " + presno + ", Name: " + presname);
		} catch (Exception e) {
			System.err.println("Error putting President Number: " + presno + ", Name: " + presname);
			e.printStackTrace();
		}
	}

        System.out.println("Data Inserted to Gemfire Successfully");

	for (Map.Entry<Integer, String> entry : region.entrySet()) {

	    Integer key = entry.getKey();
            String value = entry.getValue();
            System.out.println("President #" + key + " was " + value);
	}
        cache.close();
    }
}

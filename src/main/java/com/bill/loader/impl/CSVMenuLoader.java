package com.bill.loader.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.bill.entity.Menu;
import com.bill.loader.MenuLoader;

@Component
public class CSVMenuLoader implements MenuLoader {

	@Override
	public Map<Integer, Menu> loadMenu() {
		Map<Integer, Menu> menuMap = new LinkedHashMap<Integer, Menu>();
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new ClassPathResource("menu.csv").getInputStream()));
			String line;
			boolean headerSkipped = false;
			while ((line = br.readLine()) != null) {
				if (!headerSkipped) {
					headerSkipped = true;
					continue;
				}
				String data[] = line.split(",");
				int id = Integer.parseInt(data[0]);
				String name = data[1];
				double price = Double.parseDouble(data[2]);
				menuMap.put(id,new Menu(id, name, price));
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed to load menu.csv", e);
		}

		return menuMap;
	}

}

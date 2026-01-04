package com.bill.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.bill.entity.Menu;
import com.bill.loader.MenuLoader;

@Service
public class MenuServiceImpl {
	
	private final MenuLoader menuLoader;

    public MenuServiceImpl(MenuLoader menuLoader) {
        this.menuLoader = menuLoader;
    }

    public Map<Integer, Menu> getMenu() {
        return menuLoader.loadMenu();
    }

}

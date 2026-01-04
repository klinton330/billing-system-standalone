package com.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BillRunner implements CommandLineRunner {
	
	@Autowired
	DriverBiller driverBiller;

	@Override
	public void run(String... args) throws Exception {
		driverBiller.execute();

	}

}

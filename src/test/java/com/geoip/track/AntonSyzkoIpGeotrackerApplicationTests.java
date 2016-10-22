package com.geoip.track;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AntonSyzkoIpGeotrackerApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testPathConversion() {
		boolean exists = false;
		ClassPathResource cpt = new ClassPathResource("static/GeoLiteCity.dat");
		File file = null ;
		try {
			file = cpt.getFile();
			exists = file.exists();
			System.out.println(" FILE EXISTS ? "+exists);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

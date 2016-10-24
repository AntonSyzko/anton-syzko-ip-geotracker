package com.geoip.track;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

/**
 * Created by Admin on 23.10.2016.
 */

public class GeoIPDatTest {

    @Test
    public void testGeoIPDat() {
        ClassPathResource cpt = new ClassPathResource("/static/GeoLiteCity.dat");
        File file = null ;
        try {
            file = cpt.getFile();
            System.out.println(file.getName() + " binary file for geolocation exists:  "+ file.exists() + "\r\n  can execute: " + file.canExecute() + "\r\n can read:  " + file.canRead());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

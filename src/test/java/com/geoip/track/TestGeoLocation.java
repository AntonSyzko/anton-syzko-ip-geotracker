package com.geoip.track;

import com.geoip.track.geoip.ServerLocation;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Admin on 23.10.2016.
 */
public class TestGeoLocation {

    @Test
    public void getLocation() {
        LookupService lookup = null;
        ServerLocation serverLocation = null;
        File file = null;
        String CHECKIPAWS = "http://checkip.amazonaws.com/";
        BufferedReader br = null;
        String myIP = null;


        try {
            URL url = new URL(CHECKIPAWS);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            myIP = br.readLine();

            ClassPathResource cpt = new ClassPathResource("/static/GeoLiteCity.dat");
            file = cpt.getFile();
            serverLocation = new ServerLocation();

            lookup = new LookupService(file, LookupService.GEOIP_MEMORY_CACHE);
            Location locationServices = lookup.getLocation(myIP);

            serverLocation.setCountryCode(locationServices.countryCode);
            serverLocation.setCountryName(locationServices.countryName);
            serverLocation.setRegion(locationServices.region);
            serverLocation.setRegionName(regionName.regionNameByCode(locationServices.countryCode, locationServices.region));
            serverLocation.setCity(locationServices.city);
            serverLocation.setPostalCode(locationServices.postalCode);
            serverLocation.setLatitude(String.valueOf(locationServices.latitude));
            serverLocation.setLongitude(String.valueOf(locationServices.longitude));

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}



package com.geoip.track.geoip;

/**
 * Created by Admin on 16.10.2016.
 */

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;

public class GetLocationExample {

//     public static void main(String[] args) {

//         InetAddress ipAddr = null;
//         String ipAddrFromJava = null;
//         try {
//             ipAddr = InetAddress.getLocalHost();
//             System.out.println("my internal IP from pure  java "+ ipAddr.getHostAddress());
//             ipAddrFromJava = ipAddr.getHostAddress();
//             System.out.println(ipAddrFromJava);
//         } catch (UnknownHostException ex) {
//             ex.printStackTrace();
//         }




//         BufferedReader br = null;
//         String myIP = null;
//         try {
//             URL url = new URL("http://checkip.amazonaws.com/");

//             br = new BufferedReader(new InputStreamReader(url.openStream()));
//             myIP = br.readLine();
//             System.out.println(myIP);
//             //System.out.println(br.readLine());

//         } catch (IOException e) {
//             e.printStackTrace();
//         }


//         GetLocationExample obj = new GetLocationExample();

//         ServerLocation location = obj.getLocation(myIP);
//         System.out.println(location.getCountryName());
//         System.out.println(location);
//     }

    public ServerLocation getLocation(String ipAddress) {

//        File file = new File(
//                "D:\\IdeaProjects\\CountiresToChart\\src\\main\\java\\com\\example\\springrestchart\\geoip\\GeoLiteCity.dat");
        ClassPathResource cpt = new ClassPathResource("static/GeoLiteCity.txt");
          File file2 = new File("https://github.com/AntonSyzko/anton-syzko-ip-geotracker/blob/master/src/main/resources/static/GeoLiteCity.dat");
                  File file3 = new File("https://geolite.maxmind.com/download/geoip/database/GeoLiteCity.dat.gz");


        File file = null ;
        try {
            file = cpt.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return getLocation(ipAddress, file3);

    }

    public ServerLocation getLocation(String ipAddress, File file) {

        ServerLocation serverLocation = null;

        try {

            serverLocation = new ServerLocation();

            LookupService lookup = new LookupService(file,LookupService.GEOIP_MEMORY_CACHE);
            Location locationServices = lookup.getLocation(ipAddress);

            serverLocation.setCountryCode(locationServices.countryCode);
            serverLocation.setCountryName(locationServices.countryName);
            serverLocation.setRegion(locationServices.region);
            serverLocation.setRegionName(regionName.regionNameByCode(
                    locationServices.countryCode, locationServices.region));
            serverLocation.setCity(locationServices.city);
            serverLocation.setPostalCode(locationServices.postalCode);
            serverLocation.setLatitude(String.valueOf(locationServices.latitude));
            serverLocation.setLongitude(String.valueOf(locationServices.longitude));

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return serverLocation;

    }


}

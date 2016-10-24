package com.geoip.track;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Admin on 23.10.2016.
 */
public class TestIPCheck {

    @Test
    public  void testIPCheck(){
        String CHECKIPAWS = "http://checkip.amazonaws.com/";

        BufferedReader br = null;
        String myIP = null;

        try {
            URL url = new URL(CHECKIPAWS);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            myIP = br.readLine();
            if(myIP.equals(null)){
                System.out.println(" raw info was not  received ");
            }else{
                System.out.println("raw ip info " + myIP.toString());
            }
        } catch (IOException ex){
            ex.getMessage();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

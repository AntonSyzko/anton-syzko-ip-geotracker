package com.geoip.track.controller;


import com.geoip.track.entity.Visitor;
import com.geoip.track.geoip.GetLocationExample;
import com.geoip.track.geoip.GetLocationException;
import com.geoip.track.geoip.ServerLocation;
import com.geoip.track.repository.VisitorRepository;
import com.geoip.track.service.VisitorHasBeenAlreadyRegisteredException;
import com.geoip.track.service.VisitorNotFoundException;
import com.geoip.track.service.VisitorServiceImpl;
import com.geoip.track.utils.TrackingServlet;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Admin on 17.10.2016.
 */
@Controller
@RequestMapping("/")
public class GeoController  extends WebMvcConfigurerAdapter {
    Logger logger = LoggerFactory.getLogger(GeoController.class);

    @Autowired
    private VisitorRepository repository;
    @Autowired
    private VisitorServiceImpl service;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
        binder.registerCustomEditor(Date.class, editor);

    }

    @RequestMapping("/login")
    public String login() {

        return "login";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String findAll(Model model) {
//        Visitor demo = new Visitor();
//        demo.setIpAddress("82.13.234.000");
//
//        demo.setVisitedAt(new Date());
//        demo.setCity("London");
//        demo.setPostalCode("8H87");
//        demo.setCountryCode("GB");
//        demo.setCountryName("Great Britain");
//        demo.setRegion("UK");
//        demo.setRegionName("UK");
//        demo.setLatitude("30.123544");
//        demo.setLongtitude("-4.09309387");
//
//        service.create(demo);


//        System.out.println(repository.findAll());
        model.addAttribute("visitors", service.findAll());

            model.addAttribute("newVisitor", new Visitor());


        return "visitors";
    }




    @RequestMapping(method = RequestMethod.DELETE)
    public String delete(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        service.delete(id);
        redirectAttributes.addFlashAttribute("deleted",
                " visitor with ID number :   " + id + "    has  been deleted ") ;
        return "redirect:/";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String post(@Valid @ModelAttribute("newVisitor") Visitor visitor, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("newVisitor", visitor);
            model.addAttribute("visitors", service.findAll());
            return "visitors";
        } else {
            try{
                service.create(visitor);
            }catch (VisitorHasBeenAlreadyRegisteredException ex){
                logger.error(ex.getLocalizedMessage());
                redirectAttributes.addFlashAttribute("visitorhasalreadybeeregisteredmessage",
                        ex.getMessage()) ;
            }

            return "redirect:/";
        }
    }



@RequestMapping(value="/geo",method= RequestMethod.GET)
public String geoMap(Model model, HttpServletRequest request, HttpServletResponse response) {
    Cookie kuki = new Cookie("testCookie","TEST_COOKIE");
    response.addCookie(kuki);

    String ipAddress = TrackingServlet.getClientIpAddress(request);
    System.out.println(ipAddress);
    System.out.println("****");
    String secondIp = TrackingServlet.getClientIpAddr(request);
    System.out.println(secondIp);
    String otherInfoOfIP = request.getLocalAddr() + "\r\n" + request.getLocalName() + "\r\n" + request.getRemoteAddr()+"\r\n" + request.getRemoteHost()+"\r\n"+
            request.getServerName()+"\r\n" + request.getServerPort()+"\r\n" + request.isSecure()+"\r\n"+request.getRequestURL()+"\r\n"+
            request.getProtocol()+"\r\n"+request.getRemoteUser()+"\r\n" + request.getCookies();
    System.out.println(otherInfoOfIP);

    String kukistr = request.getCookies().toString();
    String singleCookie = kukistr.substring(0, kukistr.indexOf(";"));
    // String cookieName = singleCookie.substring(0, singleCookie.indexOf("="));
    String cookieValue = singleCookie.substring(singleCookie.indexOf("=") + 1, singleCookie.length());


    model.addAttribute("kuki", request.getCookies().length);
    model.addAttribute("allInfo" ,otherInfoOfIP);
    model.addAttribute("firstkukval",cookieValue.toString());
    return "geo";
}




    @RequestMapping(value="/visitors",method= RequestMethod.GET)
    public String visitors(Model model) {
        return "visitors";
    }






    @ModelAttribute("latitude")
    public String getLatitude(){
        ServerLocation location = getLocation();
        System.out.println(location.getCountryName());
        System.out.println(location);
        String lat =location.getLatitude();
        return lat;
    }

    public ServerLocation getLocation(){

        BufferedReader br = null;
        String myIP = null;
        GetLocationExample obj = null;
        ServerLocation location = null;
        try {
            URL url = new URL("http://checkip.amazonaws.com/");
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            myIP = br.readLine();
            System.out.println(myIP);
            obj = new GetLocationExample();
            location = obj.getLocation(myIP);
            //System.out.println(br.readLine());

        } catch (IOException ex){
            logger.error(ex.getLocalizedMessage());
            ex.getMessage();
        }
        catch (Exception e) {
            logger.error(e.getLocalizedMessage());

            e.printStackTrace();
        }

        return location;
    }



    @ModelAttribute("longtitude")
    public String getLongtitude(){
        ServerLocation location = getLocation();
        System.out.println(location.getCountryName());
        System.out.println(location);
//        double longtitude = Double.valueOf(location.getLongtitude());
        String  longtitude = location.getLongitude();
        return longtitude;
    }

    @ModelAttribute("ipaddress")
    public String getIPAddressAttribute(){
        BufferedReader br = null;
        String myIP = null;
        try {
            // optional url myexternalip.com/raw
            URL url = new URL("http://checkip.amazonaws.com/");
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            myIP = br.readLine();
            System.out.println("*******************************************************************************************************************************************************" + myIP);
        }catch (IOException ex){
            logger.error(ex.getLocalizedMessage());
            logger.debug(ex.getLocalizedMessage());
            ex.getMessage();
        }
        return myIP;
    }



    @ExceptionHandler({VisitorNotFoundException.class})
    public ModelAndView getVisitorUnavailable(VisitorNotFoundException ex) {
        return new ModelAndView("error", "error", ex.getMessage());
    }

    @ExceptionHandler({GetLocationException.class})
    public ModelAndView getGeoIpTrackingUnavailable(GetLocationException ex) {
        return new ModelAndView("error", "error", ex.getMessage());
    }
}

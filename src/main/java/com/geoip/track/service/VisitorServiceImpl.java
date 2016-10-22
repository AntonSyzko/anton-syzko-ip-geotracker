package com.geoip.track.service;


import com.geoip.track.entity.Visitor;
import com.geoip.track.geoip.GetLocationExample;
import com.geoip.track.geoip.ServerLocation;
import com.geoip.track.repository.VisitorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 17.10.2016.
 */
@Service
public class VisitorServiceImpl {
    private Logger logger = LoggerFactory.getLogger(VisitorServiceImpl.class);


    @Autowired
    private VisitorRepository repository;

    public List<Visitor> findAll() {


        return repository.findAll();
    }

//    @Transactional
//    public Visitor create(Visitor visitor) {
//
//
//
//
//        Visitor newVisitor = new Visitor();
//        newVisitor.setIpAddress(visitor.getIpAddress());
//        newVisitor.setCountryCode(visitor.getCountryCode());
//        newVisitor.setCountryName(visitor.getCountryName());
//        newVisitor.setRegion(visitor.getRegion());
//        newVisitor.setRegionName(visitor.getRegionName());
//        newVisitor.setCity(visitor.getCity());
//        newVisitor.setPostalCode(visitor.getPostalCode());
//        newVisitor.setLatitude(visitor.getLatitude());
//        newVisitor.setLongtitude(visitor.getLongtitude());
//        newVisitor.setVisitedAt(new Date());
//
//
//        return repository.saveAndFlush(newVisitor);
//    }

    @Transactional
    public Visitor create(Visitor visitor) throws VisitorHasBeenAlreadyRegisteredException {
        Visitor newVisitor = new Visitor();
        BufferedReader br = null;
        String myIP = null;
        try {
            URL url = new URL("http://checkip.amazonaws.com/");

            br = new BufferedReader(new InputStreamReader(url.openStream()));
            myIP = br.readLine();
            System.out.println(myIP);
            //System.out.println(br.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }


        GetLocationExample obj = new GetLocationExample();

        ServerLocation location = obj.getLocation(myIP);

        newVisitor.setIpAddress(myIP);
        newVisitor.setCountryCode(location.getCountryCode());
        newVisitor.setCountryName(location.getCountryName());
        newVisitor.setRegion(location.getRegion());
        newVisitor.setRegionName(location.getRegionName());
        newVisitor.setCity(location.getCity());
        newVisitor.setPostalCode(location.getPostalCode());
        newVisitor.setLatitude(location.getLatitude());
        newVisitor.setLongtitude(location.getLongitude());
        newVisitor.setVisitedAt(new Date());


        //server side duplictes chech
        if((repository.findByLatitude(location.getLatitude()).size()>=1)&(repository.findByLongtitude(location.getLongitude()).size()>=1)){
            // return null;

            throw new VisitorHasBeenAlreadyRegisteredException("VISITOR HAS BEEN ALREADY REGISTERED AND ADDED TO DATABASE");
        }
        return repository.saveAndFlush(newVisitor);
    }

    @Transactional
    public void delete(Long id) {
        Visitor visitor = findOneSafe(id);
        repository.delete(visitor);
    }

    private Visitor findOneSafe(Long id) {
        Visitor visitor = repository.findOne(id);
        if (visitor == null) {
            throw new VisitorNotFoundException("not found ");
        } else {
            return visitor;
        }
    }




}

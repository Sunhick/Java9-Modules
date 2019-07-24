package com.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

public class Runner {

    private static final Logger log = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) {
        // show the config properties

        URL resource = Runner.class.getResource(Constants.CONFIG_PROP);

        System.out.println(">> " + resource.getFile());
        log.info(">> " + resource.getFile());
        log.debug(">> " + resource.getFile());
        log.warn(">> " + resource.getFile());
        log.error(">> " + resource.getFile());
    }
}

package org.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.Set;

public class Printer {

    private static final Logger log = LogManager.getLogger(Printer.class);

    public void print(String file) {
        log.info("File passed: {}", file);

        Set<Module> modules = ModuleLayer.boot().modules();
        log.info(modules);

        Optional<Module> comModule = ModuleLayer.boot().findModule(Constants.COM_MOD);
        comModule.ifPresentOrElse(e -> this.catFile(e, file), this::missingMainModule);
    }

    private void catFile(Module comModule, String file) {
        log.debug("{} is present.", Constants.COM_MOD);

        InputStream in = null;
        try {
            in = comModule.getResourceAsStream(file);
        } catch (IOException e) {
            log.warn("Error in opening the file. ", e);
            return;
        }

        if (in == null) {
            log.error("can't open the file!");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            reader.lines().forEach(line -> log.info(line));
        } catch (IOException e) {
            // log.error("Error in opening the file", e);
        }
    }

    private void missingMainModule() {
        log.error("{} isn't available!", Constants.COM_MOD);
    }
}

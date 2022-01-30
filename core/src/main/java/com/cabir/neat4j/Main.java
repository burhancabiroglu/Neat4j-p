package com.cabir.neat4j;
import com.cabir.neat4j.logger.Log4nj;

import java.io.IOException;
import java.util.logging.Logger;

public class Main {
    static {
        try {
            Log4nj.setup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static void main(String[] args) {}
}
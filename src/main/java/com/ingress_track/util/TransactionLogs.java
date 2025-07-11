package com.ingress_track.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TransactionLogs {

    private static final Logger logger = LoggerFactory.getLogger(TransactionLogs.class);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM-dd-yy");
    private static final DateTimeFormatter timestampFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final String logDirectory = "logs/transactions/";

    public TransactionLogs() {
        File dir = new File(logDirectory);
        if (!dir.exists() && dir.mkdirs()) {
            logger.info("Created log directory: {}", logDirectory);
        }
    }

    public static void log(String message) {
        String currentDate = LocalDate.now().format(dateFormatter);
        String logFilePath = logDirectory + currentDate + ".log";
        String logMessage = LocalDateTime.now().format(timestampFormatter) + " - " + message;

        logger.info(logMessage);

        try (FileWriter writer = new FileWriter(logFilePath, true)) {
            writer.write(logMessage + System.lineSeparator());
        } catch (IOException e) {
            String errMsg = currentDate + ": Failed to write to transaction log file";
            logger.error(errMsg, e);
        }
    }
}

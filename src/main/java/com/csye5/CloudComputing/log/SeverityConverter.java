package com.csye5.CloudComputing.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;


public class SeverityConverter extends ClassicConverter {
    @Override
    public String convert(ILoggingEvent event) {
        return switch (event.getLevel().toString()) {
            case "ERROR" -> "ERROR";
            case "WARN" -> "WARN";
            case "INFO" -> "INFO";
            default -> "DEFAULT";
        };
    }
}

package com.probojnik.terminal.util;

/**
 * @author Stanislav Shamji
 */
public enum MimeType {
    PDF("application/pdf"),
    HTML("text/html"),
    Plain("text/plain"),
    Png("image/png"),
    Jpeg("image/jpeg"),
    MSWordDOC("application/msword"),
    MSWordDOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
    MSExcelXLS("application/vnd.ms-excel"),
    MSExcelXLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    PowerPointPPT("application/vnd.ms-powerpoint"),
    PowerPointPPTX("application/vnd.openxmlformats-officedocument.presentationml.presentation");

    private final String value;

    MimeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

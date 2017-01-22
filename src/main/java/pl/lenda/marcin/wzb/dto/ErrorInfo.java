package pl.lenda.marcin.wzb.dto;

public class ErrorInfo {

    private String errorCode;
    private String description;

    public ErrorInfo(String errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }
}

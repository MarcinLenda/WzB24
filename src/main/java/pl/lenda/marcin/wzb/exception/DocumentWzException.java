package pl.lenda.marcin.wzb.exception;

import org.springframework.http.HttpStatus;

public class DocumentWzException extends RuntimeException {

    private final DocumentWzErrorCode errorCode;
    private final HttpStatus statusCode;

    private DocumentWzException(DocumentWzErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
        this.statusCode = HttpStatus.BAD_REQUEST;
    }

    private DocumentWzException(DocumentWzErrorCode errorCode, HttpStatus status) {
        this.errorCode = errorCode;
        this.statusCode = status;
    }

    public enum DocumentWzErrorCode {
        DOCUMENT_ALREADY_EXISTS("Document already exists"),
        DOCUMENT_NOT_FOUND("Document not found");

        private final String description;

        DocumentWzErrorCode(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }


    //metoda produkujemy te wyjatki
    public static DocumentWzException documentAlreadyExists() {
        return new DocumentWzException(DocumentWzErrorCode.DOCUMENT_ALREADY_EXISTS, HttpStatus.CONFLICT);
    }

    public static DocumentWzException documentNotFound() {
        return new DocumentWzException(DocumentWzErrorCode.DOCUMENT_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public DocumentWzErrorCode getErrorCode() {
        return errorCode;
    }


    public HttpStatus getStatusCode() {
        return statusCode;
    }
}

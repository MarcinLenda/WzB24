package pl.lenda.marcin.wzb.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by Promar on 19.01.2017.
 */
public class ClientException extends RuntimeException {

    private final ClientErrorCode clientErrorCode;
    private final HttpStatus httpStatus;

    private ClientException(ClientErrorCode clientErrorCode) {
        super(clientErrorCode.getDescription());
        this.clientErrorCode = clientErrorCode;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public ClientException(ClientErrorCode clientErrorCode, HttpStatus httpStatus) {
        this.clientErrorCode = clientErrorCode;
        this.httpStatus = httpStatus;
    }


    public enum  ClientErrorCode{
        CLIENT_ALREADY_EXISTS("Client already exists"),
        CLIENT_NOT_FOUND("Client not found."),
        CLIENT_HAS_DOCUMENT("Client has document"),
        TEAM_NOT_FOUND_TEAM("Team not found"),
        NUMBER_ALREADY_EXISTS("Number already exists");

        private final String description;

        ClientErrorCode(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public static ClientException clientAlreadyExists(){
        return new ClientException(ClientErrorCode.CLIENT_ALREADY_EXISTS, HttpStatus.CONFLICT);
    }

    public static ClientException clientNotFound(){
        return new ClientException(ClientErrorCode.CLIENT_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public static ClientException clientHasDocumentWz(){
        return new ClientException(ClientErrorCode.CLIENT_HAS_DOCUMENT, HttpStatus.CONFLICT);
    }

    public static ClientException nameTeamNotFound(){
        return new ClientException(ClientErrorCode.TEAM_NOT_FOUND_TEAM, HttpStatus.NOT_FOUND);
    }

    public static ClientException numberAlreadyExists(){
        return new ClientException(ClientErrorCode.NUMBER_ALREADY_EXISTS, HttpStatus.CONFLICT);
    }


   public ClientErrorCode getErrorCode(){
        return clientErrorCode;
   }


    public HttpStatus getStatusCode() {
        return httpStatus;
    }
}

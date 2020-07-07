package it.mohanrc.socialmedia.exception;

//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.NOT_FOUND)
/*
    commenting out because of using SocialMediaExceptionHandler,
    if not automatically will set the status as not found
*/
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}

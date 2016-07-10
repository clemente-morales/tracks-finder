package lania.com.mx.musicfinder.net.exceptions;

import retrofit.RetrofitError;

public class NotConectionException extends Exception {

    private static final long serialVersionUID = 7060891363652783984L;

    public NotConectionException(RetrofitError cause) {
       super(cause);
    }
}

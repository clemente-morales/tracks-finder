package lania.com.mx.musicfinder.net.exceptions;

import retrofit.RetrofitError;

public class NotResponseException extends Exception {
    private static final long serialVersionUID = 3511121282835800153L;

    public NotResponseException(RetrofitError cause) {
       super(cause);
    }
}

package lania.com.mx.musicfinder.net.exceptions;

import retrofit.RetrofitError;

public class UnauthorizedException extends Exception {
    private static final long serialVersionUID = 6858821732663202180L;

    public UnauthorizedException(RetrofitError cause) {
       super(cause);
    }

}

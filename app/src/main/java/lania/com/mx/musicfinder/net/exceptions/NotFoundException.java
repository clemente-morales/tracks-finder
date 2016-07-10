package lania.com.mx.musicfinder.net.exceptions;

import retrofit.RetrofitError;

public class NotFoundException extends Exception {

    private static final long serialVersionUID = 5359877086732154931L;

    public NotFoundException(RetrofitError cause) {
       super(cause);
    }
}

package lania.com.mx.musicfinder.net.exceptions;

import retrofit.RetrofitError;

public class ServerException extends Exception {
    private static final long serialVersionUID = 2204460522786807760L;

    public ServerException(RetrofitError cause) {
       super(cause);
    }
}


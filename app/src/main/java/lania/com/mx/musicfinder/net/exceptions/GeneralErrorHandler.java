package lania.com.mx.musicfinder.net.exceptions;

import android.content.Context;
import android.util.Log;

import org.apache.http.HttpStatus;

import lania.com.mx.musicfinder.R;
import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 *
 */
public class GeneralErrorHandler implements ErrorHandler {

    private static final String TAG = 
            GeneralErrorHandler.class.getSimpleName();

    private final Context ctx;
    

    public GeneralErrorHandler(Context ctx) {
        this.ctx = ctx;
    }
    
    
    @Override
    public Throwable handleError(RetrofitError cause) {
        String errorDescription;        
        
        if (cause == null) {
            errorDescription = ctx.getString(R.string.RetrofitError_unable_to_connect);
            Log.e(TAG, errorDescription);
            return new NotConectionException(cause);
        } 
        if (cause.getResponse() == null) {
            errorDescription = ctx.getString(R.string.RetrofitError_not_response_back);
            Log.e(TAG, errorDescription);
            return new NotResponseException(cause);
        } 
        
        Response response = cause.getResponse();
        
        switch (response.getStatus()) {
        case HttpStatus.SC_UNAUTHORIZED:
            errorDescription = ctx.getString(R.string.RetrofitError_unauthorized);
            Log.e(TAG, errorDescription);
            return new UnauthorizedException(cause);   
        case HttpStatus.SC_NOT_FOUND: 
            errorDescription = ctx.getString(R.string.RetrofitError_resource_not_found);
            Log.e(TAG, errorDescription);
            return new NotFoundException(cause);
        case HttpStatus.SC_INTERNAL_SERVER_ERROR:
            errorDescription = ctx.getString(R.string.RetrofitError_internal_server_error);
            Log.e(TAG, errorDescription);
            return new ServerException(cause);
        default:
            return new Exception("An exception has occurred trying to execute the web operation.", cause);
        }
    }
}

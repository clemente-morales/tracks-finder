package lania.com.mx.musicfinder.models;

/**
 * Created by clerks on 9/9/15.
 */
public final class DataResult<D, E extends Exception> {

    private D data;

    private E exception;

    private DataResult(D data, E exception) {
        this.data = data;
        this.exception = exception;
    }

    public static <D, E extends Exception> DataResult<D, E> createDataResult(
            D data) {
        return new DataResult<D, E>(data, null);
    }

    public static <D, E extends Exception> DataResult<D, E> createExceptionResult(
            E exception) {
        return new DataResult<D, E>(null, exception);
    }

    public boolean isData() {
        return !isException();
    }

    public boolean isException() {
        return this.exception != null;
    }

    public D getData() throws UnsupportedOperationException {
        if (!isData()) {
            throw new UnsupportedOperationException(
                    "This instance hasn't data.");
        }
        return data;
    }

    public E getException() throws UnsupportedOperationException {
        if (!isException()) {
            throw new UnsupportedOperationException(
                    "This instance does not hold an exception.");
        }
        return exception;
    }
}
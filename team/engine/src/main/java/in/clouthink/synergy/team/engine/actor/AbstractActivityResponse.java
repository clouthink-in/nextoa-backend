package in.clouthink.synergy.team.engine.actor;

import in.clouthink.synergy.team.exception.EngineException;

import java.io.Serializable;

/**
 * @auther dz
 */
public class AbstractActivityResponse implements Serializable {

    final private Throwable throwable;

    public AbstractActivityResponse(Throwable throwable) {
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void throwOut() {
        throw new EngineException(this.throwable);
    }

    public boolean hasError() {
        return throwable != null;
    }

}

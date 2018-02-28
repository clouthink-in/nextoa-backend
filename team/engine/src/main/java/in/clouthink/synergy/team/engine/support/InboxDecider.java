package in.clouthink.synergy.team.engine.support;

/**
 * The strategy to decide which queue the activity will be appended to.
 * <p>
 * The queue might be located on same JVM or different physical machine.
 *
 * @auther dz
 */
public class InboxDecider {

    /**
     * @param activityId
     * @return queue address (actor location in akka)
     */
    public String decide(String activityId) {
        //TODO
        throw new UnsupportedOperationException();
    }

}

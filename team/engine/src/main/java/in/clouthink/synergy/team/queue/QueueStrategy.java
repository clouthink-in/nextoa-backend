package in.clouthink.synergy.team.queue;

/**
 * The strategy to decide which queue the activity will be appended to.
 * <p>
 * The queue might be located on same JVM or different physical machine.
 *
 * @auther dz
 */
public interface QueueStrategy {

    /**
     * @param activityId
     * @return queue address (actor location in akka)
     */
    String decide(String activityId);

}

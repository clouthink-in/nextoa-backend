package in.clouthink.synergy.team.engine.support;

import org.springframework.beans.factory.annotation.Value;

/**
 * The strategy to decide which queue the activity will be appended to.
 * <p>
 * The queue might be located on same JVM or different physical machine.
 *
 * @auther dz
 */
public class InboxDecider {

    //The recommend value is the count of CPU core
    @Value("${synergy.team.queue.count:4}")
    private int howManyQueues;


    /**
     * @param activityId
     * @return queue address (actor location in akka)
     */
    public String decide(String activityId) {
        //TODO
        throw new UnsupportedOperationException();
    }

}

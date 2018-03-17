package in.clouthink.synergy.account.domain.model;

/**
 * @auther dz
 */
public final class Groups {

    public static boolean isRoot(Group group) {
        return group.getParent() == null;
    }

}

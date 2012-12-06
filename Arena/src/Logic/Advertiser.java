package Logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: silfer
 * Date: 2012-12-06
 * Time: 14:45
 * To change this template use File | Settings | File Templates.
 */
public class Advertiser extends User {
    private int adPoints;
    List<Advertisement> ads;

    /**
     *
     * @param name
     * @param email
     * @param userType
     * ***************************
     * Subclass to user, nickname and name are the same for an advertiser
     * An advertiser have a list of ads and an amount of "adPoints" that
     * works as credits for ad clicks.
     */
    public Advertiser(String name, String email){
        super(name, email, USER_TYPE_ADVERTISER, name);
        ads = new ArrayList<Advertisement>();
    }

    public void addAd(Advertisement ad){
        ads.add(ad);
    }

    public Advertisement getAd(/*identifier for ad*/){
        return null;
    }

    public int getAdPoints() {
        return adPoints;
    }

    public void addAdPoints(int pointsToAdd){
        adPoints += pointsToAdd;
    }
}

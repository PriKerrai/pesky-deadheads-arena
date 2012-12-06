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
public class Advertiser {
    private int adPoints;
    private String advertiserName;
    List<Advertisement> ads;


    public Advertiser(String name){
        advertiserName = name;
        ads = new ArrayList<Advertisement>();
    }

    public void addAd(Advertisement ad){
        ads.add(ad);
    }


}

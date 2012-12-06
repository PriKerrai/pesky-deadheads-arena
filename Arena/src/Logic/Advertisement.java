package Logic;

/**
 * Created with IntelliJ IDEA.
 * User: silfer
 * Date: 2012-12-06
 * Time: 15:45
 * To change this template use File | Settings | File Templates.
 */
public class Advertisement {
    private Advertiser advertiser;
    private String adName;
    private String linkToAd;
    public Advertisement(Advertiser advertiser, String adName, String linkToAd){
        this.advertiser = advertiser;
        this.adName = adName;
        this.linkToAd = linkToAd;
    }

    public Advertiser getAdvertiser() {
        return advertiser;
    }

    public String getAdName() {
        return adName;
    }


    public String getLinkToAd() {
        return linkToAd;
    }

    public void setLinkToAd(String linkToAd) {
        this.linkToAd = linkToAd;
    }
}

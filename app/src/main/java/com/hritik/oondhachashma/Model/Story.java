package com.hritik.oondhachashma.Model;

public class Story {
    private int fid;
    private final int sid;
    private final String sname,evidid,url;
    private Boolean isFav;

    public Story(int sid, String sname, String url,String evidid) {
        this.sid = sid;
        this.url = url;
        this.sname = sname;
        this.evidid = evidid;
        this.isFav=false;
    }

    public void setFav(Boolean fav) {
        isFav = fav;
    }
    public boolean getFav() {
        return isFav;
    }


    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getUrl() {
        return url;
    }

    public int getFid() {
        return fid;
    }

    public int getSid() {
        return sid;
    }

    public String getSname() {
        return sname;
    }

    public String getEvidid() {
        return evidid;
    }
}

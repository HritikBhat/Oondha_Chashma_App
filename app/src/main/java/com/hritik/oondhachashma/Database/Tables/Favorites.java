package com.hritik.oondhachashma.Database.Tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import static com.hritik.oondhachashma.Assets.Constants.FavoritesTable;


@Entity(tableName = FavoritesTable)
public class Favorites
{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "fid")
    private int fid;

    @ColumnInfo(name = "sid")
    private String sid;

    @ColumnInfo(name = "sname")
    private String sname;

    @ColumnInfo(name = "url")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getFid() {
        return fid;
    }

    public String getSid() {
        return sid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

}

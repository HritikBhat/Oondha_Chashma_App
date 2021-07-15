package com.hritik.oondhachashma.Database.Tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import static com.hritik.oondhachashma.Assets.Constants.FavoritesTable;
import static com.hritik.oondhachashma.Assets.Constants.PointersTable;


@Entity(tableName = PointersTable)
public class Pointer
{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "iid")
    private int iid;

    @ColumnInfo(name = "sid")
    private String sid;

    @ColumnInfo(name = "pos")
    private String pos;

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

}

package com.hritik.oondhachashma.Assets;

import com.hritik.oondhachashma.Model.Story;

import java.util.ArrayList;

public class StoryData {
    private ArrayList<Story> storiesDataList;
    private ArrayList<String> epList;


    public StoryData() {
        epList= new ArrayList<String>();
        storiesDataList=new ArrayList<>();

        epList.add("5_zRun2jd2M");
        epList.add("KwSp5bon7go");
        epList.add("3YZS19HaTL8");
        epList.add("pFwi2cp1t9Q");
        epList.add("U2ACr7SUc7M");
        epList.add("EZZNsdnc5Ck");
        epList.add("NrV2I5-GytU");
        epList.add("QXEFrBR7ULc");
        epList.add("oXLFFTrIols");
        epList.add("HclYJC7abmc");
        epList.add("3JSPgFOAwxs");
        epList.add("_q9eXYXeFqM");

        storiesDataList.add(new Story(1,"Bootni Episode","https://i.ytimg.com/vi/KviIBv59is4/mqdefault.jpg",epList));

        epList = new ArrayList<>();

        epList.add("G5yYl7gFDx0");
        epList.add("ngmhzEUVm6I");
        epList.add("Gvvi8yw1lg0");
        storiesDataList.add(new Story(2,"Water Tanker Episode",
                "http://www.scrolldroll.com/wp-content/uploads/2020/08/champak-water-tank-meme.jpeg",epList));

        epList = new ArrayList<>();

        epList.add("pujA3bYQ4Ek");
        epList.add("oW3yzsBG7Co");
        epList.add("EqRYAYlPeF4");

        storiesDataList.add(new Story(3,"Bhide Whishle Episode",
                "https://m.media-amazon.com/images/M/MV5BM2E5YjMxYjctMjhmOS00YWQyLWE3YzYtMjc3NTUxMDNjNzVmXkEyXkFqcGdeQXVyODAzNzAwOTU@._V1_.jpg",epList));

        epList = new ArrayList<>();

        epList.add("YarelrUt6RY");
        epList.add("YRkK4EwvVfs");
        epList.add("kyWykuwptiE");
        epList.add("IM87RcWVsr4");
        epList.add("9_48sJmr5Po");
        epList.add("BjcZ2WUrWMk");
        storiesDataList.add(new Story(4,"Tarak's Alleged Affair Episode","https://i.ytimg.com/vi/YRkK4EwvVfs/mqdefault.jpg",epList));

        epList = new ArrayList<>();

        epList.add("-5NHIBo6gGc");
        epList.add("aMgPWmI-JwA");
        epList.add("FhzM030KPus");
        epList.add("EkrfwWrBDUM");
        epList.add("CwQXVoHkUmk");
        epList.add("EJcx7WXkd4Q");
        epList.add("4W3-Fmjt7sA");
        epList.add("gWxUgDZVsdU");
        epList.add("lmJkMmghZEs");
        epList.add("CzuMTjnj0Rw");
        epList.add("-ltrsy0a_Gc");
        epList.add("04BtLCPullE");
        epList.add("aeaNu7pipDo");
        storiesDataList.add(new Story(5,"Tapu's Wedding Episode",
                "https://m.media-amazon.com/images/M/MV5BYzYzNTU1ZTMtNDZkZi00OGQ4LTg3YTItZDZiOTFmYTUwMWQ2XkEyXkFqcGdeQXVyODAzNzAwOTU@._V1_.jpg",epList));

        epList = new ArrayList<>();
        epList.add("6MNc4SBNDCw");
        epList.add("1ZuUcsgSKzk");
        epList.add("TbjfF0BBJyY");
        epList.add("yDgMQNAOomk");
        storiesDataList.add(new Story(6,
                "Horse Statue Episode","https://origin-staticv2.sonyliv.com/landscape_thumb/tmkoc_landscape_thumbnail_ep702.jpg",epList));
    }

    public ArrayList<Story> getStoriesDataList() {
        return storiesDataList;
    }

    public ArrayList<String> getStoryIndexAt(int sid){
        for (Story story: storiesDataList) {
            if (story.getSid()==sid){
                return story.getEvidid();
            }
        }
        return null;
    }

}
/*
        storiesDataList.add(new Story(7,"Chaddi Gang Episode","https://i.ytimg.com/vi/0oKLrou1VVw/hqdefault.jpg","EuzuVESXCDk"));
        storiesDataList.add(new Story(8,"GPL 1 Episode","https://origin-staticv2.sonyliv.com/landscape_thumb/tmkoc_landscape_thumbnail_ep183.jpg","6CGbr7elXn8"));
        storiesDataList.add(new Story(9,"GPL 2 Episode","https://origin-staticv2.sonyliv.com/landscape_thumb/tmkoc_landscape_thumbnail_ep395.jpg","ooATjVxZXDk"));
        storiesDataList.add(new Story(10,"Play Outside Tapu Sena!! Episode","https://origin-staticv2.sonyliv.com/landscape_thumb/tmkoc_landscape_thumbnail_ep369.jpg","ESrYQsEw4nA"));
 */

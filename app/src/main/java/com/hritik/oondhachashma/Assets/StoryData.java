package com.hritik.oondhachashma.Assets;

import com.hritik.oondhachashma.Model.Story;

import java.util.ArrayList;

public class StoryData {
    private ArrayList<Story> storiesDataList;

    public StoryData() {
        storiesDataList=new ArrayList<>();
        storiesDataList.add(new Story(1,"Bootni Episode","https://i.ytimg.com/vi/KviIBv59is4/mqdefault.jpg","5_zRun2jd2M"));
        storiesDataList.add(new Story(2,"Water Tanker Episode","http://www.scrolldroll.com/wp-content/uploads/2020/08/champak-water-tank-meme.jpeg","G5yYl7gFDx0"));
        storiesDataList.add(new Story(3,"Bhide Whishle Episode","https://m.media-amazon.com/images/M/MV5BM2E5YjMxYjctMjhmOS00YWQyLWE3YzYtMjc3NTUxMDNjNzVmXkEyXkFqcGdeQXVyODAzNzAwOTU@._V1_.jpg","pujA3bYQ4Ek"));
        storiesDataList.add(new Story(4,"Tarak's Alleged Affair Episode","https://i.ytimg.com/vi/YRkK4EwvVfs/mqdefault.jpg","YarelrUt6RY"));
        storiesDataList.add(new Story(5,"Tapu's Wedding Episode","https://m.media-amazon.com/images/M/MV5BYzYzNTU1ZTMtNDZkZi00OGQ4LTg3YTItZDZiOTFmYTUwMWQ2XkEyXkFqcGdeQXVyODAzNzAwOTU@._V1_.jpg","-5NHIBo6gGc"));
        storiesDataList.add(new Story(6,"Horse Statue Episode","https://origin-staticv2.sonyliv.com/landscape_thumb/tmkoc_landscape_thumbnail_ep702.jpg","6MNc4SBNDCw"));
        storiesDataList.add(new Story(7,"Chaddi Gang Episode","https://i.ytimg.com/vi/0oKLrou1VVw/hqdefault.jpg","EuzuVESXCDk"));
        storiesDataList.add(new Story(8,"GPL 1 Episode","https://origin-staticv2.sonyliv.com/landscape_thumb/tmkoc_landscape_thumbnail_ep183.jpg","6CGbr7elXn8"));
        storiesDataList.add(new Story(9,"GPL 2 Episode","https://origin-staticv2.sonyliv.com/landscape_thumb/tmkoc_landscape_thumbnail_ep395.jpg","ooATjVxZXDk"));
        storiesDataList.add(new Story(10,"Play Outside Tapu Sena!! Episode","https://origin-staticv2.sonyliv.com/landscape_thumb/tmkoc_landscape_thumbnail_ep369.jpg","ESrYQsEw4nA"));

    }

    public ArrayList<Story> getStoriesDataList() {
        return storiesDataList;
    }
}

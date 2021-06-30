package com.example.iatimd_eindoplevering;

public class Kledingstuk {
    private String name;
    private String spiecies;
    private String season;
    private String occasion;
    private String image;
    private int uuid;

    public Kledingstuk (String name, String spiecies, String season, String occasion,String image, int uuid){
        this.name = name;
        this.spiecies = spiecies;
        this.season = season;
        this.occasion = occasion;
        this.image = image;
        this.uuid = uuid;
    }

    public String getName(){
        return this.name;
    }

    public String getSpiecies(){
        return this.spiecies;
    }

    public String getSeason(){
        return this.season;
    }

    public String getOccasion(){
        return this.occasion;
    }

    public String getImage(){
        return this.image;
    }

    public int getUuid(){
        return this.uuid;
    }

}

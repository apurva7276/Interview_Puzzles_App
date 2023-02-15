package com.example.interview_puzzle_app.db;

public class UserData {
    public int id,logedIn, avtar, age, coins, microsoft, google, yahoo, meta, apple, netflix;
    public String email, name;

    public UserData(){
        this.id = 0;
        this.logedIn = 1;
        this.avtar = 0;
        this.age = 0;
        this.coins = 0;
        this.microsoft = 0;
        this.google = 0;
        this.yahoo = 0;
        this.meta = 0;
        this.apple = 0;
        this.netflix = 0;
        this.email = "email";
        this.name = "name";
    }

    public UserData(int id,
                    String email,
                    int logedIn,
                    int avtar,
                    String name,
                    int age,
                    int coins,
                    int microsoft,
                    int google,
                    int yahoo,
                    int meta,
                    int apple,
                    int netflix) {
        this.id = id;
        this.logedIn = logedIn;
        this.avtar = avtar;
        this.age = age;
        this.coins = coins;
        this.microsoft = microsoft;
        this.google = google;
        this.yahoo = yahoo;
        this.meta = meta;
        this.apple = apple;
        this.netflix = netflix;
        this.email = email;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getLogedIn() {
        return logedIn;
    }

    public int getAvtar() {
        return avtar;
    }

    public int getAge() {
        return age;
    }

    public int getCoins() {
        return coins;
    }

    public int getMicrosoft() {
        return microsoft;
    }

    public int getGoogle() {
        return google;
    }

    public int getYahoo() {
        return yahoo;
    }

    public int getMeta() {
        return meta;
    }

    public int getApple() {
        return apple;
    }

    public int getNetflix() {
        return netflix;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}

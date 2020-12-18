package com.hv_07.bunker.friends;

public class Friend {
    private String Name;
    private String Username;
    private String ID;

    public Friend(String id, String n, String user){
        this.Name = n;
        this.ID = id;
        this.Username = user;
    }

    public String getID(){
        return this.ID;
    }
    public String getName(){
        return Name;
    }
    public String getUsername(){
        return Username;
    }
}

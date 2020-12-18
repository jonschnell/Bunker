package com.hv_07.bunker.services;

public class Service {
    private String Name;
    private String Username;
    private String password;
    private String ID;

    public Service(String n, String u, String p, String id){
        this.Name=n;
        this.Username=u;
        this.password=p;
        this.ID=id;
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
    public String getPassword() {
        return password;
    }
}

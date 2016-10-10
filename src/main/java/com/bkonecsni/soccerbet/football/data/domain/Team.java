package com.bkonecsni.soccerbet.football.data.domain;

public class Team {

    private String name;

    private _links _links;

    //TODO: use this if teams logo needed, delete otherwise. Can be null!!
    private String crestUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public _links get_links() {
        return _links;
    }

    public void set_links(_links _links) {
        this._links = _links;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public void setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
    }
}

package com.bkonecsni.soccerbet.football.data.domain.teams;

import com.bkonecsni.soccerbet.football.data.domain._links;

public class Team {

    private String name;

    private _links _links;

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
}

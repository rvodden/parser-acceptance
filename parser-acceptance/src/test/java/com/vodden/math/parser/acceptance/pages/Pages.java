package com.vodden.math.parser.acceptance.pages;

public class Pages {
    private HomeImpl home;

    public Pages(HomeImpl home) {
        this.home = home;
    }
 
    public HomeImpl home(){
        return home;
    }
 
}

package org.solarsystem.web.service;



public class CalcDistance {

    /* available planet are: "Mercury","Venus","Earth""Mars","Jupiter","Saturn","Uranus","Neptune","Pluto":
     * Time format is "yyyy-MM-dd"  , for example 2034-03-07
     * Valid for time interval 1800 AD - 2050 AD
     * calculations are taken : https://aa.quae.nl/en/reken/hemelpositie.html#1_3
     * https://www.aa.quae.nl/en/reken/zonpositie.html
     * return value is (AU) distance https://en.wikipedia.org/wiki/Astronomical_unit
     * */
    public static double getDistance(String planetStart, String planetFinish, String time){
        PlanetPosition start = new PlanetPosition(planetStart,time);
        PlanetPosition finish = new PlanetPosition(planetFinish,time);
        double x1 = start.getXhelio();
        double y1 = start.getYhelio();
        double z1 = start.getZhelio();

        double x2 = finish.getXhelio();
        double y2 = finish.getYhelio();
        double z2 = finish.getZhelio();
        return Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2)+Math.pow(z1-z2,2));
    }
}


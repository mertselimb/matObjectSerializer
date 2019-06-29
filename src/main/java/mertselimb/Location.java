package mertselimb;

import java.io.Serializable;

public class Location implements Serializable {
    public String x;
    public String y;
    public String degree;

    public Location(String x, String y, String degree) {
        this.x = x;
        this.y = y;
        this.degree = degree;
    }
}

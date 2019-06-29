package mertselimb;

import org.opencv.core.Point;

import java.io.Serializable;

public class SerializableKeyPoint implements Serializable {
    public float x;
    public float y;
    public float size;
    public float angle;
    public float response;
    public int octave;
    public int class_id;

    public SerializableKeyPoint(float x, float y, float _size, float _angle, float _response, int _octave, int _class_id) {
        this.x = x;
        this.y = y;
        this.size = _size;
        this.angle = _angle;
        this.response = _response;
        this.octave = _octave;
        this.class_id = _class_id;
    }

    public String toString() {
        return "KeyPoint [x= " + this.x +", y= " + this.y + ", size=" + this.size + ", angle=" + this.angle + ", response=" + this.response + ", octave=" + this.octave + ", class_id=" + this.class_id + "]";
    }
}

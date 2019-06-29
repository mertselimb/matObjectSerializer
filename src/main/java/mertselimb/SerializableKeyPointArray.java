package mertselimb;

import org.opencv.features2d.KeyPoint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SerializableKeyPointArray implements Serializable {

    public List<List<SerializableKeyPoint>> arr = new ArrayList<>();

    public void push(List<SerializableKeyPoint> a){
        arr.add(a);
    }

    public void transformFromKeypointArray(List<KeyPoint[]> keypoints ){
        for (KeyPoint[] keys: keypoints) {
            List<SerializableKeyPoint> newObject = new ArrayList<>();

            for (KeyPoint k : keys) {
                SerializableKeyPoint newK = new SerializableKeyPoint((float)k.pt.x, (float)k.pt.y, k.size, k.angle, k.response, k.octave, k.class_id);
                newObject.add(newK);
            }
            this.push(newObject);
        }
    }
}

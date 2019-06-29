package mertselimb;

import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.KeyPoint;
import org.opencv.highgui.Highgui;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        nu.pattern.OpenCV.loadLibrary();


        String imageLocation = "./images/test.JPG";

        Mat objectImage = Highgui.imread(imageLocation, Highgui.CV_LOAD_IMAGE_COLOR);
        MatOfKeyPoint objectKeyPoints = new MatOfKeyPoint();
        FeatureDetector siftDetector = FeatureDetector.create(FeatureDetector.SURF);
        siftDetector.detect(objectImage, objectKeyPoints);
        KeyPoint[] keypoints = objectKeyPoints.toArray();
        System.out.println(keypoints[0].toString());

        List<SerializableKeyPoint> outObject = new ArrayList<>();

        for (KeyPoint k : keypoints) {
            SerializableKeyPoint newK = new SerializableKeyPoint((float)k.pt.x, (float)k.pt.y, k.size, k.angle, k.response, k.octave, k.class_id);
            outObject.add(newK);
        }


        String filename = "file.ser";
        Serializer.serialize(outObject, filename);

        List<SerializableKeyPoint> inObject = Serializer.deSerialize(filename);


        System.out.println(inObject.get(0).toString());
    }
}

package mertselimb;

import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.KeyPoint;
import org.opencv.highgui.Highgui;

public class App {
    public static void main(String[] args) {
        nu.pattern.OpenCV.loadLibrary();


        String imageLocation = "./src/main/java/mertselimb/test.JPG";

        Mat objectImage = Highgui.imread(imageLocation, Highgui.CV_LOAD_IMAGE_COLOR);
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
        System.out.println(objectImage.empty());
        MatOfKeyPoint objectKeyPoints = new MatOfKeyPoint();
        FeatureDetector siftDetector = FeatureDetector.create(FeatureDetector.SURF);
        siftDetector.detect(objectImage, objectKeyPoints);
        KeyPoint[] keypoints = objectKeyPoints.toArray();
        System.out.println(keypoints.length);
    }
}

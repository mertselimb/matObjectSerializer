package mertselimb;

import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.KeyPoint;
import org.opencv.highgui.Highgui;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class App {
    public static void main(String[] args) {
        nu.pattern.OpenCV.loadLibrary();


        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println("Detection started. " + sdf.format(cal.getTime()));

        List<Location> locations = new ArrayList<>();
        List<KeyPoint[]> keyPointList = new ArrayList<>();

        String imageLocation = "./images/";
        File dir = new File(imageLocation);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                Mat objectImage = Highgui.imread(child.getAbsolutePath(), Highgui.CV_LOAD_IMAGE_COLOR);

                MatOfKeyPoint objectKeyPoints = new MatOfKeyPoint();
                FeatureDetector siftDetector = FeatureDetector.create(FeatureDetector.SIFT);
                siftDetector.detect(objectImage, objectKeyPoints);


                MatOfKeyPoint objectDescriptors = new MatOfKeyPoint();
                DescriptorExtractor descriptorExtractor = DescriptorExtractor.create(DescriptorExtractor.SIFT);
                descriptorExtractor.compute(objectImage, objectKeyPoints, objectDescriptors);


                KeyPoint[] descriptors = objectDescriptors.toArray();
                keyPointList.add(descriptors);
                locations.add(new Location(child.getName().split(",")[0], child.getName().split(",")[1], child.getName().split(",")[2]));
            }
        }

        cal = Calendar.getInstance();
        System.out.println("Detection ended. " + sdf.format(cal.getTime()));
        System.out.println("Serializer started. " + sdf.format(cal.getTime()));
        SerializableKeyPointArray images = new SerializableKeyPointArray();
        images.transformFromKeypointArray(keyPointList);

        Serializer.serialize(images, "./out/images.ser");
        Serializer.serialize(locations, "./out/locations.ser");


        cal = Calendar.getInstance();
        System.out.println("Serializer ended. " + sdf.format(cal.getTime()));
//        SerializableKeyPointArray inImages = (SerializableKeyPointArray) Serializer.deSerialize("./out/images.ser");
//        for (List<SerializableKeyPoint> k : inImages.arr) {
//            System.out.println(k.get(0).toString());
//        }
//
//        List<Location> inLocations = (List<Location>) Serializer.deSerialize("./out/locations.ser");
//        for (Location loc : inLocations) {
//            System.out.println(loc.x + " , " + loc.y + " , " + loc.degree);
//        }


    }
}

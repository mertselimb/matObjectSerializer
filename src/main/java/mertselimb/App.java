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


        String imageLocation = "./src/main/java/mertselimb/test.JPG";

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
        // Serialization
        try {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(outObject);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        } catch (IOException ex) {
            System.out.println("IOException is caught");
            System.out.println(ex.toString());
        }



        List<SerializableKeyPoint> inObject;
        try
        {
            FileInputStream fis = new FileInputStream("file.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);

            inObject = (List<SerializableKeyPoint>) ois.readObject();

            ois.close();
            fis.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            return;
        }
        catch (ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }

        System.out.println(inObject.get(0).toString());
    }
}

package mertselimb;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serializer {

    public static void serialize(List<SerializableKeyPoint> list, String location) {
        // Serialization
        try {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(location);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(list);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        } catch (IOException ex) {
            System.out.println("IOException is caught");
            System.out.println(ex.toString());
        }

    }

    public static List<SerializableKeyPoint> deSerialize(String location) {

        List<SerializableKeyPoint> inObject = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("file.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);

            inObject = (List<SerializableKeyPoint>) ois.readObject();

            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
        return inObject;
    }
}

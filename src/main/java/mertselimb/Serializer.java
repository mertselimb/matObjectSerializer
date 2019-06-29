package mertselimb;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serializer {

    public static void serialize(Object obj, String path) {
        // Serialization
        try {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(obj);

            out.close();
            file.close();

//            System.out.println("Object has been serialized");

        } catch (IOException ex) {
            System.out.println("IOException is caught");
            System.out.println(ex.toString());
        }

    }

    public static Object deSerialize(String path) {

        Object inObject = new Object();
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);

            inObject = ois.readObject();

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

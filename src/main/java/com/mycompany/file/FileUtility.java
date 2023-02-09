package com.mycompany.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtility {

    private static void writeIntoFile(String fileName, String text, boolean append) throws Exception {
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, append))) {
            bw.write(text);
        }
    }

    public static void writeIntoFile(String fileName, String text) throws Exception {
        writeIntoFile(fileName, text, false);
    }

    public static void appendIntoFile(String fileName, String text) throws Exception {
        writeIntoFile(fileName, text, true);
    }

    public static void writeBytes(String fileName, byte[] data) throws Exception {
        FileOutputStream fop = new FileOutputStream(fileName);
        fop.write(data);
        fop.flush();
        fop.close();
    }

    public static void writeObjectToFile(Serializable object, String name) throws Exception {
        try ( FileOutputStream fout = new FileOutputStream(name);  
              ObjectOutputStream oos = new ObjectOutputStream(fout);) {
            oos.writeObject(object);
        }
    }

    public static String read(String fileName) throws Exception {
        try ( FileInputStream in = new FileInputStream(fileName);  InputStreamReader r = new InputStreamReader(in);  BufferedReader reader = new BufferedReader(r);) {
            String line = null;
            String result = "";
            while ((line = reader.readLine()) != null) {
                result += line + "\n";
            }
            return result;
        }
    }

    public static byte[] readBytes(String fileName) throws Exception {
        File file = new File(fileName);
        byte[] bytesArray = new byte[(int) file.length()];
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(bytesArray);
        return bytesArray;
    }

    public static Object readFileDeserialize(String name) throws Exception {
        Object obj = null;
        FileInputStream fi = new FileInputStream(name);
        try ( ObjectInputStream in = new ObjectInputStream(fi)) {
            obj = in.readObject();
        } finally {
            return obj;
        }
    }

    public static void writeBytesNio(byte[] data, String fileName) throws Exception {
//        Path.filePath = Paths.get(fileName);
//        Files.write(filePath, data);
    }

    public static byte[] readBytesNio(String fileName) throws Exception {
        Path filePath = Paths.get(fileName);
        byte[] byteArray = Files.readAllBytes(filePath);
        return byteArray;
    }

}

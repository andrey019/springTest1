package stupid;


import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Data extends Thread {
    private static Map<Long, byte[]> photos = new ConcurrentHashMap<>();
    private static long size = 0;
    private static File file1 = new File("c:\\test\\photos.hmp");
    private static File file2 = new File("c:\\test\\photos.hmp.bak");
    private static Data data = new Data();

    private Data() {}

    @Override
    public void run() {
        if (!backUp(file1)) {
            backUp(file2);
        }
        while (!isInterrupted()) {
            save();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean backUp(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            this.photos = (ConcurrentHashMap<Long, byte[]>) objectInputStream.readObject();
            System.out.println("Restored successfully!");
            size = photos.size();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("BackUp failed!");
            return false;
        }
    }

    private void save() {
        if (size != photos.size()) {
            writePhotos(file1);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writePhotos(file2);
            size = photos.size();
        }
    }

    private void writePhotos(File file) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(photos);
            objectOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to save " + file.getAbsolutePath());
        }
    }

    public static Map<Long, byte[]> getPhotos() {
        return photos;
    }

    public static Data getInstance() {
        return data;
    }
}

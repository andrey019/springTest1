package stupid;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipService {
    private ZipService() {}

    public static byte[] zipFiles(Map<Long, byte[]> allFiles, List<Long> marker) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
            for (Long file : marker) {
                zipOutputStream.putNextEntry(new ZipEntry(file.toString() + ".jpg"));
                zipOutputStream.write(allFiles.get(file));
                zipOutputStream.closeEntry();
            }
            zipOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

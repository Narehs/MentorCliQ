package app.utility;

import org.springframework.web.multipart.MultipartFile;

public class CsvReader {

    public static String fileName;

    public static String getFileExtension(MultipartFile file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return " ";
        }
        return name.substring(lastIndexOf);
    }
}

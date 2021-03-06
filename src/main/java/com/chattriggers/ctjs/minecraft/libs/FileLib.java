//package com.chattriggers.ctjs.minecraft.libs;
//
//import com.chattriggers.ctjs.utils.config.Config;
//import com.chattriggers.ctjs.utils.console.Console;
//import org.apache.commons.io.FileUtils;
//
//import java.io.*;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipInputStream;
//
//public class FileLib {
//    /**
//     * Writes a file to folder in modules.
//     *
//     * @param importName name of the import
//     * @param fileName   name of the file
//     * @param toWrite    string to write in file
//     */
//    public static void write(String importName, String fileName, String toWrite) {
//        write(Config.getInstance().getModulesFolder().value + "/" + importName + "/" + fileName, toWrite);
//    }
//
//    /**
//     * Writes a file to anywhere on the system.<br>
//     * Use "./" for the ".minecraft" folder.
//     *
//     * @param fileLocation the location and file name
//     * @param toWrite      string to write in file
//     */
//    public static void write(String fileLocation, String toWrite) {
//        try {
//            //#if MC<=10809
//            FileUtils.write(new File(fileLocation), toWrite);
//            //#else
//            //$$ FileUtils.write(new File(fileLocation), toWrite, java.nio.charset.Charset.defaultCharset());
//            //#endif
//        } catch (IOException exception) {
//            Console.getInstance().printStackTrace(exception);
//        }
//    }
//
//    /**
//     * Reads a file from folder in modules.<br>
//     * Returns an empty string if file is not found.
//     *
//     * @param importName name of the import
//     * @param fileName   name of the file
//     * @return the string in the file
//     */
//    public static String read(String importName, String fileName) {
//        return read(Config.getInstance().getModulesFolder().value + "/" + importName + "/" + fileName);
//    }
//
//    /**
//     * Reads a file from anywhere on the system.<br>
//     * Use "./" for the ".minecraft" folder.<br>
//     * Returns an empty string if file is not found.
//     *
//     * @param fileLocation the location and file name
//     * @return the string in the file
//     */
//    public static String read(String fileLocation) {
//        File file = new File(fileLocation);
//        return read(file);
//    }
//
//    /**
//     * Reads a file from anywhere on the system using java.io.File.
//     *
//     * @param file the java.io.File to loadExtra
//     * @return the string in the file
//     */
//    public static String read(File file) {
//        try {
//            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
//
//            if (!file.exists() || br.readLine() == null) {
//                br.close();
//                return "";
//            }
//
//            br.close();
//            //#if MC<=10809
//            return FileUtils.readFileToString(file);
//            //#else
//            //$$ return FileUtils.readFileToString(file, java.nio.charset.Charset.defaultCharset());
//            //#endif
//        } catch (IOException exception) {
//            Console.getInstance().printStackTrace(exception);
//        }
//
//        return "";
//    }
//
//    /**
//     * Gets the contents of a url as a string.
//     *
//     * @param theUrl the url to get the data from
//     * @return the string stored in the url content
//     */
//    public static String getUrlContent(String theUrl) {
//        return getUrlContent(theUrl, null);
//    }
//
//    /**
//     * Gets the contents of a url as a string.
//     *
//     * @param theUrl the url to get the data from
//     * @param userAgent the user agent to use in the connection
//     * @return the string stored in the url content
//     */
//    public static String getUrlContent(String theUrl, String userAgent) {
//        StringBuilder content = new StringBuilder();
//
//        try {
//            URL url = new URL(theUrl);
//            URLConnection urlConnection = url.openConnection();
//            if (userAgent != null) urlConnection.setRequestProperty("User-Agent", userAgent);
//
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                content.append(line).append("\n");
//            }
//        } catch (Exception exception) {
//            Console.getInstance().printStackTrace(exception);
//        }
//
//        return content.toString();
//    }
//
//    public static boolean deleteDirectory(File dir) {
//        if (dir.isDirectory()) {
//            File[] children = dir.listFiles();
//            for (File child : children) {
//                if (!deleteDirectory(child)) return false;
//            }
//        }
//
//        return dir.delete();
//    }
//
//    /**
//     * Extracts a zip file specified by the zipFilePath to a directory specified by
//     * destDirectory (will be created if does not exists).
//     * @param zipFilePath the zip file path
//     * @param destDirectory the destination directory
//     * @throws IOException IOException
//     */
//    public static void unzip(String zipFilePath, String destDirectory) throws IOException {
//        File destDir = new File(destDirectory);
//        if (!destDir.exists()) destDir.mkdir();
//
//        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
//        ZipEntry entry = zipIn.getNextEntry();
//        // iterates over entries in the zip file
//        while (entry != null) {
//            String filePath = destDirectory + File.separator + entry.getName();
//            if (!entry.isDirectory()) {
//                // if the entry is a file, extracts it
//                extractFile(zipIn, filePath);
//            } else {
//                // if the entry is a directory, make the directory
//                File dir = new File(filePath);
//                dir.mkdir();
//            }
//            zipIn.closeEntry();
//            entry = zipIn.getNextEntry();
//        }
//        zipIn.close();
//    }
//
//    // helper method for unzipping
//    private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
//        File toWrite = new File(filePath);
//        toWrite.getParentFile().mkdirs();
//        toWrite.createNewFile();
//
//        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
//        byte[] bytesIn = new byte[4096];
//        int read = 0;
//        while ((read = zipIn.read(bytesIn)) != -1) {
//            bos.write(bytesIn, 0, read);
//        }
//        bos.close();
//    }
//}

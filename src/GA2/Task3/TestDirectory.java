//package GA2.Task3;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//
//public class TestDirectory {
//    public static void main(String[] args) throws IOException, InterruptedException {
//        Path directory = Paths.get("test-directory");
//        Files.createDirectories(directory);
//
//        for (int i = 0; i < 500; i++) {
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
//            String filePath = directory + File.separator
//                    + simpleDateFormat.format(new Timestamp(System.currentTimeMillis())) + ".txt";
//            File file = new File(filePath);
//            file.createNewFile();
//
//            FileWriter fileWriter = new FileWriter(filePath);
//            for (int k = 0; k < 10000; k++) {
//                fileWriter.append(simpleDateFormat.format(new Timestamp(System.currentTimeMillis())) + "\n");
//            }
//            fileWriter.close();
//            Thread.sleep(10);
//        }
//    }
//}

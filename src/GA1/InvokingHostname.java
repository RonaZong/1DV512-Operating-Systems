//package GA1;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//public class InvokingHostname {
//    public static void main(String[] args) {
//        ProcessBuilder processBuilder = new ProcessBuilder();
//        processBuilder.command("hostname", "freebsd-vm-group22-upd");
//
//        try {
//            Process process = processBuilder.start();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String line;
//            StringBuilder stringBuilder = new StringBuilder();
//            while((line = bufferedReader.readLine()) != null) {
//                stringBuilder.append(line);
//            }
//            String result = stringBuilder.toString();
//            int exitCode = process.waitFor();
//            System.out.println("Invoking Command: " + result + "\nExit code: " + exitCode);
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//        catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}

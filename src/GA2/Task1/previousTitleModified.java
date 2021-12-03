//package GA2.Task1;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.RandomAccessFile;
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//
//public class previousTitleModified {
//    public static void main(String[] args) {
//        ProcessHandle currentProcess = ProcessHandle.current();
//        long PID = currentProcess.pid();
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
//        // Print PID of respective process
//        System.out.println("<PID " + PID + "> <" + simpleDateFormat.format(new Timestamp(System.currentTimeMillis()))
//                + "> Process started");
//
//        while(currentProcess.isAlive()){
//            try {
//                RandomAccessFile pipe = new RandomAccessFile("test-named-pipe","r");
//
//                // Print message about event
//                System.out.println("<PID " + PID + "> <" + simpleDateFormat.format(new Timestamp(System.currentTimeMillis()))
//                        + "> Pipe opened");
//
//                // Read contents of file line by line and print them out, while adding respective process ID and timestamp
//                String line;
//                while ((line = pipe.readLine()) != null){
//                    // Sleep for 3s after each successfully read message
//                    Thread.sleep(3000);
//                    System.out.println("<PID " + PID + "> <" + simpleDateFormat.format(new Timestamp(System.currentTimeMillis()))
//                            + "> this is text read from the pipe:" + line);
//                }
//
//                // Close file, print message and sleep for 3s
//                pipe.close();
//                System.out.println("<PID " + PID + "> <" + simpleDateFormat.format(new Timestamp(System.currentTimeMillis()))
//                        + "> Pipe closed");
//                Thread.sleep(3000);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}

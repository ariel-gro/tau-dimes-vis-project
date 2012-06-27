package dimesVisGui;
import java.io.*;
 
public class Main {
 
       public static void main(String args[]) {
 
            try {
                Runtime rt = Runtime.getRuntime();
                //Process pr = rt.exec("cmd /c dir");
                Process pr = rt.exec("c:\\runMatlab.cmd");
 
                int exitVal = pr.waitFor();
                System.out.println("Exited with error code "+exitVal);
 
            } catch(Exception e) {
                System.out.println(e.toString());
                e.printStackTrace();
            }
        }
}
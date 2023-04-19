package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.net.ssl.TrustManager;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        if (args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    System.out.println(args[i]);
                }
        }

        String dirPath = args[0];
        String fileName = args[1];
        String dirPathFileName = dirPath + File.separator + fileName;       //separator method allows Java to help determine pathing term
        // System.out.println(dirPathFileName);

        /* 
         * Use file object to check if directory exists.
         * Create the directory if it does not exist.
        */

        File newDirectory = new File(dirPath);
        if (newDirectory.exists()) {
            System.out.println("Directory " + dirPath + " already exists.");
        }
        else {
            newDirectory.mkdir();
        }

        File newFile = new File(dirPathFileName);
        if (newFile.exists()) {
            System.out.println(dirPathFileName + " already exists.");
        }
        else {
            newFile.createNewFile();
        }


        //Writing information into the text file from scratch.
        String content = "Writing new content into a text file.";
        String content2 = "Still writing new content into text file...";

        FileWriter fileWriter = new FileWriter(dirPathFileName, true);
        //true = append info on top of existing info in text file
        //false = overwrite existing info in text file

        fileWriter.write(content);
        fileWriter.write("\n");
        fileWriter.write(content2);
        fileWriter.flush();
        fileWriter.close();


        //Decorator pattern for writing info into text file.
        String content3 = "A quick brown fox jumps over the wall.\n";
        //Open new writer, followed by new bufferedwriter.
        FileWriter fileWriter2 = new FileWriter(dirPathFileName, true);
        BufferedWriter bw = new BufferedWriter(fileWriter2);
        bw.append(content3);
        bw.flush();
        //Close bufferedwriter first, then writer in order.
        bw.close();
        fileWriter2.close();


        //FileOutputStream content. Does not have decorator pattern.
        String content4 = "I like to run.";
        FileOutputStream fos = new FileOutputStream(dirPathFileName, true);
        byte[] byteContent = content4.getBytes();
        fos.write(byteContent);
        fos.flush();
        fos.close();


        String content5 = "I cannot believe it.";
        FileOutputStream fos2 = new FileOutputStream(dirPathFileName, true);
        DataOutputStream dos2 = new DataOutputStream(fos2);
        dos2.writeUTF(content5);
        dos2.flush();
        dos2.close();
        fos2.close();


        //Reading from file.
        File file2 = new File(dirPath + File.separator + fileName);
        FileReader fr = new FileReader(file2);
        int dataRead = fr.read();

        while (dataRead != -1) {
            System.out.println((char) dataRead);     
            dataRead = fr.read();
        }
        fr.close();

        
        FileReader fr2 = new FileReader(file2);
        BufferedReader br = new BufferedReader(fr2);
        String line = "";
        line = br.readLine();
        
        while (line != null) {
            System.out.println(line);
            line = br.readLine();
        }
        br.close();
        fr2.close();

        
        //FileInputStream and DataInputStream decorator.
        FileInputStream fis = new FileInputStream(file2);
        DataInputStream dis = new DataInputStream(fis);
        int disData = dis.read();
        
        while (disData != -1) {
            System.out.println((char) disData);
            disData = dis.read();
        }
        dis.close();
        fis.close();
    
    }
}

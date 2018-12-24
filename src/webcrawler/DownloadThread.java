/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author PGR0101
 */
public class DownloadThread extends Thread{
    
    private String link;
    private File out;
    /**
     * out is a file with the address have to be save directory structure
     * 
     */
    public DownloadThread(String link , File out){
        this.out = out;
        this.link = link;
        this.start();
    }
    
    @Override
    public void run(){
        try{
            URL url = new URL(link);
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            double filesize = (double)http.getContentLength();
            BufferedInputStream in = new BufferedInputStream(http.getInputStream());
            FileOutputStream fos = new FileOutputStream(out);
            BufferedOutputStream bos = new BufferedOutputStream(fos , 1024);
            byte[] buffer = new byte[1024];
            double downloaded =0.00;
            int read = 0;
            double percentDownloaded = 0.00;
            while((read = in.read(buffer , 0 , 1024)) >= 0){
                bos.write(buffer , 0 , read);
                downloaded += read;
                percentDownloaded += ((downloaded*100)/filesize);
                System.out.println(percentDownloaded);
            }
            bos.close();
            in.close();
            
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
}

package webcrawler;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JProgressBar;

/**
 *
 * @author pgr0101
 */
public class DownloadFile {
    // have to get a root for the first file or directory to save it and have to get it by input
    private double size = 0.00; // size of the file
    private String name = ""; // name of the file
    private URL url; // url for downloading
    private String path = "";// path to save file for directory structure
    private boolean status = false;
    private DownloadThread dt;
    
    public DownloadFile(String url , String path) throws MalformedURLException{
        this.url = new URL(url);
        this.path = path;
        this.name = url;
        
    }
    
    /**
    * start downloading file and saving it
    */
    public void startDownload(){
        dt = new DownloadThread(this.url, new File(this.path));
    }
    
    /**
    *   start downloading file and updating progressbar that has been made by gui part
    */
    public void startDownload(JProgressBar jp){
        dt = new DownloadThread(this.url, new File(this.path) , jp);
    }
    /**
    *   return true if download completed else false
    */
    public boolean getStatus(){
        return this.dt.getStatus();
    }
    
    /**
    *   get file size for showing in gui
    */
    public double getFileSize() throws IOException{
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        this.size = (double)http.getContentLength();
        return this.size;
    }
    
    public String getFileName(){
        return this.name;
    }
    
    public String getUrl(){
        return this.url.toString();
    }
    
    
    /**
    *   open in browser or special files in their default applications
    */
    public void openFile() throws IOException{
        Desktop desktop = Desktop.getDesktop();
        desktop.open(new File(this.path));
    }   
}
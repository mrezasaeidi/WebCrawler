package webcrawler;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JProgressBar;
import sun.awt.DesktopBrowse;

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
    private ArrayList<String> links = new ArrayList<>();
// path if is file then set file name else make a directory and set as the last path
    
    public DownloadFile(String url , String path) throws MalformedURLException{
        this.url = new URL(url);
        
        // if just doing the path or do the file part with name out here
        // making path should have a process 
        this.path = path;
        // have to set name directory or file
        this.name = "";
        
    }
    
    /*
    * start downloading file and saving it
    */
    public void startDownload(){
        dt = new DownloadThread(this.url, new File(this.path));
    }
    
    /*
    *   start downloading file and updating progressbar that has been made by gui part
    */
    public void startDownload(JProgressBar jp){
        dt = new DownloadThread(this.url, new File(this.path) , jp);
    }
    /*
    *   return true if download completed else false
    */
    public boolean getStatus(){
        return this.dt.getStatus();
    }
    
    /*
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
    
    
    /*
    *   open in browser or special files in their default applications
    */
    public void openFile() throws IOException{
        Desktop desktop = Desktop.getDesktop();
        desktop.open(new File(this.path));
    }
    
    
    public ArrayList<String> returnLinks(){
        if(!links.isEmpty())
            return this.links;
        
        return null;
    }
    
}


// we have to create directories here if needed
// how to set the path is so important 


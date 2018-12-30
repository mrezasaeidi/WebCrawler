package webcrawler;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
    protected boolean status = false;
    private DownloadThread dt;

    public DownloadFile(String url, String rootpath) throws MalformedURLException {
        try {
            this.url = new URL(url);
        } catch (Exception e) {

        }
        this.name = url.replaceAll("/", ".");
        this.path = rootpath + this.name;
    }

    /**
     * start downloading file and saving it
     */
    public void startDownload() {
        dt = new DownloadThread(this.url, new File(this.path) , this);
    }

    /**
     * start downloading file and updating progressbar that has been made by gui
     * part
     */
    public void startDownload(JProgressBar jp) {
        dt = new DownloadThread(this.url, new File(this.path), jp , this);
    }

    /**
     * return true if download completed else false
     */
    public boolean getStatus() {
        return this.status;
    }

    /**
     * get file size for showing in gui
     */
    public int getFileSize() {
        return getFileSize(url);
    }

    private static int getFileSize(URL url) {
        URLConnection conn = null;
        try {
            conn = url.openConnection();
            if (conn instanceof HttpURLConnection) {
                ((HttpURLConnection) conn).setRequestMethod("HEAD");
            }
            conn.getInputStream();
            return conn.getContentLength();
        } catch (Exception e) {
            //throw new RuntimeException(e);
            return 0;
        } finally {
            if (conn instanceof HttpURLConnection) {
                ((HttpURLConnection) conn).disconnect();
            }
        }
    }

    public String getFileName() {
        return this.name;
    }

    public String getUrl() {
        return this.url.toString();
    }

    /**
     * open in browser or special files in their default applications
     */
    public void openFile() throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.open(new File(this.path));
    }

    public String getPath() {
        return this.path;
    }
    
    public void setStatus(boolean stat){
        this.status = stat;
    }
}

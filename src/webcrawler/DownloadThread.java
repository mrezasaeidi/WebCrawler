
package webcrawler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JProgressBar;

/**
 *
 * @author PGR0101
 */
public class DownloadThread extends Thread {

    // can get the progressbar and set its value here 
    private URL url;
    private JProgressBar jp = null;
    private File out;
    private boolean status = false;

    /**
     * out is a file with the address have to be save directory structure
     *
     */
    public DownloadThread(URL url, File out, JProgressBar jp) {
        this.jp = jp;
        this.out = out;
        this.url = url;
        this.start();
    }

    public DownloadThread(URL url, File out) {
        this.out = out;
        this.url = url;
        this.start();
    }

    @Override
    public void run() {
        try {
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            double filesize = (double) http.getContentLength();
            BufferedInputStream in = new BufferedInputStream(http.getInputStream());
            FileOutputStream fos = new FileOutputStream(out);
            BufferedOutputStream bos = new BufferedOutputStream(fos, 1024);
            byte[] buffer = new byte[1024];
            double downloaded = 0.00;
            int read = 0;
            double percentDownloaded = 0.00;
            while ((read = in.read(buffer, 0, 1024)) >= 0) {
                
                // does the job here for finding links if it is a html file
                bos.write(buffer, 0, read);
                downloaded += read;
                if (jp != null) {
                    percentDownloaded += (downloaded * 100 / filesize);  
                    this.jp.setMaximum((int) filesize);
                    this.jp.setValue((int) percentDownloaded);
                    this.jp.setString(percentDownloaded+"%");
                }
            }
            this.status = true;
            bos.close();
            in.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public boolean getStatus() {
        return this.status;
    }

}

// we have to do the recursion part here and we have to get a depth here to check if needed to make new Thread
// again and add them as child or Children we have to check it
// is it good to start many threads here 
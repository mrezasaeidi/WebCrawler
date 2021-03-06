package webcrawler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JProgressBar;

/**
 *
 * @author PGR0101
 */
public class DownloadThread extends Thread {

    private URL url;
    private JProgressBar jp = null;
    private File out;
    private DownloadFile df;

    public DownloadThread(URL url, File out, JProgressBar jp , DownloadFile df) {
        this.jp = jp;
        this.out = out;
        this.df = df;
        this.url = url;
        this.start();
    }

    public DownloadThread(URL url, File out , DownloadFile df) {
        this.out = out;
        this.url = url;
        this.df = df;
        this.start();
    }

    @Override
    public void run() {
        try {
            HttpURLConnection http = null;
            http = (HttpURLConnection) url.openConnection();
            double filesize = (double) http.getContentLength();
            BufferedInputStream in = new BufferedInputStream(http.getInputStream());
            FileOutputStream fos = new FileOutputStream(out);
            System.out.println(out.getPath());
            BufferedOutputStream bos = new BufferedOutputStream(fos, 1024);
            byte[] buffer = new byte[1024];
            double downloaded = 0.00;
            int read = 0;
            double percentDownloaded = 0.00;
            while ((read = in.read(buffer, 0, 1024)) >= 0) {
                bos.write(buffer, 0, read);
                downloaded += read;
                if (jp != null) {
                    percentDownloaded += (downloaded * 100 / filesize);
                    this.jp.setMaximum((int) filesize);
                    this.jp.setValue((int) downloaded);
                    this.jp.setString(percentDownloaded + "%");
                }
            }
            this.df.setStatus(true);
            bos.close();
            in.close();

        } catch (Exception ioe) {
            //ioe.printStackTrace();
        }
    }


}

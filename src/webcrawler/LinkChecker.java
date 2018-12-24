package webcrawler;

import java.io.File;
import webcrawler.Tree;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 *
 * @author pgr0101
 */
public class LinkChecker extends Thread {

    private Node<String> root; // the first link
    private int depth;

    public LinkChecker(Node<String> root, int depth) {
        this.root = root;
        this.depth = depth;
        this.start();
    }

    /**
     * returns the source of html page
     *
     */
    public String get_links(String link) {
        try {
            String webPage = link;
            URL url = new URL(webPage);
            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);

            int numCharsRead;
            char[] charArray = new char[1024];
            StringBuffer sb = new StringBuffer();
            while ((numCharsRead = isr.read(charArray)) > 0) {
                sb.append(charArray, 0, numCharsRead);
            }
            String result = sb.toString();
            return result;
        } catch (PatternSyntaxException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void run() {
        String root = this.root.getData();
        if (root.indexOf(".rar") != -1 || root.indexOf(".png") != -1 || root.indexOf(".jpg") != -1
                || root.indexOf(".css") != -1 || root.indexOf(".zip") != -1 || root.indexOf(".png") != -1
                || root.indexOf(".mp4") != -1 || root.indexOf(".mkv") != -1) {
            DownloadFile df = null;
            try {
                df = new DownloadFile(this.root.getData(), this.root.getPath() + root);
                //df.startDownload();
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            Node<DownloadFile> newnode = new Node<DownloadFile>(df , this.root.getPath() + root);
            newnode.setParent(newnode);
            this.root.addChild(newnode);
            return;
        }
        if (depth == 0) {
            return;
        }
        try {
            String path = this.root.getParent().getPath() + root;
            File theDir = new File(path);
            theDir.mkdir();
            String input;
            input = get_links(root);
            String patternString = "href=\\\"(http|https):[\\w-/\\?=&.]*\"";
            Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                String match = input.substring(start, end);
                match = match.replace("href=", "");
                match = match.replace("\"", "");
                Node<String> newnode = new Node<String>(match, path);
                newnode.setParent(this.root);
                this.root.addChild(newnode);
                new LinkChecker(newnode, depth - 1);
            }
        } catch (PatternSyntaxException e) {
            e.printStackTrace();
        }

    }
}

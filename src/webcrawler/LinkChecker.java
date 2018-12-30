package webcrawler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 *
 * @author pgr0101
 */
public class LinkChecker extends Thread {

    private Node root; // the first link
    private int depth;

    public LinkChecker(Node root, int depth) {
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
            //e.printStackTrace();
        } catch (MalformedURLException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return "";
    }

    @Override
    public void run() {
        String URL = this.root.getURL();
        System.out.println(this.root.getURL());
        if (URL.indexOf(".rar") != -1 || URL.indexOf(".png") != -1 || URL.indexOf(".jpg") != -1
                || URL.indexOf(".css") != -1 || URL.indexOf(".zip") != -1 || URL.indexOf(".png") != -1
                || URL.indexOf(".mp4") != -1 || URL.indexOf(".mkv") != -1 || URL.indexOf(".js") != -1
                || URL.indexOf(".jpeg") != -1) {
            DownloadFile df = null;
            try {
                df = new DownloadFile(URL, this.root.getParent().getPath());
                Main.df.add(df);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            Node newnode = new Node(df);
            newnode.setParent(this.root);
            this.root.addChild(newnode);
            return;
        }
        if (depth == 0) {
            return;
        }
        // if it is a html file 
        try {
            String completepath = "";
            if (this.root.getParent() == null) {
                completepath = this.root.getPath();
            } else {
                completepath = this.root.getParent().getPath() + "\\" ;
            }
            File theDir = new File(completepath + URL.replace('/', ' '));
            theDir.mkdir();
            String input;
            input = get_links(URL);
            String patternString = "(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

            Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                String match = matcher.group();
                System.out.println(match);
                Node newnode = new Node(match, completepath);
                newnode.setParent(this.root);
                this.root.addChild(newnode);
                Main.allnodes.add(newnode);
                try {
                    new LinkChecker(newnode, depth - 1).join();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
        } catch (PatternSyntaxException e) {
            e.printStackTrace();
        }
        System.gc();
    }
}
/*match = match.replace("href=", "");
                match = match.replace("\"", "");
                match = match.replace("src=", "");
                match = match.replace("https", "http");
                match = match.replace("SRC=", "");
                match = match.replace("HREF=", "");*/
// input.substring(start, end);

//int start = matcher.start();
//int end = matcher.end();
//"^[a-zA-Z0-9\\-\\.]{1,}\\.(com|org|net|mil|edu|COM|ORG|NET|MIL|EDU)" ;
            //"\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";

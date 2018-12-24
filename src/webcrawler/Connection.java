package connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.LinkedList;
import java.util.Queue;

public class Connection {

    public static Queue<String> files = new LinkedList<>();

    public static String get_links(String link) {
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

    public static void extract(String link, int depth) {
        if (link.indexOf(".rar") != -1 || link.indexOf(".png") != -1 || link.indexOf(".jpg") != -1 /*|| link.indexOf(".css") != -1 || link.indexOf(".php") != -1 || link.indexOf(".xml") != -1*/) {
            files.add(link);
            return;
        }
        if (depth == 0) {
            return;
        }
        try {
            String input;
            input = get_links(link);

            String patternString = "href=\\\"(http|https):[\\w-/\\?=&.]*\"";
            Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                String match = input.substring(start, end);
                match = match.replace("href=", "");
                match = match.replace("\"", "");
                System.out.println(match);
                extract(match, depth - 1);
            }
        } catch (PatternSyntaxException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        extract("http://p30download.com/fa/entry/83963/",2);

        for (int i = 0; i < files.size(); i++) {
            System.out.println(files.remove());
        }

    }
}

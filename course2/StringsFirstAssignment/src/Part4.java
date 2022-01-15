import java.net.*;
import java.io.*;
import java.util.Locale;

import edu.duke.*;

public class Part4 {
    public static void main(String[] args) {
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
       // URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html")
        int count = 0;
        for (String s : ur.lines()) {
            String temp = s.toLowerCase();
            int index = temp.indexOf("youtube.com");
            if(index >= 0){
                int first = s.indexOf("<a href=\"");
                int last = s.lastIndexOf("\">");
                String resultStr = s.substring(first+9, last);
                System.out.println(resultStr);

                count++;
            }
        }
        System.out.println(count);

    }

}

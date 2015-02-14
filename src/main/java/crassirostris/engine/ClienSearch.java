package crassirostris.engine;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by crassirostris on 15. 2. 7..
 */
public class ClienSearch {
    public void process() throws IOException {
        Document document = Jsoup.connect("http://www.clien.net/cs2/bbs/board.php?bo_table=news").get();
        Elements select = document.select(".board_main .mytr .post_subject");

        for (Element element : select) {
            System.out.println(element.text());
        }
    }
}

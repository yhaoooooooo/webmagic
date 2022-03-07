package us.codecraft.webmagic.utils;

import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.selector.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Tools for annotation converting. <br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.2.1
 */
public class ExtractorUtils {

    public static Selector getSelector(ExtractBy extractBy) {
        String value = extractBy.value();
        Selector selector;
        switch (extractBy.type()) {
            case Css:

                String attr = extractBy.attribute();
                    selector = StringUtils.isEmpty(attr) ? new CssSelector(value) : new CssSelector(value,attr);

                break;
            case Regex:
                selector = new RegexSelector(value);
                break;
            case XPath:
                selector = new XpathSelector(value);
                break;
            case JsonPath:
                selector = new JsonPathSelector(value);
                break;
            default:
                selector = new XpathSelector(value);
        }
        return selector;
    }

    public static List<Selector> getSelectors(ExtractBy[] extractBies) {
        List<Selector> selectors = new ArrayList<Selector>();
        if (extractBies == null) {
            return selectors;
        }
        for (ExtractBy extractBy : extractBies) {
            selectors.add(getSelector(extractBy));
        }
        return selectors;
    }
}

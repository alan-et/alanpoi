package com.alanpoi.analysis.xml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author pengzhuoxun
 * @since 1.3.4
 */
public class Label {

    public static final String XML_BEGIN_SYMBOL = "<%s %s>";
    public static final String XML_BEGIN_2_SYMBOL = "<%s>";
    public static final String XML_END_SYMBOL = "</%s>";

    protected String name;

    protected Map<String, String> prop;

    protected String val;

    protected List<Label> childs;

    public Label() {
        childs = new ArrayList<>();
    }

    public Label(String name, Map<String, String> prop, String val) {
        this.name = name;
        this.prop = prop;
        this.val = val;
        childs = new ArrayList<>();
    }

    public String formatXml() {
        StringBuilder label_xml = new StringBuilder("");
        StringBuilder label_prop = new StringBuilder("");
        if (prop != null && prop.size() > 0) {
            int i = 0;
            Set<String> keySet = prop.keySet();
            for (String key : keySet) {
                label_prop.append(key).append("=\"").append(prop.get(key)).append("\"");
                if (++i < keySet.size()) label_prop.append(" ");
            }
            label_xml.append(String.format(XML_BEGIN_SYMBOL, name, label_prop.toString()));

        } else {
            label_xml.append(String.format(XML_BEGIN_2_SYMBOL, name));
        }
        label_xml.append(val);
        if (childs != null && childs.size() > 0) {
            childs.forEach(e -> {
                label_xml.append(e.formatXml());
            });

        }
        label_xml.append(String.format(XML_END_SYMBOL, name));
        return label_xml.toString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProp(Map<String, String> prop) {
        this.prop = prop;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public void setChild(List<Label> childs) {
        this.childs = childs;
    }

    public void addChild(Label child) {
        this.childs.add(child);
    }
}

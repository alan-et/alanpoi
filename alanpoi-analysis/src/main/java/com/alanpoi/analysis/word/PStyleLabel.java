package com.alanpoi.analysis.word;

import com.alanpoi.common.enums.LabelName;

import java.util.Map;
import java.util.Set;

public class PStyleLabel extends Label {

    public static final String XML_BEGIN_SYMBOL = "<%s %s/>";

    public PStyleLabel(Map<String, String> prop) {
        this.name = LabelName.pStyle.value;
        this.prop = prop;
    }

    protected String formatXml() {
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
        if (childs != null && childs.size() > 0) {
            childs.forEach(e -> {
                label_xml.append(e.formatXml());
            });

        }
        return label_xml.toString();
    }
}

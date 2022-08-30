package com.alanpoi.excel.parse;

import com.alanpoi.excel.common.Hyperlink;
import com.alanpoi.xml.AbstractParse;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class WorkRelDocument extends AbstractParse {


    private File workRelFile;
    private Document doc;

    public WorkRelDocument(File workRelFile) throws IOException, JDOMException {
        this.workRelFile = workRelFile;
        SAXBuilder saxBuilder = new SAXBuilder();
        doc = saxBuilder.build(workRelFile);
    }

    public boolean write(List<Hyperlink> hyperlinkList) throws IOException {
        Element root = doc.getRootElement();
        for (int i = 0; i < hyperlinkList.size(); i++) {
            Hyperlink hyperlink = hyperlinkList.get(i);
            Element element = new Element("Relationship", root.getNamespace());
            element.setAttribute("Id", hyperlink.getrId());
            element.setAttribute("Type", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/hyperlink");
            element.setAttribute("Target", hyperlink.getTarget());
            element.setAttribute("TargetMode", hyperlink.getTargetMode());
            root.addContent(element);
        }
        XMLOutputter outPutter = new XMLOutputter();
        outPutter.output(doc, new FileOutputStream(workRelFile));
        return true;
    }
}

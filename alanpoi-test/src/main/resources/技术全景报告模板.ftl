<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<?mso-application progid="Word.Document"?>
<pkg:package xmlns:pkg="http://schemas.microsoft.com/office/2006/xmlPackage">
    <pkg:part pkg:name="/_rels/.rels" pkg:contentType="application/vnd.openxmlformats-package.relationships+xml"
              pkg:padding="512">
        <pkg:xmlData>
            <Relationships xmlns="http://schemas.openxmlformats.org/package/2006/relationships">
                <Relationship Id="rId3"
                              Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/extended-properties"
                              Target="docProps/app.xml"/>
                <Relationship Id="rId2"
                              Type="http://schemas.openxmlformats.org/package/2006/relationships/metadata/core-properties"
                              Target="docProps/core.xml"/>
                <Relationship Id="rId1"
                              Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument"
                              Target="word/document.xml"/>
            </Relationships>
        </pkg:xmlData>
    </pkg:part>
    <pkg:part pkg:name="/word/_rels/document.xml.rels"
              pkg:contentType="application/vnd.openxmlformats-package.relationships+xml" pkg:padding="256">
        <pkg:xmlData>
            <Relationships xmlns="http://schemas.openxmlformats.org/package/2006/relationships">
                <Relationship Id="rId8"
                              Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/hyperlink"
                              Target="http://www.qizhidao.com" TargetMode="External"/>
                <Relationship Id="rId13"
                              Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/fontTable"
                              Target="fontTable.xml"/>
                <Relationship Id="rId3"
                              Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/settings"
                              Target="settings.xml"/>
                <Relationship Id="rId7"
                              Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/hyperlink"
                              Target="http://www.qizhidao.com" TargetMode="External"/>
                <Relationship Id="rId12"
                              Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/footer"
                              Target="footer1.xml"/>
                <Relationship Id="rId2"
                              Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/styles"
                              Target="styles.xml"/>
                <Relationship Id="rId1"
                              Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/customXml"
                              Target="../customXml/item1.xml"/>
                <Relationship Id="rId6"
                              Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/endnotes"
                              Target="endnotes.xml"/>
                <Relationship Id="rId11"
                              Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/header"
                              Target="header2.xml"/>
                <Relationship Id="rId5"
                              Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/footnotes"
                              Target="footnotes.xml"/>
                <Relationship Id="rId10"
                              Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/header"
                              Target="header1.xml"/>
                <Relationship Id="rId4"
                              Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/webSettings"
                              Target="webSettings.xml"/>
                <#list groupList as group>
                    <#list group.dataList as data>
                        <Relationship Id="rIdCus${group_index}-${data_index}"
                                      Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/image"
                                      Target="media/${data.name}.png"/>
                    </#list>
                </#list>
                <Relationship Id="rId14"
                              Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/theme"
                              Target="theme/theme1.xml"/>
            </Relationships>
        </pkg:xmlData>
    </pkg:part>
    <pkg:part pkg:name="/word/document.xml"
              pkg:contentType="application/vnd.openxmlformats-officedocument.wordprocessingml.document.main+xml">
        <pkg:xmlData>
            <w:document xmlns:wpc="http://schemas.microsoft.com/office/word/2010/wordprocessingCanvas"
                        xmlns:cx="http://schemas.microsoft.com/office/drawing/2014/chartex"
                        xmlns:cx1="http://schemas.microsoft.com/office/drawing/2015/9/8/chartex"
                        xmlns:cx2="http://schemas.microsoft.com/office/drawing/2015/10/21/chartex"
                        xmlns:cx3="http://schemas.microsoft.com/office/drawing/2016/5/9/chartex"
                        xmlns:cx4="http://schemas.microsoft.com/office/drawing/2016/5/10/chartex"
                        xmlns:cx5="http://schemas.microsoft.com/office/drawing/2016/5/11/chartex"
                        xmlns:cx6="http://schemas.microsoft.com/office/drawing/2016/5/12/chartex"
                        xmlns:cx7="http://schemas.microsoft.com/office/drawing/2016/5/13/chartex"
                        xmlns:cx8="http://schemas.microsoft.com/office/drawing/2016/5/14/chartex"
                        xmlns:mc="http://schemas.openxmlformats.org/markup-compatibility/2006"
                        xmlns:aink="http://schemas.microsoft.com/office/drawing/2016/ink"
                        xmlns:am3d="http://schemas.microsoft.com/office/drawing/2017/model3d"
                        xmlns:o="urn:schemas-microsoft-com:office:office"
                        xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships"
                        xmlns:m="http://schemas.openxmlformats.org/officeDocument/2006/math"
                        xmlns:v="urn:schemas-microsoft-com:vml"
                        xmlns:wp14="http://schemas.microsoft.com/office/word/2010/wordprocessingDrawing"
                        xmlns:wp="http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing"
                        xmlns:w10="urn:schemas-microsoft-com:office:word"
                        xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main"
                        xmlns:w14="http://schemas.microsoft.com/office/word/2010/wordml"
                        xmlns:w15="http://schemas.microsoft.com/office/word/2012/wordml"
                        xmlns:w16cex="http://schemas.microsoft.com/office/word/2018/wordml/cex"
                        xmlns:w16cid="http://schemas.microsoft.com/office/word/2016/wordml/cid"
                        xmlns:w16="http://schemas.microsoft.com/office/word/2018/wordml"
                        xmlns:w16se="http://schemas.microsoft.com/office/word/2015/wordml/symex"
                        xmlns:wpg="http://schemas.microsoft.com/office/word/2010/wordprocessingGroup"
                        xmlns:wpi="http://schemas.microsoft.com/office/word/2010/wordprocessingInk"
                        xmlns:wne="http://schemas.microsoft.com/office/word/2006/wordml"
                        xmlns:wps="http://schemas.microsoft.com/office/word/2010/wordprocessingShape"
                        mc:Ignorable="w14 w15 w16se w16cid w16 w16cex wp14">
                <w:body>
                    <w:p w14:paraId="4380E821" w14:textId="77777777" w:rsidR="00636926" w:rsidRDefault="00636926"
                         w:rsidP="00636926">
                        <w:pPr>
                            <w:pStyle w:val="a5"/>
                            <w:spacing w:line="1080" w:lineRule="auto"/>
                            <w:jc w:val="left"/>
                            <w:rPr>
                                <w:rFonts w:ascii="宋体" w:eastAsia="宋体" w:hAnsi="宋体" w:cs="宋体" w:hint="eastAsia"/>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                        </w:pPr>
                        <w:bookmarkStart w:id="0" w:name="_Toc69462914"/>
                    </w:p>
                    <w:p w14:paraId="435621D7" w14:textId="77777777" w:rsidR="00636926" w:rsidRDefault="00636926"
                         w:rsidP="00636926">
                        <w:pPr>
                            <w:pStyle w:val="a5"/>
                            <w:rPr>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                        </w:pPr>
                        <w:r w:rsidRPr="00482922">
                            <w:rPr>
                                <w:rFonts w:ascii="宋体" w:eastAsia="宋体" w:hAnsi="宋体" w:cs="宋体" w:hint="eastAsia"/>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>技术全景</w:t>
                        </w:r>
                        <w:r>
                            <w:rPr>
                                <w:rFonts w:ascii="宋体" w:eastAsia="宋体" w:hAnsi="宋体" w:cs="宋体" w:hint="eastAsia"/>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>分析</w:t>
                        </w:r>
                        <w:r w:rsidRPr="00482922">
                            <w:rPr>
                                <w:rFonts w:ascii="宋体" w:eastAsia="宋体" w:hAnsi="宋体" w:cs="宋体" w:hint="eastAsia"/>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>报告</w:t>
                        </w:r>
                    </w:p>
                    <w:p w14:paraId="5A5D0581" w14:textId="77777777" w:rsidR="00636926" w:rsidRPr="0043393C"
                         w:rsidRDefault="00636926" w:rsidP="00636926">
                        <w:pPr>
                            <w:pStyle w:val="a5"/>
                            <w:spacing w:line="480" w:lineRule="auto"/>
                            <w:jc w:val="left"/>
                            <w:rPr>
                                <w:rStyle w:val="afc"/>
                                <w:rFonts w:ascii="DengXian" w:eastAsia="DengXian" w:hAnsi="DengXian"
                                          w:hint="eastAsia"/>
                                <w:b/>
                                <w:bCs/>
                                <w:sz w:val="22"/>
                                <w:szCs w:val="22"/>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                        </w:pPr>
                    </w:p>
                    <w:p w14:paraId="3DA8FBE2" w14:textId="77777777" w:rsidR="00636926" w:rsidRDefault="00636926"
                         w:rsidP="00636926">
                        <w:pPr>
                            <w:pStyle w:val="a5"/>
                            <w:rPr>
                                <w:rFonts w:ascii="微软雅黑" w:eastAsia="微软雅黑" w:hAnsi="微软雅黑"/>
                                <w:color w:val="7F7F7F"/>
                                <w:sz w:val="21"/>
                                <w:szCs w:val="21"/>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                        </w:pPr>
                        <w:r>
                            <w:rPr>
                                <w:rFonts w:ascii="微软雅黑" w:eastAsia="微软雅黑" w:hAnsi="微软雅黑" w:hint="eastAsia"/>
                                <w:color w:val="7F7F7F"/>
                                <w:sz w:val="21"/>
                                <w:szCs w:val="21"/>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>制作人:</w:t>
                        </w:r>
                        <w:proofErr w:type="spellStart"/>
                        <w:r>
                            <w:rPr>
                                <w:rFonts w:ascii="微软雅黑" w:eastAsia="微软雅黑" w:hAnsi="微软雅黑" w:hint="eastAsia"/>
                                <w:color w:val="7F7F7F"/>
                                <w:sz w:val="21"/>
                                <w:szCs w:val="21"/>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>weizheng</w:t>
                        </w:r>
                        <w:proofErr w:type="spellEnd"/>
                    </w:p>
                    <w:p w14:paraId="16D3D4A9" w14:textId="77777777" w:rsidR="00636926" w:rsidRDefault="00636926"
                         w:rsidP="00636926">
                        <w:pPr>
                            <w:pStyle w:val="a5"/>
                            <w:spacing w:line="480" w:lineRule="auto"/>
                            <w:rPr>
                                <w:rFonts w:ascii="微软雅黑" w:eastAsia="微软雅黑" w:hAnsi="微软雅黑"/>
                                <w:color w:val="7F7F7F"/>
                                <w:sz w:val="21"/>
                                <w:szCs w:val="21"/>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                        </w:pPr>
                        <w:r>
                            <w:rPr>
                                <w:rFonts w:ascii="微软雅黑" w:eastAsia="微软雅黑" w:hAnsi="微软雅黑" w:hint="eastAsia"/>
                                <w:color w:val="7F7F7F"/>
                                <w:sz w:val="21"/>
                                <w:szCs w:val="21"/>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>时 间:${createDate}</w:t>
                        </w:r>
                    </w:p>
                    <w:p w14:paraId="799FAD68" w14:textId="77777777" w:rsidR="00636926" w:rsidRPr="00892BA1"
                         w:rsidRDefault="00636926" w:rsidP="00636926">
                        <w:pPr>
                            <w:pStyle w:val="a9"/>
                        </w:pPr>
                    </w:p>
                    <w:p w14:paraId="28A2258B" w14:textId="77777777" w:rsidR="00636926" w:rsidRDefault="00636926"
                         w:rsidP="00636926">
                        <w:pPr>
                            <w:pStyle w:val="ac"/>
                            <w:spacing w:line="780" w:lineRule="auto"/>
                        </w:pPr>
                    </w:p>
                    <w:p w14:paraId="6F86FBAB" w14:textId="77777777" w:rsidR="00636926" w:rsidRPr="00892BA1"
                         w:rsidRDefault="00636926" w:rsidP="00636926">
                        <w:pPr>
                            <w:pStyle w:val="ac"/>
                            <w:spacing w:line="360" w:lineRule="auto"/>
                            <w:rPr>
                                <w:rFonts w:ascii="微软雅黑" w:eastAsia="微软雅黑" w:hAnsi="微软雅黑"/>
                                <w:color w:val="FFFFFF" w:themeColor="background1"/>
                                <w:sz w:val="21"/>
                                <w:szCs w:val="21"/>
                            </w:rPr>
                        </w:pPr>
                        <w:r w:rsidRPr="00892BA1">
                            <w:rPr>
                                <w:rFonts w:ascii="微软雅黑" w:eastAsia="微软雅黑" w:hAnsi="微软雅黑" w:cs="Arial"/>
                                <w:color w:val="FFFFFF" w:themeColor="background1"/>
                                <w:sz w:val="21"/>
                                <w:szCs w:val="21"/>
                            </w:rPr>
                            <w:fldChar w:fldCharType="begin"/>
                        </w:r>
                        <w:r w:rsidRPr="00892BA1">
                            <w:rPr>
                                <w:rFonts w:ascii="微软雅黑" w:eastAsia="微软雅黑" w:hAnsi="微软雅黑" w:cs="Arial"/>
                                <w:color w:val="FFFFFF" w:themeColor="background1"/>
                                <w:sz w:val="21"/>
                                <w:szCs w:val="21"/>
                            </w:rPr>
                            <w:instrText xml:space="preserve"> HYPERLINK "http://www.qizhidao.com" </w:instrText>
                        </w:r>
                        <w:r w:rsidRPr="00892BA1">
                            <w:rPr>
                                <w:rFonts w:ascii="微软雅黑" w:eastAsia="微软雅黑" w:hAnsi="微软雅黑" w:cs="Arial"/>
                                <w:color w:val="FFFFFF" w:themeColor="background1"/>
                                <w:sz w:val="21"/>
                                <w:szCs w:val="21"/>
                            </w:rPr>
                            <w:fldChar w:fldCharType="separate"/>
                        </w:r>
                        <w:r w:rsidRPr="00892BA1">
                            <w:rPr>
                                <w:rStyle w:val="afe"/>
                                <w:rFonts w:ascii="微软雅黑" w:eastAsia="微软雅黑" w:hAnsi="微软雅黑" w:cs="Arial"/>
                                <w:color w:val="FFFFFF" w:themeColor="background1"/>
                                <w:sz w:val="21"/>
                                <w:szCs w:val="21"/>
                            </w:rPr>
                            <w:t>www.</w:t>
                        </w:r>
                        <w:r w:rsidRPr="00892BA1">
                            <w:rPr>
                                <w:rStyle w:val="afe"/>
                                <w:rFonts w:ascii="微软雅黑" w:eastAsia="微软雅黑" w:hAnsi="微软雅黑" w:cs="Arial"/>
                                <w:color w:val="FFFFFF" w:themeColor="background1"/>
                                <w:sz w:val="21"/>
                                <w:szCs w:val="21"/>
                            </w:rPr>
                            <w:t>q</w:t>
                        </w:r>
                        <w:r w:rsidRPr="00892BA1">
                            <w:rPr>
                                <w:rStyle w:val="afe"/>
                                <w:rFonts w:ascii="微软雅黑" w:eastAsia="微软雅黑" w:hAnsi="微软雅黑" w:cs="Arial"/>
                                <w:color w:val="FFFFFF" w:themeColor="background1"/>
                                <w:sz w:val="21"/>
                                <w:szCs w:val="21"/>
                            </w:rPr>
                            <w:t>izhidao.com</w:t>
                        </w:r>
                        <w:r w:rsidRPr="00892BA1">
                            <w:rPr>
                                <w:rFonts w:ascii="微软雅黑" w:eastAsia="微软雅黑" w:hAnsi="微软雅黑" w:cs="Arial"/>
                                <w:color w:val="FFFFFF" w:themeColor="background1"/>
                                <w:sz w:val="21"/>
                                <w:szCs w:val="21"/>
                            </w:rPr>
                            <w:fldChar w:fldCharType="end"/>
                        </w:r>
                    </w:p>
                    <w:p w14:paraId="2871B57A" w14:textId="77777777" w:rsidR="00636926" w:rsidRPr="00892BA1"
                         w:rsidRDefault="00636926" w:rsidP="00636926">
                        <w:pPr>
                            <w:pStyle w:val="ac"/>
                            <w:spacing w:line="480" w:lineRule="auto"/>
                            <w:rPr>
                                <w:rFonts w:ascii="微软雅黑" w:eastAsia="微软雅黑" w:hAnsi="微软雅黑"/>
                                <w:color w:val="7F7F7F"/>
                                <w:sz w:val="21"/>
                                <w:szCs w:val="21"/>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                        </w:pPr>
                        <w:r w:rsidRPr="00892BA1">
                            <w:rPr>
                                <w:rFonts w:ascii="微软雅黑" w:eastAsia="微软雅黑" w:hAnsi="微软雅黑" w:hint="eastAsia"/>
                                <w:color w:val="7F7F7F"/>
                                <w:sz w:val="21"/>
                                <w:szCs w:val="21"/>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>企知道</w:t>
                        </w:r>
                    </w:p>
                    <w:p w14:paraId="1A43697A" w14:textId="77777777" w:rsidR="00636926" w:rsidRPr="00636926"
                         w:rsidRDefault="00636926" w:rsidP="00636926">
                        <w:pPr>
                            <w:rPr>
                                <w:rFonts w:hint="eastAsia"/>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                        </w:pPr>
                    </w:p>
                    <w:p w14:paraId="1A533DE3" w14:textId="2EB29D74" w:rsidR="008A69A6" w:rsidRDefault="00C5384D">
                        <w:pPr>
                            <w:pStyle w:val="ae"/>
                            <w:keepNext w:val="0"/>
                            <w:keepLines w:val="0"/>
                            <w:pageBreakBefore/>
                            <w:rPr>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                        </w:pPr>
                        <w:r>
                            <w:rPr>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>数据范围</w:t>
                        </w:r>
                        <w:bookmarkEnd w:id="0"/>
                    </w:p>
                    <w:p w14:paraId="0679F27A" w14:textId="3809313B" w:rsidR="008A69A6" w:rsidRDefault="00C5384D">
                        <w:pPr>
                            <w:pStyle w:val="af"/>
                            <w:keepNext w:val="0"/>
                            <w:keepLines w:val="0"/>
                            <w:rPr>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                        </w:pPr>
                        <w:r>
                            <w:rPr>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>在</w:t>
                        </w:r>
                        <w:bookmarkStart w:id="1" w:name="OLE_LINK11"/>
                        <w:bookmarkStart w:id="2" w:name="OLE_LINK12"/>
                        <w:r w:rsidR="00AA0EB9">
                            <w:rPr>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>${countryNum}</w:t>
                        </w:r>
                        <w:bookmarkEnd w:id="1"/>
                        <w:bookmarkEnd w:id="2"/>
                        <w:r>
                            <w:rPr>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>个国家</w:t>
                        </w:r>
                        <w:r>
                            <w:rPr>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>/</w:t>
                        </w:r>
                        <w:r>
                            <w:rPr>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>地区中，搜索出</w:t>
                        </w:r>
                        <w:r w:rsidR="00AA0EB9">
                            <w:rPr>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>${patentNum}</w:t>
                        </w:r>
                        <w:r>
                            <w:rPr>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>组简单同族专利</w:t>
                        </w:r>
                    </w:p>
                    <w:p w14:paraId="0F57F0F7" w14:textId="77777777" w:rsidR="008A69A6" w:rsidRDefault="00C5384D">
                        <w:pPr>
                            <w:pStyle w:val="af0"/>
                            <w:keepNext w:val="0"/>
                            <w:keepLines w:val="0"/>
                            <w:rPr>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                        </w:pPr>
                        <w:bookmarkStart w:id="3" w:name="_Toc69462915"/>
                        <w:r>
                            <w:rPr>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>检索条件</w:t>
                        </w:r>
                        <w:bookmarkEnd w:id="3"/>
                    </w:p>
                    <w:p w14:paraId="3BC3E183" w14:textId="2E58C0EF" w:rsidR="008A69A6" w:rsidRDefault="00C5384D">
                        <w:pPr>
                            <w:pStyle w:val="af1"/>
                            <w:keepNext w:val="0"/>
                            <w:keepLines w:val="0"/>
                            <w:rPr>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                        </w:pPr>
                        <w:r>
                            <w:rPr>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>检索式</w:t>
                        </w:r>
                        <w:r w:rsidR="00EF6A14">
                            <w:rPr>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t xml:space="preserve">: </w:t>
                        </w:r>
                        <w:r w:rsidR="00AA0EB9">
                            <w:rPr>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>${searchCond}</w:t>
                        </w:r>
                    </w:p>
                    <#list groupList as group>
                        <w:p w14:paraId="64AF37C1" w14:textId="77777777" w:rsidR="008A69A6" w:rsidRDefault="00C5384D">
                            <w:pPr>
                                <w:pStyle w:val="ae"/>
                                <w:pageBreakBefore/>
                                <w:rPr>
                                    <w:lang w:eastAsia="zh-CN"/>
                                </w:rPr>
                            </w:pPr>
                            <w:bookmarkStart w:id="6" w:name="_Toc69462916"/>
                            <w:r>
                                <w:rPr>
                                    <w:lang w:eastAsia="zh-CN"/>
                                </w:rPr>
                                <w:t>${group.title}</w:t>
                            </w:r>
                            <w:bookmarkEnd w:id="6"/>
                        </w:p>
                        <#list group.dataList as data>
                            <w:p w14:paraId="30FAC6CC" w14:textId="77777777" w:rsidR="00EF6A14"
                                 w:rsidRDefault="00C5384D" w:rsidP="008F6ACC">
                                <w:pPr>
                                    <w:pStyle w:val="af3"/>
                                    <w:rPr>
                                        <w:rFonts w:ascii="宋体" w:eastAsia="宋体" w:hAnsi="宋体" w:cs="宋体"/>
                                        <w:lang w:eastAsia="zh-CN"/>
                                    </w:rPr>
                                </w:pPr>
                                <w:bookmarkStart w:id="7" w:name="_Toc69462917"/>
                                <w:r>
                                    <w:rPr>
                                        <w:rFonts w:ascii="宋体" w:eastAsia="宋体" w:hAnsi="宋体" w:cs="宋体"
                                                  w:hint="eastAsia"/>
                                        <w:lang w:eastAsia="zh-CN"/>
                                    </w:rPr>
                                    <w:t>${data.title}</w:t>
                                </w:r>
                                <w:bookmarkEnd w:id="7"/>
                            </w:p>
                            <w:p w14:paraId="1FA9583A" w14:textId="69863512" w:rsidR="00E56352"
                                 w:rsidRDefault="003D04AB" w:rsidP="00E56352">
                                <w:pPr>
                                    <w:rPr>
                                        <w:lang w:eastAsia="zh-CN"/>
                                    </w:rPr>
                                </w:pPr>
                                <w:r>
                                    <w:rPr>
                                        <w:rFonts w:hint="eastAsia"/>
                                        <w:lang w:eastAsia="zh-CN"/>
                                    </w:rPr>
                                    <w:t>${data.desc}</w:t>
                                </w:r>
                            </w:p>
                            <w:p w14:paraId="0434299A" w14:textId="438D969C" w:rsidR="008F6ACC"
                                 w:rsidRDefault="008F6ACC" w:rsidP="003D04AB">
                                <w:pPr>
                                    <w:rPr>
                                        <w:noProof/>
                                        <w:lang w:eastAsia="zh-CN"/>
                                    </w:rPr>
                                </w:pPr>
                                <w:r>
                                    <w:rPr>
                                        <w:noProof/>
                                        <w:lang w:eastAsia="zh-CN"/>
                                    </w:rPr>
                                    <w:drawing>
                                        <wp:inline distT="0" distB="0" distL="0" distR="0" wp14:anchorId="014ABC32"
                                                   wp14:editId="2B227865">
                                            <wp:extent cx="5334000" cy="2495550"/>
                                            <wp:effectExtent l="0" t="0" r="0" b="0"/>
                                            <wp:docPr id="55" name="图片 55"/>
                                            <wp:cNvGraphicFramePr>
                                                <a:graphicFrameLocks
                                                        xmlns:a="http://schemas.openxmlformats.org/drawingml/2006/main"
                                                        noChangeAspect="1"/>
                                            </wp:cNvGraphicFramePr>
                                            <a:graphic xmlns:a="http://schemas.openxmlformats.org/drawingml/2006/main">
                                                <a:graphicData
                                                        uri="http://schemas.openxmlformats.org/drawingml/2006/picture">
                                                    <pic:pic
                                                            xmlns:pic="http://schemas.openxmlformats.org/drawingml/2006/picture">
                                                        <pic:nvPicPr>
                                                            <pic:cNvPr id="1" name=""/>
                                                            <pic:cNvPicPr/>
                                                        </pic:nvPicPr>
                                                        <pic:blipFill>
                                                            <a:blip r:embed="rIdCus${group_index}-${data_index}"/>
                                                            <a:stretch>
                                                                <a:fillRect/>
                                                            </a:stretch>
                                                        </pic:blipFill>
                                                        <pic:spPr>
                                                            <a:xfrm>
                                                                <a:off x="0" y="0"/>
                                                                <a:ext cx="5334000" cy="2495550"/>
                                                            </a:xfrm>
                                                            <a:prstGeom prst="rect">
                                                                <a:avLst/>
                                                            </a:prstGeom>
                                                        </pic:spPr>
                                                    </pic:pic>
                                                </a:graphicData>
                                            </a:graphic>
                                        </wp:inline>
                                    </w:drawing>
                                </w:r>
                            </w:p>
                        </#list>
                    </#list>

                    <w:p w14:paraId="602CAC1F" w14:textId="77777777" w:rsidR="008F6ACC" w:rsidRDefault="008F6ACC">
                        <w:pPr>
                            <w:rPr>
                                <w:noProof/>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                        </w:pPr>
                    </w:p>
                    <w:p w14:paraId="1AE2C14D" w14:textId="77777777" w:rsidR="008A69A6" w:rsidRDefault="005719A2">
                        <w:pPr>
                            <w:pStyle w:val="af1"/>
                            <w:keepNext w:val="0"/>
                            <w:keepLines w:val="0"/>
                            <w:pageBreakBefore/>
                            <w:rPr>
                                <w:rFonts w:eastAsiaTheme="minorEastAsia"/>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                        </w:pPr>
                        <w:r w:rsidRPr="005719A2">
                            <w:rPr>
                                <w:color w:val="FF0000"/>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>（申明</w:t>
                        </w:r>
                        <w:r>
                            <w:rPr>
                                <w:color w:val="FF0000"/>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>的文案</w:t>
                        </w:r>
                        <w:r w:rsidRPr="005719A2">
                            <w:rPr>
                                <w:color w:val="FF0000"/>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>待定）</w:t>
                        </w:r>
                        <w:r w:rsidR="00864A1B" w:rsidRPr="005719A2">
                            <w:rPr>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>企知道</w:t>
                        </w:r>
                        <w:r w:rsidR="00C5384D" w:rsidRPr="005719A2">
                            <w:rPr>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>对于外部数据所作出的决定不承担任何责任</w:t>
                        </w:r>
                        <w:r w:rsidR="00C5384D" w:rsidRPr="005719A2">
                            <w:rPr>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t xml:space="preserve">:  </w:t>
                        </w:r>
                        <w:r w:rsidR="00C5384D" w:rsidRPr="005719A2">
                            <w:rPr>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>本报告</w:t>
                        </w:r>
                        <w:r w:rsidR="00C5384D" w:rsidRPr="005719A2">
                            <w:rPr>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>(</w:t>
                        </w:r>
                        <w:r w:rsidR="00C5384D" w:rsidRPr="005719A2">
                            <w:rPr>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>包括任何附件</w:t>
                        </w:r>
                        <w:r w:rsidR="00C5384D" w:rsidRPr="005719A2">
                            <w:rPr>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>)</w:t>
                        </w:r>
                        <w:r w:rsidR="00C5384D" w:rsidRPr="005719A2">
                            <w:rPr>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>
                                将会为您带来独家的权益及优势。如果本报告用于其他特殊用途或者第三方团体使用本报告作于特殊目的，智慧芽公司，其下属员工，包括其合作伙伴或代理商将不承担任何责任。本报告中的所有信息均严格符合平台规则，但不能完全保证信息的完整性、准确性及所得结果的准确性
                            </w:t>
                        </w:r>
                        <w:r w:rsidR="00C5384D" w:rsidRPr="005719A2">
                            <w:rPr>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>;</w:t>
                        </w:r>
                        <w:r w:rsidR="00C5384D" w:rsidRPr="005719A2">
                            <w:rPr>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                            <w:t>
                                本报告中不包含任何明示或暗示的保证信息，且不包含不适用于特定用途的说明。在任何情况下，根据本报告中所包含的信息或者分析结果所引发造成的损失和最终决策意见，智慧芽公司，其下属员工，包括其合作伙伴或代理商将不承担任何责任。除非智慧芽公司事先明确书面同意，本报告任何部分均不得转载，分发或传达给任何第三方。
                            </w:t>
                        </w:r>
                    </w:p>
                    <w:p w14:paraId="081E78F3" w14:textId="77777777" w:rsidR="005719A2" w:rsidRPr="005719A2"
                         w:rsidRDefault="005719A2">
                        <w:pPr>
                            <w:pStyle w:val="af1"/>
                            <w:keepNext w:val="0"/>
                            <w:keepLines w:val="0"/>
                            <w:pageBreakBefore/>
                            <w:rPr>
                                <w:rFonts w:eastAsiaTheme="minorEastAsia"/>
                                <w:lang w:eastAsia="zh-CN"/>
                            </w:rPr>
                        </w:pPr>
                    </w:p>
                    <w:sectPr w:rsidR="005719A2" w:rsidRPr="005719A2" w:rsidSect="00B051E7">
                        <w:headerReference w:type="default" r:id="rId10"/>
                        <w:headerReference w:type="first" r:id="rId11"/>
                        <w:footerReference w:type="first" r:id="rId12"/>
                        <w:pgSz w:w="11907" w:h="16839" w:code="9"/>
                        <w:pgMar w:top="1440" w:right="1440" w:bottom="1440" w:left="1440" w:header="680" w:footer="720"
                                 w:gutter="0"/>
                        <w:cols w:space="720"/>
                        <w:titlePg/>
                        <w:docGrid w:linePitch="299"/>
                    </w:sectPr>
                </w:body>
            </w:document>
        </pkg:xmlData>
    </pkg:part>
    <pkg:part pkg:name="/word/header2.xml"
              pkg:contentType="application/vnd.openxmlformats-officedocument.wordprocessingml.header+xml">
        <pkg:xmlData>
            <w:hdr xmlns:wpc="http://schemas.microsoft.com/office/word/2010/wordprocessingCanvas"
                   xmlns:cx="http://schemas.microsoft.com/office/drawing/2014/chartex"
                   xmlns:cx1="http://schemas.microsoft.com/office/drawing/2015/9/8/chartex"
                   xmlns:cx2="http://schemas.microsoft.com/office/drawing/2015/10/21/chartex"
                   xmlns:cx3="http://schemas.microsoft.com/office/drawing/2016/5/9/chartex"
                   xmlns:cx4="http://schemas.microsoft.com/office/drawing/2016/5/10/chartex"
                   xmlns:cx5="http://schemas.microsoft.com/office/drawing/2016/5/11/chartex"
                   xmlns:cx6="http://schemas.microsoft.com/office/drawing/2016/5/12/chartex"
                   xmlns:cx7="http://schemas.microsoft.com/office/drawing/2016/5/13/chartex"
                   xmlns:cx8="http://schemas.microsoft.com/office/drawing/2016/5/14/chartex"
                   xmlns:mc="http://schemas.openxmlformats.org/markup-compatibility/2006"
                   xmlns:aink="http://schemas.microsoft.com/office/drawing/2016/ink"
                   xmlns:am3d="http://schemas.microsoft.com/office/drawing/2017/model3d"
                   xmlns:o="urn:schemas-microsoft-com:office:office"
                   xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships"
                   xmlns:m="http://schemas.openxmlformats.org/officeDocument/2006/math"
                   xmlns:v="urn:schemas-microsoft-com:vml"
                   xmlns:wp14="http://schemas.microsoft.com/office/word/2010/wordprocessingDrawing"
                   xmlns:wp="http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing"
                   xmlns:w10="urn:schemas-microsoft-com:office:word"
                   xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main"
                   xmlns:w14="http://schemas.microsoft.com/office/word/2010/wordml"
                   xmlns:w15="http://schemas.microsoft.com/office/word/2012/wordml"
                   xmlns:w16cex="http://schemas.microsoft.com/office/word/2018/wordml/cex"
                   xmlns:w16cid="http://schemas.microsoft.com/office/word/2016/wordml/cid"
                   xmlns:w16="http://schemas.microsoft.com/office/word/2018/wordml"
                   xmlns:w16se="http://schemas.microsoft.com/office/word/2015/wordml/symex"
                   xmlns:wpg="http://schemas.microsoft.com/office/word/2010/wordprocessingGroup"
                   xmlns:wpi="http://schemas.microsoft.com/office/word/2010/wordprocessingInk"
                   xmlns:wne="http://schemas.microsoft.com/office/word/2006/wordml"
                   xmlns:wps="http://schemas.microsoft.com/office/word/2010/wordprocessingShape"
                   mc:Ignorable="w14 w15 w16se w16cid w16 w16cex wp14">
                <w:p w14:paraId="68D80D82" w14:textId="77777777" w:rsidR="00C5384D" w:rsidRDefault="003928D4">
                    <w:pPr>
                        <w:rPr>
                            <w:lang w:eastAsia="zh-CN"/>
                        </w:rPr>
                    </w:pPr>
                    <w:r>
                        <w:rPr>
                            <w:noProof/>
                            <w:lang w:eastAsia="zh-CN"/>
                        </w:rPr>
                        <w:drawing>
                            <wp:inline distT="0" distB="0" distL="0" distR="0" wp14:anchorId="40DF46D4"
                                       wp14:editId="50AD0F07">
                                <wp:extent cx="812006" cy="314325"/>
                                <wp:effectExtent l="0" t="0" r="0" b="0"/>
                                <wp:docPr id="4" name="图片 4"/>
                                <wp:cNvGraphicFramePr>
                                    <a:graphicFrameLocks xmlns:a="http://schemas.openxmlformats.org/drawingml/2006/main"
                                                         noChangeAspect="1"/>
                                </wp:cNvGraphicFramePr>
                                <a:graphic xmlns:a="http://schemas.openxmlformats.org/drawingml/2006/main">
                                    <a:graphicData uri="http://schemas.openxmlformats.org/drawingml/2006/picture">
                                        <pic:pic xmlns:pic="http://schemas.openxmlformats.org/drawingml/2006/picture">
                                            <pic:nvPicPr>
                                                <pic:cNvPr id="4" name="49DA2F4D-A40E-4195-B25E-F4BEEFF70C56.png"/>
                                                <pic:cNvPicPr/>
                                            </pic:nvPicPr>
                                            <pic:blipFill>
                                                <a:blip r:embed="rId1">
                                                    <a:extLst>
                                                        <a:ext uri="{28A0092B-C50C-407E-A947-70E740481C1C}">
                                                            <a14:useLocalDpi
                                                                    xmlns:a14="http://schemas.microsoft.com/office/drawing/2010/main"
                                                                    val="0"/>
                                                        </a:ext>
                                                    </a:extLst>
                                                </a:blip>
                                                <a:stretch>
                                                    <a:fillRect/>
                                                </a:stretch>
                                            </pic:blipFill>
                                            <pic:spPr>
                                                <a:xfrm>
                                                    <a:off x="0" y="0"/>
                                                    <a:ext cx="821419" cy="317969"/>
                                                </a:xfrm>
                                                <a:prstGeom prst="rect">
                                                    <a:avLst/>
                                                </a:prstGeom>
                                            </pic:spPr>
                                        </pic:pic>
                                    </a:graphicData>
                                </a:graphic>
                            </wp:inline>
                        </w:drawing>
                    </w:r>
                    <w:r w:rsidR="00E408E5" w:rsidRPr="00E408E5">
                        <w:ptab w:relativeTo="margin" w:alignment="center" w:leader="none"/>
                    </w:r>
                    <w:r>
                        <w:rPr>
                            <w:lang w:eastAsia="zh-CN"/>
                        </w:rPr>
                        <w:t>技术全景报告</w:t>
                    </w:r>
                    <w:r w:rsidR="00E408E5" w:rsidRPr="00E408E5">
                        <w:ptab w:relativeTo="margin" w:alignment="right" w:leader="none"/>
                    </w:r>
                </w:p>
            </w:hdr>
        </pkg:xmlData>
    </pkg:part>
    <pkg:part pkg:name="/word/_rels/header2.xml.rels"
              pkg:contentType="application/vnd.openxmlformats-package.relationships+xml">
        <pkg:xmlData>
            <Relationships xmlns="http://schemas.openxmlformats.org/package/2006/relationships">
                <Relationship Id="rId1" Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/image"
                              Target="media/image2.png"/>
            </Relationships>
        </pkg:xmlData>
    </pkg:part>
    <pkg:part pkg:name="/word/footnotes.xml"
              pkg:contentType="application/vnd.openxmlformats-officedocument.wordprocessingml.footnotes+xml">
        <pkg:xmlData>
            <w:footnotes xmlns:wpc="http://schemas.microsoft.com/office/word/2010/wordprocessingCanvas"
                         xmlns:cx="http://schemas.microsoft.com/office/drawing/2014/chartex"
                         xmlns:cx1="http://schemas.microsoft.com/office/drawing/2015/9/8/chartex"
                         xmlns:cx2="http://schemas.microsoft.com/office/drawing/2015/10/21/chartex"
                         xmlns:cx3="http://schemas.microsoft.com/office/drawing/2016/5/9/chartex"
                         xmlns:cx4="http://schemas.microsoft.com/office/drawing/2016/5/10/chartex"
                         xmlns:cx5="http://schemas.microsoft.com/office/drawing/2016/5/11/chartex"
                         xmlns:cx6="http://schemas.microsoft.com/office/drawing/2016/5/12/chartex"
                         xmlns:cx7="http://schemas.microsoft.com/office/drawing/2016/5/13/chartex"
                         xmlns:cx8="http://schemas.microsoft.com/office/drawing/2016/5/14/chartex"
                         xmlns:mc="http://schemas.openxmlformats.org/markup-compatibility/2006"
                         xmlns:aink="http://schemas.microsoft.com/office/drawing/2016/ink"
                         xmlns:am3d="http://schemas.microsoft.com/office/drawing/2017/model3d"
                         xmlns:o="urn:schemas-microsoft-com:office:office"
                         xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships"
                         xmlns:m="http://schemas.openxmlformats.org/officeDocument/2006/math"
                         xmlns:v="urn:schemas-microsoft-com:vml"
                         xmlns:wp14="http://schemas.microsoft.com/office/word/2010/wordprocessingDrawing"
                         xmlns:wp="http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing"
                         xmlns:w10="urn:schemas-microsoft-com:office:word"
                         xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main"
                         xmlns:w14="http://schemas.microsoft.com/office/word/2010/wordml"
                         xmlns:w15="http://schemas.microsoft.com/office/word/2012/wordml"
                         xmlns:w16cex="http://schemas.microsoft.com/office/word/2018/wordml/cex"
                         xmlns:w16cid="http://schemas.microsoft.com/office/word/2016/wordml/cid"
                         xmlns:w16="http://schemas.microsoft.com/office/word/2018/wordml"
                         xmlns:w16se="http://schemas.microsoft.com/office/word/2015/wordml/symex"
                         xmlns:wpg="http://schemas.microsoft.com/office/word/2010/wordprocessingGroup"
                         xmlns:wpi="http://schemas.microsoft.com/office/word/2010/wordprocessingInk"
                         xmlns:wne="http://schemas.microsoft.com/office/word/2006/wordml"
                         xmlns:wps="http://schemas.microsoft.com/office/word/2010/wordprocessingShape"
                         mc:Ignorable="w14 w15 w16se w16cid w16 w16cex wp14">
                <w:footnote w:type="separator" w:id="-1">
                    <w:p w14:paraId="6C42010E" w14:textId="77777777" w:rsidR="003822FD" w:rsidRDefault="003822FD">
                        <w:pPr>
                            <w:spacing w:after="0" w:line="240" w:lineRule="auto"/>
                        </w:pPr>
                        <w:r>
                            <w:separator/>
                        </w:r>
                    </w:p>
                </w:footnote>
                <w:footnote w:type="continuationSeparator" w:id="0">
                    <w:p w14:paraId="261C0F2B" w14:textId="77777777" w:rsidR="003822FD" w:rsidRDefault="003822FD">
                        <w:pPr>
                            <w:spacing w:after="0" w:line="240" w:lineRule="auto"/>
                        </w:pPr>
                        <w:r>
                            <w:continuationSeparator/>
                        </w:r>
                    </w:p>
                </w:footnote>
            </w:footnotes>
        </pkg:xmlData>
    </pkg:part>
    <pkg:part pkg:name="/word/endnotes.xml"
              pkg:contentType="application/vnd.openxmlformats-officedocument.wordprocessingml.endnotes+xml">
        <pkg:xmlData>
            <w:endnotes xmlns:wpc="http://schemas.microsoft.com/office/word/2010/wordprocessingCanvas"
                        xmlns:cx="http://schemas.microsoft.com/office/drawing/2014/chartex"
                        xmlns:cx1="http://schemas.microsoft.com/office/drawing/2015/9/8/chartex"
                        xmlns:cx2="http://schemas.microsoft.com/office/drawing/2015/10/21/chartex"
                        xmlns:cx3="http://schemas.microsoft.com/office/drawing/2016/5/9/chartex"
                        xmlns:cx4="http://schemas.microsoft.com/office/drawing/2016/5/10/chartex"
                        xmlns:cx5="http://schemas.microsoft.com/office/drawing/2016/5/11/chartex"
                        xmlns:cx6="http://schemas.microsoft.com/office/drawing/2016/5/12/chartex"
                        xmlns:cx7="http://schemas.microsoft.com/office/drawing/2016/5/13/chartex"
                        xmlns:cx8="http://schemas.microsoft.com/office/drawing/2016/5/14/chartex"
                        xmlns:mc="http://schemas.openxmlformats.org/markup-compatibility/2006"
                        xmlns:aink="http://schemas.microsoft.com/office/drawing/2016/ink"
                        xmlns:am3d="http://schemas.microsoft.com/office/drawing/2017/model3d"
                        xmlns:o="urn:schemas-microsoft-com:office:office"
                        xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships"
                        xmlns:m="http://schemas.openxmlformats.org/officeDocument/2006/math"
                        xmlns:v="urn:schemas-microsoft-com:vml"
                        xmlns:wp14="http://schemas.microsoft.com/office/word/2010/wordprocessingDrawing"
                        xmlns:wp="http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing"
                        xmlns:w10="urn:schemas-microsoft-com:office:word"
                        xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main"
                        xmlns:w14="http://schemas.microsoft.com/office/word/2010/wordml"
                        xmlns:w15="http://schemas.microsoft.com/office/word/2012/wordml"
                        xmlns:w16cex="http://schemas.microsoft.com/office/word/2018/wordml/cex"
                        xmlns:w16cid="http://schemas.microsoft.com/office/word/2016/wordml/cid"
                        xmlns:w16="http://schemas.microsoft.com/office/word/2018/wordml"
                        xmlns:w16se="http://schemas.microsoft.com/office/word/2015/wordml/symex"
                        xmlns:wpg="http://schemas.microsoft.com/office/word/2010/wordprocessingGroup"
                        xmlns:wpi="http://schemas.microsoft.com/office/word/2010/wordprocessingInk"
                        xmlns:wne="http://schemas.microsoft.com/office/word/2006/wordml"
                        xmlns:wps="http://schemas.microsoft.com/office/word/2010/wordprocessingShape"
                        mc:Ignorable="w14 w15 w16se w16cid w16 w16cex wp14">
                <w:endnote w:type="separator" w:id="-1">
                    <w:p w14:paraId="1E1C178E" w14:textId="77777777" w:rsidR="003822FD" w:rsidRDefault="003822FD">
                        <w:pPr>
                            <w:spacing w:after="0" w:line="240" w:lineRule="auto"/>
                        </w:pPr>
                        <w:r>
                            <w:separator/>
                        </w:r>
                    </w:p>
                </w:endnote>
                <w:endnote w:type="continuationSeparator" w:id="0">
                    <w:p w14:paraId="0FBA670F" w14:textId="77777777" w:rsidR="003822FD" w:rsidRDefault="003822FD">
                        <w:pPr>
                            <w:spacing w:after="0" w:line="240" w:lineRule="auto"/>
                        </w:pPr>
                        <w:r>
                            <w:continuationSeparator/>
                        </w:r>
                    </w:p>
                </w:endnote>
            </w:endnotes>
        </pkg:xmlData>
    </pkg:part>
    <pkg:part pkg:name="/word/footer1.xml"
              pkg:contentType="application/vnd.openxmlformats-officedocument.wordprocessingml.footer+xml">
        <pkg:xmlData>
            <w:ftr xmlns:wpc="http://schemas.microsoft.com/office/word/2010/wordprocessingCanvas"
                   xmlns:cx="http://schemas.microsoft.com/office/drawing/2014/chartex"
                   xmlns:cx1="http://schemas.microsoft.com/office/drawing/2015/9/8/chartex"
                   xmlns:cx2="http://schemas.microsoft.com/office/drawing/2015/10/21/chartex"
                   xmlns:cx3="http://schemas.microsoft.com/office/drawing/2016/5/9/chartex"
                   xmlns:cx4="http://schemas.microsoft.com/office/drawing/2016/5/10/chartex"
                   xmlns:cx5="http://schemas.microsoft.com/office/drawing/2016/5/11/chartex"
                   xmlns:cx6="http://schemas.microsoft.com/office/drawing/2016/5/12/chartex"
                   xmlns:cx7="http://schemas.microsoft.com/office/drawing/2016/5/13/chartex"
                   xmlns:cx8="http://schemas.microsoft.com/office/drawing/2016/5/14/chartex"
                   xmlns:mc="http://schemas.openxmlformats.org/markup-compatibility/2006"
                   xmlns:aink="http://schemas.microsoft.com/office/drawing/2016/ink"
                   xmlns:am3d="http://schemas.microsoft.com/office/drawing/2017/model3d"
                   xmlns:o="urn:schemas-microsoft-com:office:office"
                   xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships"
                   xmlns:m="http://schemas.openxmlformats.org/officeDocument/2006/math"
                   xmlns:v="urn:schemas-microsoft-com:vml"
                   xmlns:wp14="http://schemas.microsoft.com/office/word/2010/wordprocessingDrawing"
                   xmlns:wp="http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing"
                   xmlns:w10="urn:schemas-microsoft-com:office:word"
                   xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main"
                   xmlns:w14="http://schemas.microsoft.com/office/word/2010/wordml"
                   xmlns:w15="http://schemas.microsoft.com/office/word/2012/wordml"
                   xmlns:w16cex="http://schemas.microsoft.com/office/word/2018/wordml/cex"
                   xmlns:w16cid="http://schemas.microsoft.com/office/word/2016/wordml/cid"
                   xmlns:w16="http://schemas.microsoft.com/office/word/2018/wordml"
                   xmlns:w16se="http://schemas.microsoft.com/office/word/2015/wordml/symex"
                   xmlns:wpg="http://schemas.microsoft.com/office/word/2010/wordprocessingGroup"
                   xmlns:wpi="http://schemas.microsoft.com/office/word/2010/wordprocessingInk"
                   xmlns:wne="http://schemas.microsoft.com/office/word/2006/wordml"
                   xmlns:wps="http://schemas.microsoft.com/office/word/2010/wordprocessingShape"
                   mc:Ignorable="w14 w15 w16se w16cid w16 w16cex wp14">
                <w:sdt>
                    <w:sdtPr>
                        <w:id w:val="-967052381"/>
                        <w:docPartObj>
                            <w:docPartGallery w:val="Page Numbers (Bottom of Page)"/>
                            <w:docPartUnique/>
                        </w:docPartObj>
                    </w:sdtPr>
                    <w:sdtEndPr/>
                    <w:sdtContent>
                        <w:p w14:paraId="4AFA7914" w14:textId="77777777" w:rsidR="00C339C0" w:rsidRDefault="00C339C0">
                            <w:pPr>
                                <w:pStyle w:val="aff1"/>
                                <w:jc w:val="center"/>
                            </w:pPr>
                            <w:r>
                                <w:fldChar w:fldCharType="begin"/>
                            </w:r>
                            <w:r>
                                <w:instrText>PAGE \* MERGEFORMAT</w:instrText>
                            </w:r>
                            <w:r>
                                <w:fldChar w:fldCharType="separate"/>
                            </w:r>
                            <w:r w:rsidR="003E332C" w:rsidRPr="003E332C">
                                <w:rPr>
                                    <w:noProof/>
                                    <w:lang w:val="zh-CN" w:eastAsia="zh-CN"/>
                                </w:rPr>
                                <w:t>1</w:t>
                            </w:r>
                            <w:r>
                                <w:fldChar w:fldCharType="end"/>
                            </w:r>
                        </w:p>
                    </w:sdtContent>
                </w:sdt>
                <w:p w14:paraId="0603C498" w14:textId="77777777" w:rsidR="00C339C0" w:rsidRDefault="00C339C0">
                    <w:pPr>
                        <w:pStyle w:val="aff1"/>
                    </w:pPr>
                </w:p>
            </w:ftr>
        </pkg:xmlData>
    </pkg:part>
    <pkg:part pkg:name="/word/header1.xml"
              pkg:contentType="application/vnd.openxmlformats-officedocument.wordprocessingml.header+xml">
        <pkg:xmlData>
            <w:hdr xmlns:wpc="http://schemas.microsoft.com/office/word/2010/wordprocessingCanvas"
                   xmlns:cx="http://schemas.microsoft.com/office/drawing/2014/chartex"
                   xmlns:cx1="http://schemas.microsoft.com/office/drawing/2015/9/8/chartex"
                   xmlns:cx2="http://schemas.microsoft.com/office/drawing/2015/10/21/chartex"
                   xmlns:cx3="http://schemas.microsoft.com/office/drawing/2016/5/9/chartex"
                   xmlns:cx4="http://schemas.microsoft.com/office/drawing/2016/5/10/chartex"
                   xmlns:cx5="http://schemas.microsoft.com/office/drawing/2016/5/11/chartex"
                   xmlns:cx6="http://schemas.microsoft.com/office/drawing/2016/5/12/chartex"
                   xmlns:cx7="http://schemas.microsoft.com/office/drawing/2016/5/13/chartex"
                   xmlns:cx8="http://schemas.microsoft.com/office/drawing/2016/5/14/chartex"
                   xmlns:mc="http://schemas.openxmlformats.org/markup-compatibility/2006"
                   xmlns:aink="http://schemas.microsoft.com/office/drawing/2016/ink"
                   xmlns:am3d="http://schemas.microsoft.com/office/drawing/2017/model3d"
                   xmlns:o="urn:schemas-microsoft-com:office:office"
                   xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships"
                   xmlns:m="http://schemas.openxmlformats.org/officeDocument/2006/math"
                   xmlns:v="urn:schemas-microsoft-com:vml"
                   xmlns:wp14="http://schemas.microsoft.com/office/word/2010/wordprocessingDrawing"
                   xmlns:wp="http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing"
                   xmlns:w10="urn:schemas-microsoft-com:office:word"
                   xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main"
                   xmlns:w14="http://schemas.microsoft.com/office/word/2010/wordml"
                   xmlns:w15="http://schemas.microsoft.com/office/word/2012/wordml"
                   xmlns:w16cex="http://schemas.microsoft.com/office/word/2018/wordml/cex"
                   xmlns:w16cid="http://schemas.microsoft.com/office/word/2016/wordml/cid"
                   xmlns:w16="http://schemas.microsoft.com/office/word/2018/wordml"
                   xmlns:w16se="http://schemas.microsoft.com/office/word/2015/wordml/symex"
                   xmlns:wpg="http://schemas.microsoft.com/office/word/2010/wordprocessingGroup"
                   xmlns:wpi="http://schemas.microsoft.com/office/word/2010/wordprocessingInk"
                   xmlns:wne="http://schemas.microsoft.com/office/word/2006/wordml"
                   xmlns:wps="http://schemas.microsoft.com/office/word/2010/wordprocessingShape"
                   mc:Ignorable="w14 w15 w16se w16cid w16 w16cex wp14">
                <w:tbl>
                    <w:tblPr>
                        <w:tblW w:w="5000" w:type="pct"/>
                        <w:tblLook w:val="04A0" w:firstRow="1" w:lastRow="0" w:firstColumn="1" w:lastColumn="0"
                                   w:noHBand="0" w:noVBand="1"/>
                    </w:tblPr>
                    <w:tblGrid>
                        <w:gridCol w:w="9027"/>
                    </w:tblGrid>
                    <w:tr w:rsidR="00C5384D" w14:paraId="6C234BE5" w14:textId="77777777">
                        <w:tc>
                            <w:tcPr>
                                <w:tcW w:w="3750" w:type="pct"/>
                                <w:vAlign w:val="center"/>
                            </w:tcPr>
                            <w:p w14:paraId="050D5930" w14:textId="77777777" w:rsidR="00864A1B"
                                 w:rsidRDefault="00970EA0" w:rsidP="00970EA0">
                                <w:pPr>
                                    <w:pStyle w:val="af6"/>
                                </w:pPr>
                                <w:r>
                                    <w:rPr>
                                        <w:rFonts w:hint="eastAsia"/>
                                        <w:noProof/>
                                        <w:lang w:eastAsia="zh-CN"/>
                                    </w:rPr>
                                    <w:drawing>
                                        <wp:inline distT="0" distB="0" distL="0" distR="0" wp14:anchorId="6EAA9060"
                                                   wp14:editId="207197F5">
                                            <wp:extent cx="836612" cy="323850"/>
                                            <wp:effectExtent l="0" t="0" r="1905" b="0"/>
                                            <wp:docPr id="2" name="图片 2"/>
                                            <wp:cNvGraphicFramePr>
                                                <a:graphicFrameLocks
                                                        xmlns:a="http://schemas.openxmlformats.org/drawingml/2006/main"
                                                        noChangeAspect="1"/>
                                            </wp:cNvGraphicFramePr>
                                            <a:graphic xmlns:a="http://schemas.openxmlformats.org/drawingml/2006/main">
                                                <a:graphicData
                                                        uri="http://schemas.openxmlformats.org/drawingml/2006/picture">
                                                    <pic:pic
                                                            xmlns:pic="http://schemas.openxmlformats.org/drawingml/2006/picture">
                                                        <pic:nvPicPr>
                                                            <pic:cNvPr id="2"
                                                                       name="49DA2F4D-A40E-4195-B25E-F4BEEFF70C56.png"/>
                                                            <pic:cNvPicPr/>
                                                        </pic:nvPicPr>
                                                        <pic:blipFill>
                                                            <a:blip r:embed="rId1">
                                                                <a:extLst>
                                                                    <a:ext uri="{28A0092B-C50C-407E-A947-70E740481C1C}">
                                                                        <a14:useLocalDpi
                                                                                xmlns:a14="http://schemas.microsoft.com/office/drawing/2010/main"
                                                                                val="0"/>
                                                                    </a:ext>
                                                                </a:extLst>
                                                            </a:blip>
                                                            <a:stretch>
                                                                <a:fillRect/>
                                                            </a:stretch>
                                                        </pic:blipFill>
                                                        <pic:spPr>
                                                            <a:xfrm>
                                                                <a:off x="0" y="0"/>
                                                                <a:ext cx="847983" cy="328252"/>
                                                            </a:xfrm>
                                                            <a:prstGeom prst="rect">
                                                                <a:avLst/>
                                                            </a:prstGeom>
                                                        </pic:spPr>
                                                    </pic:pic>
                                                </a:graphicData>
                                            </a:graphic>
                                        </wp:inline>
                                    </w:drawing>
                                </w:r>
                                <w:r>
                                    <w:t xml:space="preserve">                                            </w:t>
                                </w:r>
                                <w:r>
                                    <w:rPr>
                                        <w:rFonts w:hint="eastAsia"/>
                                    </w:rPr>
                                    <w:t>技术全景报告</w:t>
                                </w:r>
                            </w:p>
                        </w:tc>
                    </w:tr>
                </w:tbl>
            </w:hdr>
        </pkg:xmlData>
    </pkg:part>
    <pkg:part pkg:name="/word/_rels/header1.xml.rels"
              pkg:contentType="application/vnd.openxmlformats-package.relationships+xml">
        <pkg:xmlData>
            <Relationships xmlns="http://schemas.openxmlformats.org/package/2006/relationships">
                <Relationship Id="rId1" Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/image"
                              Target="media/image2.png"/>
            </Relationships>
        </pkg:xmlData>
    </pkg:part>
    <pkg:part pkg:name="/word/media/image2.png" pkg:contentType="image/png" pkg:compression="store">
        <pkg:binaryData>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
        </pkg:binaryData>
    </pkg:part>
    <#list groupList as group>
        <#list group.dataList as data>
            <pkg:part pkg:name="/word/media/${data.name}.png" pkg:contentType="image/png" pkg:compression="store">
                <pkg:binaryData>${data.imgData}</pkg:binaryData>
            </pkg:part>
        </#list>
    </#list>
    <pkg:part pkg:name="/word/theme/theme1.xml"
              pkg:contentType="application/vnd.openxmlformats-officedocument.theme+xml">
        <pkg:xmlData>
            <a:theme xmlns:a="http://schemas.openxmlformats.org/drawingml/2006/main" name="Office 主题">
                <a:themeElements>
                    <a:clrScheme name="Office">
                        <a:dk1>
                            <a:sysClr val="windowText" lastClr="000000"/>
                        </a:dk1>
                        <a:lt1>
                            <a:sysClr val="window" lastClr="FFFFFF"/>
                        </a:lt1>
                        <a:dk2>
                            <a:srgbClr val="44546A"/>
                        </a:dk2>
                        <a:lt2>
                            <a:srgbClr val="E7E6E6"/>
                        </a:lt2>
                        <a:accent1>
                            <a:srgbClr val="5B9BD5"/>
                        </a:accent1>
                        <a:accent2>
                            <a:srgbClr val="ED7D31"/>
                        </a:accent2>
                        <a:accent3>
                            <a:srgbClr val="A5A5A5"/>
                        </a:accent3>
                        <a:accent4>
                            <a:srgbClr val="FFC000"/>
                        </a:accent4>
                        <a:accent5>
                            <a:srgbClr val="4472C4"/>
                        </a:accent5>
                        <a:accent6>
                            <a:srgbClr val="70AD47"/>
                        </a:accent6>
                        <a:hlink>
                            <a:srgbClr val="0563C1"/>
                        </a:hlink>
                        <a:folHlink>
                            <a:srgbClr val="954F72"/>
                        </a:folHlink>
                    </a:clrScheme>
                    <a:fontScheme name="Office">
                        <a:majorFont>
                            <a:latin typeface="Calibri Light" panose="020F0302020204030204"/>
                            <a:ea typeface=""/>
                            <a:cs typeface=""/>
                            <a:font script="Jpan" typeface="ＭＳ ゴシック"/>
                            <a:font script="Hang" typeface="맑은 고딕"/>
                            <a:font script="Hans" typeface="宋体"/>
                            <a:font script="Hant" typeface="新細明體"/>
                            <a:font script="Arab" typeface="Times New Roman"/>
                            <a:font script="Hebr" typeface="Times New Roman"/>
                            <a:font script="Thai" typeface="Angsana New"/>
                            <a:font script="Ethi" typeface="Nyala"/>
                            <a:font script="Beng" typeface="Vrinda"/>
                            <a:font script="Gujr" typeface="Shruti"/>
                            <a:font script="Khmr" typeface="MoolBoran"/>
                            <a:font script="Knda" typeface="Tunga"/>
                            <a:font script="Guru" typeface="Raavi"/>
                            <a:font script="Cans" typeface="Euphemia"/>
                            <a:font script="Cher" typeface="Plantagenet Cherokee"/>
                            <a:font script="Yiii" typeface="Microsoft Yi Baiti"/>
                            <a:font script="Tibt" typeface="Microsoft Himalaya"/>
                            <a:font script="Thaa" typeface="MV Boli"/>
                            <a:font script="Deva" typeface="Mangal"/>
                            <a:font script="Telu" typeface="Gautami"/>
                            <a:font script="Taml" typeface="Latha"/>
                            <a:font script="Syrc" typeface="Estrangelo Edessa"/>
                            <a:font script="Orya" typeface="Kalinga"/>
                            <a:font script="Mlym" typeface="Kartika"/>
                            <a:font script="Laoo" typeface="DokChampa"/>
                            <a:font script="Sinh" typeface="Iskoola Pota"/>
                            <a:font script="Mong" typeface="Mongolian Baiti"/>
                            <a:font script="Viet" typeface="Times New Roman"/>
                            <a:font script="Uigh" typeface="Microsoft Uighur"/>
                            <a:font script="Geor" typeface="Sylfaen"/>
                        </a:majorFont>
                        <a:minorFont>
                            <a:latin typeface="Calibri" panose="020F0502020204030204"/>
                            <a:ea typeface=""/>
                            <a:cs typeface=""/>
                            <a:font script="Jpan" typeface="ＭＳ 明朝"/>
                            <a:font script="Hang" typeface="맑은 고딕"/>
                            <a:font script="Hans" typeface="宋体"/>
                            <a:font script="Hant" typeface="新細明體"/>
                            <a:font script="Arab" typeface="Arial"/>
                            <a:font script="Hebr" typeface="Arial"/>
                            <a:font script="Thai" typeface="Cordia New"/>
                            <a:font script="Ethi" typeface="Nyala"/>
                            <a:font script="Beng" typeface="Vrinda"/>
                            <a:font script="Gujr" typeface="Shruti"/>
                            <a:font script="Khmr" typeface="DaunPenh"/>
                            <a:font script="Knda" typeface="Tunga"/>
                            <a:font script="Guru" typeface="Raavi"/>
                            <a:font script="Cans" typeface="Euphemia"/>
                            <a:font script="Cher" typeface="Plantagenet Cherokee"/>
                            <a:font script="Yiii" typeface="Microsoft Yi Baiti"/>
                            <a:font script="Tibt" typeface="Microsoft Himalaya"/>
                            <a:font script="Thaa" typeface="MV Boli"/>
                            <a:font script="Deva" typeface="Mangal"/>
                            <a:font script="Telu" typeface="Gautami"/>
                            <a:font script="Taml" typeface="Latha"/>
                            <a:font script="Syrc" typeface="Estrangelo Edessa"/>
                            <a:font script="Orya" typeface="Kalinga"/>
                            <a:font script="Mlym" typeface="Kartika"/>
                            <a:font script="Laoo" typeface="DokChampa"/>
                            <a:font script="Sinh" typeface="Iskoola Pota"/>
                            <a:font script="Mong" typeface="Mongolian Baiti"/>
                            <a:font script="Viet" typeface="Arial"/>
                            <a:font script="Uigh" typeface="Microsoft Uighur"/>
                            <a:font script="Geor" typeface="Sylfaen"/>
                        </a:minorFont>
                    </a:fontScheme>
                    <a:fmtScheme name="Office">
                        <a:fillStyleLst>
                            <a:solidFill>
                                <a:schemeClr val="phClr"/>
                            </a:solidFill>
                            <a:gradFill rotWithShape="1">
                                <a:gsLst>
                                    <a:gs pos="0">
                                        <a:schemeClr val="phClr">
                                            <a:lumMod val="110000"/>
                                            <a:satMod val="105000"/>
                                            <a:tint val="67000"/>
                                        </a:schemeClr>
                                    </a:gs>
                                    <a:gs pos="50000">
                                        <a:schemeClr val="phClr">
                                            <a:lumMod val="105000"/>
                                            <a:satMod val="103000"/>
                                            <a:tint val="73000"/>
                                        </a:schemeClr>
                                    </a:gs>
                                    <a:gs pos="100000">
                                        <a:schemeClr val="phClr">
                                            <a:lumMod val="105000"/>
                                            <a:satMod val="109000"/>
                                            <a:tint val="81000"/>
                                        </a:schemeClr>
                                    </a:gs>
                                </a:gsLst>
                                <a:lin ang="5400000" scaled="0"/>
                            </a:gradFill>
                            <a:gradFill rotWithShape="1">
                                <a:gsLst>
                                    <a:gs pos="0">
                                        <a:schemeClr val="phClr">
                                            <a:satMod val="103000"/>
                                            <a:lumMod val="102000"/>
                                            <a:tint val="94000"/>
                                        </a:schemeClr>
                                    </a:gs>
                                    <a:gs pos="50000">
                                        <a:schemeClr val="phClr">
                                            <a:satMod val="110000"/>
                                            <a:lumMod val="100000"/>
                                            <a:shade val="100000"/>
                                        </a:schemeClr>
                                    </a:gs>
                                    <a:gs pos="100000">
                                        <a:schemeClr val="phClr">
                                            <a:lumMod val="99000"/>
                                            <a:satMod val="120000"/>
                                            <a:shade val="78000"/>
                                        </a:schemeClr>
                                    </a:gs>
                                </a:gsLst>
                                <a:lin ang="5400000" scaled="0"/>
                            </a:gradFill>
                        </a:fillStyleLst>
                        <a:lnStyleLst>
                            <a:ln w="6350" cap="flat" cmpd="sng" algn="ctr">
                                <a:solidFill>
                                    <a:schemeClr val="phClr"/>
                                </a:solidFill>
                                <a:prstDash val="solid"/>
                                <a:miter lim="800000"/>
                            </a:ln>
                            <a:ln w="12700" cap="flat" cmpd="sng" algn="ctr">
                                <a:solidFill>
                                    <a:schemeClr val="phClr"/>
                                </a:solidFill>
                                <a:prstDash val="solid"/>
                                <a:miter lim="800000"/>
                            </a:ln>
                            <a:ln w="19050" cap="flat" cmpd="sng" algn="ctr">
                                <a:solidFill>
                                    <a:schemeClr val="phClr"/>
                                </a:solidFill>
                                <a:prstDash val="solid"/>
                                <a:miter lim="800000"/>
                            </a:ln>
                        </a:lnStyleLst>
                        <a:effectStyleLst>
                            <a:effectStyle>
                                <a:effectLst/>
                            </a:effectStyle>
                            <a:effectStyle>
                                <a:effectLst/>
                            </a:effectStyle>
                            <a:effectStyle>
                                <a:effectLst>
                                    <a:outerShdw blurRad="57150" dist="19050" dir="5400000" algn="ctr" rotWithShape="0">
                                        <a:srgbClr val="000000">
                                            <a:alpha val="63000"/>
                                        </a:srgbClr>
                                    </a:outerShdw>
                                </a:effectLst>
                            </a:effectStyle>
                        </a:effectStyleLst>
                        <a:bgFillStyleLst>
                            <a:solidFill>
                                <a:schemeClr val="phClr"/>
                            </a:solidFill>
                            <a:solidFill>
                                <a:schemeClr val="phClr">
                                    <a:tint val="95000"/>
                                    <a:satMod val="170000"/>
                                </a:schemeClr>
                            </a:solidFill>
                            <a:gradFill rotWithShape="1">
                                <a:gsLst>
                                    <a:gs pos="0">
                                        <a:schemeClr val="phClr">
                                            <a:tint val="93000"/>
                                            <a:satMod val="150000"/>
                                            <a:shade val="98000"/>
                                            <a:lumMod val="102000"/>
                                        </a:schemeClr>
                                    </a:gs>
                                    <a:gs pos="50000">
                                        <a:schemeClr val="phClr">
                                            <a:tint val="98000"/>
                                            <a:satMod val="130000"/>
                                            <a:shade val="90000"/>
                                            <a:lumMod val="103000"/>
                                        </a:schemeClr>
                                    </a:gs>
                                    <a:gs pos="100000">
                                        <a:schemeClr val="phClr">
                                            <a:shade val="63000"/>
                                            <a:satMod val="120000"/>
                                        </a:schemeClr>
                                    </a:gs>
                                </a:gsLst>
                                <a:lin ang="5400000" scaled="0"/>
                            </a:gradFill>
                        </a:bgFillStyleLst>
                    </a:fmtScheme>
                </a:themeElements>
                <a:objectDefaults/>
                <a:extraClrSchemeLst/>
                <a:extLst>
                    <a:ext uri="{05A4C25C-085E-4340-85A3-A5531E510DB2}">
                        <thm15:themeFamily xmlns:thm15="http://schemas.microsoft.com/office/thememl/2012/main"
                                           name="Office Theme" id="{62F939B6-93AF-4DB8-9C6B-D6C7DFDC589F}"
                                           vid="{4A3C46E8-61CC-4603-A589-7422A47A8E4A}"/>
                    </a:ext>
                </a:extLst>
            </a:theme>
        </pkg:xmlData>
    </pkg:part>
    <pkg:part pkg:name="/word/settings.xml"
              pkg:contentType="application/vnd.openxmlformats-officedocument.wordprocessingml.settings+xml">
        <pkg:xmlData>
            <w:settings xmlns:mc="http://schemas.openxmlformats.org/markup-compatibility/2006"
                        xmlns:o="urn:schemas-microsoft-com:office:office"
                        xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships"
                        xmlns:m="http://schemas.openxmlformats.org/officeDocument/2006/math"
                        xmlns:v="urn:schemas-microsoft-com:vml" xmlns:w10="urn:schemas-microsoft-com:office:word"
                        xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main"
                        xmlns:w14="http://schemas.microsoft.com/office/word/2010/wordml"
                        xmlns:w15="http://schemas.microsoft.com/office/word/2012/wordml"
                        xmlns:w16cex="http://schemas.microsoft.com/office/word/2018/wordml/cex"
                        xmlns:w16cid="http://schemas.microsoft.com/office/word/2016/wordml/cid"
                        xmlns:w16="http://schemas.microsoft.com/office/word/2018/wordml"
                        xmlns:w16se="http://schemas.microsoft.com/office/word/2015/wordml/symex"
                        xmlns:sl="http://schemas.openxmlformats.org/schemaLibrary/2006/main"
                        mc:Ignorable="w14 w15 w16se w16cid w16 w16cex">
                <w:view w:val="web"/>
                <w:zoom w:percent="225"/>
                <w:bordersDoNotSurroundHeader/>
                <w:bordersDoNotSurroundFooter/>
                <w:proofState w:spelling="clean" w:grammar="clean"/>
                <w:defaultTabStop w:val="420"/>
                <w:characterSpacingControl w:val="doNotCompress"/>
                <w:hdrShapeDefaults>
                    <o:shapedefaults v:ext="edit" spidmax="2049"/>
                </w:hdrShapeDefaults>
                <w:footnotePr>
                    <w:footnote w:id="-1"/>
                    <w:footnote w:id="0"/>
                </w:footnotePr>
                <w:endnotePr>
                    <w:endnote w:id="-1"/>
                    <w:endnote w:id="0"/>
                </w:endnotePr>
                <w:compat>
                    <w:useFELayout/>
                    <w:compatSetting w:name="compatibilityMode" w:uri="http://schemas.microsoft.com/office/word"
                                     w:val="15"/>
                    <w:compatSetting w:name="overrideTableStyleFontSizeAndJustification"
                                     w:uri="http://schemas.microsoft.com/office/word" w:val="1"/>
                    <w:compatSetting w:name="enableOpenTypeFeatures" w:uri="http://schemas.microsoft.com/office/word"
                                     w:val="1"/>
                    <w:compatSetting w:name="doNotFlipMirrorIndents" w:uri="http://schemas.microsoft.com/office/word"
                                     w:val="1"/>
                    <w:compatSetting w:name="differentiateMultirowTableHeaders"
                                     w:uri="http://schemas.microsoft.com/office/word" w:val="1"/>
                    <w:compatSetting w:name="useWord2013TrackBottomHyphenation"
                                     w:uri="http://schemas.microsoft.com/office/word" w:val="1"/>
                </w:compat>
                <w:rsids>
                    <w:rsidRoot w:val="008A69A6"/>
                    <w:rsid w:val="000D156C"/>
                    <w:rsid w:val="00121C7D"/>
                    <w:rsid w:val="00145407"/>
                    <w:rsid w:val="00185577"/>
                    <w:rsid w:val="001A65D5"/>
                    <w:rsid w:val="002E7E71"/>
                    <w:rsid w:val="0030654A"/>
                    <w:rsid w:val="0035254D"/>
                    <w:rsid w:val="003822FD"/>
                    <w:rsid w:val="003928D4"/>
                    <w:rsid w:val="003D04AB"/>
                    <w:rsid w:val="003E332C"/>
                    <w:rsid w:val="00463C8E"/>
                    <w:rsid w:val="00482922"/>
                    <w:rsid w:val="005103B9"/>
                    <w:rsid w:val="0054466E"/>
                    <w:rsid w:val="005719A2"/>
                    <w:rsid w:val="005C4F35"/>
                    <w:rsid w:val="006E21A5"/>
                    <w:rsid w:val="006F2458"/>
                    <w:rsid w:val="00795664"/>
                    <w:rsid w:val="007C15C5"/>
                    <w:rsid w:val="007C2938"/>
                    <w:rsid w:val="00805CDA"/>
                    <w:rsid w:val="008132CE"/>
                    <w:rsid w:val="00864A1B"/>
                    <w:rsid w:val="00865FDD"/>
                    <w:rsid w:val="0087429F"/>
                    <w:rsid w:val="008A69A6"/>
                    <w:rsid w:val="008F6ACC"/>
                    <w:rsid w:val="00926317"/>
                    <w:rsid w:val="00970EA0"/>
                    <w:rsid w:val="0099551D"/>
                    <w:rsid w:val="009F1425"/>
                    <w:rsid w:val="00A06A1F"/>
                    <w:rsid w:val="00A163F5"/>
                    <w:rsid w:val="00A67451"/>
                    <w:rsid w:val="00AA0EB9"/>
                    <w:rsid w:val="00B051E7"/>
                    <w:rsid w:val="00BB7003"/>
                    <w:rsid w:val="00BF4FBA"/>
                    <w:rsid w:val="00C339C0"/>
                    <w:rsid w:val="00C343C7"/>
                    <w:rsid w:val="00C5384D"/>
                    <w:rsid w:val="00D0208A"/>
                    <w:rsid w:val="00D63F65"/>
                    <w:rsid w:val="00E207B0"/>
                    <w:rsid w:val="00E2429D"/>
                    <w:rsid w:val="00E408E5"/>
                    <w:rsid w:val="00E56352"/>
                    <w:rsid w:val="00EB78D3"/>
                    <w:rsid w:val="00EF6A14"/>
                </w:rsids>
                <m:mathPr>
                    <m:mathFont m:val="Cambria Math"/>
                    <m:brkBin m:val="before"/>
                    <m:brkBinSub m:val="--"/>
                    <m:smallFrac m:val="0"/>
                    <m:dispDef/>
                    <m:lMargin m:val="0"/>
                    <m:rMargin m:val="0"/>
                    <m:defJc m:val="centerGroup"/>
                    <m:wrapIndent m:val="1440"/>
                    <m:intLim m:val="subSup"/>
                    <m:naryLim m:val="undOvr"/>
                </m:mathPr>
                <w:themeFontLang w:val="en-US" w:eastAsia="zh-CN"/>
                <w:clrSchemeMapping w:bg1="light1" w:t1="dark1" w:bg2="light2" w:t2="dark2" w:accent1="accent1"
                                    w:accent2="accent2" w:accent3="accent3" w:accent4="accent4" w:accent5="accent5"
                                    w:accent6="accent6" w:hyperlink="hyperlink"
                                    w:followedHyperlink="followedHyperlink"/>
                <w:shapeDefaults>
                    <o:shapedefaults v:ext="edit" spidmax="2049"/>
                    <o:shapelayout v:ext="edit">
                        <o:idmap v:ext="edit" data="1"/>
                    </o:shapelayout>
                </w:shapeDefaults>
                <w:decimalSymbol w:val="."/>
                <w:listSeparator w:val=","/>
                <w14:docId w14:val="3F4736C4"/>
                <w15:docId w15:val="{316E843B-B332-49EA-A622-B4E12B618E6E}"/>
            </w:settings>
        </pkg:xmlData>
    </pkg:part>
    <pkg:part pkg:name="/docProps/core.xml" pkg:contentType="application/vnd.openxmlformats-package.core-properties+xml"
              pkg:padding="256">
        <pkg:xmlData>
            <cp:coreProperties xmlns:cp="http://schemas.openxmlformats.org/package/2006/metadata/core-properties"
                               xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:dcterms="http://purl.org/dc/terms/"
                               xmlns:dcmitype="http://purl.org/dc/dcmitype/"
                               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
                <dc:title>技术全景报告</dc:title>
                <dc:creator>docx4j 8.1.3</dc:creator>
                <cp:lastModifiedBy>彭 卓勋</cp:lastModifiedBy>
                <cp:revision>2</cp:revision>
                <dcterms:created xsi:type="dcterms:W3CDTF">2021-04-17T01:35:00Z</dcterms:created>
                <dcterms:modified xsi:type="dcterms:W3CDTF">2021-04-17T01:35:00Z</dcterms:modified>
            </cp:coreProperties>
        </pkg:xmlData>
    </pkg:part>
    <pkg:part pkg:name="/docProps/app.xml"
              pkg:contentType="application/vnd.openxmlformats-officedocument.extended-properties+xml" pkg:padding="256">
        <pkg:xmlData>
            <Properties xmlns="http://schemas.openxmlformats.org/officeDocument/2006/extended-properties"
                        xmlns:vt="http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes">
                <Template>Normal.dotm</Template>
                <TotalTime>1</TotalTime>
                <Pages>1</Pages>
                <Words>63</Words>
                <Characters>363</Characters>
                <Application>Microsoft Office Word</Application>
                <DocSecurity>0</DocSecurity>
                <Lines>3</Lines>
                <Paragraphs>1</Paragraphs>
                <ScaleCrop>false</ScaleCrop>
                <Company/>
                <LinksUpToDate>false</LinksUpToDate>
                <CharactersWithSpaces>425</CharactersWithSpaces>
                <SharedDoc>false</SharedDoc>
                <HyperlinksChanged>false</HyperlinksChanged>
                <AppVersion>16.0000</AppVersion>
            </Properties>
        </pkg:xmlData>
    </pkg:part>
    <pkg:part pkg:name="/customXml/itemProps1.xml"
              pkg:contentType="application/vnd.openxmlformats-officedocument.customXmlProperties+xml" pkg:padding="32">
        <pkg:xmlData pkg:originalXmlStandalone="no">
            <ds:datastoreItem ds:itemID="{95F7052C-EBA4-46E5-B0D9-2B7D18549FCC}"
                              xmlns:ds="http://schemas.openxmlformats.org/officeDocument/2006/customXml">
                <ds:schemaRefs>
                    <ds:schemaRef ds:uri="http://schemas.openxmlformats.org/officeDocument/2006/bibliography"/>
                </ds:schemaRefs>
            </ds:datastoreItem>
        </pkg:xmlData>
    </pkg:part>
    <pkg:part pkg:name="/customXml/item1.xml" pkg:contentType="application/xml" pkg:padding="32">
        <pkg:xmlData pkg:originalXmlStandalone="no">
            <b:Sources SelectedStyle="\APASixthEditionOfficeOnline.xsl" StyleName="APA" Version="6"
                       xmlns:b="http://schemas.openxmlformats.org/officeDocument/2006/bibliography"
                       xmlns="http://schemas.openxmlformats.org/officeDocument/2006/bibliography"/>
        </pkg:xmlData>
    </pkg:part>
    <pkg:part pkg:name="/customXml/_rels/item1.xml.rels"
              pkg:contentType="application/vnd.openxmlformats-package.relationships+xml" pkg:padding="256">
        <pkg:xmlData>
            <Relationships xmlns="http://schemas.openxmlformats.org/package/2006/relationships">
                <Relationship Id="rId1"
                              Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/customXmlProps"
                              Target="itemProps1.xml"/>
            </Relationships>
        </pkg:xmlData>
    </pkg:part>
    <pkg:part pkg:name="/word/styles.xml"
              pkg:contentType="application/vnd.openxmlformats-officedocument.wordprocessingml.styles+xml">
        <pkg:xmlData>
            <w:styles xmlns:mc="http://schemas.openxmlformats.org/markup-compatibility/2006"
                      xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships"
                      xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main"
                      xmlns:w14="http://schemas.microsoft.com/office/word/2010/wordml"
                      xmlns:w15="http://schemas.microsoft.com/office/word/2012/wordml"
                      xmlns:w16cex="http://schemas.microsoft.com/office/word/2018/wordml/cex"
                      xmlns:w16cid="http://schemas.microsoft.com/office/word/2016/wordml/cid"
                      xmlns:w16="http://schemas.microsoft.com/office/word/2018/wordml"
                      xmlns:w16se="http://schemas.microsoft.com/office/word/2015/wordml/symex"
                      mc:Ignorable="w14 w15 w16se w16cid w16 w16cex">
                <w:docDefaults>
                    <w:rPrDefault>
                        <w:rPr>
                            <w:rFonts w:asciiTheme="minorHAnsi" w:eastAsiaTheme="minorEastAsia"
                                      w:hAnsiTheme="minorHAnsi" w:cstheme="minorBidi"/>
                            <w:sz w:val="22"/>
                            <w:szCs w:val="22"/>
                            <w:lang w:val="en-US" w:eastAsia="en-US" w:bidi="ar-SA"/>
                        </w:rPr>
                    </w:rPrDefault>
                    <w:pPrDefault>
                        <w:pPr>
                            <w:spacing w:after="200" w:line="276" w:lineRule="auto"/>
                        </w:pPr>
                    </w:pPrDefault>
                </w:docDefaults>
                <w:latentStyles w:defLockedState="0" w:defUIPriority="99" w:defSemiHidden="0" w:defUnhideWhenUsed="0"
                                w:defQFormat="0" w:count="376">
                    <w:lsdException w:name="Normal" w:uiPriority="0" w:qFormat="1"/>
                    <w:lsdException w:name="heading 1" w:uiPriority="9" w:qFormat="1"/>
                    <w:lsdException w:name="heading 2" w:semiHidden="1" w:uiPriority="9" w:unhideWhenUsed="1"
                                    w:qFormat="1"/>
                    <w:lsdException w:name="heading 3" w:semiHidden="1" w:uiPriority="9" w:unhideWhenUsed="1"
                                    w:qFormat="1"/>
                    <w:lsdException w:name="heading 4" w:semiHidden="1" w:uiPriority="9" w:unhideWhenUsed="1"
                                    w:qFormat="1"/>
                    <w:lsdException w:name="heading 5" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="heading 6" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="heading 7" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="heading 8" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="heading 9" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="index 1" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="index 2" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="index 3" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="index 4" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="index 5" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="index 6" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="index 7" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="index 8" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="index 9" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="toc 1" w:semiHidden="1" w:uiPriority="39" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="toc 2" w:semiHidden="1" w:uiPriority="39" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="toc 3" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="toc 4" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="toc 5" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="toc 6" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="toc 7" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="toc 8" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="toc 9" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Normal Indent" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="footnote text" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="annotation text" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="header" w:semiHidden="1" w:uiPriority="0" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="footer" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="index heading" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="caption" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="table of figures" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="envelope address" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="envelope return" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="footnote reference" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="annotation reference" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="line number" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="page number" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="endnote reference" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="endnote text" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="table of authorities" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="macro" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="toa heading" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="List" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="List Bullet" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="List Number" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="List 2" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="List 3" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="List 4" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="List 5" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="List Bullet 2" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="List Bullet 3" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="List Bullet 4" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="List Bullet 5" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="List Number 2" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="List Number 3" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="List Number 4" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="List Number 5" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Title" w:uiPriority="0" w:qFormat="1"/>
                    <w:lsdException w:name="Closing" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Signature" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Default Paragraph Font" w:semiHidden="1" w:uiPriority="1"
                                    w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Body Text" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Body Text Indent" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="List Continue" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="List Continue 2" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="List Continue 3" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="List Continue 4" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="List Continue 5" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Message Header" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Subtitle" w:uiPriority="11" w:qFormat="1"/>
                    <w:lsdException w:name="Salutation" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Date" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Body Text First Indent" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Body Text First Indent 2" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Note Heading" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Body Text 2" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Body Text 3" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Body Text Indent 2" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Body Text Indent 3" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Block Text" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Hyperlink" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="FollowedHyperlink" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Emphasis" w:uiPriority="20" w:qFormat="1"/>
                    <w:lsdException w:name="Document Map" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Plain Text" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="E-mail Signature" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="HTML Top of Form" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="HTML Bottom of Form" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Normal (Web)" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="HTML Acronym" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="HTML Address" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="HTML Cite" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="HTML Code" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="HTML Definition" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="HTML Keyboard" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="HTML Preformatted" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="HTML Sample" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="HTML Typewriter" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="HTML Variable" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="annotation subject" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="No List" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Outline List 1" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Outline List 2" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Outline List 3" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table Simple 1" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table Simple 2" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table Simple 3" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table Classic 1" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table Classic 2" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table Classic 3" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table Classic 4" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table Colorful 1" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table Colorful 2" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table Colorful 3" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table Columns 1" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table Columns 2" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table Columns 3" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table Columns 4" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table Columns 5" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table Grid 1" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table Grid 2" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table Grid 3" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table Grid 4" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table Grid 5" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table Grid 6" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table Grid 7" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table Grid 8" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table List 1" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table List 2" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table List 3" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table List 4" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table List 5" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table List 6" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table List 7" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table List 8" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table 3D effects 1" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table 3D effects 2" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table 3D effects 3" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table Contemporary" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table Elegant" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table Professional" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table Subtle 2" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table Web 1" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Balloon Text" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Table Grid" w:uiPriority="59"/>
                    <w:lsdException w:name="Table Theme" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Placeholder Text" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="No Spacing" w:semiHidden="1" w:uiPriority="0" w:unhideWhenUsed="1"
                                    w:qFormat="1"/>
                    <w:lsdException w:name="Light Shading"/>
                    <w:lsdException w:name="Light List"/>
                    <w:lsdException w:name="Light Grid"/>
                    <w:lsdException w:name="Medium Shading 1"/>
                    <w:lsdException w:name="Medium Shading 2"/>
                    <w:lsdException w:name="Medium List 1"/>
                    <w:lsdException w:name="Medium List 2"/>
                    <w:lsdException w:name="Medium Grid 1"/>
                    <w:lsdException w:name="Medium Grid 2"/>
                    <w:lsdException w:name="Medium Grid 3"/>
                    <w:lsdException w:name="Dark List"/>
                    <w:lsdException w:name="Colorful Shading"/>
                    <w:lsdException w:name="Colorful List"/>
                    <w:lsdException w:name="Colorful Grid"/>
                    <w:lsdException w:name="Light Shading Accent 1"/>
                    <w:lsdException w:name="Medium List 1 Accent 1"/>
                    <w:lsdException w:name="Revision" w:semiHidden="1"/>
                    <w:lsdException w:name="Medium List 2 Accent 1"/>
                    <w:lsdException w:name="Medium Grid 1 Accent 1"/>
                    <w:lsdException w:name="Medium Grid 2 Accent 1"/>
                    <w:lsdException w:name="Medium Grid 3 Accent 1"/>
                    <w:lsdException w:name="Dark List Accent 1"/>
                    <w:lsdException w:name="Colorful Shading Accent 1"/>
                    <w:lsdException w:name="Colorful List Accent 1"/>
                    <w:lsdException w:name="Colorful Grid Accent 1"/>
                    <w:lsdException w:name="Light Shading Accent 2"/>
                    <w:lsdException w:name="Light List Accent 2"/>
                    <w:lsdException w:name="Light Grid Accent 2"/>
                    <w:lsdException w:name="Medium Shading 1 Accent 2"/>
                    <w:lsdException w:name="Medium Shading 2 Accent 2"/>
                    <w:lsdException w:name="Medium List 1 Accent 2"/>
                    <w:lsdException w:name="Medium List 2 Accent 2"/>
                    <w:lsdException w:name="Medium Grid 1 Accent 2"/>
                    <w:lsdException w:name="Medium Grid 2 Accent 2"/>
                    <w:lsdException w:name="Medium Grid 3 Accent 2"/>
                    <w:lsdException w:name="Dark List Accent 2"/>
                    <w:lsdException w:name="Colorful Shading Accent 2"/>
                    <w:lsdException w:name="Colorful List Accent 2"/>
                    <w:lsdException w:name="Colorful Grid Accent 2"/>
                    <w:lsdException w:name="Light Shading Accent 3"/>
                    <w:lsdException w:name="Light List Accent 3"/>
                    <w:lsdException w:name="Light Grid Accent 3"/>
                    <w:lsdException w:name="Medium Shading 1 Accent 3"/>
                    <w:lsdException w:name="Medium Shading 2 Accent 3"/>
                    <w:lsdException w:name="Medium List 1 Accent 3"/>
                    <w:lsdException w:name="Medium List 2 Accent 3"/>
                    <w:lsdException w:name="Medium Grid 1 Accent 3"/>
                    <w:lsdException w:name="Medium Grid 2 Accent 3"/>
                    <w:lsdException w:name="Medium Grid 3 Accent 3"/>
                    <w:lsdException w:name="Dark List Accent 3"/>
                    <w:lsdException w:name="Colorful Shading Accent 3"/>
                    <w:lsdException w:name="Colorful List Accent 3"/>
                    <w:lsdException w:name="Colorful Grid Accent 3"/>
                    <w:lsdException w:name="Light Shading Accent 4"/>
                    <w:lsdException w:name="Light List Accent 4"/>
                    <w:lsdException w:name="Light Grid Accent 4"/>
                    <w:lsdException w:name="Medium Shading 1 Accent 4"/>
                    <w:lsdException w:name="Medium Shading 2 Accent 4"/>
                    <w:lsdException w:name="Medium List 1 Accent 4"/>
                    <w:lsdException w:name="Medium List 2 Accent 4"/>
                    <w:lsdException w:name="Medium Grid 1 Accent 4"/>
                    <w:lsdException w:name="Medium Grid 2 Accent 4"/>
                    <w:lsdException w:name="Medium Grid 3 Accent 4"/>
                    <w:lsdException w:name="Dark List Accent 4"/>
                    <w:lsdException w:name="Colorful Shading Accent 4"/>
                    <w:lsdException w:name="Colorful List Accent 4"/>
                    <w:lsdException w:name="Colorful Grid Accent 4"/>
                    <w:lsdException w:name="Light Shading Accent 5"/>
                    <w:lsdException w:name="Light List Accent 5"/>
                    <w:lsdException w:name="Light Grid Accent 5"/>
                    <w:lsdException w:name="Medium Shading 1 Accent 5"/>
                    <w:lsdException w:name="Medium Shading 2 Accent 5"/>
                    <w:lsdException w:name="Medium List 1 Accent 5"/>
                    <w:lsdException w:name="Medium List 2 Accent 5"/>
                    <w:lsdException w:name="Medium Grid 1 Accent 5"/>
                    <w:lsdException w:name="Medium Grid 2 Accent 5"/>
                    <w:lsdException w:name="Medium Grid 3 Accent 5"/>
                    <w:lsdException w:name="Dark List Accent 5"/>
                    <w:lsdException w:name="Colorful Shading Accent 5"/>
                    <w:lsdException w:name="Colorful List Accent 5"/>
                    <w:lsdException w:name="Colorful Grid Accent 5"/>
                    <w:lsdException w:name="Light Shading Accent 6"/>
                    <w:lsdException w:name="Light List Accent 6"/>
                    <w:lsdException w:name="Light Grid Accent 6"/>
                    <w:lsdException w:name="Medium Shading 1 Accent 6"/>
                    <w:lsdException w:name="Medium Shading 2 Accent 6"/>
                    <w:lsdException w:name="Medium List 1 Accent 6"/>
                    <w:lsdException w:name="Medium List 2 Accent 6"/>
                    <w:lsdException w:name="Medium Grid 1 Accent 6"/>
                    <w:lsdException w:name="Medium Grid 2 Accent 6"/>
                    <w:lsdException w:name="Bibliography" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="TOC Heading" w:semiHidden="1" w:uiPriority="39" w:unhideWhenUsed="1"
                                    w:qFormat="1"/>
                    <w:lsdException w:name="Plain Table 1" w:uiPriority="41"/>
                    <w:lsdException w:name="Plain Table 2" w:uiPriority="42"/>
                    <w:lsdException w:name="Plain Table 3" w:uiPriority="43"/>
                    <w:lsdException w:name="Plain Table 4" w:uiPriority="44"/>
                    <w:lsdException w:name="Plain Table 5" w:uiPriority="45"/>
                    <w:lsdException w:name="Grid Table Light" w:uiPriority="40"/>
                    <w:lsdException w:name="Grid Table 1 Light" w:uiPriority="46"/>
                    <w:lsdException w:name="Grid Table 2" w:uiPriority="47"/>
                    <w:lsdException w:name="Grid Table 3" w:uiPriority="48"/>
                    <w:lsdException w:name="Grid Table 4" w:uiPriority="49"/>
                    <w:lsdException w:name="Grid Table 5 Dark" w:uiPriority="50"/>
                    <w:lsdException w:name="Grid Table 6 Colorful" w:uiPriority="51"/>
                    <w:lsdException w:name="Grid Table 7 Colorful" w:uiPriority="52"/>
                    <w:lsdException w:name="Grid Table 1 Light Accent 1" w:uiPriority="46"/>
                    <w:lsdException w:name="Grid Table 2 Accent 1" w:uiPriority="47"/>
                    <w:lsdException w:name="Grid Table 3 Accent 1" w:uiPriority="48"/>
                    <w:lsdException w:name="Grid Table 4 Accent 1" w:uiPriority="49"/>
                    <w:lsdException w:name="Grid Table 5 Dark Accent 1" w:uiPriority="50"/>
                    <w:lsdException w:name="Grid Table 6 Colorful Accent 1" w:uiPriority="51"/>
                    <w:lsdException w:name="Grid Table 7 Colorful Accent 1" w:uiPriority="52"/>
                    <w:lsdException w:name="Grid Table 1 Light Accent 2" w:uiPriority="46"/>
                    <w:lsdException w:name="Grid Table 2 Accent 2" w:uiPriority="47"/>
                    <w:lsdException w:name="Grid Table 3 Accent 2" w:uiPriority="48"/>
                    <w:lsdException w:name="Grid Table 4 Accent 2" w:uiPriority="49"/>
                    <w:lsdException w:name="Grid Table 5 Dark Accent 2" w:uiPriority="50"/>
                    <w:lsdException w:name="Grid Table 6 Colorful Accent 2" w:uiPriority="51"/>
                    <w:lsdException w:name="Grid Table 7 Colorful Accent 2" w:uiPriority="52"/>
                    <w:lsdException w:name="Grid Table 1 Light Accent 3" w:uiPriority="46"/>
                    <w:lsdException w:name="Grid Table 2 Accent 3" w:uiPriority="47"/>
                    <w:lsdException w:name="Grid Table 3 Accent 3" w:uiPriority="48"/>
                    <w:lsdException w:name="Grid Table 4 Accent 3" w:uiPriority="49"/>
                    <w:lsdException w:name="Grid Table 5 Dark Accent 3" w:uiPriority="50"/>
                    <w:lsdException w:name="Grid Table 6 Colorful Accent 3" w:uiPriority="51"/>
                    <w:lsdException w:name="Grid Table 7 Colorful Accent 3" w:uiPriority="52"/>
                    <w:lsdException w:name="Grid Table 1 Light Accent 4" w:uiPriority="46"/>
                    <w:lsdException w:name="Grid Table 2 Accent 4" w:uiPriority="47"/>
                    <w:lsdException w:name="Grid Table 3 Accent 4" w:uiPriority="48"/>
                    <w:lsdException w:name="Grid Table 4 Accent 4" w:uiPriority="49"/>
                    <w:lsdException w:name="Grid Table 5 Dark Accent 4" w:uiPriority="50"/>
                    <w:lsdException w:name="Grid Table 6 Colorful Accent 4" w:uiPriority="51"/>
                    <w:lsdException w:name="Grid Table 7 Colorful Accent 4" w:uiPriority="52"/>
                    <w:lsdException w:name="Grid Table 1 Light Accent 5" w:uiPriority="46"/>
                    <w:lsdException w:name="Grid Table 2 Accent 5" w:uiPriority="47"/>
                    <w:lsdException w:name="Grid Table 3 Accent 5" w:uiPriority="48"/>
                    <w:lsdException w:name="Grid Table 4 Accent 5" w:uiPriority="49"/>
                    <w:lsdException w:name="Grid Table 5 Dark Accent 5" w:uiPriority="50"/>
                    <w:lsdException w:name="Grid Table 6 Colorful Accent 5" w:uiPriority="51"/>
                    <w:lsdException w:name="Grid Table 7 Colorful Accent 5" w:uiPriority="52"/>
                    <w:lsdException w:name="Grid Table 1 Light Accent 6" w:uiPriority="46"/>
                    <w:lsdException w:name="Grid Table 2 Accent 6" w:uiPriority="47"/>
                    <w:lsdException w:name="Grid Table 3 Accent 6" w:uiPriority="48"/>
                    <w:lsdException w:name="Grid Table 4 Accent 6" w:uiPriority="49"/>
                    <w:lsdException w:name="Grid Table 5 Dark Accent 6" w:uiPriority="50"/>
                    <w:lsdException w:name="Grid Table 6 Colorful Accent 6" w:uiPriority="51"/>
                    <w:lsdException w:name="Grid Table 7 Colorful Accent 6" w:uiPriority="52"/>
                    <w:lsdException w:name="List Table 1 Light" w:uiPriority="46"/>
                    <w:lsdException w:name="List Table 2" w:uiPriority="47"/>
                    <w:lsdException w:name="List Table 3" w:uiPriority="48"/>
                    <w:lsdException w:name="List Table 4" w:uiPriority="49"/>
                    <w:lsdException w:name="List Table 5 Dark" w:uiPriority="50"/>
                    <w:lsdException w:name="List Table 6 Colorful" w:uiPriority="51"/>
                    <w:lsdException w:name="List Table 7 Colorful" w:uiPriority="52"/>
                    <w:lsdException w:name="List Table 1 Light Accent 1" w:uiPriority="46"/>
                    <w:lsdException w:name="List Table 2 Accent 1" w:uiPriority="47"/>
                    <w:lsdException w:name="List Table 3 Accent 1" w:uiPriority="48"/>
                    <w:lsdException w:name="List Table 4 Accent 1" w:uiPriority="49"/>
                    <w:lsdException w:name="List Table 5 Dark Accent 1" w:uiPriority="50"/>
                    <w:lsdException w:name="List Table 6 Colorful Accent 1" w:uiPriority="51"/>
                    <w:lsdException w:name="List Table 7 Colorful Accent 1" w:uiPriority="52"/>
                    <w:lsdException w:name="List Table 1 Light Accent 2" w:uiPriority="46"/>
                    <w:lsdException w:name="List Table 2 Accent 2" w:uiPriority="47"/>
                    <w:lsdException w:name="List Table 3 Accent 2" w:uiPriority="48"/>
                    <w:lsdException w:name="List Table 4 Accent 2" w:uiPriority="49"/>
                    <w:lsdException w:name="List Table 5 Dark Accent 2" w:uiPriority="50"/>
                    <w:lsdException w:name="List Table 6 Colorful Accent 2" w:uiPriority="51"/>
                    <w:lsdException w:name="List Table 7 Colorful Accent 2" w:uiPriority="52"/>
                    <w:lsdException w:name="List Table 1 Light Accent 3" w:uiPriority="46"/>
                    <w:lsdException w:name="List Table 2 Accent 3" w:uiPriority="47"/>
                    <w:lsdException w:name="List Table 3 Accent 3" w:uiPriority="48"/>
                    <w:lsdException w:name="List Table 4 Accent 3" w:uiPriority="49"/>
                    <w:lsdException w:name="List Table 5 Dark Accent 3" w:uiPriority="50"/>
                    <w:lsdException w:name="List Table 6 Colorful Accent 3" w:uiPriority="51"/>
                    <w:lsdException w:name="List Table 7 Colorful Accent 3" w:uiPriority="52"/>
                    <w:lsdException w:name="List Table 1 Light Accent 4" w:uiPriority="46"/>
                    <w:lsdException w:name="List Table 2 Accent 4" w:uiPriority="47"/>
                    <w:lsdException w:name="List Table 3 Accent 4" w:uiPriority="48"/>
                    <w:lsdException w:name="List Table 4 Accent 4" w:uiPriority="49"/>
                    <w:lsdException w:name="List Table 5 Dark Accent 4" w:uiPriority="50"/>
                    <w:lsdException w:name="List Table 6 Colorful Accent 4" w:uiPriority="51"/>
                    <w:lsdException w:name="List Table 7 Colorful Accent 4" w:uiPriority="52"/>
                    <w:lsdException w:name="List Table 1 Light Accent 5" w:uiPriority="46"/>
                    <w:lsdException w:name="List Table 2 Accent 5" w:uiPriority="47"/>
                    <w:lsdException w:name="List Table 3 Accent 5" w:uiPriority="48"/>
                    <w:lsdException w:name="List Table 4 Accent 5" w:uiPriority="49"/>
                    <w:lsdException w:name="List Table 5 Dark Accent 5" w:uiPriority="50"/>
                    <w:lsdException w:name="List Table 6 Colorful Accent 5" w:uiPriority="51"/>
                    <w:lsdException w:name="List Table 7 Colorful Accent 5" w:uiPriority="52"/>
                    <w:lsdException w:name="List Table 1 Light Accent 6" w:uiPriority="46"/>
                    <w:lsdException w:name="List Table 2 Accent 6" w:uiPriority="47"/>
                    <w:lsdException w:name="List Table 3 Accent 6" w:uiPriority="48"/>
                    <w:lsdException w:name="List Table 4 Accent 6" w:uiPriority="49"/>
                    <w:lsdException w:name="List Table 5 Dark Accent 6" w:uiPriority="50"/>
                    <w:lsdException w:name="List Table 6 Colorful Accent 6" w:uiPriority="51"/>
                    <w:lsdException w:name="List Table 7 Colorful Accent 6" w:uiPriority="52"/>
                    <w:lsdException w:name="Mention" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Smart Hyperlink" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Hashtag" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Unresolved Mention" w:semiHidden="1" w:unhideWhenUsed="1"/>
                    <w:lsdException w:name="Smart Link" w:semiHidden="1" w:unhideWhenUsed="1"/>
                </w:latentStyles>
                <w:style w:type="paragraph" w:default="1" w:styleId="a">
                    <w:name w:val="Normal"/>
                    <w:qFormat/>
                    <w:rsid w:val="004A3277"/>
                </w:style>
                <w:style w:type="paragraph" w:styleId="1">
                    <w:name w:val="heading 1"/>
                    <w:basedOn w:val="a"/>
                    <w:next w:val="a"/>
                    <w:link w:val="10"/>
                    <w:uiPriority w:val="9"/>
                    <w:qFormat/>
                    <w:rsid w:val="00841CD9"/>
                    <w:pPr>
                        <w:keepNext/>
                        <w:keepLines/>
                        <w:spacing w:before="480"/>
                        <w:outlineLvl w:val="0"/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:asciiTheme="majorHAnsi" w:eastAsiaTheme="majorEastAsia" w:hAnsiTheme="majorHAnsi"
                                  w:cstheme="majorBidi"/>
                        <w:b/>
                        <w:bCs/>
                        <w:color w:val="2E74B5" w:themeColor="accent1" w:themeShade="BF"/>
                        <w:sz w:val="28"/>
                        <w:szCs w:val="28"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:styleId="2">
                    <w:name w:val="heading 2"/>
                    <w:basedOn w:val="a"/>
                    <w:next w:val="a"/>
                    <w:link w:val="20"/>
                    <w:uiPriority w:val="9"/>
                    <w:unhideWhenUsed/>
                    <w:qFormat/>
                    <w:rsid w:val="00841CD9"/>
                    <w:pPr>
                        <w:keepNext/>
                        <w:keepLines/>
                        <w:spacing w:before="200"/>
                        <w:outlineLvl w:val="1"/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:asciiTheme="majorHAnsi" w:eastAsiaTheme="majorEastAsia" w:hAnsiTheme="majorHAnsi"
                                  w:cstheme="majorBidi"/>
                        <w:b/>
                        <w:bCs/>
                        <w:color w:val="5B9BD5" w:themeColor="accent1"/>
                        <w:sz w:val="26"/>
                        <w:szCs w:val="26"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:styleId="3">
                    <w:name w:val="heading 3"/>
                    <w:basedOn w:val="a"/>
                    <w:next w:val="a"/>
                    <w:link w:val="30"/>
                    <w:uiPriority w:val="9"/>
                    <w:unhideWhenUsed/>
                    <w:qFormat/>
                    <w:rsid w:val="00841CD9"/>
                    <w:pPr>
                        <w:keepNext/>
                        <w:keepLines/>
                        <w:spacing w:before="200"/>
                        <w:outlineLvl w:val="2"/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:asciiTheme="majorHAnsi" w:eastAsiaTheme="majorEastAsia" w:hAnsiTheme="majorHAnsi"
                                  w:cstheme="majorBidi"/>
                        <w:b/>
                        <w:bCs/>
                        <w:color w:val="5B9BD5" w:themeColor="accent1"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:styleId="4">
                    <w:name w:val="heading 4"/>
                    <w:basedOn w:val="a"/>
                    <w:next w:val="a"/>
                    <w:link w:val="40"/>
                    <w:uiPriority w:val="9"/>
                    <w:unhideWhenUsed/>
                    <w:qFormat/>
                    <w:rsid w:val="00841CD9"/>
                    <w:pPr>
                        <w:keepNext/>
                        <w:keepLines/>
                        <w:spacing w:before="200"/>
                        <w:outlineLvl w:val="3"/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:asciiTheme="majorHAnsi" w:eastAsiaTheme="majorEastAsia" w:hAnsiTheme="majorHAnsi"
                                  w:cstheme="majorBidi"/>
                        <w:b/>
                        <w:bCs/>
                        <w:i/>
                        <w:iCs/>
                        <w:color w:val="5B9BD5" w:themeColor="accent1"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="character" w:default="1" w:styleId="a0">
                    <w:name w:val="Default Paragraph Font"/>
                    <w:uiPriority w:val="1"/>
                    <w:semiHidden/>
                    <w:unhideWhenUsed/>
                </w:style>
                <w:style w:type="table" w:default="1" w:styleId="a1">
                    <w:name w:val="Normal Table"/>
                    <w:uiPriority w:val="99"/>
                    <w:semiHidden/>
                    <w:unhideWhenUsed/>
                    <w:tblPr>
                        <w:tblInd w:w="0" w:type="dxa"/>
                        <w:tblCellMar>
                            <w:top w:w="0" w:type="dxa"/>
                            <w:left w:w="108" w:type="dxa"/>
                            <w:bottom w:w="0" w:type="dxa"/>
                            <w:right w:w="108" w:type="dxa"/>
                        </w:tblCellMar>
                    </w:tblPr>
                </w:style>
                <w:style w:type="numbering" w:default="1" w:styleId="a2">
                    <w:name w:val="No List"/>
                    <w:uiPriority w:val="99"/>
                    <w:semiHidden/>
                    <w:unhideWhenUsed/>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="a3">
                    <w:name w:val="封面顶部样式"/>
                    <w:pPr>
                        <w:shd w:val="clear" w:color="auto" w:fill="629E2C"/>
                        <w:spacing w:after="30" w:line="4000" w:lineRule="auto"/>
                    </w:pPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="a4">
                    <w:name w:val="封面报告种类样式"/>
                    <w:pPr>
                        <w:shd w:val="clear" w:color="auto" w:fill="629E2C"/>
                        <w:spacing w:after="50"/>
                        <w:jc w:val="center"/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:ascii="HELVETICA NEUE MEDIUM" w:eastAsia="Source Han Sans CN"/>
                        <w:color w:val="FFFFFF"/>
                        <w:sz w:val="28"/>
                        <w:szCs w:val="28"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="a5">
                    <w:name w:val="封面报告标题样式"/>
                    <w:pPr>
                        <w:shd w:val="clear" w:color="auto" w:fill="629E2C"/>
                        <w:spacing w:after="0"/>
                        <w:jc w:val="center"/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:ascii="HELVETICA NEUE MEDIUM" w:eastAsia="Source Han Sans CN"/>
                        <w:color w:val="FFFFFF"/>
                        <w:sz w:val="32"/>
                        <w:szCs w:val="32"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="a6">
                    <w:name w:val="封面顶部样式二"/>
                    <w:pPr>
                        <w:shd w:val="clear" w:color="auto" w:fill="629E2C"/>
                        <w:spacing w:after="30" w:line="3000" w:lineRule="auto"/>
                    </w:pPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="a7">
                    <w:name w:val="封面报告日期样式"/>
                    <w:pPr>
                        <w:shd w:val="clear" w:color="auto" w:fill="629E2C"/>
                        <w:spacing w:after="30" w:line="1000" w:lineRule="auto"/>
                        <w:jc w:val="center"/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:eastAsia="Source Han Sans CN"/>
                        <w:color w:val="D3FB90"/>
                        <w:sz w:val="16"/>
                        <w:szCs w:val="16"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="A8">
                    <w:name w:val="封面顶部样式二A"/>
                    <w:pPr>
                        <w:shd w:val="clear" w:color="auto" w:fill="629E2C"/>
                        <w:spacing w:after="6" w:line="350" w:lineRule="auto"/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:eastAsia="Source Han Sans CN"/>
                        <w:color w:val="D3FB90"/>
                        <w:sz w:val="6"/>
                        <w:szCs w:val="6"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="a9">
                    <w:name w:val="封面空行样式"/>
                    <w:pPr>
                        <w:spacing w:after="30" w:line="30" w:lineRule="auto"/>
                    </w:pPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="aa">
                    <w:name w:val="封面底部样式"/>
                    <w:pPr>
                        <w:shd w:val="clear" w:color="auto" w:fill="444444"/>
                        <w:spacing w:after="30" w:line="700" w:lineRule="auto"/>
                        <w:jc w:val="center"/>
                    </w:pPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="ab">
                    <w:name w:val="封面图标样式"/>
                    <w:pPr>
                        <w:shd w:val="clear" w:color="auto" w:fill="444444"/>
                        <w:spacing w:after="30" w:line="180" w:lineRule="auto"/>
                        <w:jc w:val="center"/>
                    </w:pPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="ac">
                    <w:name w:val="封面域名样式"/>
                    <w:pPr>
                        <w:shd w:val="clear" w:color="auto" w:fill="444444"/>
                        <w:spacing w:after="30" w:line="1100" w:lineRule="auto"/>
                        <w:jc w:val="center"/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:eastAsia="Source Han Sans CN"/>
                        <w:color w:val="FFFFFF"/>
                        <w:sz w:val="15"/>
                        <w:szCs w:val="15"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="Ad">
                    <w:name w:val="封面底部样式A"/>
                    <w:pPr>
                        <w:shd w:val="clear" w:color="auto" w:fill="444444"/>
                        <w:spacing w:after="30" w:line="40" w:lineRule="auto"/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:eastAsia="Source Han Sans CN"/>
                        <w:color w:val="FFFFFF"/>
                        <w:sz w:val="15"/>
                        <w:szCs w:val="15"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="ae">
                    <w:name w:val="设置样式"/>
                    <w:uiPriority w:val="9"/>
                    <w:pPr>
                        <w:keepNext/>
                        <w:keepLines/>
                        <w:jc w:val="center"/>
                        <w:outlineLvl w:val="0"/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:eastAsia="Source Han Sans CN"/>
                        <w:b/>
                        <w:color w:val="75BB00"/>
                        <w:sz w:val="40"/>
                        <w:szCs w:val="40"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="af">
                    <w:name w:val="概述样式"/>
                    <w:uiPriority w:val="9"/>
                    <w:pPr>
                        <w:keepNext/>
                        <w:keepLines/>
                        <w:jc w:val="center"/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:eastAsia="Source Han Sans CN"/>
                        <w:b/>
                        <w:color w:val="404040"/>
                        <w:sz w:val="28"/>
                        <w:szCs w:val="28"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="af0">
                    <w:name w:val="设置项标题样式"/>
                    <w:uiPriority w:val="9"/>
                    <w:pPr>
                        <w:keepNext/>
                        <w:keepLines/>
                        <w:outlineLvl w:val="1"/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:eastAsia="Source Han Sans CN"/>
                        <w:b/>
                        <w:color w:val="000000"/>
                        <w:sz w:val="28"/>
                        <w:szCs w:val="28"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="af1">
                    <w:name w:val="设置项内容样式"/>
                    <w:uiPriority w:val="9"/>
                    <w:pPr>
                        <w:keepNext/>
                        <w:keepLines/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:eastAsia="Source Han Sans CN"/>
                        <w:color w:val="000000"/>
                        <w:sz w:val="24"/>
                        <w:szCs w:val="24"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="settingstyle">
                    <w:name w:val="setting style"/>
                    <w:uiPriority w:val="9"/>
                    <w:pPr>
                        <w:keepNext/>
                        <w:keepLines/>
                        <w:jc w:val="center"/>
                        <w:outlineLvl w:val="0"/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:ascii="HELVETICA NEUE MEDIUM"/>
                        <w:b/>
                        <w:color w:val="75BB00"/>
                        <w:sz w:val="40"/>
                        <w:szCs w:val="40"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="summarystyle">
                    <w:name w:val="summary style"/>
                    <w:uiPriority w:val="9"/>
                    <w:pPr>
                        <w:keepNext/>
                        <w:keepLines/>
                        <w:jc w:val="center"/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:ascii="HELVETICA NEUE THIN"/>
                        <w:bCs/>
                        <w:color w:val="404040"/>
                        <w:sz w:val="28"/>
                        <w:szCs w:val="28"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="settingitemtitlestyle">
                    <w:name w:val="setting item title style"/>
                    <w:uiPriority w:val="9"/>
                    <w:pPr>
                        <w:keepNext/>
                        <w:keepLines/>
                        <w:outlineLvl w:val="1"/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:ascii="HELVETICA NEUE MEDIUM"/>
                        <w:b/>
                        <w:color w:val="000000"/>
                        <w:sz w:val="28"/>
                        <w:szCs w:val="28"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="settingitemcontentstyle">
                    <w:name w:val="setting item content style"/>
                    <w:uiPriority w:val="9"/>
                    <w:pPr>
                        <w:keepNext/>
                        <w:keepLines/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:ascii="HELVETICA NEUE LIGHT"/>
                        <w:color w:val="000000"/>
                        <w:sz w:val="24"/>
                        <w:szCs w:val="24"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="af2">
                    <w:name w:val="图表名称样式"/>
                    <w:uiPriority w:val="9"/>
                    <w:pPr>
                        <w:keepNext/>
                        <w:keepLines/>
                        <w:outlineLvl w:val="0"/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:eastAsia="Source Han Sans CN"/>
                        <w:b/>
                        <w:color w:val="000000"/>
                        <w:sz w:val="48"/>
                        <w:szCs w:val="48"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="af3">
                    <w:name w:val="图表标题样式"/>
                    <w:uiPriority w:val="9"/>
                    <w:pPr>
                        <w:keepNext/>
                        <w:keepLines/>
                        <w:outlineLvl w:val="1"/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:eastAsia="Source Han Sans CN"/>
                        <w:b/>
                        <w:color w:val="000000"/>
                        <w:sz w:val="36"/>
                        <w:szCs w:val="36"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="af4">
                    <w:name w:val="图表问题描述示例"/>
                    <w:uiPriority w:val="9"/>
                    <w:pPr>
                        <w:keepNext/>
                        <w:keepLines/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:eastAsia="Source Han Sans CN"/>
                        <w:color w:val="404040"/>
                        <w:sz w:val="20"/>
                        <w:szCs w:val="20"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="af5">
                    <w:name w:val="图表样式"/>
                    <w:pPr>
                        <w:jc w:val="center"/>
                    </w:pPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="chartmodulestyle">
                    <w:name w:val="chart module style"/>
                    <w:uiPriority w:val="9"/>
                    <w:pPr>
                        <w:keepNext/>
                        <w:keepLines/>
                        <w:outlineLvl w:val="0"/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:ascii="HELVETICA NEUE MEDIUM"/>
                        <w:b/>
                        <w:color w:val="000000"/>
                        <w:sz w:val="48"/>
                        <w:szCs w:val="48"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="charttitlestyle">
                    <w:name w:val="chart title style"/>
                    <w:uiPriority w:val="9"/>
                    <w:pPr>
                        <w:keepNext/>
                        <w:keepLines/>
                        <w:outlineLvl w:val="1"/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:ascii="HELVETICA NEUE THIN"/>
                        <w:b/>
                        <w:color w:val="000000"/>
                        <w:sz w:val="36"/>
                        <w:szCs w:val="36"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="chartquestionstyle">
                    <w:name w:val="chart question style"/>
                    <w:uiPriority w:val="9"/>
                    <w:pPr>
                        <w:keepNext/>
                        <w:keepLines/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:ascii="HELVETICA NEUE LIGHT"/>
                        <w:color w:val="404040"/>
                        <w:sz w:val="20"/>
                        <w:szCs w:val="20"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:styleId="af6">
                    <w:name w:val="header"/>
                    <w:basedOn w:val="a"/>
                    <w:link w:val="af7"/>
                    <w:unhideWhenUsed/>
                    <w:rsid w:val="00841CD9"/>
                    <w:pPr>
                        <w:tabs>
                            <w:tab w:val="center" w:pos="4680"/>
                            <w:tab w:val="right" w:pos="9360"/>
                        </w:tabs>
                    </w:pPr>
                </w:style>
                <w:style w:type="character" w:customStyle="1" w:styleId="af7">
                    <w:name w:val="页眉 字符"/>
                    <w:basedOn w:val="a0"/>
                    <w:link w:val="af6"/>
                    <w:rsid w:val="00841CD9"/>
                </w:style>
                <w:style w:type="character" w:customStyle="1" w:styleId="10">
                    <w:name w:val="标题 1 字符"/>
                    <w:basedOn w:val="a0"/>
                    <w:link w:val="1"/>
                    <w:uiPriority w:val="9"/>
                    <w:rsid w:val="00841CD9"/>
                    <w:rPr>
                        <w:rFonts w:asciiTheme="majorHAnsi" w:eastAsiaTheme="majorEastAsia" w:hAnsiTheme="majorHAnsi"
                                  w:cstheme="majorBidi"/>
                        <w:b/>
                        <w:bCs/>
                        <w:color w:val="2E74B5" w:themeColor="accent1" w:themeShade="BF"/>
                        <w:sz w:val="28"/>
                        <w:szCs w:val="28"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="character" w:customStyle="1" w:styleId="20">
                    <w:name w:val="标题 2 字符"/>
                    <w:basedOn w:val="a0"/>
                    <w:link w:val="2"/>
                    <w:uiPriority w:val="9"/>
                    <w:rsid w:val="00841CD9"/>
                    <w:rPr>
                        <w:rFonts w:asciiTheme="majorHAnsi" w:eastAsiaTheme="majorEastAsia" w:hAnsiTheme="majorHAnsi"
                                  w:cstheme="majorBidi"/>
                        <w:b/>
                        <w:bCs/>
                        <w:color w:val="5B9BD5" w:themeColor="accent1"/>
                        <w:sz w:val="26"/>
                        <w:szCs w:val="26"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="character" w:customStyle="1" w:styleId="30">
                    <w:name w:val="标题 3 字符"/>
                    <w:basedOn w:val="a0"/>
                    <w:link w:val="3"/>
                    <w:uiPriority w:val="9"/>
                    <w:rsid w:val="00841CD9"/>
                    <w:rPr>
                        <w:rFonts w:asciiTheme="majorHAnsi" w:eastAsiaTheme="majorEastAsia" w:hAnsiTheme="majorHAnsi"
                                  w:cstheme="majorBidi"/>
                        <w:b/>
                        <w:bCs/>
                        <w:color w:val="5B9BD5" w:themeColor="accent1"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="character" w:customStyle="1" w:styleId="40">
                    <w:name w:val="标题 4 字符"/>
                    <w:basedOn w:val="a0"/>
                    <w:link w:val="4"/>
                    <w:uiPriority w:val="9"/>
                    <w:rsid w:val="00841CD9"/>
                    <w:rPr>
                        <w:rFonts w:asciiTheme="majorHAnsi" w:eastAsiaTheme="majorEastAsia" w:hAnsiTheme="majorHAnsi"
                                  w:cstheme="majorBidi"/>
                        <w:b/>
                        <w:bCs/>
                        <w:i/>
                        <w:iCs/>
                        <w:color w:val="5B9BD5" w:themeColor="accent1"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:styleId="af8">
                    <w:name w:val="Normal Indent"/>
                    <w:basedOn w:val="a"/>
                    <w:uiPriority w:val="99"/>
                    <w:unhideWhenUsed/>
                    <w:rsid w:val="00841CD9"/>
                    <w:pPr>
                        <w:ind w:left="720"/>
                    </w:pPr>
                </w:style>
                <w:style w:type="paragraph" w:styleId="af9">
                    <w:name w:val="Subtitle"/>
                    <w:basedOn w:val="a"/>
                    <w:next w:val="a"/>
                    <w:link w:val="afa"/>
                    <w:uiPriority w:val="11"/>
                    <w:qFormat/>
                    <w:rsid w:val="00841CD9"/>
                    <w:pPr>
                        <w:numPr>
                            <w:ilvl w:val="1"/>
                        </w:numPr>
                        <w:ind w:left="86"/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:asciiTheme="majorHAnsi" w:eastAsiaTheme="majorEastAsia" w:hAnsiTheme="majorHAnsi"
                                  w:cstheme="majorBidi"/>
                        <w:i/>
                        <w:iCs/>
                        <w:color w:val="5B9BD5" w:themeColor="accent1"/>
                        <w:spacing w:val="15"/>
                        <w:sz w:val="24"/>
                        <w:szCs w:val="24"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="character" w:customStyle="1" w:styleId="afa">
                    <w:name w:val="副标题 字符"/>
                    <w:basedOn w:val="a0"/>
                    <w:link w:val="af9"/>
                    <w:uiPriority w:val="11"/>
                    <w:rsid w:val="00841CD9"/>
                    <w:rPr>
                        <w:rFonts w:asciiTheme="majorHAnsi" w:eastAsiaTheme="majorEastAsia" w:hAnsiTheme="majorHAnsi"
                                  w:cstheme="majorBidi"/>
                        <w:i/>
                        <w:iCs/>
                        <w:color w:val="5B9BD5" w:themeColor="accent1"/>
                        <w:spacing w:val="15"/>
                        <w:sz w:val="24"/>
                        <w:szCs w:val="24"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:styleId="afb">
                    <w:name w:val="Title"/>
                    <w:basedOn w:val="a"/>
                    <w:next w:val="a"/>
                    <w:link w:val="afc"/>
                    <w:qFormat/>
                    <w:rsid w:val="00841CD9"/>
                    <w:pPr>
                        <w:pBdr>
                            <w:bottom w:val="single" w:sz="8" w:space="4" w:color="5B9BD5" w:themeColor="accent1"/>
                        </w:pBdr>
                        <w:spacing w:after="300"/>
                        <w:contextualSpacing/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:asciiTheme="majorHAnsi" w:eastAsiaTheme="majorEastAsia" w:hAnsiTheme="majorHAnsi"
                                  w:cstheme="majorBidi"/>
                        <w:color w:val="323E4F" w:themeColor="text2" w:themeShade="BF"/>
                        <w:spacing w:val="5"/>
                        <w:kern w:val="28"/>
                        <w:sz w:val="52"/>
                        <w:szCs w:val="52"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="character" w:customStyle="1" w:styleId="afc">
                    <w:name w:val="标题 字符"/>
                    <w:basedOn w:val="a0"/>
                    <w:link w:val="afb"/>
                    <w:rsid w:val="00841CD9"/>
                    <w:rPr>
                        <w:rFonts w:asciiTheme="majorHAnsi" w:eastAsiaTheme="majorEastAsia" w:hAnsiTheme="majorHAnsi"
                                  w:cstheme="majorBidi"/>
                        <w:color w:val="323E4F" w:themeColor="text2" w:themeShade="BF"/>
                        <w:spacing w:val="5"/>
                        <w:kern w:val="28"/>
                        <w:sz w:val="52"/>
                        <w:szCs w:val="52"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="character" w:styleId="afd">
                    <w:name w:val="Emphasis"/>
                    <w:basedOn w:val="a0"/>
                    <w:uiPriority w:val="20"/>
                    <w:qFormat/>
                    <w:rsid w:val="00D1197D"/>
                    <w:rPr>
                        <w:i/>
                        <w:iCs/>
                    </w:rPr>
                </w:style>
                <w:style w:type="character" w:styleId="afe">
                    <w:name w:val="Hyperlink"/>
                    <w:basedOn w:val="a0"/>
                    <w:uiPriority w:val="99"/>
                    <w:unhideWhenUsed/>
                    <w:rPr>
                        <w:color w:val="0563C1" w:themeColor="hyperlink"/>
                        <w:u w:val="single"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="table" w:styleId="aff">
                    <w:name w:val="Table Grid"/>
                    <w:basedOn w:val="a1"/>
                    <w:uiPriority w:val="59"/>
                    <w:pPr>
                        <w:spacing w:after="0" w:line="240" w:lineRule="auto"/>
                    </w:pPr>
                    <w:tblPr>
                        <w:tblBorders>
                            <w:top w:val="single" w:sz="4" w:space="0" w:color="000000" w:themeColor="text1"/>
                            <w:left w:val="single" w:sz="4" w:space="0" w:color="000000" w:themeColor="text1"/>
                            <w:bottom w:val="single" w:sz="4" w:space="0" w:color="000000" w:themeColor="text1"/>
                            <w:right w:val="single" w:sz="4" w:space="0" w:color="000000" w:themeColor="text1"/>
                            <w:insideH w:val="single" w:sz="4" w:space="0" w:color="000000" w:themeColor="text1"/>
                            <w:insideV w:val="single" w:sz="4" w:space="0" w:color="000000" w:themeColor="text1"/>
                        </w:tblBorders>
                    </w:tblPr>
                </w:style>
                <w:style w:type="paragraph" w:styleId="aff0">
                    <w:name w:val="caption"/>
                    <w:basedOn w:val="a"/>
                    <w:next w:val="a"/>
                    <w:uiPriority w:val="35"/>
                    <w:semiHidden/>
                    <w:unhideWhenUsed/>
                    <w:qFormat/>
                    <w:rsid w:val="007109C0"/>
                    <w:pPr>
                        <w:spacing w:line="240" w:lineRule="auto"/>
                    </w:pPr>
                    <w:rPr>
                        <w:b/>
                        <w:bCs/>
                        <w:color w:val="5B9BD5" w:themeColor="accent1"/>
                        <w:sz w:val="18"/>
                        <w:szCs w:val="18"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="tablecellheaderstyle">
                    <w:name w:val="table cell header style"/>
                    <w:uiPriority w:val="9"/>
                    <w:pPr>
                        <w:wordWrap w:val="0"/>
                        <w:spacing w:afterLines="100" w:line="240" w:lineRule="auto"/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:ascii="Source Han Sans CN" w:eastAsia="Source Han Sans CN"/>
                        <w:color w:val="777777"/>
                        <w:sz w:val="18"/>
                        <w:szCs w:val="18"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="tablecellcontentstyle">
                    <w:name w:val="table cell content style"/>
                    <w:uiPriority w:val="9"/>
                    <w:pPr>
                        <w:wordWrap w:val="0"/>
                        <w:spacing w:afterLines="100" w:line="240" w:lineRule="auto"/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:ascii="Source Han Sans CN" w:eastAsia="Source Han Sans CN"/>
                        <w:b/>
                        <w:color w:val="000000"/>
                        <w:sz w:val="30"/>
                        <w:szCs w:val="30"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="tablecelldescstyle">
                    <w:name w:val="table cell desc style"/>
                    <w:rPr>
                        <w:color w:val="777777"/>
                        <w:sz w:val="18"/>
                        <w:szCs w:val="18"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="richtablecellheader">
                    <w:name w:val="rich table cell header"/>
                    <w:uiPriority w:val="9"/>
                    <w:pPr>
                        <w:jc w:val="center"/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:ascii="Source Han Sans CN" w:eastAsia="Source Han Sans CN"/>
                        <w:b/>
                        <w:color w:val="000000"/>
                        <w:sz w:val="18"/>
                        <w:szCs w:val="18"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="richtablecellcontentstyle">
                    <w:name w:val="rich table cell content style"/>
                    <w:uiPriority w:val="9"/>
                    <w:pPr>
                        <w:spacing w:afterLines="100" w:line="240" w:lineRule="auto"/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:ascii="Source Han Sans CN" w:eastAsia="Source Han Sans CN"/>
                        <w:color w:val="333333"/>
                        <w:sz w:val="20"/>
                        <w:szCs w:val="20"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="richtablecellpnstyle">
                    <w:name w:val="rich table cell pn style"/>
                    <w:uiPriority w:val="9"/>
                    <w:pPr>
                        <w:spacing w:afterLines="100" w:line="240" w:lineRule="auto"/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:ascii="Source Han Sans CN" w:eastAsia="Source Han Sans CN"/>
                        <w:b/>
                        <w:color w:val="333333"/>
                        <w:sz w:val="20"/>
                        <w:szCs w:val="20"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:customStyle="1" w:styleId="richtablecellassignstyle">
                    <w:name w:val="rich table cell assign style"/>
                    <w:uiPriority w:val="9"/>
                    <w:pPr>
                        <w:spacing w:afterLines="100" w:line="240" w:lineRule="auto"/>
                    </w:pPr>
                    <w:rPr>
                        <w:rFonts w:ascii="Source Han Sans CN" w:eastAsia="Source Han Sans CN"/>
                        <w:b/>
                        <w:color w:val="2D74A5"/>
                        <w:sz w:val="20"/>
                        <w:szCs w:val="20"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:styleId="aff1">
                    <w:name w:val="footer"/>
                    <w:basedOn w:val="a"/>
                    <w:link w:val="aff2"/>
                    <w:uiPriority w:val="99"/>
                    <w:unhideWhenUsed/>
                    <w:rsid w:val="00C339C0"/>
                    <w:pPr>
                        <w:tabs>
                            <w:tab w:val="center" w:pos="4153"/>
                            <w:tab w:val="right" w:pos="8306"/>
                        </w:tabs>
                        <w:snapToGrid w:val="0"/>
                        <w:spacing w:line="240" w:lineRule="auto"/>
                    </w:pPr>
                    <w:rPr>
                        <w:sz w:val="18"/>
                        <w:szCs w:val="18"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="character" w:customStyle="1" w:styleId="aff2">
                    <w:name w:val="页脚 字符"/>
                    <w:basedOn w:val="a0"/>
                    <w:link w:val="aff1"/>
                    <w:uiPriority w:val="99"/>
                    <w:rsid w:val="00C339C0"/>
                    <w:rPr>
                        <w:sz w:val="18"/>
                        <w:szCs w:val="18"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="character" w:customStyle="1" w:styleId="aff3">
                    <w:name w:val="无间隔 字符"/>
                    <w:link w:val="aff4"/>
                    <w:locked/>
                    <w:rsid w:val="00482922"/>
                    <w:rPr>
                        <w:kern w:val="2"/>
                        <w:lang w:eastAsia="zh-CN"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:styleId="aff4">
                    <w:name w:val="No Spacing"/>
                    <w:link w:val="aff3"/>
                    <w:qFormat/>
                    <w:rsid w:val="00482922"/>
                    <w:pPr>
                        <w:spacing w:after="0" w:line="240" w:lineRule="auto"/>
                    </w:pPr>
                    <w:rPr>
                        <w:kern w:val="2"/>
                        <w:lang w:eastAsia="zh-CN"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:styleId="TOC">
                    <w:name w:val="TOC Heading"/>
                    <w:basedOn w:val="1"/>
                    <w:next w:val="a"/>
                    <w:uiPriority w:val="39"/>
                    <w:unhideWhenUsed/>
                    <w:qFormat/>
                    <w:rsid w:val="009F1425"/>
                    <w:pPr>
                        <w:spacing w:before="240" w:after="0" w:line="259" w:lineRule="auto"/>
                        <w:outlineLvl w:val="9"/>
                    </w:pPr>
                    <w:rPr>
                        <w:b w:val="0"/>
                        <w:bCs w:val="0"/>
                        <w:sz w:val="32"/>
                        <w:szCs w:val="32"/>
                        <w:lang w:eastAsia="zh-CN"/>
                    </w:rPr>
                </w:style>
                <w:style w:type="paragraph" w:styleId="TOC1">
                    <w:name w:val="toc 1"/>
                    <w:basedOn w:val="a"/>
                    <w:next w:val="a"/>
                    <w:autoRedefine/>
                    <w:uiPriority w:val="39"/>
                    <w:unhideWhenUsed/>
                    <w:rsid w:val="009F1425"/>
                </w:style>
                <w:style w:type="paragraph" w:styleId="TOC2">
                    <w:name w:val="toc 2"/>
                    <w:basedOn w:val="a"/>
                    <w:next w:val="a"/>
                    <w:autoRedefine/>
                    <w:uiPriority w:val="39"/>
                    <w:unhideWhenUsed/>
                    <w:rsid w:val="009F1425"/>
                    <w:pPr>
                        <w:ind w:leftChars="200" w:left="420"/>
                    </w:pPr>
                </w:style>
            </w:styles>
        </pkg:xmlData>
    </pkg:part>
    <pkg:part pkg:name="/word/webSettings.xml"
              pkg:contentType="application/vnd.openxmlformats-officedocument.wordprocessingml.webSettings+xml">
        <pkg:xmlData>
            <w:webSettings xmlns:mc="http://schemas.openxmlformats.org/markup-compatibility/2006"
                           xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships"
                           xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main"
                           xmlns:w14="http://schemas.microsoft.com/office/word/2010/wordml"
                           xmlns:w15="http://schemas.microsoft.com/office/word/2012/wordml"
                           xmlns:w16cex="http://schemas.microsoft.com/office/word/2018/wordml/cex"
                           xmlns:w16cid="http://schemas.microsoft.com/office/word/2016/wordml/cid"
                           xmlns:w16="http://schemas.microsoft.com/office/word/2018/wordml"
                           xmlns:w16se="http://schemas.microsoft.com/office/word/2015/wordml/symex"
                           mc:Ignorable="w14 w15 w16se w16cid w16 w16cex"/>
        </pkg:xmlData>
    </pkg:part>
    <pkg:part pkg:name="/word/fontTable.xml"
              pkg:contentType="application/vnd.openxmlformats-officedocument.wordprocessingml.fontTable+xml">
        <pkg:xmlData>
            <w:fonts xmlns:mc="http://schemas.openxmlformats.org/markup-compatibility/2006"
                     xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships"
                     xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main"
                     xmlns:w14="http://schemas.microsoft.com/office/word/2010/wordml"
                     xmlns:w15="http://schemas.microsoft.com/office/word/2012/wordml"
                     xmlns:w16cex="http://schemas.microsoft.com/office/word/2018/wordml/cex"
                     xmlns:w16cid="http://schemas.microsoft.com/office/word/2016/wordml/cid"
                     xmlns:w16="http://schemas.microsoft.com/office/word/2018/wordml"
                     xmlns:w16se="http://schemas.microsoft.com/office/word/2015/wordml/symex"
                     mc:Ignorable="w14 w15 w16se w16cid w16 w16cex">
                <w:font w:name="Calibri">
                    <w:panose1 w:val="020F0502020204030204"/>
                    <w:charset w:val="00"/>
                    <w:family w:val="swiss"/>
                    <w:pitch w:val="variable"/>
                    <w:sig w:usb0="E0002AFF" w:usb1="C000247B" w:usb2="00000009" w:usb3="00000000" w:csb0="000001FF"
                           w:csb1="00000000"/>
                </w:font>
                <w:font w:name="宋体">
                    <w:altName w:val="SimSun"/>
                    <w:panose1 w:val="02010600030101010101"/>
                    <w:charset w:val="86"/>
                    <w:family w:val="auto"/>
                    <w:pitch w:val="variable"/>
                    <w:sig w:usb0="00000003" w:usb1="288F0000" w:usb2="00000016" w:usb3="00000000" w:csb0="00040001"
                           w:csb1="00000000"/>
                </w:font>
                <w:font w:name="Times New Roman">
                    <w:panose1 w:val="02020603050405020304"/>
                    <w:charset w:val="00"/>
                    <w:family w:val="roman"/>
                    <w:pitch w:val="variable"/>
                    <w:sig w:usb0="E0002EFF" w:usb1="C000785B" w:usb2="00000009" w:usb3="00000000" w:csb0="000001FF"
                           w:csb1="00000000"/>
                </w:font>
                <w:font w:name="Calibri Light">
                    <w:panose1 w:val="020F0302020204030204"/>
                    <w:charset w:val="00"/>
                    <w:family w:val="swiss"/>
                    <w:pitch w:val="variable"/>
                    <w:sig w:usb0="E0002AFF" w:usb1="C000247B" w:usb2="00000009" w:usb3="00000000" w:csb0="000001FF"
                           w:csb1="00000000"/>
                </w:font>
                <w:font w:name="HELVETICA NEUE MEDIUM">
                    <w:altName w:val="Helvetica Neue Medium"/>
                    <w:panose1 w:val="020B0604020202020204"/>
                    <w:charset w:val="00"/>
                    <w:family w:val="swiss"/>
                    <w:pitch w:val="variable"/>
                    <w:sig w:usb0="A00002FF" w:usb1="5000205B" w:usb2="00000002" w:usb3="00000000" w:csb0="0000009B"
                           w:csb1="00000000"/>
                </w:font>
                <w:font w:name="Source Han Sans CN">
                    <w:altName w:val="Cambria"/>
                    <w:panose1 w:val="020B0604020202020204"/>
                    <w:charset w:val="00"/>
                    <w:family w:val="roman"/>
                    <w:notTrueType/>
                    <w:pitch w:val="default"/>
                </w:font>
                <w:font w:name="HELVETICA NEUE THIN">
                    <w:altName w:val="Helvetica Neue Thin"/>
                    <w:panose1 w:val="020B0403020202020204"/>
                    <w:charset w:val="00"/>
                    <w:family w:val="swiss"/>
                    <w:pitch w:val="variable"/>
                    <w:sig w:usb0="E00002EF" w:usb1="5000205B" w:usb2="00000002" w:usb3="00000000" w:csb0="0000009F"
                           w:csb1="00000000"/>
                </w:font>
                <w:font w:name="HELVETICA NEUE LIGHT">
                    <w:altName w:val="Helvetica Neue Light"/>
                    <w:panose1 w:val="02000403000000020004"/>
                    <w:charset w:val="00"/>
                    <w:family w:val="auto"/>
                    <w:pitch w:val="variable"/>
                    <w:sig w:usb0="A00002FF" w:usb1="5000205B" w:usb2="00000002" w:usb3="00000000" w:csb0="00000007"
                           w:csb1="00000000"/>
                </w:font>
                <w:font w:name="微软雅黑">
                    <w:panose1 w:val="020B0503020204020204"/>
                    <w:charset w:val="86"/>
                    <w:family w:val="swiss"/>
                    <w:pitch w:val="variable"/>
                    <w:sig w:usb0="80000287" w:usb1="28CF3C52" w:usb2="00000016" w:usb3="00000000" w:csb0="0004001F"
                           w:csb1="00000000"/>
                </w:font>
                <w:font w:name="Arial">
                    <w:panose1 w:val="020B0604020202020204"/>
                    <w:charset w:val="00"/>
                    <w:family w:val="swiss"/>
                    <w:pitch w:val="variable"/>
                    <w:sig w:usb0="E0002AFF" w:usb1="C0007843" w:usb2="00000009" w:usb3="00000000" w:csb0="000001FF"
                           w:csb1="00000000"/>
                </w:font>
                <w:font w:name="Arial Unicode MS">
                    <w:panose1 w:val="020B0604020202020204"/>
                    <w:charset w:val="80"/>
                    <w:family w:val="swiss"/>
                    <w:pitch w:val="variable"/>
                    <w:sig w:usb0="F7FFAFFF" w:usb1="E9DFFFFF" w:usb2="0000003F" w:usb3="00000000" w:csb0="003F01FF"
                           w:csb1="00000000"/>
                </w:font>
            </w:fonts>
        </pkg:xmlData>
    </pkg:part>
</pkg:package>
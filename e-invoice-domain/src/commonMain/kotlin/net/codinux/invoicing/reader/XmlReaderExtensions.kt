package net.codinux.invoicing.reader

/**
 * XmlReader crashes if XML e.g. starts with a non-breaking space. So remove everything before first '<'
 */
fun fixXmlForReading(xml: String): String =
    xml.indexOf('<').let { index ->
        if (index < 1) xml
        else xml.substring(index)
    }
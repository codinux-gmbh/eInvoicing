package net.codinux.invoicing.reader

import nl.adaptivity.xmlutil.EventType
import nl.adaptivity.xmlutil.XmlReader

/**
 * XmlReader crashes if XML e.g. starts with a non-breaking space. So remove everything before first '<'
 */
fun fixXmlForReading(xml: String): String =
    xml.indexOf('<').let { index ->
        if (index < 1) xml
        else xml.substring(index)
    }

fun XmlReader.readToNextStartTag(): Boolean =
    readToNext(EventType.START_ELEMENT)

fun XmlReader.readToNextEndTag(): Boolean =
    readToNext(EventType.END_ELEMENT)

fun XmlReader.readToNextText(): Boolean =
    readToNext(EventType.TEXT)

fun XmlReader.readToNext(type: EventType): Boolean {
    while (hasNext()) { // .nextTag() throws an exception on TEXT events
        val event = this.next()
        if (event == type) {
            return true
        }
    }

    return false
}
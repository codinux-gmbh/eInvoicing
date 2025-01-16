package net.codinux.invoicing.extension

import org.w3c.dom.Element
import org.w3c.dom.NamedNodeMap
import org.w3c.dom.Node
import org.w3c.dom.NodeList


val Node.childNodesList: List<Node>
    get() = this.childNodes.toList()

fun NodeList.toList() =
    (0 until this.length).map { item(it) }

fun NamedNodeMap.toMap(): Map<String, String> =
    (0 until this.length).map { this.item(it) }.associate { it.nodeName to it.nodeValue }

fun Node.getNamespaceDeclarations(): Map<String, String> =
    this.attributes.toMap()
        .filter { it.key.startsWith("xmlns:") }
        .mapKeys { it.key.substringAfter("xmlns:") }

val Element.childElements: List<Element>
    get() = this.childNodesList.filterIsInstance<Element>()

fun Element.getElementOrNull(tagName: String): Element? =
    this.childElements.firstOrNull { it.tagName == tagName }
package net.codinux.invoicing.pdf

import net.codinux.invoicing.extension.readAllBytesAndClose
import java.io.InputStream

fun PdfAttachmentReader.getFileAttachments(pdfInputStream: InputStream) =
    this.getFileAttachments(pdfInputStream.readAllBytesAndClose())
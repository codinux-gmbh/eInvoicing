import {
  PDFArray,
  PDFDict,
  PDFDocument,
  PDFHexString,
  PDFName,
  PDFNumber, PDFRawStream,
  PDFStream,
  PDFString
} from "pdf-lib"

import { PdfEmbeddedFile } from "./PdfEmbeddedFile"

// Kotlin/JS fails to call PdfReader constructor, so i added this creator method
export function createPdfReader(): PdfReader {
  return new PdfReader()
}


export class PdfReader {

  constructor() {

  }

  async extractEmbeddedFiles(pdfBytes: ArrayBuffer | Uint8Array | string): Promise<PdfEmbeddedFile[]> {
    const foundEmbeddedFiles: PdfEmbeddedFile[] = []

    try {
      const document = await this.loadDocument(pdfBytes)
      const catalog = document.catalog

      // the default is storing embedded files in the AssociatedFiles array at document level
      const associatedFiles = catalog.lookupMaybe(PDFName.of("AF"), PDFArray)
      if (associatedFiles) {
        this.readEmbeddedFilesArray(associatedFiles, foundEmbeddedFiles)
      } else { // additionally and sometimes only (which is not PDF/A-3 conformant) they are stored in Names -> EmbeddedFiles -> Names dictionary entries
        const names = catalog.lookupMaybe(PDFName.of("Names"), PDFDict)

        if (names) {
          const embeddedFiles = names.lookup(PDFName.of("EmbeddedFiles"), PDFDict)

          if (embeddedFiles) {
            this.readEmbeddedFilesDict(embeddedFiles, foundEmbeddedFiles)
          }
        }
      }
    } catch (e) {
      console.error("Could not read PDF", e)
    }

    return foundEmbeddedFiles
  }

  readEmbeddedFilesDict(embeddedFilesDict: PDFDict, foundEmbeddedFiles: PdfEmbeddedFile[]) {
    const embeddedFilesNames = embeddedFilesDict.lookupMaybe(PDFName.of("Names"), PDFArray)

    if (embeddedFilesNames) {
      this.readEmbeddedFilesArray(embeddedFilesNames, foundEmbeddedFiles)
    }

    // don't know how often this is the case, but Factur-X specification says sometimes embedded files are not directly in
    // EmbeddedFiles -> Names dict but indirectly in EmbeddedFiles -> Kids -> Names
    const kids = embeddedFilesDict.lookupMaybe(PDFName.of("Kids"), PDFArray)
    if (kids) {
      for (let i = 0; i < kids.size(); i++) {
        const kid = kids.lookup(i)
        if (kid instanceof PDFDict) {
          this.readEmbeddedFilesDict(kid, foundEmbeddedFiles)
        }
      }
    }
  }

  readEmbeddedFilesArray(embeddedFilesNames: PDFArray, foundEmbeddedFiles: PdfEmbeddedFile[]) {
    for (let i = 0; i < embeddedFilesNames.size(); i++) {
      const entry = embeddedFilesNames.lookup(i)

      if (entry instanceof PDFDict) {
        try {
          this.collectAllEmbeddedFiles(entry, foundEmbeddedFiles)
        } catch (e) {
          console.error("Could not read embedded file from FileSpecification (PDFDict)", entry, e)
        }
      }
    }
  }

  collectAllEmbeddedFiles(fileSpecification: PDFDict, foundEmbeddedFiles: PdfEmbeddedFile[]) {
    const embeddedFile = fileSpecification.lookupMaybe(PDFName.of("EF"), PDFDict)

    if (embeddedFile) {
      const embeddedFileStream = embeddedFile.lookupMaybe(PDFName.of("F"), PDFStream)
        ?? embeddedFile.lookupMaybe(PDFName.of("UF"), PDFStream)

      if (embeddedFileStream) {
        const fileContent = this.readBytesFromStream(embeddedFileStream)

        const length = (embeddedFileStream.dict.lookupMaybe(PDFName.of("Length"), PDFNumber) // 'Length' is correct, some use 'Size'
          ?? embeddedFileStream.dict.lookupMaybe(PDFName.of("Size"), PDFNumber))?.value()
        // @ts-ignore
        const mimeType = embeddedFileStream.dict.lookupMaybe(PDFName.of("Subtype"), PDFString, PDFName)?.decodeText()
        const params = embeddedFileStream.dict.lookupMaybe(PDFName.of("Params"), PDFDict)
        const creationDate = params?.lookupMaybe(PDFName.CreationDate, PDFString, PDFHexString)?.decodeDate()
        const modificationDate = params?.lookupMaybe(PDFName.ModDate, PDFString, PDFHexString)?.decodeDate()

        // @ts-ignore
        const filename = fileSpecification.lookupMaybe(PDFName.of("F"), PDFString, PDFName)?.decodeText()
        // @ts-ignore
        const unicodeFilename = fileSpecification.lookupMaybe(PDFName.of("UF"), PDFString, PDFName)?.decodeText()
        // @ts-ignore
        const description = fileSpecification.lookupMaybe(PDFName.of("Desc"), PDFString, PDFName)?.decodeText()
        // @ts-ignore
        const relationship = fileSpecification.lookupMaybe(PDFName.of("AFRelationship"), PDFString, PDFName)?.decodeText()

        foundEmbeddedFiles.push(new PdfEmbeddedFile(
          unicodeFilename ?? filename,
          fileContent,
          description,
          relationship,
          mimeType,
          length ?? null,
          creationDate ?? null,
          modificationDate ?? null,
        ))
      }
    }
  }

  readBytesFromStream(pdfStream: PDFStream): Uint8Array {
    if (pdfStream instanceof PDFRawStream) {
      return pdfStream.asUint8Array()
    } else {
      const fileContent = new Uint8Array(pdfStream.sizeInBytes())
      pdfStream.copyBytesInto(fileContent, 0)
      return fileContent
    }
  }


  async loadDocument(pdfBytes: ArrayBuffer | Uint8Array | string): Promise<PDFDocument> {
    return await PDFDocument.load(pdfBytes)
  }

}
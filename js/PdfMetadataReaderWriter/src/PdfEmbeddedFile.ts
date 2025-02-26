
export class PdfEmbeddedFile {
  constructor(readonly filename: string, readonly data: Uint8Array,
              readonly description: string | null = null, readonly relationship: string | null = null,
              readonly mimeType: string | null = null, readonly size: number | null = null,
              readonly creationDate: Date | null = null, readonly modificationDate: Date | null = null) {
  }
}
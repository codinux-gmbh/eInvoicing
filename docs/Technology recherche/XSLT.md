# XSLT

## JS/Browser

Modern browsers include [XSLTProcessor](https://developer.mozilla.org/en-US/docs/Web/API/XSLTProcessor).

But even though not stated in documentation, **it only supports XSLT 1.0**. 

Which makes it unusable for us as the Schematron / XSLT files provided by Factur-X project require XSLT 2.0:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<schema xmlns="http://purl.oclc.org/dsdl/schematron"
    queryBinding="xslt2"
    schemaVersion="iso">
```
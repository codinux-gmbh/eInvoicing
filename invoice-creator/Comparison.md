
## OpenHtmlToPdf

### Pro

:heavy_check_mark: Pure Java, no external dependency (does not required a browser to be installed)

:heavy_check_mark: Supports `position: running()`, header and footer can therefore be defined inline in HTML

### Con

:x: Supports only CSS 2.1

:x: Does not support Flexbox and Grid layout


## Playwright

### Pro

:heavy_check_mark: Supports CSS 3

:heavy_check_mark: Supports Flexbox and Grid layout

### Con

:x: Needs Chromium to be installed (heavyweight)

:x: Does not support `position: running()` e.g. for header and footer. Header and footer templates have to be specified explicitly.
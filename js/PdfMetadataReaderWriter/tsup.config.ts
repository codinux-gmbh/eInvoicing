import { defineConfig } from "tsup";

export default defineConfig({
  // entry: ["src/index.ts"],
  entry: ["src/PdfEmbeddedFile.ts", "src/PdfReader.ts"],
  format: ["esm", "cjs"],
  dts: true,
  sourcemap: true,
  clean: true
});

import { defineConfig } from "vite"

export default defineConfig({
  build: {
    minify: "esbuild",
    lib: {
      entry: "dist/index.js",
      formats: ["es"],
    },
    rollupOptions: {
      output: {
        dir: "dist",
      },
    },
  },
})

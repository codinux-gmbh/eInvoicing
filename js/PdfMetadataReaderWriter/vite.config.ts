import { defineConfig } from "vite"

export default defineConfig({
  build: {
    minify: "esbuild",
    lib: {
      entry: "dist/index.js",
      formats: ["es", "cjs"],
    },
    rollupOptions: {
      output: {
        dir: "dist",
      },
    },
  },
})

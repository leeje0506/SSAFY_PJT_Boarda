import { defineConfig } from 'vite'
import path from 'path'
import react from '@vitejs/plugin-react'
import path from 'path'

// https://vitejs.dev/config/
export default defineConfig({
  base: "./",
  plugins: [react()],
  assetsInclude: ["**/*.jpg","**/*.png","**/*.svg"],
  server: {
    port: 3000,
    host: "0.0.0.0",
  },
  resolve: {
    alias: [{ find: "@", replacement: path.resolve(__dirname, "src") }],
  },

})

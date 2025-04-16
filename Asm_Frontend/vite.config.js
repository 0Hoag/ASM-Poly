import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";

// https://vite.dev/config/
export default defineConfig({
  vue: {
    compilerOptions: {
      isCustomElement: (tag) => tag === 'marquee'
    }
  },
  plugins: [vue()],
  assetsInclude: ["**/*.PNG"],
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '/api')
      }
    }
  }
  
});

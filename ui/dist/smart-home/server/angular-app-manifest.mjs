
export default {
  bootstrap: () => import('./main.server.mjs').then(m => m.default),
  inlineCriticalCss: true,
  baseHref: '/',
  locale: undefined,
  routes: [
  {
    "renderMode": 2,
    "redirectTo": "/home",
    "route": "/"
  },
  {
    "renderMode": 2,
    "route": "/home"
  },
  {
    "renderMode": 2,
    "route": "/contact"
  },
  {
    "renderMode": 1,
    "route": "/find-us"
  },
  {
    "renderMode": 2,
    "route": "/**"
  }
],
  entryPointToBrowserMapping: undefined,
  assets: {
    'index.csr.html': {size: 790, hash: '12774e944edc6439550017363adbc788c531bf9b16ed13e31b13cbb3cddf61b9', text: () => import('./assets-chunks/index_csr_html.mjs').then(m => m.default)},
    'index.server.html': {size: 998, hash: '3c8ca0530e27305892768e00dd749f635b557d06178a38ef0d6cc2c82cd58796', text: () => import('./assets-chunks/index_server_html.mjs').then(m => m.default)},
    'contact/index.html': {size: 4568, hash: '7f9debc74c2009d7c73ed16e00f55816e5c5de1cb405c01fbf43714a8bd81556', text: () => import('./assets-chunks/contact_index_html.mjs').then(m => m.default)},
    'home/index.html': {size: 7097, hash: '37fe139bc547f50af54340a3bb583c4049799bfe04baa9f2d8ff4816eca90e99', text: () => import('./assets-chunks/home_index_html.mjs').then(m => m.default)},
    'styles-3LW4PVFG.css': {size: 15969, hash: '24CVC/L9NOY', text: () => import('./assets-chunks/styles-3LW4PVFG_css.mjs').then(m => m.default)}
  },
};

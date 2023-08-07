import { createApp } from 'vue'
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import App from './App.vue'
import router from './router'
import store from './store'
import * as Icons from '@ant-design/icons-vue'

const app = createApp(App);
app.use(Antd).use(store).use(router).mount('#app');

const icons = Icons;
for(const icon in icons) {
    app.component(icon, icons[icon]);
}
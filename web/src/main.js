import { createApp } from 'vue'
import Antd, {notification} from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import App from './App.vue'
import router from './router'
import store from './store'
import * as Icons from '@ant-design/icons-vue'
import axios   from 'axios'
const app = createApp(App);
app.use(Antd).use(store).use(router).mount('#app');

const icons = Icons;
for(const icon in icons) {
    app.component(icon, icons[icon]);
}

/**
 * axios拦截器
 */
axios.interceptors.request.use(function (config) {
    console.log('请求参数：', config);
    // const _token = store.state.member.token;
    // if (_token) {
    //     config.headers.token = _token;
    //     console.log("请求headers增加token:", _token);
    // }
    return config;
}, error => {
    return Promise.reject(error);
});
axios.interceptors.response.use(function (response) {
    console.log('返回结果：', response);
    return response;
}, error => {
    console.log('返回错误：', error);
    const response = error.response;
    const status = response.status;
    if (status === 401) {
        // 判断状态码是401 跳转到登录页
        console.log("未登录或登录超时，跳到登录页");
        store.commit("setMember", {});
        notification.error({ description: "未登录或登录超时" });
        router.push('/login');
    }
    return Promise.reject(error);
});
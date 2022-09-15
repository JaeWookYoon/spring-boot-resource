import { createApp } from 'vue'
import App from './App.vue'
import MyHeader from './headerfooter/myHeader/myHeader.vue';
import MyFooter from './headerfooter/myFooter/myFooter.vue';
import router from './router/router';

const header = createApp(MyHeader);
header.use(router);
header.mount('#headers');
const footer = createApp(MyFooter);
footer.mount('#footers');
const app = createApp(App);
app.use(router);
//app.use(VueAxios, axios)
app.mount('#app');

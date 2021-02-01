import Vue from 'vue'
import App from './App.vue'
import vueBem from '@/mixins/vue-bem.vue'

Vue.config.productionTip = true
Vue.mixin(vueBem);

new Vue({
  render: h => h(App),
}).$mount('#app')

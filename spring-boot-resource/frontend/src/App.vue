<template>
  <!--<img alt="Vue logo" src="./assets/logo.png">-->
  <MyBody msg="Welcome to Your Vue.js App"/>
</template>

<script>
import MyBody from './components/myBody/myBody'
export default {
  name: 'App',
  components: {
    MyBody
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>

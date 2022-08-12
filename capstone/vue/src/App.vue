
<template>
  <div id="app">
    <div id="nav">
      <router-link
        class="homeLink"
        v-if="this.$router.currentRoute.path != '/'"
        v-bind:to="{ name: 'home' }"
      ></router-link
      >&nbsp;&nbsp;
      <router-link
        class="logOut"
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >
    </div>
    <router-view />
  </div>
</template>

<style>
body {
  margin: 0px, 0px, 0px, 0px;
}
#nav {
  display: flex;
  justify-content: flex-end;
}
.logOut {
  font-size: 14pt;
  color: aliceblue;
}
</style>

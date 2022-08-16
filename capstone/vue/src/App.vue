
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
        ><img
          title="LOGOUT"
          src=".\assets\turn-off.png"
          alt="logout"
          class="logoutLogo"
      /></router-link>
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
  font-weight: 900;
}
.logoutLogo {
  max-width: auto;
  width: 30px;
  margin-top: 0.5rem;
  margin-right: 0.5rem;
}
</style>

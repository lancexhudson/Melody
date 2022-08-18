
<template>
  <div class="wholePage">
    <router-link v-bind:to="{ name: 'home' }" class="logoContainer">
      <img
        src="..\assets\LOGO TRANSPARENCY white.png"
        alt="Melody Logo"
        class="homelogo"
      />
    </router-link>
    <div class="homeNav">
      <div class="inboxContainer">
        <router-link class="inbox" :to="{ name: 'inbox' }">
          <img
            title="INBOX"
            src="..\assets\inbox.png"
            alt="mail"
            class="inboxLogo"
          />
        </router-link>
      </div>
      <div class="addBandContainer">
        <router-link class="addBand" :to="{ name: 'addBand' }"
          ><img
            title="Add a Band"
            src="..\assets\add-user.png"
            alt="addUser"
            class="addUser"
          />
        </router-link>
      </div>

      <!-- <div class="followListContainer">
        <router-link class="addBand" :to="{ name: 'addBand' }"
          ><img
            title="Add a Band"
            src="..\assets\love.png"
            alt="addUser"
            class="addUser"
          />
        </router-link>
      </div> -->
    </div>
    <band-list></band-list>

    <br />
    <footer>Connor, Patrick, Austin, Michael, Lance</footer>
  </div>
</template>

<script>
// import FavoriteBands from "@/components/FavoriteBands";
import BandList from "@/components/BandList.vue";
export default {
  beforeCreate: function () {
    document.body.className = "home";
  },
  name: "home",
  components: { BandList },
};
</script>

<style>
.homelogo {
  align-self: auto;

  max-width: 35%;
  margin: 0%;

  flex-shrink: 1;
}
body.home {
  display: flex;
  text-align: center;
  flex-direction: column;
  justify-content: flex-center;

  align-content: center;
  background: black;
  color: white;
  background-image: url("~@/assets/crowd.png");
  background-position: bottom;
  background-size: cover;
  background-attachment: fixed;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  height: 100vh;
}

.addBand {
  color: rgb(250, 250, 250);
  font-size: 14pt;
  font-weight: 900;
}
.inboxLogo {
  max-width: auto;
  width: 52px;
  margin: 2em;
}
.addUser {
  max-width: auto;
  width: 48px;
  margin: 2em;
}

.followListContainer {
  max-width: auto;
  width: 50px;
  margin: 2em;
}
.homeNav {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
}

footer {
  font-weight: 50;
}
</style>
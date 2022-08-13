<template>
  <div>
    favorite-bands
    <div class="melodyLogo">
      <router-link v-bind:to="{ name: 'home' }">
        <img
          src="..\assets\LOGO TRANSPARENCY white.png"
          alt="Melody Logo"
          class="homelogo"
        />
      </router-link>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
</style>
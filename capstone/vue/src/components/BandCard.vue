<template>
  <div class="card">
    <h2 class="band-name">{{ band.bandName }}</h2>
    <img class="cover" :src="band.imageLink" alt="Dave" />
  </div>
</template>

<script>
export default {
  name: "band-card",
  props: ["band"],
};
</script>

<style>
.card {
  border: solid rgb(0, 234, 255) 2px;
  border-radius: 2rem;
  background-color: rgb(195, 231, 223);
  padding: 4px;
}
.cover {
  border-radius: 2rem;
}
</style>
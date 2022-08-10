<template>
  <form class="new-band-form" v-on:submit.prevent="saveBand">
    <input
      class="band-name-input"
      type="text"
      placeholder="Band Name"
      v-model="newBand.bandName"
    />
    <input
      class="band-description-input"
      type="text"
      placeholder="Your Band's Description"
      v-model="newBand.description"
    />
    <select name="genre-selector" id="genre-select" v-model="newBand.genre">
      <option value="Pop">Pop</option>
      <option value="Rock">Rock</option>
      <option value="HipHop">HipHop</option>
      <option value="Classical">Classical</option>
      <option value="RnB">{{ "R & B" }}</option>
      <option value="Electronic">Electronic</option>
      <option value="Country">Country</option>
      <option value="Blues">Blues</option>
      <option value="Jazz">Jazz</option>
      <option value="Metal">Metal</option>
      <option value="Reggae">Reggae</option>
      <option value="Folk">Folk</option>
      <option value="Latin">Latin</option>
      <option value="A capella">A capella</option>
      <option value="Funk">Funk</option>
      <option value="Worship">Worship</option>
      <option value="Instrumental">Instrumental</option>
      <option value="Punk">Punk</option>
      <option value="Singer/Songwriter">Singer/Songwriter</option>
      <option value="World">World</option>
    </select>
    <input
      class="image-link"
      type="text"
      placeholder="Image Link"
      v-model="newBand.imageLink"
    />
    <button class="button">Save</button>
  </form>
</template>

<script>
import bandService from "@/services/BandService.js";
export default {
  name: "band-form",
  data() {
    return {
      newBand: {
        bandName: "",
        description: "",
        genre: "",
        imageLink: "",
      },
    };
  },
  methods: {
    saveBand() {
      bandService.createBand(this.newBand).then((response) => {
        if (response.status == 201) {
          this.$router.push({ name: "home" });
        }
      });
      this.newBand = {
        bandName: "",
        description: "",
        genre: "",
        imageLink: "",
      };
    },
  },
};
</script>

<style>
/* .button {
  font-size: 200px;
} */
</style>
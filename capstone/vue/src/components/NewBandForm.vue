<template>
  <form class="new-band-form" v-on:submit.prevent="saveBand">
    <ul class="band-creation">
      <li>
        <input
          class="band-name-input"
          type="text"
          placeholder="Band Name"
          v-model="newBand.bandName"
        />
      </li>
      <li>
        <textarea
          class="band-description-input"
          type="textarea"
          placeholder="Your Band's Description"
          v-model="newBand.description"
        >
        </textarea>
      </li>
      <li>
        <select
          name="genre-selector"
          id="genre-select"
          v-model="newBand.genre"
          multiple
        >
          <option value="" disabled selected>Select Genre</option>
          <option
            v-for="genre in this.$store.state.genres"
            v-bind:key="genre.genreId"
            :value="genre.genreId"
          >
            {{ genre.genreName }}
          </option>
        </select>
      </li>
      <li>
        <textarea
          class="image-link"
          placeholder="Image Link"
          v-model="newBand.imageLink"
        >
        </textarea>
      </li>
      <button class="button">Save Band</button>
    </ul>
  </form>
</template>

<script>
import bandService from "@/services/BandService.js";
import genreService from "@/services/GenreService.js";

export default {
  name: "band-form",
  data() {
    return {
      newBand: {
        bandName: "",
        description: "",
        genre: [],
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
        genre: [],
        imageLink: "",
      };
    },
    listGenres() {
      genreService.listGenres().then((response) => {
        this.$store.commit("SET_GENRES", response.data);
      });
    },
  },
  created() {
    this.listGenres();
  },
};
</script>

<style>
/* .button {
  font-size: 200px;
} */
.band-creation,
.band-name-input,
option,
button {
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
}
</style>
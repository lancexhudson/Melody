<template>
  <form class="update-band-form" v-on:submit.prevent="updateBand">
    <ul class="band-updating">
      <li class="form-row">
        <input
          class="band-name-update"
          type="text"
          placeholder="  Band Name"
          v-model="newBand.bandName"
          required
        />
      </li>
      <li class="form-row">
        <textarea
          class="band-description-input"
          type="textarea"
          placeholder=" Your Band's Description"
          v-model="newBand.description"
          required
        >
        </textarea>
      </li>
      <li class="form-row">
        <select
          class="genre-selector-update"
          name="genre-selector"
          id="genre-select"
          v-model="newBand.genre"
          required
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
      <li class="form-row">
        <textarea
          class="image-link-update"
          placeholder=" Image Link"
          v-model="newBand.imageLink"
          required
        >
        </textarea>
      </li>

      <li class="form-row">
        <button class="updateBandButton">Update Band</button>
      </li>
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
    updateBand() {
      bandService.updateBand(this.newBand).then((response) => {
        if (response.status == 200) {
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
.band-updating,
.band-name-update,
option,
button {
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
}
.form {
  justify-content: center;
  align-items: center;
}
.band-updating {
  display: flex;
  flex-direction: column;
  padding: 0;
  list-style: none;
}

.band-name-update {
  width: 330px;
  padding: 0;
  margin: 0;
  border: 0;
}
.band-description-input {
  height: 50px;
  width: 330px;
  padding: 0;
  border: 3px;
  margin: 0;
}
.genre-selector-update {
  width: 330px;
  padding: 0;
  border: 3px;
  margin: 0;
}
.image-link-update {
  width: 330px;
  padding: 0;
  border: 3px;
  margin: 0;
}
.updateBandButton {
  width: 330px;
  padding: 0;
  border: 3px;
  margin: 0;
}
</style>
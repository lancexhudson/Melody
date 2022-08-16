<template>
  <div class="add-your-band-form">
    <h4 class="addYourBand">ADD YOUR BAND</h4>
    <form class="new-band-form" v-on:submit.prevent="saveBand">
      <ul class="band-creation">
        <li class="form-row">
          <input
            class="band-name-input"
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
            class="genre-selector"
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
            class="image-link"
            placeholder=" Image Link"
            v-model="newBand.imageLink"
            required
          >
          </textarea>
        </li>

        <li class="form-row">
          <button class="saveBandButton">Save Band</button>
        </li>
      </ul>
    </form>
  </div>
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
.add-your-band-form {
  display: inline-flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  border: solid rgb(255, 255, 255) 1px;
  background: rgba(0, 0, 0, 0.8);
  border-radius: 0.5rem;
  width: 700px;
  margin-top: 150px;
  margin-bottom: 230px;
  max-width: 700px;
}

.new-band-form {
  max-height: 360px;
}

.band-creation,
.band-name-input,
option,
button {
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
}
.form {
  justify-content: center;
  align-items: center;
  padding-top: 80px;
}
.band-creation {
  display: flex;
  flex-direction: column;
  margin-bottom: 300px;
  margin-right: 20px;
  list-style: none;
}

.band-name-input {
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
.genre-selector {
  width: 330px;
  padding: 0;
  border: 3px;
  margin: 0;
}
.image-link {
  width: 330px;
  padding: 0;
  border: 3px;
  margin: 0;
}
.saveBandButton {
  width: auto;
  min-width: 300px;
  padding: 0;
  border: 3px;
  margin: 0;
}
.addYourBand {
  font-size: 2rem;
  margin-bottom: 10px;
}
</style>
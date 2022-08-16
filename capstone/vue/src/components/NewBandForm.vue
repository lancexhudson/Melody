<template>
  <div class="add-your-band-form">
    <h4 class="addYourBand">Add Your Band</h4>
    <form class="new-band-form" v-on:submit.prevent="saveBand">
      <ul class="band-creation">
        <li class="form-row-name">
          <input
            class="band-name-input"
            type="text"
            placeholder="  Band Name"
            v-model="newBand.bandName"
            required
          />
        </li>
        <li class="form-row-description">
          <textarea
            class="band-description-input"
            type="textarea"
            placeholder=" Your Band's Description"
            v-model="newBand.description"
            required
          >
          </textarea>
        </li>
        <li class="form-row-genre">
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
        <li class="form-row-image">
          <textarea
            class="image-link"
            placeholder=" Image Link"
            v-model="newBand.imageLink"
            required
          >
          </textarea>
        </li>

        <li class="form-row-save">
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
  width: 100%;
  margin-bottom: 20px;
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
}
.band-creation {
  display: flex;
  flex-direction: column;
  margin-bottom: 300px;
  margin-right: 40px;
  list-style: none;
}

.band-name-input {
  width: 330px;
  height: 30px;
  padding: 0;
  margin: 0;
  border-radius: 0.2rem;
}

.band-description-input {
  height: 40px;
  width: 330px;
  padding: 0;
  border: 3px;
  margin: 0px;
  border-radius: 0.2rem;
}
.genre-selector {
  width: 330px;
  padding: 0;
  border: 3px;
  margin: 0;
  border-radius: 0.2rem;
}

.image-link {
  width: 330px;
  padding: 0;
  border: 3px;
  margin: 0;
  border-radius: 0.2rem;
}
.saveBandButton {
  width: auto;
  min-width: 300px;
  padding: 0;
  border: 3px;
  margin: 0;
  border-radius: 0.2rem;
}
.addYourBand {
  font-size: 2rem;
  margin-bottom: 10px;
}

.form-row {
  border: white solid;
  border-radius: 1rem;
  background-color: 255, 255, 255, 0.8;

  backdrop-filter: blur(10px);
  margin: 1rem;
  margin-right: auto;
  max-width: 50rem;
  margin-left: 9px;
  border-radius: 0.5rem;
}
</style>
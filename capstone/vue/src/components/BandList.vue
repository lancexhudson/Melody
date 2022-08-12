<template>
  <div>
    <div class="searchBands">
      <h1 v-if="isFilteredEmpty">NO MATCHING RESULTS</h1>
      <table id="tableBands">
        <thead>
          <tr>
            <th>Band Name</th>
          </tr>
        </thead>
        <td>
          <input type="text" id="bandNameFilter" v-model="filter.bandName" />
        </td>
        <tr>
          <th>Genre</th>
        </tr>
        <td>
          <input type="text" id="genreFilter" v-model="filter.genreFilter" />
        </td>
      </table>
    </div>
    <div class="band-container">
      <p v-if="isEmpty">NO MATCHING RESULTS</p>
      <band-card
        v-bind:band="band"
        v-for="band in filteredBandList"
        v-bind:key="band.id"
        v-bind:filteredBandList="testArray"
      >
      </band-card>
    </div>
    <p>
      ______________________________________________________________________________________
    </p>
    <div>
      <band-card
        v-for="favorite in this.$store.state.myBands"
        v-bind:key="favorite.bandId"
      ></band-card>
    </div>
  </div>
</template>

<script>
import bandCard from "@/components/BandCard.vue";
import bandService from "@/services/BandService.js";
export default {
  name: "band-list",
  components: {
    bandCard,
  },
  data() {
    return {
      filter: {
        bandName: "",
        genreFilter: "",
      },
      isEmpty: this.isFilteredEmpty,
      testArray: [],
    };
  },
  computed: {
    filteredBandList() {
      let filteredBands = this.$store.state.bands;
      if (this.filter.bandName != "") {
        filteredBands = filteredBands.filter((band) =>
          band.bandName
            .toLowerCase()
            .includes(this.filter.bandName.toLowerCase())
        );
      }
      if (this.filter.genreFilter != "") {
        filteredBands = filteredBands.filter((band) =>
          band.genre
            .toLowerCase()
            .includes(this.filter.genreFilter.toLowerCase())
        );
      }
      return filteredBands;
    },
    isFilteredEmpty() {
      if (this.filteredBandList.length == 0) {
        return true;
      } else {
        return false;
      }
    },
  },
  methods: {
    listBands() {
      bandService.listBands().then((response) => {
        this.$store.commit("SET_BANDS", response.data);
      });
    },
  },
  created() {
    this.listBands();
  },
};
</script>

<style>
.band-container {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
}
#tableBands {
  align-self: flex-end;
}
#tableBands {
  display: flex;
  align-items: flex-start;

  flex-direction: column;
}
</style>
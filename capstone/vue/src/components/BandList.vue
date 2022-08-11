<template>
  <div class="band-container">
    <table id="tableBands">
      <thead>
        <tr>
          <th>Band Name</th>
          <!-- <th>Genre</th> -->
        </tr>
      </thead>
      <td>
        <input type="text" id="bandNameFilter" v-model="filter.bandName" />
      </td>
    </table>
    <band-card
      v-bind:band="band"
      v-for="band in filteredBandList"
      v-bind:key="band.id"
    >
    </band-card>
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
        // genreFilter: "",
      },
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
      return filteredBands;
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
</style>
<template>
  <b-table-simple caption-top>
    <caption>
      {{caption}}
    </caption>
    <b-thead class="thead-dark">
      <b-tr>
        <b-th v-for="header in headers" :key="header">{{header}}</b-th>
      </b-tr>
    </b-thead>

    <b-tbody>
      <b-tr v-for="item in items" :key="item">
        <b-td v-for="key in item" :key="key" sticky-column>{{key}}</b-td>
      </b-tr>
    </b-tbody>

  </b-table-simple>
</template>

<script>
import {BTableSimple, BTbody, BTh, BThead, BTd, BTr} from "bootstrap-vue-3";
export default {
  name: "table-component",
  components: {BTbody, BTh, BThead, BTableSimple, BTd, BTr},
  props: ['headers', 'items', 'caption']
}
</script>

<style scoped>
table, th, td {
  border: 0.1px solid black;
}
</style>
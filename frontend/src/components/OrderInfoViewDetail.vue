<template>

    <v-card outlined>
        <v-card-title>
            OrderInfo # {{$route.params.id }}
        </v-card-title>

        <v-card-text>
            <div>
                <String label="OrderStatus" v-model="item.orderStatus" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'OrderInfoViewDetail',
    props: {
      value: Object,
    },
    data: () => ({
        item : [],
    }),
    async created() {
      var params = this.$route.params;
      var temp = await axios.get(axios.fixUrl('/orderInfos/' + params.id))

      this.item = temp.data;

    },
    methods: {
    }
  }
</script>


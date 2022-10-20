<template>

    <v-data-table
        :headers="headers"
        :items="orderInfo"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'OrderInfoView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
                { text: "orderStatus", value: "orderStatus" },
            ],
            orderInfo : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/orderInfos'))

            temp.data._embedded.orderInfos.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.orderInfo = temp.data._embedded.orderInfos;
        },
        methods: {
        }
    }
</script>


<template>

    <v-data-table
        :headers="headers"
        :items="orderConfirm"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'OrderConfirmView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            orderConfirm : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/orderConfirms'))

            temp.data._embedded.orderConfirms.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.orderConfirm = temp.data._embedded.orderConfirms;
        },
        methods: {
        }
    }
</script>


<template>

    <v-data-table
        :headers="headers"
        :items="ui"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'UiView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            ui : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/uis'))

            temp.data._embedded.uis.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.ui = temp.data._embedded.uis;
        },
        methods: {
        }
    }
</script>


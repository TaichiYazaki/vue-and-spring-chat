<template>
  <v-app id="inspire">
    <sidebar />
    <v-app-bar app shrink-on-scroll>
      <v-toolbar-title>ルーム一覧</v-toolbar-title>
    </v-app-bar>

    <v-main>
      <v-container>
        <v-row>
          <v-col v-for="room in rooms" :key="room.id">
            <router-link :to="{ path: '/chatBoard', query: { room_id: room.id } }">
            <v-avatar color="primary" size="70">
              <img :src="require('@/assets/img/roomIcon/' + room.file)" />
            </v-avatar>
            </router-link>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import axios from "axios";
import sidebar from "@/components/layouts/SideBar";

export default {
  components: {
    sidebar,
  },
  mounted() {
    axios
      .get("/showRooms")
      .then((response) => {
        const rooms = response.data;
        for (const room of rooms) {
          this.rooms.push(room);
        }
      })
      .catch((e) => {
        console.log(e);
      });
  },
  data: () => ({
    id: sessionStorage.getItem("id"),
    email: sessionStorage.getItem("email"),
    rooms: [],
  }),
};
</script>
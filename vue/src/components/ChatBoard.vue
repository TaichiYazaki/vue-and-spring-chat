<template>
  <v-app id="inspire">
    <v-main>
      <v-container class="py-8 px-6" fluid>
        <v-row>
          <v-col cols="12">
            <v-card>
              <v-list two-line>
                <template v-for="n in 6">
                  <v-list-item :key="n">
                    <v-list-item-avatar color="grey darken-1">
                    </v-list-item-avatar>

                    <v-list-item-content>
                      <v-list-item-subtitle>
                        Lorem ipsum dolor sit amet, consectetur adipisicing
                        elit. Nihil repellendus distinctio similique
                      </v-list-item-subtitle>
                    </v-list-item-content>
                  </v-list-item>
                  <v-divider
                    v-if="n !== 6"
                    :key="`divider-${n}`"
                    inset
                  ></v-divider>
                </template>
              </v-list>
            </v-card>
          </v-col>
        </v-row>
        <v-textarea
          v-model="body"
          append-icon="mdi-comment"
          class="mx-2"
          label="メッセージを送信する"
          rows="3"
          auto-grow
        ></v-textarea>
        <v-btn
          color="success"
          @click="submit"
          class="mr-4"
          type="submit"
          :disabled="invalid"
        >
          submit
        </v-btn>
        <v-btn @click="clear"> clear </v-btn>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import axios from "axios";
export default {
  created() {
    //ユーザーアイコンの取得
    axios.get("/findById", {
      params: {
        id: this.userId,
      },
    }).then((response) => {
        // 1個目の通信 成功
        const data = response.data;
        const name = data.imgFile;
        this.$set(this.imgName, "name", name);
      })
      .catch((e) => {
        // エラーが発生
        console.log(e);
      });
    //ルームIDの取得
    this.roomId = this.$route.query.room_id;
    //ユーザーアイコンの取得
  },
  methods: {
    submit(){
      const data = new FormData()
      data.append("message", this.body)
      data.append("userId", this.userId)
      data.append("roomId", this.roomId)
      axios.post("/submit",data)
    }
  },
  data: () => ({
    roomId: "",
    userId: sessionStorage.getItem("id"),
    drawer: null,
    imgName: {
      name: "",
    },
    body:""
  }),
};
</script>
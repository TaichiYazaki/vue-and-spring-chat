<template>
  <v-dialog v-model="dialog" persistent max-width="600px">
    <template v-slot:activator="{ on, attrs }">
      <v-text v-bind="attrs" v-on="on" display="none"> Create Room </v-text>
    </template>
    <v-card>
      <v-card-title class="text-h5 grey lighten-2"> ルーム作成 </v-card-title>

      <v-card-text>
        <v-container>
          <v-row>
            <v-col cols="12">
              <v-text-field
                v-model="name"
                label="ルーム名*"
                required
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-file-input
                v-model="file"
                accept="image/jpeg, image/jpg, image/png"
                label="ファイル選択"
              ></v-file-input>
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <div class="roomsBtn">
          <v-btn color="success" class="roomBtn"> 作成 </v-btn>
          <v-btn color="error" class="roomBtn" @click="clear"> クリア </v-btn>
          <v-btn color="primary" text @click="dialog = false"> 戻る </v-btn>
        </div>
      </v-card-actions>
    </v-card>
 
</template>
    
<script>
export default {
  data: () => ({
    name: "",
    file: "",
    dialog: false,
  }),
  methods: {
    clear(){
        this.name = "",
        this.file = ""
    }
  }
};
</script>

<style>
.roomsBtn {
  padding-right: 150px;
}
.roomBtn {
  margin-right: 20px;
}
</style>
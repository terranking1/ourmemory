<script lang="ts" setup>
import {defineEmits, ref} from 'vue'
import store from "@/store";
import {editGroupAPI} from "@/api";

const updateGroupRequest = ref({
    name: ""
})

const emit = defineEmits(['closeModal']);

const closeModal = function () {
    emit('closeModal')
}

const updateGroup = async function () {
    try {
        if (updateGroupRequest.value.name) {

            await editGroupAPI(store.state.groupData.id, updateGroupRequest.value);
            store.commit('setGroupName', updateGroupRequest.value.name)
            closeModal()
        }
    } catch (error) {
        console.log(error)
    }

}

</script>

<template>
    <div class="group-key-modal">
        <div class="group-key-modal-content">
            <div class="group-key-modal-content-header">
                <span>그룹명 변경</span>
            </div>
            <div class="group-key-modal-content-body">
                <input type="text" v-model="updateGroupRequest.name" placeholder="그룹명">

            </div>
            <div class="group-key-modal-content-footer">
                <button class="btn btn-secondary me-3" @click="updateGroup">변경</button>
                <button class="btn btn-secondary" @click="closeModal">닫기</button>
            </div>
        </div>
    </div>
</template>

<style scoped>
span {
    font-size: 20px;
}
.group-key-modal {
    width: 300px; height: 200px;
    border: 1px solid darkgray;
    border-radius: 8px;
    z-index: 999;
    position: absolute;
    top: 10px;
    background: white;
}
.group-key-modal-content {
    width: 100%; height: 100%;
    display: flex;
    flex-direction: column;

}
.group-key-modal-content-header {
    flex: 1;
    display: flex;
    justify-content: center;
    align-items: center;
    border-top-left-radius: 8px;
    border-top-right-radius: 8px;
}
.group-key-modal-content-body {
    flex: 2;
    border-top: 1px solid darkgray;
    border-bottom: 1px solid darkgray;
    display: flex;
    justify-content: center;
    align-items: center;
}
.group-key-modal-content-footer {
    flex: 1;
    display: flex;
    justify-content: center;
    align-items: center;
    border-bottom-left-radius: 8px;
    border-bottom-right-radius: 8px;
}

@media screen and (max-width: 768px) {
    span {
        font-size: 15px;
    }
    .group-key-modal {
        width: 200px; height: 150px;
        border: 1px solid darkgray;
        border-radius: 8px;
        z-index: 999;
        position: absolute;
        top: 10px;
        background: white;
    }
    .group-key-modal-content {
        width: 100%; height: 100%;
        display: flex;
        flex-direction: column;

    }
    .group-key-modal-content-header {
        flex: 1;
        display: flex;
        justify-content: center;
        align-items: center;
        border-top-left-radius: 8px;
        border-top-right-radius: 8px;
    }
    .group-key-modal-content-body {
        flex: 2;
        border-top: 1px solid darkgray;
        border-bottom: 1px solid darkgray;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .group-key-modal-content-footer {
        flex: 1;
        display: flex;
        justify-content: center;
        align-items: center;
        border-bottom-left-radius: 8px;
        border-bottom-right-radius: 8px;
    }
    button {
        font-size: 11px;
        padding: 5px 10px 5px 10px;
    }
    input {
        font-size: 12px;
    }
}
</style>

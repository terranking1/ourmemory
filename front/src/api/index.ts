import axios from "axios";
import {setInterceptors} from "@/api/common/interceptors";
import store from "@/store";

const createAuthInstance = function () {
    const instance = axios.create({
        baseURL: "http://localhost:3000/",
    })
    return setInterceptors(instance)
}

const instance = axios.create({
    baseURL: "http://localhost:3000"
})
const authInstance = createAuthInstance()

const loginAPI = function (loginRequest : any) {
    return instance.post("/api/users/login", loginRequest, {
        // headers: {
        //     Authorization: `Bearer ${store.state.token}`
        // }
    })
}

const registerUserAPI = function (registerRequest : any) {
    return instance.post(
        "/api/users", registerRequest)
}

const createGroupAPI = function (createGroupRequest : any) {
    return authInstance.post(
        "/api/userGroups/create", createGroupRequest)
}

const joinGroupAPI = function (joinGroupRequest : any) {
    return authInstance.post(
        "/api/userGroups/join", joinGroupRequest)
}

// const getGroupAPI = function (getGroupRequest : any) {
//     return authInstance.get(
//         `/api/userGroups/byUser?userId=${getGroupRequest.userId}&size=${getGroupRequest.size}&page=${getGroupRequest.page}`, getGroupRequest)
// }

const getGroupAPI = function (userId : any) {
    return authInstance.get(
        `/api/userGroups/byUser/${userId}`, userId)
}

const userListAPI = function (getUserRequest : any) {
    return authInstance.get(
        `/api/users?size=${getUserRequest.size}&page=${getUserRequest.page}`, getUserRequest)
}

const deleteUserAPI = function (userId: any) {
    return authInstance.delete(
        `/api/users/${userId}`)
}

const getUserAPI = function (userId: any) {
    return authInstance.get(
        `/api/users/${userId}`)
}

export { loginAPI, registerUserAPI, userListAPI, deleteUserAPI, getUserAPI, createGroupAPI, getGroupAPI, joinGroupAPI }
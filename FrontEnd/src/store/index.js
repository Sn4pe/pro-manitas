import { createStore } from "vuex";
import { auth } from "./auth.model";

const store = createStore({
    modules: {
        auth,
    },
});

export default store;
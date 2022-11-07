import express from "express";
import config from "config";
import router from "./routers/router.js";
import userRouter from "./routers/userRouter.js";

//comment for recalculate on github
const PORT = process.env.PORT ?? config.get("server.port");
const HOST = process.env.PORT ?? config.get("server.host");

const app = new express();

app.use(express.json());
app.use("/api", router);
app.use("/users", userRouter);

const start = () => {
    try {
        app.listen(PORT, HOST, () => console.log(`Server started on port ${PORT}`));
    } catch(e) {
        console.log(e);
    }
};

start();

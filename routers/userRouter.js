import { Router } from "express";
import userController from "../controllers/userController.js";
import authMiddleware from "../middlewares/authMiddleware.js";

const userRouter = new Router();

userRouter.post("/login", userController.login);
userRouter.post("/add", authMiddleware, userController.add);

export default userRouter;
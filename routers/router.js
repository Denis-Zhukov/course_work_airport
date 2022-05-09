import { Router } from "express";
import controller from "../controllers/controller.js";
import authMiddleware from "../middlewares/authMiddleware.js";

const router = new Router();

router.post("/add_country", authMiddleware, controller.addCountry);
router.post("/add_city", authMiddleware, controller.addCity);
router.post("/add_airport", authMiddleware, controller.addAirport);
router.post("/add_route", authMiddleware, controller.addRoute);
router.post("/add_flight", authMiddleware, controller.addFlight);
router.post("/add_plane", authMiddleware, controller.addPlane);
router.post("/add_seating_layout", authMiddleware, controller.addSeatingLayout);
router.post("/add_ticket", authMiddleware, controller.addTicket);
router.post("/add_price", authMiddleware, controller.addPrice);

router.get("/get_roles", authMiddleware, controller.getRoles);
router.get("/get_accounts", authMiddleware, controller.getAccounts);

router.post("/add_role", authMiddleware, controller.addRole);
router.post("/add_account", authMiddleware, controller.addAccount);
router.post("/set_role", authMiddleware, controller.setRole);

export default router;
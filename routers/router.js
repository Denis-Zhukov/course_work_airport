import {Router} from "express";
import controller from "../controllers/controller.js";
import authMiddleware from "../middlewares/authMiddleware.js";

const router = new Router();
//GET
router.get("/get_countries", authMiddleware, controller.getCountries);
router.get("/get_cities", authMiddleware, controller.getCities);
router.get("/get_all_airports", authMiddleware, controller.getAllAirports);
router.post("/get_city", authMiddleware, controller.getCity);
router.post("/get_airports_cities_countries", authMiddleware, controller.getAirportsCitiesCountries);
router.post("/get_prices", controller.getPrices);
router.post("/get_route_by_from_to", controller.getRouteByFromTo);
router.post("/get_flights_by_date_and_route", controller.getFlightsByDateAndRoute);
router.get("/get_routes_popularity", authMiddleware, controller.getRoutesPopularity);
router.post("/get_flights_fullname_class", authMiddleware, controller.getFlightsFullNameClass);

//ADD
router.post("/add_country", authMiddleware, controller.addCountry);
router.post("/add_city", authMiddleware, controller.addCity);
router.post("/add_airport", authMiddleware, controller.addAirport);
router.post("/add_route", authMiddleware, controller.addRoute);
router.post("/add_flight", authMiddleware, controller.addFlight);
router.post("/add_plane", authMiddleware, controller.addPlane);
router.post("/add_seating_layout", authMiddleware, controller.addSeatingLayout);
router.post("/add_ticket", controller.addTicket);
router.post("/add_price", authMiddleware, controller.addPrice);

//SET (ADD, UPDATE)
router.post("/set_prices", authMiddleware, controller.setPrices);

//UPDATE
router.post("/update_country", authMiddleware, controller.updateCountry);
router.post("/update_city", authMiddleware, controller.updateCity);
router.post("/update_airport", authMiddleware, controller.updateAirport);
router.post("/update_route", authMiddleware, controller.updateRoute);
router.post("/update_flight", authMiddleware, controller.updateFlight);

//DELETE
router.delete("/delete_country/:id", authMiddleware, controller.deleteCountry);
router.delete("/delete_city/:id", authMiddleware, controller.deleteCity);
router.delete("/delete_airport/:id", authMiddleware, controller.deleteAirport);
router.delete("/delete_route/:id", authMiddleware, controller.deleteRoute);
router.delete("/delete_flight/:id", authMiddleware, controller.deleteFlight);


//Account & Roles
router.post("/add_role", authMiddleware, controller.addRole);
router.post("/add_account", authMiddleware, controller.addAccount);
router.post("/set_role", authMiddleware, controller.setRole);

router.get("/get_roles", authMiddleware, controller.getRoles);
router.post("/get_role", authMiddleware, controller.getRole);
router.get("/get_accounts", authMiddleware, controller.getAccounts);

router.delete("/delete_account/:id", authMiddleware, controller.deleteAccount);
router.delete("/deprive_role/:id_account", authMiddleware, controller.depriveRole);
router.delete("/delete_role/:id_role", authMiddleware, controller.deleteRole);

router.post("/update_account", authMiddleware, controller.updateAccount);
router.get("/get_seating_layouts", authMiddleware, controller.getSeatingLayouts);
router.post("/get_seating_layout", authMiddleware, controller.getSeatingLayout);
router.get("/get_new_seating_layout", authMiddleware, controller.getNewSeatingLayout);
router.get("/get_airplane_numbers", authMiddleware, controller.getAirplaneNumbers);
router.delete("/delete_airplane/:id_airplane", authMiddleware, controller.deleteAirplane);
router.post("/update_airplane", authMiddleware, controller.updateAirplane);
router.post("/get_airplane_by_id", authMiddleware, controller.getAirplaneById);
router.delete("/delete_seating_layout/:id", authMiddleware, controller.deleteSeatingLayout);
router.get("/get_all_seating_layouts", authMiddleware, controller.getAllSeatingLayouts);
router.get("/get_routes", authMiddleware, controller.getRoutes);
router.get("/get_airports", controller.getAirports);
router.get("/get_flights", authMiddleware, controller.getFlights);


export default router;
import config from "config";
import mysql from "mysql2/promise";

const databaseConfig = config.get("database");

class controller {
    async addCountry(req, res) {
        await addPrototype(req, res,
            ["admin", "traffic coordination dispatcher"],
            ["country"],
            "countries",
            ["name"],
            true,
            "This country already exists.");
    }

    async addCity(req, res) {
        await addPrototype(req, res,
            ["admin", "traffic coordination dispatcher"],
            ["city", "id_country"],
            "cities",
            ["name", "id_country"],
            true,
            "This city already exists.");
    }

    async addAirport(req, res) {
        await addPrototype(req, res,
            ["admin", "traffic coordination dispatcher"],
            ["airport", "id_city"],
            "airports",
            ["name", "id_city"],
            true,
            "This airport already exists.");
    }

    async addRoute(req, res) {
        await addPrototype(req, res,
            ["admin", "traffic coordination dispatcher"],
            ["id_airport_departure", "id_airport_distanation"],
            "routes",
            ["id_airport_departure", "id_airport_distanation"],
            true,
            "This route already exists.");
    }

    async addFlight(req, res) {
        await addPrototype(req, res,
            ["admin", "traffic coordination dispatcher"],
            ["id_plane", "id_route", "boarding_date"],
            "flights",
            ["id_plane", "id_route", "boarding_date"],
            true,
            "This flight already exists.");
    }

    async addPlane(req, res) {
        await addPrototype(req, res,
            ["admin", "maintenance dispatcher"],
            ["id_seating_layout", "plane", "number"],
            "planes",
            ["id_seating_layout", "name", "number"],
            true,
            "This plane already exists.");
    }

    async addSeatingLayout(req, res) {
        const validRoles = ["admin", "maintenance dispatcher"];
        const {id, id_class, count_rows, count_cols} = req.body;
        const tableName = "seating_layouts";

        console.log(req.body);
        if (!validRoles.includes(req.user.role))
            return res.status(403).json({message: "User have no permission"});

        if (!id || !id_class || typeof (count_rows) === "undefined" || typeof (count_cols) === "undefined")
            return res.status(400)
                .json({message: `Something is missing: id, id_class, count_rows, count_cols`});

        try {
            const connection = await mysql.createConnection(databaseConfig);
            await connection.connect(err => {
                if (err) return res.status(500).json({message: "Connection problem", ...err});
            });


            let query = `SELECT COUNT(*) as count FROM \`seating_layouts\` WHERE id=${id} AND id_class=${id_class}`;
            let [[{count}]] = await connection.query(query);
            if (count > 0)
                return res.status(400).json({message: "This seating layout already exists."});

            query = `INSERT INTO \`${tableName}\` (\`id\`, \`id_class\`, \`count_rows\`, \`count_cols\`) VALUES (?, ?, ?, ?)`;
            let [result] = await connection.query(query, [id, id_class, count_rows, count_cols]);

            connection.end();
            return res.json(result);
        } catch (e) {
            return res.status(500).json(e);
        }
    }

    //I don't mean adding place classes via api
    async addTicket(req, res) {
        console.log(1)
        let fieldNames = ["id_flight", "id_class", "buy_datetime", "fullname", "passport"];
        let tableName = "tickets";
        let tableFields = ["id_flight", "id_class", "buy_datetime", "fullname", "passport"];

        const values = fieldNames.map(fieldName => req.body[fieldName]);

        if (values.some(field => !field))
            return res.status(400).json({message: `Something is missing: ${fieldNames.join(", ")}`});

        try {
            const connection = await mysql.createConnection(databaseConfig);
            await connection.connect(err => {
                if (err) res.status(500).json({message: "Connection problem", ...err});
            });

            let query = `SELECT * FROM seating_layouts
            WHERE seating_layouts.id=(SELECT \`id_seating_layout\` FROM planes
            WHERE planes.id = (SELECT \`id_plane\` FROM \`flights\` WHERE \`id\`=?))`;
            let [result] = await connection.query(query, [values[0]]);

            let classes = result.find(el => el.id_class = values[1]);
            let maxCount = classes.count_cols * classes.count_rows;

            query = `SELECT COUNT(*) as count FROM \`tickets\` WHERE \`id_flight\`=? AND \`id_class\`=?`;
            [result] = await connection.query(query, [values[0], values[1]]);

            if(result[0].count >= maxCount)
                return res.status(410).json({message: "There are no seats"})

            query = `INSERT INTO \`${tableName}\` (${tableFields.map(field=>"`"+field+"`").join(", ")}) VALUES (${"?".repeat(tableFields.length).split("").join(", ")})`;
            [result] = await connection.query(query, values);

            connection.end();
            return res.json(result);
        } catch (e) {
            return res.status(500).json(e);
        }
    }

    async addPrice(req, res) {
        await addPrototype(req, res,
            ["admin"],
            ["id_flight", "id_class", "price"],
            "ticket",
            ["id_flight", "id_class", "price"],
            false,
            null);
    }

    async addRole(req, res) {
        await addPrototype(req, res,
            ["admin"],
            ["role"],
            "roles",
            ["role"],
            true,
            "This role already exists.");
    }

    async addAccount(req, res) {
        const validRoles = ["admin"];
        const {username, hashPassword} = req.body;
        const tableName = "accounts";

        if (!validRoles.includes(req.user.role))
            return res.status(403).json({message: "User have no permission"});

        if (!username || !hashPassword || hashPassword.length != 128)
            return res.status(400)
                .json({message: `Something is missing: username, hashPassword or uncorrect hashPassword`});

        try {
            const connection = await mysql.createConnection(databaseConfig);
            await connection.connect(err => {
                if (err) res.status(500).json({message: "Connection problem", ...err});
            });

            let query = `SELECT COUNT(*) as count FROM \`${tableName}\` WHERE  \`username\`=?`;
            let [result] = await connection.query(query, [username]);
            if (result[0].count > 0) return res.status(400).json({message: "This account already exists."});

            query = `INSERT INTO \`accounts\` (\`username\`, \`hashPassword\`) VALUES (?, ?)`;
            [result] = await connection.query(query, [username, hashPassword]);

            connection.end();
            return res.json(result);
        } catch (e) {
            return res.status(500).json(e);
        }
    }

    async setRole(req, res) {
        const validRoles = ["admin"];
        const {id_account, id_role} = req.body;
        const tableName = "accounts_roles";

        if (!validRoles.includes(req.user.role))
            return res.status(403).json({message: "User have no permission"});

        if (!id_account || !id_role)
            return res.status(400).json({message: `Something is missing: id_account, id_role.`});

        try {
            const connection = await mysql.createConnection(databaseConfig);
            await connection.connect(err => {
                if (err) res.status(500).json({message: "Connection problem", ...err});
            });

            let query = `SELECT COUNT(*) as count FROM \`${tableName}\` WHERE  \`id_account\`=?`;
            let [result] = await connection.query(query, [id_account]);
            if (result[0].count > 0) query = "UPDATE `accounts_roles` SET `id_role` = ? WHERE `id_account` = ?";
            else query = "INSERT INTO `accounts_roles` (`id_role`, `id_account`) VALUES (?, ?)";
            [result] = await connection.query(query, [id_role, id_account]);
            connection.end();
            return res.status(200).json(result);
        } catch (e) {
            return res.status(500).json({message: "fatal server error", ...e});
        }
    }

    async getRoles(req, res) {
        await getPrototype(req, res,
            ["admin"],
            "roles",
            ["*"],
        );
    }

    async getRole(req, res) {
        await getPrototype(req, res,
            ["admin"],
            "(`accounts_roles` JOIN roles ON `accounts_roles`.`id_role` = roles.id)",
            ["role"],
            ["id_account"],
        );
    }

    async getAccounts(req, res) {
        await getPrototype(req, res,
            ["admin"],
            "accounts",
            ["id", "username"],
        );
    }

    async deleteAccount(req, res) {
        await deletePrototype(req, res,
            ["admin"],
            ["id"],
            "accounts",
            ["id"],
        );
    }

    async depriveRole(req, res) {
        await deletePrototype(req, res,
            ["admin"],
            ["id_account"],
            "accounts_roles",
            ["id_account"],
        );
    }

    async deleteRole(req, res) {
        await deletePrototype(req, res,
            ["admin"],
            ["id_role"],
            "roles",
            ["id"],
        );
    }

    async updateAccount(req, res) {
        const validRoles = ["admin"];
        const fieldNames = ["id_account", "id_role", "newUsername", "newHashPassword"];
        const accountsRolesTable = "accounts_roles";
        const accountsTable = "accounts";

        if (!validRoles.includes(req.user.role))
            return res.status(403).json({message: "User have no permission"});

        const values = fieldNames.map(fieldName => req.body[fieldName]);

        if (values.some((val, i) => !val && i !== 3))
            return res.status(400).json({message: `Something is missing: ${fieldNames.join(", ")} or newValues`});
        try {
            const connection = await mysql.createConnection(databaseConfig);
            await connection.connect(err => {
                if (err) res.status(500).json({message: "Connection problem", ...err});
            });

            let query = `SELECT COUNT(*) as count FROM ${accountsTable} WHERE \`id\`=?`;
            let [result] = await connection.execute(query, [values[0]]);

            if (result?.[0]?.count === 0)
                return res.status(410).json({message: "No account to update"});


            query = `UPDATE ${accountsTable} SET \`username\`=?${values[3]!==""?",\`hashPassword\`=?":""} WHERE \`id\`=?`;
            let updatedValues = [values[2], values[0]];
            if (values[3] !== "") updatedValues.splice(1, 0, values[3]);
            let [firstResult] = await connection.execute(query, updatedValues);

            query = `UPDATE ${accountsRolesTable} SET \`id_role\`=? WHERE \`id_account\`=?`;
            [result] = await connection.execute(query, [values[1], values[0]]);

            connection.end();
            return await new controller().setRole(req, res);
        } catch (e) {
            console.log(e);
            return res.status(500).json({message: "fatal server error", ...e});
        }
    }

    //SELECT DISTINCT `id` FROM `seating_layouts`
    async getSeatingLayouts(req, res) {
        await getPrototype(req, res,
            ["admin", "maintenance dispatcher"],
            "seating_layouts",
            ["id"],
            [],
            true,
        );
    }

    async getSeatingLayout(req, res) {
        let validRoles = ["admin", "maintenance dispatcher"];
        let valuesFields = ["id"];

        if (!validRoles.includes(req.user.role))
            return res.status(403).json({message: "User have no permission"});

        let valuesConditions = valuesFields.map(field => req.body[field]);

        try {
            const connection = await mysql.createConnection(databaseConfig);
            await connection.connect(err => {
                if (err) res.status(500).json({message: "Connection problem", ...err});
            });

            let query = `SELECT name as class,\`count_rows\`,\`count_cols\` FROM (seating_layouts JOIN classes ON seating_layouts.\`id_class\`=classes.id) WHERE seating_layouts.id=?`;
            let [result] = await connection.execute(query, valuesConditions);
            connection.end();
            return res.json({result});
        } catch (e) {
            return res.status(500).json(e);
        }
    }

    async getNewSeatingLayout(req, res) {
        await getPrototype(req, res,
            ["admin", "maintenance dispatcher"],
            "seating_layouts",
            ["IFNULL((MAX(`id`)+1), 0) as id"],
            [],
        );
    }

    async getAirplaneById(req, res) {
        await getPrototype(req, res,
            ["admin", "maintenance dispatcher"],
            "planes",
            ["id_seating_layout", "name", "number"],
            ["id"],
        );
    }

    async getAirplaneNumbers(req, res) {
        await getPrototype(req, res,
            ["admin", "maintenance dispatcher", "traffic coordination dispatcher"],
            "planes",
            ["id", "number"],
        );
    }

    async deleteAirplane(req, res) {
        await deletePrototype(req, res,
            ["admin", "maintenance dispatcher"],
            ["id_airplane"],
            "planes",
            ["id"],
        );
    }

    async updateAirplane(req, res) {
        const validRoles = ["admin", "maintenance dispatcher"];
        const fieldNames = ["id_seating_layout", "name", "number", "id"];
        const table = "planes";
        if (!validRoles.includes(req.user.role))
            return res.status(403).json({message: "User have no permission"});

        const values = fieldNames.map(fieldName => req.body[fieldName]);

        if (values.some(val => !val))
            return res.status(400).json({message: `Something is missing: ${fieldNames.join(", ")} or newValues`});

        try {
            const connection = await mysql.createConnection(databaseConfig);
            await connection.connect(err => {
                if (err) res.status(500).json({message: "Connection problem", ...err});
            });

            let query = `SELECT COUNT(*) as count FROM ${table} WHERE \`id\`=?`;
            let [result] = await connection.execute(query, [values[3]]);

            if (result?.[0]?.count === 0 ?? true)
                return res.status(410).json({message: "No airplane to update"});

            query = `UPDATE ${table} SET id_seating_layout=?, name=?, number=? WHERE id=?`;
            [result] = await connection.execute(query, values);

            connection.end();

            return res.json(result);
        } catch (e) {
            console.log(e);
            return res.status(500).json({message: "fatal server error", ...e});
        }
    }

    async deleteSeatingLayout(req, res) {
        await deletePrototype(req, res,
            ["admin", "maintenance dispatcher"],
            ["id"],
            "seating_layouts",
            ["id"],
        );
    }

    async getAllSeatingLayouts(req, res) {
        await getPrototype(req, res,
            ["admin", "maintenance dispatcher"],
            "seating_layouts",
            ["*"],
        );
    }

    async getRoutes(req, res) {
        await getPrototype(req, res,
            ["admin", "traffic coordination dispatcher"],
            "from_to_view",
            ["*"],
        );
    }

    async getAirports(req, res) {
        await getPrototype(req, res,
            [],
            "airports_view",
            ["*"],
        );
    }

    async getFlights(req, res) {
        await getPrototype(req, res,
            ["admin", "traffic coordination dispatcher", "economist"],
            "flights_view",
            ["*"],
        );
    }

    async getCountries(req, res) {
        await getPrototype(req, res,
            ["admin", "traffic coordination dispatcher"],
            "countries",
            ["*"],
        );
    }

    async updateCountry(req, res) {
        await updatePrototype(req, res,
            ["admin", "traffic coordination dispatcher"],
            ["country"],
            ["id"],
            "countries",
            ["name"],
            ["id"],
        );
    }

    async deleteCountry(req, res) {
        await deletePrototype(req, res,
            ["admin", "traffic coordination dispatcher"],
            ["id"],
            "countries",
            ["id"],
        );
    }

    //SELECT cities.id, cities.name as city, countries.name as country FROM cities JOIN countries ON cities.id_country=countries.id
    async getCities(req, res) {
        await getPrototype(req, res,
            ["admin", "traffic coordination dispatcher"],
            "cities_view",
            ["*"],
        );
    }

    async updateCity(req, res) {
        await updatePrototype(req, res,
            ["admin", "traffic coordination dispatcher"],
            ["city"],
            ["id"],
            "cities",
            ["name"],
            ["id"],
        );
    }

    async deleteCity(req, res) {
        await deletePrototype(req, res,
            ["admin", "traffic coordination dispatcher"],
            ["id"],
            "cities",
            ["id"],
        );
    }

    async getAllAirports(req, res) {
        await getPrototype(req, res,
            ["admin", "traffic coordination dispatcher"],
            "airports",
            ["id", "name"],
        );
    }

    async getCity(req, res) {
        await getPrototype(req, res,
            ["admin", "traffic coordination dispatcher"],
            "id_airport_country_and_city",
            ["country", "city"],
            ["id_airport"],
        );
    }

    async updateAirport(req, res) {
        await updatePrototype(req, res,
            ["admin", "traffic coordination dispatcher"],
            ["id_city", "airport_name"],
            ["id"],
            "airports",
            ["id_city", "name"],
            ["id"],
        );
    }

    async deleteAirport(req, res) {
        await deletePrototype(req, res,
            ["admin", "traffic coordination dispatcher"],
            ["id"],
            "airports",
            ["id"],
        );
    }

    async getAirportsCitiesCountries(req, res) {
        await getPrototype(req, res,
            [],
            "airports_view",
            ["*"],
        );
    }

    async updateRoute(req, res) {
        await updatePrototype(req, res,
            ["admin", "traffic coordination dispatcher"],
            ["id_airport_departure", "id_airport_distanation"],
            ["id"],
            "routes",
            ["id_airport_departure", "id_airport_distanation"],
            ["id"],
        );
    }

    async deleteRoute(req, res) {
        await deletePrototype(req, res,
            ["admin", "traffic coordination dispatcher"],
            ["id"],
            "routes",
            ["id"],
        );
    }

    async updateFlight(req, res) {
        await updatePrototype(req, res,
            ["admin", "traffic coordination dispatcher"],
            ["id_plane", "id_route", "boarding_date"],
            ["id"],
            "flights",
            ["id_plane", "id_route", "boarding_date"],
            ["id"],
        );
    }

    async deleteFlight(req, res) {
        await deletePrototype(req, res,
            ["admin", "traffic coordination dispatcher"],
            ["id"],
            "flights",
            ["id"],
        );
    }

    async getPrices(req, res) {
        await getPrototype(req, res,
            [],
            "price_list_view",
            ["class", "price"],
            ["id_flight"]
        );
    }

    async setPrices(req, res) {
        const validRoles = ["admin", "economist"];
        const fieldNames = ["id_flight", "first_class_price", "business_class_price", "economy_class_price"];
        const table = "price_list";

        if (!validRoles.includes(req.user.role))
            return res.status(403).json({message: "User have no permission"});

        const values = fieldNames.map(fieldName => req.body[fieldName]);

        if (values.some(val => typeof (val) === "undefined"))
            return res.status(400).json({message: `Something is missing: ${fieldNames.join(", ")} or newValues`});

        try {
            const connection = await mysql.createConnection(databaseConfig);
            await connection.connect(err => {
                if (err) res.status(500).json({message: "Connection problem", ...err});
            });

            let query = `SELECT COUNT(*) as count FROM ${table} WHERE \`id_flight\`=?`;
            let [result] = await connection.execute(query, [values[0]]);

            if (result?.[0]?.count === 3) {
                query = mysql.format(`DELETE FROM ${table} WHERE id_flight=?`, [values[0]])
                await connection.execute(query);
            }

            if (result?.[0]?.count === 0 || result?.[0]?.count === 3) {
                query = mysql.format(`INSERT INTO ${table} (id_flight, id_class, price) VALUES (?, ?, ?),`, [values[0], 1, values[1]]);
                query += mysql.format(`(?, ?, ?),`, [values[0], 2, values[2]])
                query += mysql.format(`(?, ?, ?);`, [values[0], 3, values[3]]);

                let [result] = await connection.execute(query, [values[0], values[1],
                    values[0], values[2],
                    values[0], values[3]]
                );
                connection.end();
                return res.json(result);
            } else {
                return res.status(410).json({message: "This flight has an invalid structure in database's table flights"});
            }
        } catch (e) {
            console.log(e);
            return res.status(500).json({message: "fatal server error", ...e});
        }
    }


    async getRouteByFromTo(req, res) {
        await getPrototype(req, res,
            [],
            "routes",
            ["id"],
            ["id_airport_departure", "id_airport_distanation"]
        );
    }

    async getFlightsByDateAndRoute(req, res) {
        let tableName = "flights_view";
        let tableFields = ["id", 'fromAirport', 'fromCity', 'fromCountry', 'toAirport', 'toCity', 'toCountry', 'boarding_datetime'];
        let whereFields = ["id_route", "boarding_datetime"];

        let valuesConditions = whereFields.map(field => req.body[field]);
        if (valuesConditions.some(field => !field))
            return res.status(400).json({message: `Something is missing: ${whereFields.join(", ")}`});

        try {
            const connection = await mysql.createConnection(databaseConfig);
            await connection.connect(err => {
                if (err) res.status(500).json({message: "Connection problem", ...err});
            });

            let query = `SELECT ${tableFields.join(", ")} FROM (${tableName}) WHERE id_route=? AND DATE(boarding_datetime)=?`;
            let [result] = await connection.execute(query, valuesConditions);
            connection.end();
            return res.json({result});
        } catch (e) {
            return res.status(500).json(e);
        }
    }
}

async function addPrototype(req, res, validRoles, fieldNames, tableName, tableFields, mustBeUniq, nonUniqueError) {
    if (!validRoles.includes(req.user.role))
        return res.status(403).json({message: "User have no permission"});

    const values = fieldNames.map(fieldName => req.body[fieldName]);

    if (values.some(field => !field))
        return res.status(400).json({message: `Something is missing: ${fieldNames.join(", ")}`});

    try {
        const connection = await mysql.createConnection(databaseConfig);
        await connection.connect(err => {
            if (err) res.status(500).json({message: "Connection problem", ...err});
        });

        if (mustBeUniq) {
            let query = `SELECT COUNT(*) as count FROM \`${tableName}\` WHERE ${tableFields.map(field => `\`${field}\``).join("=? AND ") + "=?"}`;
            let [result] = await connection.query(query, values);
            if (result[0].count > 0) return res.status(400).json({message: nonUniqueError});
        }

        let query = `INSERT INTO \`${tableName}\` (${tableFields.map(field=>"`"+field+"`").join(", ")}) VALUES (${"?".repeat(tableFields.length).split("").join(", ")})`;
        console.log(query);
        let [result] = await connection.query(query, values);

        connection.end();
        return res.json(result);
    } catch (e) {
        return res.status(500).json(e);
    }
}

async function getPrototype(req, res, validRoles, tableName, tableFields, whereFields = [], distinct = false) {
    if (validRoles.length !== 0 && !validRoles.includes(req.user.role))
        return res.status(403).json({message: "User have no permission"});

    let valuesConditions = whereFields.map(field => req.body[field]);
    if (valuesConditions.some(field => !field))
        return res.status(400).json({message: `Something is missing: ${whereFields.join(", ")}`});

    try {
        const connection = await mysql.createConnection(databaseConfig);
        await connection.connect(err => {
            if (err) res.status(500).json({message: "Connection problem", ...err});
        });

        let query = `SELECT ${distinct ? "DISTINCT":""} ${tableFields.join(", ")} FROM (${tableName})
        ${whereFields.length > 0 ? "WHERE "+whereFields.map(field=>"`"+field+"`=? ").join(" AND ") : "" }`;
        console.log(query);
        let [result] = await connection.execute(query, valuesConditions);
        connection.end();
        return res.json({result});
    } catch (e) {
        return res.status(500).json(e);
    }
}

async function deletePrototype(req, res, validRoles, paramsNames, tableName, wheresFields) {
    if (!validRoles.includes(req.user.role))
        return res.status(403).json({message: "User have no permission"});

    const values = paramsNames.map(fieldName => req.params[fieldName]);

    if (values.some(field => !field))
        return res.status(400).json({message: `Something is missing: ${paramsNames.join(", ")}`});

    try {
        const connection = await mysql.createConnection(databaseConfig);
        await connection.connect(err => {
            if (err) res.status(500).json({message: "Connection problem", ...err});
        });

        let query = `SELECT COUNT(*) as count FROM \`${tableName}\` WHERE ${wheresFields.map(field=>`\`${field}\``).join("=? AND ")+"=?"}`;
        let [result] = await connection.query(query, values);
        if (result[0].count === 0) return res.status(400).json({message: "This entry no longer exist or changed"});

        query = `DELETE FROM ${tableName} WHERE ${wheresFields.map(field=>"\`"+field+"\`=?").join(" AND ")}`;
        [result] = await connection.execute(query, values);
        connection.end();
        return res.json({result});
    } catch (e) {
        if (e.code === "ER_ROW_IS_REFERENCED_2") return res.status(400)
            .json({message: "This template is used by airplane\n"});
        return res.status(500).json(e);
    }
}

async function updatePrototype(req, res, validRoles, valuesFieldNames, whereFieldNames, tableName, updateFields, conditionFields) {
    if (!validRoles.includes(req.user.role))
        return res.status(403).json({message: "User have no permission"});

    const values = valuesFieldNames.map(fieldName => req.body[fieldName]);
    const wheres = whereFieldNames.map(fieldName => req.body[fieldName]);

    if (values.some(field => !field))
        return res.status(400).json({message: `Something is missing: ${valuesFieldNames.join(", ")}`});

    try {
        const connection = await mysql.createConnection(databaseConfig);
        await connection.connect(err => {
            if (err) res.status(500).json({message: "Connection problem", ...err});
        });


        let sets = updateFields.map(field => field + "=?").join(", ");
        let conds = conditionFields.length > 0 ? (" WHERE " + conditionFields.map(field => field)) + "=?" : "";

        let query = `SELECT COUNT(*) as count FROM \`${tableName}\`` + conds;
        let [result] = await connection.query(query, wheres);
        if (result[0].count === 0) return res.status(400).json({message: "No entry to update"});

        query = `UPDATE ${tableName} SET ${sets} ${conds}`;
        [result] = await connection.query(query, [...values, ...wheres]);

        connection?.end();
        return res.json(result);
    } catch (e) {
        return res.status(500).json({message: "server problem", ...e});
    }
}

export default new controller();
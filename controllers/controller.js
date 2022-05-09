import config from "config";
import mysql from "mysql2/promise";

const databaseConfig = config.get("database");

class controller {
    async addCountry(req, res) {
        await addPrototype(req, res,
            ["admin"],
            ["country"],
            "countries",
            ["name"],
            true,
            "This country already exists.");
    }

    async addCity(req, res) {
        await addPrototype(req, res,
            ["admin"],
            ["city", "id_country"],
            "cities",
            ["name", "id_country"],
            true,
            "This city already exists.");
    }

    async addAirport(req, res) {
        await addPrototype(req, res,
            ["admin"],
            ["airport", "id_city"],
            "airports",
            ["name", "id_city"],
            true,
            "This airport already exists.");
    }

    async addRoute(req, res) {
        await addPrototype(req, res,
            ["admin"],
            ["id_airport_departure", "id_airport_distanation"],
            "routes",
            ["id_airport_departure", "id_airport_distanation"],
            true,
            "This route already exists.");
    }

    async addFlight(req, res) {
        await addPrototype(req, res,
            ["admin"],
            ["id_plane", "id_route", "boarding_date"],
            "routes",
            ["id_plane", "id_route", "boarding_date"],
            true,
            "This flight already exists.");
    }

    async addPlane(req, res) {
        await addPrototype(req, res,
            ["admin"],
            ["id_seating_layout", "plane", "number"],
            "planes",
            ["id_seating_layout", "name", "number"],
            true,
            "This plane already exists.");
    }

    async addSeatingLayout(req, res) {
        const validRoles = ["admin"];
        const {id, id_class, count_rows, count_cols} = req.body;
        const tableName = "seating_layouts";

        if( !validRoles.includes(req.user.role) )
            return res.status(403).json({message: "User have no permission"});

        if( !id || !id_class || !count_rows || !count_cols )
            return res.status(400)
                .json({message: `Something is missing: id, id_class, count_rows, count_cols`});

        try {
            const connection = await mysql.createConnection(databaseConfig);
            await connection.connect(err => {
                if( err ) return res.status(500).json({message: "Connection problem", ...err});
            });


            let query = `SELECT COUNT(*) as count FROM \`seating_layouts\` WHERE id=${id} AND id_class=${id_class}`;
            let [[{count}]] = await connection.query(query);
            if( count > 0 )
                return res.status(400).json({message: "This seating layout already exists."});

            query = `INSERT INTO \`${tableName}\` (\`id\`, \`id_class\`, \`count_rows\`, \`count_cols\`) VALUES (?, ?, ?, ?)`;
            let [result] = await connection.query(query, [id, id_class, count_rows, count_cols]);

            connection.end();
            return res.json(result);
        } catch(e) {
            return res.status(500).json(e);
        }
    }

    //I don't mean adding place classes via api
    async addTicket(req, res) {
        await addPrototype(req, res,
            ["admin"],
            ["id_flight", "seat", "buy_datetime", "fullname", "passport"],
            "ticket",
            ["id_flight", "seat", "buy_datetime", "fullname", "passport"],
            true,
            "This ticket already exists.");
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

        if( !validRoles.includes(req.user.role) )
            return res.status(403).json({message: "User have no permission"});

        if( !username || !hashPassword || hashPassword.length != 128 )
            return res.status(400)
                .json({message: `Something is missing: username, hashPassword or uncorrect hashPassword`});

        try {
            const connection = await mysql.createConnection(databaseConfig);
            await connection.connect(err => {
                if( err ) res.status(500).json({message: "Connection problem", ...err});
            });

            let query = `SELECT COUNT(*) as count FROM \`${tableName}\` WHERE  \`username\`=?`;
            let [result] = await connection.query(query, [username]);
            if( result[0].count > 0 ) return res.status(400).json({message: "This account already exists."});

            query = `INSERT INTO \`accounts\` (\`username\`, \`hashPassword\`) VALUES (?, ?)`;
            [result] = await connection.query(query, [username, hashPassword]);

            connection.end();
            return res.json(result);
        } catch(e) {
            return res.status(500).json(e);
        }
    }

    async setRole(req, res) {
        const validRoles = ["admin"];
        const {id_account, id_role} = req.body;
        const tableName = "accounts_roles";

        if( !validRoles.includes(req.user.role) )
            return res.status(403).json({message: "User have no permission"});

        if( !id_account || !id_role )
            return res.status(400).json({message: `Something is missing: id_account, id_role.`});

        try {
            const connection = await mysql.createConnection(databaseConfig);
            await connection.connect(err => {
                if( err ) res.status(500).json({message: "Connection problem", ...err});
            });

            let query = `SELECT COUNT(*) as count FROM \`${tableName}\` WHERE  \`id_account\`=?`;
            let [result] = await connection.query(query, [id_account]);
            if( result[0].count > 0 ) query = "UPDATE `accounts_roles` SET `id_role` = ? WHERE `id_account` = ?";
            else query = "INSERT INTO `accounts_roles` (`id_role`, `id_account`) VALUES (?, ?)";

            [result] = await connection.query(query, [id_role, id_account]);
            connection.end();
            return res.json(result);
        } catch(e) {
            return res.status(500).json(e);
        }
    }

    async getRoles(req, res) {
        await getPrototype(req, res,
            ["admin"],
            "roles",
            ["*"],
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
    async deleteRole(req, res){
        await deletePrototype(req, res,
            ["admin"],
            ["id_role"],
            "roles",
            ["id"],
        );
    }
}

async function addPrototype(req, res, validRoles, fieldNames, tableName, tableFields, mustBeUniq, nonUniqueError) {
    if( !validRoles.includes(req.user.role) )
        return res.status(403).json({message: "User have no permission"});

    const values = fieldNames.map(fieldName => req.body[fieldName]);

    if( values.some(field => !field) )
        return res.status(400).json({message: `Something is missing: ${fieldNames.join(", ")}`});

    try {
        const connection = await mysql.createConnection(databaseConfig);
        await connection.connect(err => {
            if( err ) res.status(500).json({message: "Connection problem", ...err});
        });

        if( mustBeUniq ) {
            let query = `SELECT COUNT(*) as count FROM \`${tableName}\` WHERE ${tableFields.map(field=>`\`${field}\``).join("=? AND ")+"=?"}`;
            let [result] = await connection.query(query, values);
            if( result[0].count > 0 ) return res.status(400).json({message: nonUniqueError});
        }

        let query = `INSERT INTO \`${tableName}\` (${tableFields.map(field=>"`"+field+"`").join(", ")}) VALUES (${"?".repeat(tableFields.length).split("").join(", ")})`;
        let [result] = await connection.query(query, values);

        connection.end();
        return res.json(result);
    } catch(e) {
        return res.status(500).json(e);
    }
}

async function getPrototype(req, res, validRoles, tableName, tableFields) {
    if( !validRoles.includes(req.user.role) )
        return res.status(403).json({message: "User have no permission"});

    try {
        const connection = await mysql.createConnection(databaseConfig);
        await connection.connect(err => {
            if( err ) res.status(500).json({message: "Connection problem", ...err});
        });

        let query = `SELECT ${tableFields.map(field=>"\`"+field+"\`").join(", ")} FROM \`${tableName}\``;
        let [result] = await connection.execute(query);
        connection.end();
        return res.json({result});
    } catch(e) {
        return res.status(500).json(e);
    }
}

async function deletePrototype(req, res, validRoles, paramsNames, tableName, tableFields) {
    if( !validRoles.includes(req.user.role) )
        return res.status(403).json({message: "User have no permission"});

    const values = paramsNames.map(fieldName => req.params[fieldName]);

    if( values.some(field => !field) )
        return res.status(400).json({message: `Something is missing: ${paramsNames.join(", ")}`});

    try {
        const connection = await mysql.createConnection(databaseConfig);
        await connection.connect(err => {
            if( err ) res.status(500).json({message: "Connection problem", ...err});
        });

        let query = `SELECT COUNT(*) as count FROM \`${tableName}\` WHERE ${tableFields.map(field=>`\`${field}\``).join("=? AND ")+"=?"}`;
        let [result] = await connection.query(query, values);
        if( result[0].count == 0 ) return res.status(400).json({message: "This entry does not exist"});

        query = `DELETE FROM ${tableName} WHERE ${tableFields.map(field=>"\`"+field+"\`=?").join(" AND ")}`;
        [result] = await connection.execute(query, values);
        connection.end();
        return res.json({result});
    } catch(e) {
        return res.status(500).json(e);
    }
}

export default new controller();
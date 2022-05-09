import mysql from "mysql2/promise";
import jwt from "jsonwebtoken";
import config from "config";

const privateKey = config.get("server.private-key");
const lifetimeToken = config.get("server.lifetime-token");
const databaseConfig = config.get("database");

const generateAccessToken = (payload) => jwt.sign(payload, privateKey, {expiresIn: lifetimeToken}, null);

class userController {
    async login(req, res) {
        const {username, hashPassword} = req.body;
        if( !username || !hashPassword ) return res.status(500).json({message: "No username or password"});


        try {
            const connection = await mysql.createConnection(databaseConfig);
            await connection.connect(err => {
                if( err ) res.status(500).json({message: "Connection problem", ...err});
            });

            let query = `SELECT accounts.id, accounts.username as login, roles.role
                                    FROM accounts 
                                    INNER JOIN accounts_roles
                                    ON accounts.id = accounts_roles.id_account
                                    INNER JOIN roles
                                    ON roles.id = accounts_roles.id_role
                                    WHERE username=? AND hashPassword=?
                                    LIMIT 1`;

            const [payload] = await connection.execute(query, [username, hashPassword]);
            connection.end();
            if( !payload?.[0] ) return res.status(400).json({message: "User with the specified data was not found"});
            const token = generateAccessToken(payload[0]);
            return res.json({role: payload[0].role, username: payload[0].login,token});
        } catch(e) {
            return res.status(500).json({suspended_message: "fatal error", ...e});
        }
    }

    async add(req, res) {
        return res.json(req.user);
    }
}

export default new userController();
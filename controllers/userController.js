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
        if (!username || !hashPassword) return res.status(500).json({message: "No username or password"});


        try {
            const connection = await mysql.createConnection(databaseConfig);
            await connection.connect(err => {
                if (err) res.status(500).json({message: "Connection problem", ...err});
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

            query = `SELECT COUNT(*) as count FROM accounts WHERE username=? AND hashPassword=? LIMIT 1`

            const [result] = await connection.execute(query, [username, hashPassword]);
            connection.end();
            if (!payload?.[0] && result?.[0].count === 1) return res.status(400).json({message: "Authorization successful\nBut your rights are not set"});
            if (!payload?.[0]) return res.status(400).json({message: "Wrong login or password"});
            const token = generateAccessToken(payload[0]);
            return res.json({role: payload[0].role, username: payload[0].login, token});
        } catch (e) {
            console.error(e)
            return res.status(500).json({message: "Server fatal error!"});
        }
    }

    async add(req, res) {
        return res.json(req.user);
    }
}

export default new userController();
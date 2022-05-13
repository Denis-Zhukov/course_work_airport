import jwt from "jsonwebtoken";
import config from "config";

const privateKey = config.get("server.private-key");

export default function(req, res, next) {
    if( req.method === "OPTIONS" )
        next();

    try {
        const token = req.header("authorization");
        if( !token )
            return res.status(401).json({message: "User not authorized"});

        const account = jwt.verify(token, privateKey, null, null);
        req.user = account;
        next();
    } catch(e) {
        console.log(e);
        return res.status(401).json({message: "User not authorized. Token is deprecated"});
    }
};
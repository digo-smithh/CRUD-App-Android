const dbConfig = require("../config/db.config.js");
const mssql = require("mssql");

mssql
  .connect(dbConfig)
  .then((conexao) => (global.conexao = conexao))
  .catch((erro) => console.log(erro));
module.exports = mssql;

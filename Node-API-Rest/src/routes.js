module.exports = (app) => {
  const students = require("./controllers/student.controller.js");

  app.post("/students", students.create);

  app.get("/students", students.findAll);

  app.get("/students/:code", students.findOne);

  app.put("/students/:code", students.update);

  app.delete("/students/:code", students.delete);

  app.delete("/students", students.deleteAll);
};

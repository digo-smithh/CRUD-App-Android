const Student = require("../models/student.model.js");

exports.create = (req, res) => {
  if (!req.body) {
    res.status(400).send({
      message: "The content can not be empty.",
    });
  }

  const student = new Student({
    code: req.body.code,
    email: req.body.email,
    name: req.body.name,
  });

  Student.create(student, (err, data) => {
    if (err)
      res.status(500).send({
        message: err.message || "Error when trying to create student.",
      });
    else res.send(data.recordset);
  });
};

exports.findAll = (req, res) => {
  Student.getAll((err, data) => {
    if (err)
      res.status(500).send({
        message: err.message || "Error while searching for students.",
      });
    else res.send(data.recordset);
  });
};

exports.findOne = (req, res) => {
  Student.findByCode(req.params.code, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `The student with the code: ${req.params.code} wasn't found.`,
        });
      } else {
        res.status(500).send({
          message: "Error while searching for the student with the code: " + req.params.code,
        });
      }
    } else res.send(data);
  });
};

exports.update = (req, res) => {
  if (!req.body) {
    res.status(400).send({
      message: "The content can not be empty",
    });
  }

  Student.updateByCode(req.params.code, new Student(req.body), (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `The student with the code: ${req.params.code} wasn't found.`,
        });
      } else {
        res.status(500).send({
          message: "Error while updating the student with the code: " + req.params.code,
        });
      }
    } else res.send(data);
  });
};

exports.delete = (req, res) => {
  Student.remove(req.params.code, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `The student with the code: ${req.params.code} wasn't found.`,
        });
      } else {
        res.status(500).send({
          message: "Error while deleting the student with the code: " + req.params.code,
        });
      }
    } else {
      res.send({
        message: `Student was deleted succesfully!`,
      });
    }
  });
};

exports.deleteAll = (req, res) => {
  Student.removeAll((err, data) => {
    if (err)
      res.status(500).send({
        message: err.message || "An error was thrown while deleting students.",
      });
    else
      res.send({
        message: `All students were deleted successfully!`,
      });
  });
};

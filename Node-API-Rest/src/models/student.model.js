const sql = require("./db.js");

const Student = function (student) {
  this.code = student.code;
  this.email = student.email;
  this.name = student.name;
};

Student.create = (newStudent, result) => {
  
  sql.query(
    "INSERT INTO student VALUES('"+newStudent.code +"', '"+newStudent.name +"', '"+newStudent.email+"')",
    (err, res) => {
      if (err) {
        result(err, null);
        return;
      }

      result(null, {
        code: res.insertCode,
        ...newStudent,
      });
    }
  );
};

Student.findByCode = (studentCode, result) => {
  sql.query(`SELECT * FROM student WHERE code = ${studentCode}`, (err, res) => {

    if (res.recordset.length > 0) {
      result(null, res.recordset[0]);
      return;
    }

    if (err) {
      result(err, null);
      return;
    }

    result({kind: "not_found"},null);
  });
};

Student.getAll = (result) => {
  sql.query("SELECT * FROM student order by name", (err, res) => {
    if (err) {
      result(null, err);
      return;
    }

    result(null, res);
  });
};

Student.updateByCode = (code, student, result) => {
  sql.query(
    `UPDATE student SET name = '${student.name}', email = '${student.email}' WHERE code = '${code}'`,
    (err, res) => {
      if (err) {
        result(null, err);
        return;
      }

      result(null, {
        code: code,
        ...student
      });
    }
  );
};

Student.remove = (code, result) => {
  sql.query(`DELETE FROM student WHERE code = '${code}' `, (err, res) => {
    if (err) {
      result(null, err);
      return;
    }

    if (res.affectedRows == 0) {
      result({kind: "not_found"},null);
      return;
    }
    result(null, res);
  });
};

Student.removeAll = (result) => {
  sql.query("DELETE FROM student", (err, res) => {
    if (err) {
      result(null, err);
      return;
    }

    result(null, res);
  });
};

module.exports = Student;

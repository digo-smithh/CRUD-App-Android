# CRUD-App-Android <img align="right" src="https://img.shields.io/badge/java-%23ED8B00.svg?&style=for-the-badge&logo=java&logoColor=white"/> <img align="right" src="https://img.shields.io/badge/express.js%20-%23404d59.svg?&style=for-the-badge"/> <img align="right" src="https://img.shields.io/badge/node.js%20-%2343853D.svg?&style=for-the-badge&logo=node.js&logoColor=white"/> 

Android and NodeJS project to add, update and delete students from a school. It consists of an API and an Android front-end. The API uses mssql driver, express and body-parser to receive requests and deal with the database. The android app uses the Material UI library, volley and retrofit.

<p float="left">
  <img src="/img1.jpeg" width="250" />
  <img src="img2.jpeg" width="250" /> 
</p>

## Installation and Setup

Just download the project and run them separetely. To run the android app, you can either run the apk or open it in an IDE and run it there. To run the api, open it in a terminal and use:

```node
npm install
npm start
```

You will need a sql server database to use the api. To acces the data base, go to Node-API-Rest/src/config/db.example.config.js and change the file name to db.config.js. Then, change the content to your info:

```javascript
module.exports = {
    user: 'Database User',//Your user
    password: 'Password',// Your password
    server: 'Server', // Your server
    database: 'Database Name', // Your database name
    "options": {
      "encrypt": false,
      "enableArithAbort": true
      }
  };
```
The SQL code to create the table:
```sql
CREATE TABLE Student(
code CHAR(5) NOT NULL,
name VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL
)
```
## Meta

Project developed by [Eduardo Migueis](https://github.com/edumigueis) and [Rodrigo Smith](https://github.com/digo-smithh). Licensed under the [APACHE 2.0](https://choosealicense.com/licenses/apache-2.0/) license.

[![ForTheBadge built-with-love](http://ForTheBadge.com/images/badges/built-with-love.svg)](https://GitHub.com/Naereen/)
[![ForTheBadge uses-badges](http://ForTheBadge.com/images/badges/uses-badges.svg)](http://ForTheBadge.com)
[![ForTheBadge powered-by-electricity](http://ForTheBadge.com/images/badges/powered-by-electricity.svg)](http://ForTheBadge.com)

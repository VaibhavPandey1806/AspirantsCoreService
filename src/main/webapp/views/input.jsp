<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>User Information Form</title>
</head>
<body>
    <h1>User Information Form</h1>
    <form action="addUser" >
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br><br>

        <label for="age">Age:</label>
        <input type="number" id="age" name="age" required><br><br>

        <label for="id">ID:</label>
        <input type="text" id="id" name="id" required><br><br>

        <label for="company">Company:</label>
        <input type="text" id="company" name="company" required><br><br>

        <input type="submit" value="Submit">
    </form>
      <form action="getUser" >
        
        <label for="id">ID:</label>
        <input type="text" id="id" name="id" required><br><br>


        <input type="submit" value="Submit">
    </form>
</body>
</html>

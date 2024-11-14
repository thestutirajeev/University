<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Professor Profile</title>
	<script>
        function togglePasswordVisibility() {
            var passwordField = document.getElementById("password");
            var eyeButton = document.getElementById("eyeButton");
            if (passwordField.type === "password") {
                passwordField.type = "text";
                eyeButton.innerHTML = "&#128065;"; // Show open eye icon
            } else {
                passwordField.type = "password";
                eyeButton.innerHTML = "&#128584;"; // Show closed eye icon
            }
        }
    </script>
</head>
<body>
	<h2>Professor Profile</h2>

    <div class="form-group">
        <label class="label">Professor Name:</label>
        <input type="text" class="input-field" value="${Prof_name}" readonly />
    </div>

    <div class="form-group">
        <label class="label">Email:</label>
        <input type="text" class="input-field" value="${email}" readonly />
    </div>

    <div class="form-group">
        <label class="label">Mobile:</label>
        <input type="text" class="input-field" value="${mobile}" readonly />
    </div>

    <div class="form-group">
        <label class="label">Department:</label>
        <input type="text" class="input-field" value="${dname}" readonly />
    </div>

    <div class="form-group">
        <label class="label">Course:</label>
        <input type="text" class="input-field" value="${crs_name}" readonly />
    </div>

    <div class="form-group">
        <label class="label">Password:</label>
        <input type="password" id="password" value="${password}" readonly />
        <button type="button" id="eyeButton" class="eye-button" onclick="togglePasswordVisibility()">&#128584;</button>
    </div>

</body>
</html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration Page</title>
</head>
<body>

    <p><a href="/" ><--Back to Login page</a></p>
    <form action="/NewUserRegistration" method="post">
    <p>Login:</p>
    <input type="text" name="loginName" required>
    <p>E-mail:</p>
    <input type="text" name="e-mail" required>
    <p>Password:</p>
    <input type="password" name="loginPassword" required>
    <p>Re-enter Password:</p>
    <input type="password" name="loginPassword2" required>
    <p>${message!''}</p>
        <p><input type="submit"/></p>
    </form>

</body>
</html>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Hello, friend!</title>
    <link rel="stylesheet" href="/resources/main.css">
    <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
</head>

<body>
    

    <div class="wrapper">
        
        <div class="container">
            <h1>Enter login & password</h1>
            <form action="/login" method="post">
                <p>Login:</p>
                <input type="text" class="input-area" name="loginName" required>
                <p>Password:</p>
                <input type="password" class="input-area" name="loginPassword" required>
                <input type="submit" class="button" value="Submit">
                <input type="submit" class="button" value="Register" onclick = "window.location.href ='/registration'; ">
                
            </form>
        </div>
    </div>
</body>

</html>
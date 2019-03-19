<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    Hello, ${user.name}!

    <form action="/sendMessage" method="post">
        <b>To:</b><input type="text" name="toUser" required>
        <textarea rows="10" cols="30" name="message"/>
        <input type="submit" value="Send">
    </form>


</body>
</html>
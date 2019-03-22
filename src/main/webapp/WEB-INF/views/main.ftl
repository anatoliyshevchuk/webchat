
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Main Page</title>
</head>
<body>
    Hello, ${user.name}! <a href="/logoff">logoff</a>

    <p>Start conversation with</p>
    <form action="/startConversation">
    <input type="text" name="conversationPartner" required>
    <input type="submit">
    <p>${error!''}</p>
    </form>

</body>
</html>
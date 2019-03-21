<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Conversation</title>
</head>
<body>

        <#list messagelist as message>
            <#if message.user.id == user.id>
                <b>I said: ${message.message!''}</b>
            <#else>
                <p>He said: ${message.message!''}</p>
            </#if>
        </#list>

        <form action="/sendMessage" method="post">
            <b>To:</b><input type="text" name="toUser" required>
            <b></b>
            <textarea rows="10" cols="25" name="message">${message!''}</textarea>
            <input type="submit" value="Send">
        </form>

</body>
</html>
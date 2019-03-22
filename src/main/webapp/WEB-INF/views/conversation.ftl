<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Conversation with ${Partner.name}</title>
</head>
<body>

Hello, ${user.name}!  <a href="/logoff">logoff</a>

<p><b><a href="/menu"><-Back to Menu</a></b></p>

        <#list messagelist as message>
            <#if message.user.id == user.id>
                <p>${message.date}   ${Partner.name} said: ${message.message!''}</p>
            <#else>
                <p>${message.date}   I said: ${message.message!''}</p>
            </#if>
        </#list>

        <form action="/sendMessage" method="post">
            <textarea rows="15" cols="20" name="message">${message!''}</textarea>
            <input type="submit" value="Send">
        </form>

</body>
</html>
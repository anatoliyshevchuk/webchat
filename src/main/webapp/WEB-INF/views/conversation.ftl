<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Conversation with ${currentPartner.name}</title>
    <link rel="stylesheet" href="/resources/main.css">
    <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
</head>

<body>
    <div class="wrapper">
        <div class="container">

                <p><b><a href="/menu">
                            <-Back to Menu</a> </b> </p> <#list messagelist as message>
                                <#if message.messageToUser==user.id>
                                    <p>${message.date} ${currentPartner.name} said: ${message.message!''}</p>
                                    <#else>
                                        <p>${message.date} I said: ${message.message!''}</p>
                                </#if>
                                </#list>
            
                                <form action="/sendMessage" method="post">
                                    <textarea rows="15" cols="20" name="message">${message!''}</textarea>
                                    <input type="hidden" class="button" name="sendto" value=${currentPartner.name}>
                                    <input type="submit" class="button" value="Send">
                                </form>
        </div>

        </div>
            
    

</body>

</html>
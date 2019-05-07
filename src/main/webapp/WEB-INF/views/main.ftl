<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">

<head>
    <meta charset="UTF-8">
    <title>Main Page</title>
    <link rel="stylesheet" href="/resources/main.css">
    <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
</head>

<body>
    <div class="wrapper">

        <div class="container">
            <h1>Hello, ${user.name}! <a href="/logoff">logoff</a></h1>
            <p>Start conversation with</p>
            <div class="auth-form">
                <form action="/startConversation">
                    <input type="text" class="input-area" name="conversationPartner" required>
                    <input type="submit" class="button" value="Submit" >
                    <p>${error!''}</p>
                </form>
            </div>

            <#if Conversations??>
                <p>Opened conversations:</p>
                <#list Conversations as Conversation>
                    <#if Conversation.currentUser.id==user.id>
                        <p><a href="/showConversation/${Conversation.partnerUser.name}">(<b>${Conversation.countNewMessages!''}</b>)Conversation
                                with ${Conversation.partnerUser.name}</a><a
                                href="/updateConversation/${Conversation.partnerUser.name}">[X]</a></p>
                        <#else>
                            <p><a href="/showConversation/${Conversation.currentUser.name}">NEW Conversation with
                                    ${Conversation.currentUser.name}</a></p>
                    </#if>
                </#list>
            </#if>
        </div>
    </div>




</body>

</html>
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

    <p>Opened conversations:</p>
    <#list Conversations as Conversation>
        <#if Conversation.currentUser.id == user.id>
            <p><a href="/showConversation/${Conversation.partnerUser.name}">Conversation with ${Conversation.partnerUser.name}</a><a href="/deleteConversation/${Conversation.partnerUser.name}">[X]</a></p>
        <#else>
            <p><a href="/showConversation/${Conversation.currentUser.name}">NEW Conversation with ${Conversation.currentUser.name}</a></p>
        </#if>
    </#list>

</body>
</html>
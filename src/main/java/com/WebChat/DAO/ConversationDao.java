package com.WebChat.DAO;

import com.WebChat.Entity.Conversation;
import com.WebChat.Entity.Message;
import com.WebChat.Entity.User;

import java.util.List;

public interface ConversationDao {
    List<Message> getListMessage(int currentUser, int partnerId);
    List<Conversation> getConversationsList(User currentUser);
    void save(Conversation conversation);
    void delete(Conversation conversation);
    Conversation getConversation(User usr1,User usr2);
    int checkNewMessageCount(Conversation conversation);
    void updateConversation(Conversation conversation);
}

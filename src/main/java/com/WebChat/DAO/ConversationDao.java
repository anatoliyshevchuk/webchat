package com.WebChat.DAO;

import com.WebChat.Entity.Conversation;
import com.WebChat.Entity.Message;
import com.WebChat.Entity.User;

import java.util.List;

public interface ConversationDao {
    List<Message> getConversation(int currentUser, int partnerId);
    List<Conversation> getSuperficialConversationsList(User currentUser);
}

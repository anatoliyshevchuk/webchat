package com.WebChat.Service;

import com.WebChat.DAO.ConversationDao;
import com.WebChat.Entity.Conversation;
import com.WebChat.Entity.Message;
import com.WebChat.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationService {

    private ConversationDao conversationDao;

    @Autowired
    public ConversationService(ConversationDao conversationDao) {
        this.conversationDao = conversationDao;
    }

    public List<Conversation> getConversationListByUser(User user) {
        return conversationDao.getSuperficialConversationsList(user);
    }

    public List<Message> getConversation(int myId, int PartnerId) {
        return conversationDao.getConversation(myId, PartnerId);
    }
}

package com.WebChat.Service;

import com.WebChat.DAO.ConversationDao;
import com.WebChat.Entity.Conversation;
import com.WebChat.Entity.Message;
import com.WebChat.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConversationService {

    private ConversationDao conversationDao;

    @Autowired
    public ConversationService(ConversationDao conversationDao) {
        this.conversationDao = conversationDao;
    }

    public Map<String,User> getPartnersMapByUser(User user) {
        List<Conversation> list = conversationDao.getSuperficialConversationsList(user);
        Map<String,User> partnerMap = new HashMap<>();
        for (Conversation conv:list) {
            partnerMap.put(conv.getPartnerUser().getName(),conv.getPartnerUser());
        }
        return partnerMap;
    }

    public List<Message> getListMessage(int myId, int PartnerId) {
        return conversationDao.getListMessage(myId, PartnerId);
    }

    public void save(Conversation conversation) {
        conversationDao.save(conversation);
    }

    public void deleteConversation(User currentUser,User partnerUser) {
        Conversation conv = getConversation(currentUser,partnerUser);
        conversationDao.delete(conv);
    }

    private Conversation getConversation(User usr1,User usr2) {
        return conversationDao.getConversation(usr1, usr2);
    }

}

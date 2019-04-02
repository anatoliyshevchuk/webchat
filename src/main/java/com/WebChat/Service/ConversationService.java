package com.WebChat.Service;

import com.WebChat.DAO.ConversationDao;
import com.WebChat.Entity.Conversation;
import com.WebChat.Entity.Message;
import com.WebChat.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ConversationService {

    private ConversationDao conversationDao;

    @Autowired
    public ConversationService(ConversationDao conversationDao) {
        this.conversationDao = conversationDao;
    }

    public List<Conversation> getConversationListByUser(User user) {
        List<Conversation> list = conversationDao.getConversationsList(user);

        //TODO: Check new messages where I partner User
        //Delete Conversation duplicate (where Conversation open from me and to me). Leave only conversation opened from My side
        List<Conversation> toRemove = new ArrayList<>();
        for (Conversation conv : list)
        {
            if(conv.getCurrentUser().getId().equals(user.getId()))
            {
                for(Conversation conv2 : list)
                {
                    if(conv2.getPartnerUser().getId().equals(conv.getCurrentUser().getId()) && conv2.getCurrentUser().getId().equals(conv.getPartnerUser().getId()))
                    {
                        toRemove.add(conv2);
                    }
                }
            }
        }
        list.removeAll(toRemove);
        return list;
    }

    public List<Message> getListMessage(int myId, int PartnerId) {
        return conversationDao.getListMessage(myId, PartnerId);
    }

    public Conversation saveAsConversation(User currentUser,User partnerUser) {
        Conversation conv = getConversation(currentUser,partnerUser);
        if(conv==null) {
            conv = new Conversation(currentUser, partnerUser, new Date());
            conversationDao.save(conv);
        }
        return conv;
    }

    public void deleteConversation(User currentUser,User partnerUser) {
        Conversation conv = getConversation(currentUser,partnerUser);
        conversationDao.delete(conv);
    }

    private Conversation getConversation(User usr1, User usr2) {
        return conversationDao.getConversation(usr1, usr2);
    }

}

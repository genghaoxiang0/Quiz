package com.ghx.service.impl;

import com.ghx.dao.MessageDao;
import com.ghx.entity.Message;
import com.ghx.entity.User;
import com.ghx.service.MessageService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Data
@NoArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    @Qualifier("messageDaoHibernateImpl")
    private MessageDao messageDaoHibernate;

    @Override
    @Transactional
    public int addMessage(User user, String title, String content) {
        return messageDaoHibernate.addMessage(user, title, content);
    }

    @Override
    @Transactional
    public List<Message> getAllMessages() {
        return messageDaoHibernate.getAllMessages();
    }

    @Override
    @Transactional
    public Message findMessageById(long id) {
        return messageDaoHibernate.findMessageById(id);
    }

    @Override
    @Transactional
    public Message updateMessage(long id, boolean unread) {
        return messageDaoHibernate.updateMessage(id, unread);
    }
}

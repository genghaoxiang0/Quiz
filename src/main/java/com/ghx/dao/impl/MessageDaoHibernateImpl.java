package com.ghx.dao.impl;

import com.ghx.dao.AbstractHibernateDAO;
import com.ghx.dao.MessageDao;
import com.ghx.entity.Message;
import com.ghx.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class MessageDaoHibernateImpl extends AbstractHibernateDAO<Message> implements MessageDao {
    public MessageDaoHibernateImpl() {
        setClazz(Message.class);
    }

    @Override
    public int addMessage(User user, String title, String content) {
        Message message = new Message();
        message.setUser(user);
        message.setTitle(title);
        message.setContent(content);
        message.setTime(new Date());
        message.setUnread(true);
        this.getCurrentSession().save(message);
        return 0;
    }

    @Override
    public List<Message> getAllMessages() {
        return findAll();
    }

    @Override
    public Message findMessageById(long id) {
        return findById(id);
    }

    @Override
    public Message updateMessage(long id, boolean unread) {
        Message message = findById(id);
        message.setUnread(unread);
        return message;
    }
}

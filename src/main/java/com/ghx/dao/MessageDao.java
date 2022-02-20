package com.ghx.dao;

import com.ghx.entity.Message;
import com.ghx.entity.User;

import java.util.List;

public interface MessageDao {
    public int addMessage(User user, String title, String content);

    public List<Message> getAllMessages();

    public Message findMessageById(long id);

    public Message updateMessage(long id, boolean unread);
}

package com.techelevator.dao;

import com.techelevator.model.Message;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageDao {

    boolean createMessage(int userId, int bandId, String message);

    Message getMessageById(int messageId);

    List<Message> listAllMessagesByUserId(int userId);

    List<Message> ListAllMessagesByBandId(int bandId);

    Boolean deleteMessageById(int messageId);






}

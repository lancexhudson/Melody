package com.techelevator.controller;

import com.techelevator.dao.MessageDao;
import com.techelevator.model.Message;
import com.techelevator.model.MessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class MessagesController {
    private MessageDao messageDao;

    public MessagesController(MessageDao messageDao) {
        this.messageDao = messageDao;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping (path = "/message/send", method = RequestMethod.POST)
    public void createMessage(@RequestBody MessageDTO messageDTO) {
        messageDao.createMessage(messageDTO.getUserId(), messageDTO.getBandId(), messageDTO.getMessage());
    }

    @RequestMapping(path = "/message/{id}", method = RequestMethod.GET)
    public Message message (@PathVariable("id") int messageId) {
        return messageDao.getMessageById(messageId);
    }

    @RequestMapping(path = "/message/user/{id}", method = RequestMethod.GET)
    public List<Message> messagesByUserId (@PathVariable("id") int userId) {
        return messageDao.listAllMessagesByUserId(userId);
    }

    @RequestMapping(path = "/message/band/{id}", method = RequestMethod.GET)
    public List<Message> messagesByBandId (@PathVariable("id") int bandId) {
        return messageDao.ListAllMessagesByBandId(bandId);
    }

    @RequestMapping(path = "/message/delete/{id}", method = RequestMethod.DELETE)
    public void Boolean (@PathVariable("id") int messageId) {
        messageDao.deleteMessageById(messageId);
    }
}

package com.techelevator.dao;

import com.techelevator.model.Message;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class JdbcMessageDao implements MessageDao  {
    private final JdbcTemplate jdbcTemplate;
    private final JdbcUserDao jdbcUserDao;


    public JdbcMessageDao(JdbcTemplate jdbcTemplate, JdbcUserDao jdbcUserDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcUserDao = jdbcUserDao;
    }


    public boolean createMessage(int userId, int bandId, String message, LocalDateTime date) {
        Message messageDetails = new Message();
//        messageDetails.setUserId(userId);
//        messageDetails.setBandId(bandId);
//        messageDetails.setMessage(message);
//        messageDetails.setDate(date);
//
//        String sql = "insert into message (to_user, from_band, message, date_received)\n" +
//                "values (?, ?, ?, ?) Returning messageId;";
//        int messageId = jdbcTemplate.queryForObject(sql, int.class, userId, bandId, message, date);
//        messageDetails.setMessageId(messageId);
//
//
//
return true;
    }

    @Override //delete this later. Here for compile.
    public boolean createMessage(int userId, int bandId, String message, LocalDateTime date, int messageId) {
        return false;
    }

    public Message getMessageById(int messageId) {
        Message message = new Message();
        return message;
    }

    public List<Message> listAllMessagesByUserId(int userId) {
        List<Message> messageList = new ArrayList<>();
        return messageList;
    }

    public List<Message> ListAllMessagesByBandId(int bandId) {
        List<Message> messageList = new ArrayList<>();
        return messageList;
    }


    public Boolean deleteMessageById(int messageId){
        return true;
    }

    private Message mapRowToMessage(SqlRowSet rs) {
        Message messageDetails = new Message();
        messageDetails.setUserId(rs.getInt("to_user"));
        messageDetails.setBandId(rs.getInt("from_band"));
        messageDetails.setMessage(rs.getString("message"));
        messageDetails.setDate(LocalDateTime.now());
        return messageDetails;
    }

}

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

    @Override
    public boolean createMessage(int userId, int bandId, String message) {
        Message messageDetails = new Message();
        messageDetails.setUserId(userId);
        messageDetails.setBandId(bandId);
        messageDetails.setMessage(message);
        messageDetails.setDate(String.valueOf(LocalDateTime.now()));

        String sql = "INSERT INTO messages (user_id, band_id, message, date) " +
                "VALUES (?, ?, ?, ?) RETURNING message_id;";
        int messageId = jdbcTemplate.queryForObject(sql, int.class, userId, bandId, message, messageDetails.getDate());
        messageDetails.setMessageId(messageId);

        return true;
    }

    public Message getMessageById(int messageId) {
        Message message = new Message();
        String sql = "SELECT user_id, band_id, message, date, message_id FROM messages WHERE message_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, messageId);
        if (results.next()) {
            message = mapRowToMessage(results);
        }
        return message;
    }

    public List<Message> listAllMessagesByUserId(int userId) {
        List<Message> messageList = new ArrayList<>();
        String sql = "SELECT m.user_id, m.band_id, m.message, m.date, m.message_id, b.band_name FROM messages AS m JOIN band AS b ON b.band_id = m.band_id WHERE m.user_id = ? ORDER BY message_id desc;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            Message message = mapRowToMessage(results);
            messageList.add(message);
        }
        return messageList;
    }

    public List<Message> ListAllMessagesByBandId(int bandId) {
        List<Message> messageList = new ArrayList<>();
        String sql = "SELECT user_id, band_id, message, date, message_id FROM messages WHERE band_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, bandId);
        while (results.next()) {
            Message message = mapRowToMessage(results);
            messageList.add(message);
        }
        return messageList;
    }


    public Boolean deleteMessageById(int messageId){
        String sql = "DELETE from messages WHERE message_id = ?";
        jdbcTemplate.update(sql, messageId);
        return true;
    }

    private Message mapRowToMessage(SqlRowSet rs) {
        Message messageDetails = new Message();
        messageDetails.setUserId(rs.getInt("user_id"));
        messageDetails.setBandId(rs.getInt("band_id"));
        messageDetails.setMessage(rs.getString("message"));
        messageDetails.setDate(rs.getString("date"));
        messageDetails.setMessageId(rs.getInt("message_id"));
        messageDetails.setFromBand(rs.getString("band_name"));
        return messageDetails;
    }

}

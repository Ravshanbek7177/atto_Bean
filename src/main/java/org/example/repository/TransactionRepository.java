package org.example.repository;


import org.example.db.DataBase;
import org.example.dto.Transaction;
import org.example.enums.TransactionType;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
@Repository
public class TransactionRepository {

    public int createTransaction(Transaction transaction) {
        try (Connection connection = DataBase.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into transaction(card_id,terminal_id,amount,type,created_date) " +
                            "values (?,?,?,?,?)");
            statement.setInt(1, transaction.getCardId());

            if (transaction.getTerminalId() != null) {
                statement.setInt(2, transaction.getTerminalId());
            } else {
                statement.setObject(2, null);
            }

            statement.setDouble(3, transaction.getAmount());
            statement.setString(4, transaction.getTransactionType().name());
            statement.setTimestamp(5, Timestamp.valueOf(transaction.getCreatedDate()));

            int resultSet = statement.executeUpdate();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;

    }
    public List<Transaction> admintransactionList() {
        try (Connection connection = DataBase.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from transaction");
            List<Transaction> transactionList = new LinkedList<>();
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(resultSet.getInt("id"));
                transaction.setCardId(resultSet.getInt("card_id"));
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setTerminalId(resultSet.getInt("terminal_id"));
                transaction.setTransactionType(TransactionType.valueOf(resultSet.getString("type")));
                transaction.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
                transactionList.add(transaction);
            }
            return transactionList;
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }


}

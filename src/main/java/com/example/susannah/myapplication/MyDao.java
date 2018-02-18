package com.example.susannah.myapplication;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import java.util.List;

/**
 * Created by Susannah on 2/18/2018.
 */

@Dao
public interface MyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUsers(User... users);

    @Insert
    public void insertBothUsers(User userA, User userB);

    @Insert
    public void insertUsersAndFriends(User user, List<User> friends);

    @Update
    public void updateUsers(User... users);

    @Delete
    public void deleteUsers(User... users);

    @Query("SELECT * FROM user WHERE age > :minAge")
    public User[] loadAllUsersOlderThan(int minAge);

    @Query("SELECT * FROM user WHERE first_name LIKE :search "
            + "OR last_name LIKE :search")
    public List<User> findUserWithName(String search);

    @Query("SELECT * FROM user WHERE age > :minAge LIMIT 5")
    public Cursor loadRawUsersOlderThan(int minAge); // using Cursor is discouraged.

    @Query("SELECT * FROM book "
            + "INNER JOIN loan ON loan.book_id = book.id "
            + "INNER JOIN user ON user.id = loan.user_id "
            + "WHERE user.name LIKE :userName")
    public List<Book> findBooksBorrowedByNameSync(String userName);
}

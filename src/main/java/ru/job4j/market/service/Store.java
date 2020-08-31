package ru.job4j.market.service;

import ru.job4j.market.persistence.Photo;
import ru.job4j.market.persistence.PostCar;
import ru.job4j.market.persistence.User;

import java.util.Collection;

public interface Store {
    User findUserByEmail(String email);
    void saveUser(User user);
    User findUserById(int id);
    Integer savePhoto(Photo photo);
    void savePostCar(PostCar postCar);
    Collection<PostCar> findAllPostCar();
    Collection<PostCar> findPostCarByUserId(int id);
    void changeStatusByPostId(int id, boolean status);
}

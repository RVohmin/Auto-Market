package ru.job4j.market.persistence;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "posts")
public class PostCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String mark;
    private String model;
    private String color;
    private String type;
    private String manufacturedYear;
    private String mileage;
    private String bodyType;
    private boolean status;
    private String price;
    private String fuel;
    private Timestamp data;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id")
    private Photo photo;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public PostCar() {
    }

    public PostCar(
            String mark, String model, String color, String type, String manufacturedYear,
            String mileage, String bodyType, boolean status, String price, String fuel,
            Timestamp data, Photo photo, User user) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.color = color;
        this.type = type;
        this.manufacturedYear = manufacturedYear;
        this.mileage = mileage;
        this.bodyType = bodyType;
        this.status = status;
        this.price = price;
        this.fuel = fuel;
        this.data = data;
        this.photo = photo;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturedYear() {
        return manufacturedYear;
    }

    public void setManufacturedYear(String manufacturedYear) {
        this.manufacturedYear = manufacturedYear;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PostCar postCar = (PostCar) o;
        return id == postCar.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PostCar{"
                + "id=" + id
                + ", mark='" + mark + '\''
                + ", model='" + model + '\''
                + ", color='" + color + '\''
                + ", type='" + type + '\''
                + ", manufacturedYear='" + manufacturedYear + '\''
                + ", mileage='" + mileage + '\''
                + ", bodyType='" + bodyType + '\''
                + ", status=" + status
                + ", price='" + price + '\''
                + ", fuel='" + fuel + '\''
                + ", data=" + data
                + ", photo=" + photo
                + ", user=" + user
                + '}';
    }
}
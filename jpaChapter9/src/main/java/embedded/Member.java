package embedded;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("all")
@Entity
public class Member {
    @Id
    @Column(name = "member_id", nullable = false)
    private String id;

    // 값 타입을 하나 이상 저장하려면 컬렉션에 보관하고 해당 애너테이션 생성
    @ElementCollection
    @CollectionTable(name = "favorite_foods", joinColumns = @JoinColumn(name = "member_id"))
    @Column(name = "food_name")
    private Set<String> favoriteFoods = new HashSet<>();


    @ElementCollection
    @CollectionTable(name = "address", joinColumns = @JoinColumn(name = "member_id"))
    private List<Address> addresseHistory = new ArrayList<>();

    @Embedded Address homeAddress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<Address> getAddresseHistory() {
        return addresseHistory;
    }

    public void setAddresseHistory(List<Address> addresseHistory) {
        this.addresseHistory = addresseHistory;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }
}

@SuppressWarnings("all")
@Embeddable
class Address {

    public Address() {

    }
    public Address(String street, String city, String state) {
        this.street = street;
        this.city = city;
        this.state = state;
    }

    String street;
    String city;
    String state;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

@SuppressWarnings("all")
@Embeddable
class PhoneNumber {
    String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}


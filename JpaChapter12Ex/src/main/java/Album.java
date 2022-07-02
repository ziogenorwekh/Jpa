import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue("A")
@PrimaryKeyJoinColumn(name = "ALBUM_ID")
public class Album extends Item{
    private String artist;
}

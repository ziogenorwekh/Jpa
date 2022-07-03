package oneEntityManyTable;

import javax.persistence.*;

@Entity
@Table(name = "BOARD")
@SecondaryTable(name = "BOARD_DETAIL",pkJoinColumns = @PrimaryKeyJoinColumn(name = "BOARD_DETAIL_ID"))
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOARD_ID", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(table = "BOARD_DETAIL")
    private String content;
}

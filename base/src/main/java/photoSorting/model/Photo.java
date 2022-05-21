package photoSorting.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PHOTO", indexes = @Index(columnList = "hashphoto"))
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "namephoto", nullable = false, unique = true)
    private String name;
    @Column(name = "createphoto", nullable = false)
    private Date create;
    @Column(name = "sizephoto", nullable = false)
    private Long size;
    @Column(name = "locationphoto", nullable = false)
    private String location;
    @Column (name = "hashphoto", nullable = false)
    private String hash;
}



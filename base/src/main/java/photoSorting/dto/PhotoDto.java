package photoSorting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoDto {

    private Long id;
    private String name;
    private Date create;
    private Long size;
    private String location;
}

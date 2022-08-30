package co.com.sofka.model.galleta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Galleta {

    private String codigo;
    private String sabor;
    private String forma;

}

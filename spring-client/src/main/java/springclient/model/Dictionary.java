package springclient.model;


import lombok.Data;


import java.io.Serializable;
import java.util.List;


/**
 * Dictionary
 */

@Data
public class Dictionary implements Serializable {

  private String label;

  private List<String> context = null;

}


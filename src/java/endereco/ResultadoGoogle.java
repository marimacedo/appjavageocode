/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endereco;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maric
 */
public class ResultadoGoogle {
    public List<resultEndereco> results = new ArrayList<resultEndereco>();

    public List<resultEndereco> getResults() {
        return results;
    }

    public void setResults(List<resultEndereco> results) {
        this.results = results;
    }
}

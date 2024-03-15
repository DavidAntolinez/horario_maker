package logica;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;
import vista.*;

public class CrearHorario {

    String[] vector = { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo" };

    public CrearHorario() {
    }

    public void crear() {
        try {
            JSONArray array = new JSONArray(new JSONTokener(new FileInputStream("Materias.json")));
            ArrayList<String[][]> instancias = new ArrayList<String[][]>();
            instancias = Instancias(array, instancias, 0, new String[7][10]);
            for (String[][] matriz : instancias) {
                for (int i = 0; i < matriz.length; i++) {
                    for (int j = 0; j < matriz[i].length; j++) {
                        if(matriz[i][j] == null){
                            matriz[i][j] = ".";
                        }
                    }
                }
                SwingUtilities.invokeLater(() -> new MatrizStringGUI(matriz));    
            }
        } catch (JSONException | FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int BuscarIndex(String dato) {
        int i = 0;
        while (!vector[i].equals(dato)) {
            i++;
        }
        return i;
    }

    ArrayList<String[][]> Instancias(JSONArray array, ArrayList<String[][]> instancia, int indexM,
            String[][] horarios) {
        if (indexM < array.length()) {
            for (int i = 0; i < array.getJSONArray(indexM).length(); i++) {
                String[][] horario = copia(horarios);
                String[] dias = array.getJSONArray(indexM).getJSONObject(i).getString("dias").split("[/]");
                String[] horas = array.getJSONArray(indexM).getJSONObject(i).getString("horas").split("[/]");
                boolean Switch = true;
                for (int j = 0; j < dias.length && Switch; j++) {
                    String[] horas2 = horas[j].split("[-]");
                    for (int k = 0; k < Integer.parseInt(horas2[1]) - Integer.parseInt(horas2[0]) && Switch; k++) {
                        if (horario[BuscarIndex(dias[j])][Integer.parseInt(horas2[0]) - 6 + k] == null) {
                            horario[BuscarIndex(dias[j])][Integer.parseInt(horas2[0]) - 6 + k] = array
                                    .getJSONArray(indexM).getJSONObject(i).getString("id");
                        } else {
                            Switch = false;
                        }
                    }
                }
                if (Switch) {
                    Instancias(array, instancia, indexM + 1, horario);
                    if (indexM + 1 == array.length()) {
                        instancia.add(copia(horario));
                    }
                }
            }
        }
        return instancia;
    }

    private String[][] copia(String[][] matriz) {
        String[][] copia = new String[7][10];

        for (int i = 0; i < copia.length; i++) {
            for (int j = 0; j < copia.length; j++) {
                copia[i][j] = matriz[i][j];
            }
        }
        return copia;
    }

    
}

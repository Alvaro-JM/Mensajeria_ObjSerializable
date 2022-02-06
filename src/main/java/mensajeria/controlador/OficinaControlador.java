package mensajeria.controlador;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import mensajeria.modelo.Oficina;

/**
 *
 * @author Álvaro
 */
public class OficinaControlador {
    private final File file;
    private FileOutputStream fileOutput;
    private ObjectOutputStream dataOutput;
    private FileInputStream fileInput;
    private ObjectInputStream dataInput;
    private List<Oficina> listaOficinas;

    public OficinaControlador() throws FileNotFoundException, IOException, ClassNotFoundException {
        file = new File("Oficinas.dat");
        if (!file.exists()) {
            crearFicheroPrimeraVez(file);
        }
        leerOficina();
    }
    
    private void leerOficina() throws FileNotFoundException, IOException, ClassNotFoundException {
        listaOficinas = new ArrayList<>();
        fileInput = new FileInputStream(file);
        dataInput = new ObjectInputStream(fileInput);
        try {
            while (true) {
                Oficina oficina = (Oficina) dataInput.readObject();
                listaOficinas.add(oficina);
            }
        } catch (EOFException eofe) {
            System.out.println("fin de lectura");
        }
        dataInput.close();
        fileInput.close();
    }
    
    private void crearFicheroPrimeraVez(File file) throws FileNotFoundException, IOException {
        fileOutput = new FileOutputStream(file);
        dataOutput = new ObjectOutputStream(fileOutput);
        dataOutput.writeObject(new Oficina(0, null, null, null, null, 0, false));
        dataOutput.close();
        fileOutput.close();
    }
    
    public void escribirOficina(List<Oficina> lista) throws FileNotFoundException, IOException {
        fileOutput = new FileOutputStream(file);
        dataOutput = new ObjectOutputStream(fileOutput);
        Iterator<Oficina> it = lista.iterator();
        while (it.hasNext()) {
            dataOutput.writeObject(it.next());
        }
        dataOutput.close();
        fileOutput.close();
    }

    public List<Oficina> getListaOficinas() {
        return listaOficinas;
    }
    
}

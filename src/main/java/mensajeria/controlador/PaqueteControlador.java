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
import mensajeria.modelo.Paquete;

/**
 *
 * @author Álvaro
 */
public class PaqueteControlador {
    private final File file;
    private FileOutputStream fileOutput;
    private ObjectOutputStream dataOutput;
    private FileInputStream fileInput;
    private ObjectInputStream dataInput;
    private List<Paquete> listaPaquetes;

    public PaqueteControlador() throws FileNotFoundException, IOException, ClassNotFoundException {
        file = new File("Paquetes.dat");
        if (!file.exists()) {
            crearFicheroPrimeraVez(file);
        }
        leerPaquete();
    }
    
    private void leerPaquete() throws FileNotFoundException, IOException, ClassNotFoundException {
        listaPaquetes = new ArrayList<>();
        fileInput = new FileInputStream(file);
        dataInput = new ObjectInputStream(fileInput);
        try {
            while (true) {
                Paquete paquete = (Paquete) dataInput.readObject();
                listaPaquetes.add(paquete);
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
        dataOutput.writeObject(new Paquete(0, null, null, null, null, null, 0, false));
        dataOutput.close();
        fileOutput.close();
    }
    
    public void escribirPaquete(List<Paquete> lista) throws FileNotFoundException, IOException {
        fileOutput = new FileOutputStream(file);
        dataOutput = new ObjectOutputStream(fileOutput);
        Iterator<Paquete> it = lista.iterator();
        while (it.hasNext()) {
            dataOutput.writeObject(it.next());
        }
        dataOutput.close();
        fileOutput.close();
    }

    public List<Paquete> getListaPaquetes() {
        return listaPaquetes;
    }
    
}

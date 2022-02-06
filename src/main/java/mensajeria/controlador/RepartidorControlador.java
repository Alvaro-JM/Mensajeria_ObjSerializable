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
import mensajeria.modelo.Repartidor;

/**
 *
 * @author Álvaro
 */
public class RepartidorControlador {
    private final File file;
    private FileOutputStream fileOutput;
    private ObjectOutputStream dataOutput;
    private FileInputStream fileInput;
    private ObjectInputStream dataInput;
    private List<Repartidor> listaRepartidores;

    public RepartidorControlador() throws FileNotFoundException, IOException, ClassNotFoundException {
        file = new File("Repartidores.dat");
        if (!file.exists()) {
            crearFicheroPrimeraVez(file);
        }
        leerRepartidor();
    }
    
    private void leerRepartidor() throws FileNotFoundException, IOException, ClassNotFoundException {
        listaRepartidores = new ArrayList<>();
        fileInput = new FileInputStream(file);
        dataInput = new ObjectInputStream(fileInput);
        try {
            while (true) {
                Repartidor repartidor = (Repartidor) dataInput.readObject();
                listaRepartidores.add(repartidor);
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
        dataOutput.writeObject(new Repartidor(0, null, null, null, 0, 0, false));
        dataOutput.close();
        fileOutput.close();
    }
    
    public void escribirRepartidor(List<Repartidor> lista) throws FileNotFoundException, IOException {
        fileOutput = new FileOutputStream(file);
        dataOutput = new ObjectOutputStream(fileOutput);
        Iterator<Repartidor> it = lista.iterator();
        while (it.hasNext()) {
            dataOutput.writeObject(it.next());
        }
        dataOutput.close();
        fileOutput.close();
    }

    public List<Repartidor> getListaRepartidores() {
        return listaRepartidores;
    }
    
}
